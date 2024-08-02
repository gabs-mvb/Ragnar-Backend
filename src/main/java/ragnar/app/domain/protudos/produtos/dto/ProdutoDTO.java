package ragnar.app.domain.protudos.produtos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ragnar.app.domain.protudos.categorias.dto.CategoriaDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDTO {

    private Integer id;
    private CategoriaDTO categoria;
    private String nome;
    private String descricao;
    private String tamanho;
    private BigDecimal preco;
    private BigDecimal desconto;
    private Integer estoque;
    private String imagemId;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;
}
