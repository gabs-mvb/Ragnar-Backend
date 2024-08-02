package ragnar.app.controller.categorias;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ragnar.app.domain.protudos.categorias.dto.CategoriaDTO;
import ragnar.app.domain.protudos.categorias.mapper.CategoriaMapper;
import ragnar.app.service.produtos.categorias.CategoriaService;

import java.util.List;

@RestController
@RequestMapping("/categorias")
@RequiredArgsConstructor
public class CategoriaController {
    private final CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> obterTodasCategorias() {
        List<CategoriaDTO> list = categoriaService.obterTodasAsCategorias()
                .stream().map(CategoriaMapper::toDTO).toList();

        if(list.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(list);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<CategoriaDTO> cadastrarCategoria(CategoriaDTO categoria) {
        return ResponseEntity.ok(CategoriaMapper.toDTO(categoriaService.criarCategoria(categoria)));
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<CategoriaDTO> obterCategoriaPorNome(String nome) {
        return ResponseEntity.ok(CategoriaMapper.toDTO(categoriaService.obterCategoriaPorNome(nome)));
    }
}
