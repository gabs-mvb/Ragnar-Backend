package ragnar.app.service.produtos;

import ragnar.app.domain.protudos.produtos.Produto;
import ragnar.app.domain.protudos.produtos.dto.ProdutoDTO;

import java.math.BigDecimal;
import java.util.List;

public interface ProdutoService {

    Produto criarProduto(ProdutoDTO produto);

    Produto obterProdutoPorId(Integer id);

    List<Produto> obterTodosOsProdutos();

    Produto atualizarProduto(Integer id, ProdutoDTO produtoAtualizado);

    void excluirProduto(Integer id);

    Produto atualizarEstoque(Integer id, int novaQuantidade);

    Produto aplicarDesconto(Integer id, Double desconto);

    List<Produto> buscarProdutosPorCategoria(Integer categoriaId);

    List<Produto> buscarProdutosPorNomeOuDescricao(String termo);

    List<Produto> filtrarProdutosPorFaixaDePreco(BigDecimal precoMin, BigDecimal precoMax);

    List<Produto> obterProdutosEmPromocao();

    Produto atualizarPreco(Integer id, Double novoPreco);
}
