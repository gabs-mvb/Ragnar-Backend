package studenty.api.viewer.produtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import studenty.api.entity.Produto;

public record DTOProdutos(
        @NotNull
        NomeProduto nome,
        @NotBlank
        String marca,
        @NotBlank
        String tamanho,
        String fornecedor,
        @NotNull
        Double precoCompra,
        @NotNull
        Double precoVenda
        ) {

}
