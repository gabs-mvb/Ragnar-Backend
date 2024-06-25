package ragnar.app.domain.usuario.dto.mapper;

import ragnar.app.domain.usuario.Usuario;
import ragnar.app.domain.usuario.dto.UsuarioCriacaoDTO;
import ragnar.app.domain.usuario.dto.UsuarioDTO;
import ragnar.app.domain.usuario.dto.UsuarioTokenDTO;
import ragnar.app.utils.enums.PermissaoEnum;
import ragnar.app.utils.enums.StatusEnum;

import java.time.LocalDateTime;

public class UsuarioMapper {

    public static Usuario toEntity (UsuarioCriacaoDTO usuarioCriacaoDTO){
        return Usuario.builder()
                .nome(usuarioCriacaoDTO.getNome())
                .sobrenome(usuarioCriacaoDTO.getSobrenome())
                .email(usuarioCriacaoDTO.getEmail())
                .senha(usuarioCriacaoDTO.getSenha())
                .dataRegistro(LocalDateTime.now())
                .telefone(usuarioCriacaoDTO.getTelefone())
                .cpf(usuarioCriacaoDTO.getCpf())
                .build();
    }

    public static UsuarioDTO toDTO (Usuario usuario){
        return UsuarioDTO.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .sobrenome(usuario.getSobrenome())
                .email(usuario.getEmail())
                .telefone(usuario.getTelefone())
                .cpf(usuario.getCpf())
                .status(usuario.getStatusUsuario())
                .permissao(usuario.getPermissao())
                .build();
    }

    public static UsuarioTokenDTO toTokenDTO (Usuario usuario, String token){
        return UsuarioTokenDTO.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .sobrenome(usuario.getSobrenome())
                .email(usuario.getEmail())
                .token(token)
                .status(usuario.getStatusUsuario())
                .permissao(usuario.getPermissao())
                .build();
    }

}
