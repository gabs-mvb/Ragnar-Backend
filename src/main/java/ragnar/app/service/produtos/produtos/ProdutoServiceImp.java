package ragnar.app.service.produtos.produtos;

import static ragnar.app.utils.Utils.atualizarCampo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ragnar.app.domain.protudos.categorias.mapper.CategoriaMapper;
import ragnar.app.domain.protudos.produtos.Produto;
import ragnar.app.domain.protudos.produtos.dto.ProdutoDTO;
import ragnar.app.domain.protudos.produtos.mapper.ProdutoMapper;
import ragnar.app.domain.protudos.produtos.repository.ProdutoRepository;
import ragnar.app.service.produtos.ProdutoService;
import ragnar.app.utils.exceptions.ProdutoNaoEncontradoException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProdutoServiceImp implements ProdutoService {

    private final ProdutoRepository produtoRepository;

    @Override
    public Produto criarProduto(ProdutoDTO produto) {
        Produto entity = ProdutoMapper.toEntity(produto);
        entity.setDataCriacao(LocalDateTime.now());
        entity.setDataAtualizacao(LocalDateTime.now());
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
        Produto produtoExistente = produtoRepository.findById(id)
                  .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto com ID " + id + " não encontrado."));

        atualizarCampo(produtoAtualizado.getNome(), produtoExistente::setNome);
        atualizarCampo(produtoAtualizado.getDescricao(), produtoExistente::setDescricao);
        atualizarCampo(produtoAtualizado.getPreco(), produtoExistente::setPreco);
        atualizarCampo(produtoAtualizado.getEstoque(), produtoExistente::setEstoque);
        atualizarCampo(produtoAtualizado.getCategoria(), categoriaDTO ->
                  produtoExistente.setCategoria(CategoriaMapper.toEntity(categoriaDTO)));
        atualizarCampo(produtoAtualizado.getDataAtualizacao(), produtoExistente::setDataAtualizacao);

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
    public Produto aplicarDesconto(Integer id, Integer desconto) {
        return produtoRepository.findById(id)
                .map(produto -> {
                    produto.setDesconto(desconto);
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
