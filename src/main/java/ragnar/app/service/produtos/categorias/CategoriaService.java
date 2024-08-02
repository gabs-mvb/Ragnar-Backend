package ragnar.app.service.produtos.categorias;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ragnar.app.domain.protudos.categorias.Categoria;
import ragnar.app.domain.protudos.categorias.dto.CategoriaDTO;
import ragnar.app.domain.protudos.categorias.mapper.CategoriaMapper;
import ragnar.app.domain.protudos.categorias.repository.CategoriaRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public Categoria criarCategoria(CategoriaDTO categoria) {
        Categoria entity = CategoriaMapper.toEntity(categoria);
        categoriaRepository.save(entity);
        return entity;
    }

    public List<Categoria> obterTodasAsCategorias() {
        return categoriaRepository.findAll();
    }

    public Categoria obterCategoriaPorNome(String nome) {
        return categoriaRepository.findByNome(nome);
    }
}
