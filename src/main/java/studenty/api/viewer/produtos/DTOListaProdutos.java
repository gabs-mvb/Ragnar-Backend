package studenty.api.viewer.produtos;

import studenty.api.entity.Produto;

public record DTOListaProdutos (NomeProduto nome, String marca, String tamanho, Double precoVenda){
    public DTOListaProdutos(Produto produto) {
        this(
                produto.getNome(),
                produto.getMarca(),
                produto.getTamanho(),
                produto.getPrecoVenda()
        );
    }
}
