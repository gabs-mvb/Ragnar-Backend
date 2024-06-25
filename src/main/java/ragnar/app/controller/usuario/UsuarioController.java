package ragnar.app.controller.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ragnar.app.domain.usuario.dto.*;
import ragnar.app.domain.usuario.dto.mapper.UsuarioMapper;
import ragnar.app.service.usuario.UsuarioService;
import ragnar.app.utils.enums.PermissaoEnum;

import java.util.List;

@RestController
@RequestMapping("/usuarios")

public class UsuarioController {

    private final UsuarioService usuarioService;


    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listarTodosUsuarios() {
        return ResponseEntity.ok(usuarioService.listarTodosOsUsuarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> listarUsuarioPorID(@PathVariable int id) {
        return ResponseEntity.ok(usuarioService.listarUsuarioPorId(id));
    }

    @PostMapping("/cadastro")
    public ResponseEntity<UsuarioDTO> cadastrarUsuario(@RequestBody UsuarioCriacaoDTO usuarioCriacao) {
        return ResponseEntity.status(201).body(usuarioService.cadastrarUsuario(usuarioCriacao));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> atualizarUsuario(@PathVariable int id, @RequestBody UsuarioCriacaoDTO usuario) {
        return ResponseEntity.status(200).body(usuarioService.atualizarUsuario(id, usuario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desativarUsuario(@PathVariable int id) {
        usuarioService.desativarUsuario(id);
        return ResponseEntity.status(204).build();
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioTokenDTO> login(@RequestBody UsuarioLoginDTO usuarioLoginDTO) {
        UsuarioTokenDTO usuarioTokenDTO = this.usuarioService.autenticar(usuarioLoginDTO);
        return ResponseEntity.ok(usuarioTokenDTO);
    }

    @PatchMapping("/senhas/{idUsuario}")
    public ResponseEntity<UsuarioDTO> atualizarSenhaUsuario(@PathVariable int idUsuario, @RequestBody UsuarioSenhaDTO usuarioSenhaDTO) {
        //TODO: ARRUMAR ERRO CONTA BLOQUEADA
        return ResponseEntity.ok(UsuarioMapper.toDTO(usuarioService.atualizarSenhaUsuario(idUsuario, usuarioSenhaDTO)));
    }

    @PutMapping("/permissionar/{idUsuario}")
    public ResponseEntity<UsuarioDTO> permissionarUsuarioAdministrador(@PathVariable int idUsuario, @RequestBody PermissaoEnum permissaoEnum) {
        return ResponseEntity.ok(UsuarioMapper.toDTO(usuarioService.permissionarUsuario(idUsuario, permissaoEnum)));
    }


    @PutMapping("/ativar/{idUsuario}")
    public ResponseEntity<Void> ativarUsuario(@PathVariable int idUsuario) {
        usuarioService.ativarUsuario(idUsuario);
        return ResponseEntity.status(204).build();
    }
}
