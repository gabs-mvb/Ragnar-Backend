package ragnar.app.domain.protudos.produtos.mapper;

import ragnar.app.domain.protudos.categorias.mapper.CategoriaMapper;
import ragnar.app.domain.protudos.produtos.Produto;
import ragnar.app.domain.protudos.produtos.dto.ProdutoDTO;

public class ProdutoMapper {
    public static ProdutoDTO toDTO(Produto produto) {
        return ProdutoDTO.builder()
                .id(produto.getId())
                .categoria(CategoriaMapper.toDTO(produto.getCategoria()))
                .nome(produto.getNome())
                .descricao(produto.getDescricao())
                .preco(produto.getPreco())
                .desconto(produto.getDesconto())
                .estoque(produto.getEstoque())
                .tamanho(produto.getTamanho())
                .imagemId(produto.getImagemId())
                .dataCriacao(produto.getDataCriacao())
                .dataAtualizacao(produto.getDataAtualizacao())
                .build();
    }

    public static Produto toEntity(ProdutoDTO produto) {
        return Produto.builder()
                .id(produto.getId())
                .categoria(CategoriaMapper.toEntity(produto.getCategoria()))
                .nome(produto.getNome())
                .descricao(produto.getDescricao())
                .preco(produto.getPreco())
                .desconto(produto.getDesconto())
                .estoque(produto.getEstoque())
                .tamanho(produto.getTamanho())
                .imagemId(produto.getImagemId())
                .dataCriacao(produto.getDataCriacao())
                .dataAtualizacao(produto.getDataAtualizacao())
                .build();
    }
}
