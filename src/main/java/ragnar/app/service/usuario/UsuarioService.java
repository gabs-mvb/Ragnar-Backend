package ragnar.app.service.usuario;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ragnar.app.domain.usuario.Usuario;
import ragnar.app.domain.usuario.dto.*;
import ragnar.app.domain.usuario.mapper.UsuarioMapper;
import ragnar.app.domain.usuario.repository.UsuarioRepository;
import ragnar.app.service.usuario.configuration.security.jwt.GerenciadorTokenJwt;
import ragnar.app.utils.enums.PermissaoEnum;
import ragnar.app.utils.enums.StatusEnum;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private GerenciadorTokenJwt gerenciadorTokenJwt;

    @Autowired
    private AuthenticationManager authenticationManager;


    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    //Autenticação de Usuario

    public UsuarioTokenDTO autenticar(UsuarioLoginDTO usuarioLoginDTO){
        final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(
                usuarioLoginDTO.getEmail(),usuarioLoginDTO.getSenha()
        );

        final Authentication authentication = this.authenticationManager.authenticate(credentials);

        Usuario usuarioAutenticado =
                usuarioRepository.findByEmail(usuarioLoginDTO.getEmail())
                        .orElseThrow(
                                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Usuário não cadastrado")
                        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String token = gerenciadorTokenJwt.generateToken(authentication);

        return UsuarioMapper.toTokenDTO(usuarioAutenticado,token);
    }

    public UsuarioDTO cadastrarUsuario(UsuarioCriacaoDTO usuario) {
        if (this.usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Usuario já cadastrado");
        }
        Usuario novoUsuario = UsuarioMapper.toEntity(usuario);

        String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
        novoUsuario.setSenha(senhaCriptografada);
        novoUsuario.setStatusUsuario(StatusEnum.ATIVO);
        novoUsuario.setPermissao(PermissaoEnum.USUARIO);
        return UsuarioMapper.toDTO(usuarioRepository.save(novoUsuario));
    }

    public List<UsuarioDTO> listarTodosOsUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();

        if (usuarios.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Nenhum usuário encontrado");
        }

        return usuarios.stream()
                .map(UsuarioMapper::toDTO)
                .collect(Collectors.toList());
    }

    public UsuarioDTO listarUsuarioPorId(int id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado")
                );

        return UsuarioMapper.toDTO(usuario);
    }


    public UsuarioDTO atualizarUsuario(int id, UsuarioCriacaoDTO usuario) {
        Usuario usuarioAnterior = usuarioRepository.findById(id).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não encontrado"));

        Usuario entity = UsuarioMapper.toEntity(usuario);
        entity.setSenha(usuarioAnterior.getSenha());
        entity.setId(usuarioAnterior.getId());
        entity.setPermissao(usuarioAnterior.getPermissao());
        entity.setTelefone(usuario.getTelefone());
        entity.setEmail(usuario.getEmail());
        entity.setCpf(usuarioAnterior.getCpf());
        entity.setStatusUsuario(usuarioAnterior.getStatusUsuario());


        return UsuarioMapper.toDTO(usuarioRepository.save(entity));
    }

    public void desativarUsuario(int idUsuario) {
        Usuario usuarioAhDesativar = usuarioRepository.findById(idUsuario)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não cadastrado")
                );

        usuarioAhDesativar.setStatusUsuario(StatusEnum.INATIVO);
        usuarioRepository.save(usuarioAhDesativar);
    }


    public void ativarUsuario(int idUsuario) {
        Usuario usuarioAhAtivar = usuarioRepository.findById(idUsuario)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não cadastrado")
                );

        usuarioAhAtivar.setStatusUsuario(StatusEnum.ATIVO);
        usuarioRepository.save(usuarioAhAtivar);
    }

    public Usuario permissionarUsuario(int idUsuario, PermissaoEnum permissao) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não cadastrado")
                );

        switch (permissao) {
            case ADMINISTRADOR:
                usuario.setPermissao(PermissaoEnum.ADMINISTRADOR);
                break;
            case CLIENTE:
                usuario.setPermissao(PermissaoEnum.CLIENTE);
                break;
            case FUNCIONARIO:
                usuario.setPermissao(PermissaoEnum.FUNCIONARIO);
                break;
            default:
                throw new IllegalArgumentException("Permissão inválida: " + permissao);
        }

        return usuarioRepository.save(usuario);
    }

    public Usuario atualizarSenhaUsuario(int idUsuario, UsuarioSenhaDTO usuarioSenhaDTO) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não cadastrado")
                );

        usuario.setSenha(usuarioSenhaDTO.getSenha());
        String encode = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(encode);
        return usuarioRepository.save(usuario);
    }
}
