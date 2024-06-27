package ragnar.app.controller.endereco;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ragnar.app.domain.endereco.Endereco;
import ragnar.app.domain.endereco.dto.EnderecoDTO;
import ragnar.app.domain.endereco.mapper.EnderecoMapper;
import ragnar.app.service.endereco.EnderecoService;

import java.util.List;

@RestController
@RequestMapping("/enderecos")
@RequiredArgsConstructor
public class EnderecoController {

    private final EnderecoService enderecoService;

    @GetMapping("/{idUsuario}")
    public ResponseEntity<List<EnderecoDTO>> listarEnderecosUsuario(@PathVariable int idUsuario) {
        List<EnderecoDTO> list = enderecoService.listarEnderecoUsuario(idUsuario)
                .stream().map(EnderecoMapper::toDTO).toList();

        if(list.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(list);
    }

    @PostMapping("/{idUsuario}")
    public ResponseEntity<EnderecoDTO> cadastrarEndereco(@PathVariable int idUsuario, @RequestBody EnderecoDTO enderecoDTO) {
        Endereco enderecoCadastrado = enderecoService.cadastrarEndereco(
                enderecoDTO.getCep(),
                enderecoDTO.getComplemento(),
                enderecoDTO.getNumero(),
                idUsuario
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(EnderecoMapper.toDTO(enderecoCadastrado));
    }

    @DeleteMapping("/{idEndereco}")
    public ResponseEntity<Void> deletarEndereco(@PathVariable int idEndereco) {
        enderecoService.deletarEndereco(idEndereco);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{idEndereco}")
    public ResponseEntity<Void> marcarComoAtual(@PathVariable int idEndereco) {
        enderecoService.marcarComoAtual(idEndereco);
        return ResponseEntity.ok().build();
    }

}
