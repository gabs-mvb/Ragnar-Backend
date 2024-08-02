package ragnar.app.domain.protudos.categorias.mapper;

import ragnar.app.domain.protudos.categorias.Categoria;
import ragnar.app.domain.protudos.categorias.dto.CategoriaDTO;

public class CategoriaMapper {
    public static CategoriaDTO toDTO(Categoria categoria) {
        return CategoriaDTO.builder()
                .id(categoria.getId())
                .nome(categoria.getNome())
                .descricao(categoria.getDescricao())
                .build();
    }

    public static Categoria toEntity(CategoriaDTO categoriaDTO) {
        return Categoria.builder()
                .id(categoriaDTO.getId())
                .nome(categoriaDTO.getNome())
                .descricao(categoriaDTO.getDescricao())
                .build();
    }
}
