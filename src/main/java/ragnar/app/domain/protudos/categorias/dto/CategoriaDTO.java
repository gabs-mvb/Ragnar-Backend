package ragnar.app.domain.protudos.categorias.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoriaDTO {
    private Integer id;
    private String nome;
    private String descricao;
}
