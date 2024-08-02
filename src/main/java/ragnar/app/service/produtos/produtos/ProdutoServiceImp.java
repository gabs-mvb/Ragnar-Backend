package ragnar.app.service.produtos.produtos;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ragnar.app.domain.protudos.categorias.mapper.CategoriaMapper;
import ragnar.app.domain.protudos.produtos.Produto;
import ragnar.app.domain.protudos.produtos.dto.ProdutoDTO;
import ragnar.app.domain.protudos.produtos.mapper.ProdutoMapper;
import ragnar.app.domain.protudos.produtos.repository.ProdutoRepository;
import ragnar.app.service.produtos.ProdutoService;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoServiceImp implements ProdutoService {

    private final ProdutoRepository produtoRepository;

    @Override
    public Produto criarProduto(ProdutoDTO produto) {
        Produto entity = ProdutoMapper.toEntity(produto);
        produtoRepository.save(entity);
        return entity;
    }

    @Override
    public Produto obterProdutoPorId(Integer id) {
        return produtoRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    @Override
    public List<Produto> obterTodosOsProdutos() {
        return produtoRepository.findAll();
    }

    @Override
    public Produto atualizarProduto(Integer id, ProdutoDTO produtoAtualizado) {
        Produto produtoExistente = produtoRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        if (produtoAtualizado.getNome() != null && !produtoAtualizado.getNome().isEmpty()) {
            produtoExistente.setNome(produtoAtualizado.getNome());
        }

        if (produtoAtualizado.getDescricao() != null && !produtoAtualizado.getDescricao().isEmpty()) {
            produtoExistente.setDescricao(produtoAtualizado.getDescricao());
        }

        if (produtoAtualizado.getPreco() != null) {
            produtoExistente.setPreco(produtoAtualizado.getPreco());
        }

        if (produtoAtualizado.getEstoque() != null) {
            produtoExistente.setEstoque(produtoAtualizado.getEstoque());
        }

        if (produtoAtualizado.getCategoria() != null) {
            produtoExistente.setCategoria(CategoriaMapper.toEntity(produtoAtualizado.getCategoria()));
        }

        if (produtoAtualizado.getDataAtualizacao() != null) {
            produtoExistente.setDataAtualizacao(produtoAtualizado.getDataAtualizacao());
        }

        return produtoRepository.save(produtoExistente);
    }


    @Override
    public void excluirProduto(Integer id) {
        produtoRepository.deleteById(id);
    }

    @Override
    public Produto atualizarEstoque(Integer id, int novaQuantidade) {
        return produtoRepository.findById(id)
                .map(produto -> {
                    produto.setEstoque(novaQuantidade);
                    return produtoRepository.save(produto);
                }).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    @Override
    public Produto aplicarDesconto(Integer id, Double desconto) {
        return produtoRepository.findById(id)
                .map(produto -> {
                    produto.setDesconto(BigDecimal.valueOf(desconto));
                    return produtoRepository.save(produto);
                }).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    @Override
    public List<Produto> buscarProdutosPorCategoria(Integer categoriaId) {
        return produtoRepository.findByCategoriaId(categoriaId);
    }

    @Override
    public List<Produto> buscarProdutosPorNomeOuDescricao(String termo) {
        return produtoRepository.findByNomeOrDescricao(termo);
    }

    @Override
    public List<Produto> filtrarProdutosPorFaixaDePreco(BigDecimal precoMin, BigDecimal precoMax) {
        return produtoRepository.findByPrecoBetween(precoMin, precoMax);
    }

    @Override
    public List<Produto> obterProdutosEmPromocao() {
        return produtoRepository.findByDescontoGreaterThan(BigDecimal.ZERO);
    }

    @Override
    public Produto atualizarPreco(Integer id, Double novoPreco) {
        return produtoRepository.findById(id)
                .map(produto -> {
                    produto.setPreco(BigDecimal.valueOf(novoPreco));
                    return produtoRepository.save(produto);
                }).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }
}
