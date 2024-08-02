package ragnar.app.controller.produtos;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ragnar.app.domain.protudos.produtos.Produto;
import ragnar.app.domain.protudos.produtos.dto.ProdutoDTO;
import ragnar.app.domain.protudos.produtos.mapper.ProdutoMapper;
import ragnar.app.service.produtos.produtos.ProdutoServiceImp;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoServiceImp produtoService;

    @PostMapping("/cadastrar")
    public ResponseEntity<ProdutoDTO> cadastrarProduto(@RequestBody ProdutoDTO produtoDTO) {
        Produto produtoCadastrado = produtoService.criarProduto(produtoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(ProdutoMapper.toDTO(produtoCadastrado));
    }

    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> listarTodosProdutos() {
        return ResponseEntity.ok(produtoService.obterTodosOsProdutos()
                .stream().map(ProdutoMapper::toDTO).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> listarProdutoPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(ProdutoMapper.toDTO(produtoService.obterProdutoPorId(id)));
    }

    @GetMapping("/categoria/{id}")
    public ResponseEntity<List<ProdutoDTO>> listarProdutosPorCategoria(@PathVariable Integer id) {
        return ResponseEntity.ok(produtoService.buscarProdutosPorCategoria(id)
                .stream().map(ProdutoMapper::toDTO).collect(Collectors.toList()));
    }

    @GetMapping("/preco/{preco}")
    public ResponseEntity<List<ProdutoDTO>> listarProdutosPorPreco(@PathVariable Double preco) {
        return ResponseEntity.ok(produtoService.obterTodosOsProdutos()
                .stream().filter(produto -> produto.getPreco().equals(preco))
                .map(ProdutoMapper::toDTO).collect(Collectors.toList()));
    }

    @GetMapping("/estoque/{estoque}")
    public ResponseEntity<List<ProdutoDTO>> listarProdutosPorEstoque(@PathVariable Integer estoque) {
        return ResponseEntity.ok(produtoService.obterTodosOsProdutos()
                .stream().filter(produto -> produto.getEstoque().equals(estoque))
                .map(ProdutoMapper::toDTO).collect(Collectors.toList()));
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<ProdutoDTO>> listarProdutosPorNome(@PathVariable String nome) {
        return ResponseEntity.ok(produtoService.obterTodosOsProdutos()
                .stream().filter(produto -> produto.getNome().equals(nome))
                .map(ProdutoMapper::toDTO).collect(Collectors.toList()));
    }

    @GetMapping("data/{data}")
    public ResponseEntity<List<ProdutoDTO>> listarProdutosPorData(@PathVariable String data) {
        return ResponseEntity.ok(produtoService.obterTodosOsProdutos()
                .stream().filter(produto -> produto.getDataCriacao().equals(data))
                .map(ProdutoMapper::toDTO).collect(Collectors.toList()));
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<ProdutoDTO> atualizarProduto(@PathVariable Integer id, @RequestBody ProdutoDTO produtoDTO) {
        Produto produtoAtualizado = produtoService.atualizarProduto(id, produtoDTO);
        return ResponseEntity.ok(ProdutoMapper.toDTO(produtoAtualizado));
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Void> excluirProduto(@PathVariable Integer id) {
        produtoService.excluirProduto(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/atualizarEstoque/{id}")
    public ResponseEntity<ProdutoDTO> atualizarEstoque(@PathVariable Integer id, @RequestParam Integer novaQuantidade) {
        Produto produtoAtualizado = produtoService.atualizarEstoque(id, novaQuantidade);
        return ResponseEntity.ok(ProdutoMapper.toDTO(produtoAtualizado));
    }

    @PutMapping("/atualizarPreco/{id}")
    public ResponseEntity<ProdutoDTO> atualizarPreco(@PathVariable Integer id, @RequestParam Double novoPreco) {
        Produto produtoAtualizado = produtoService.atualizarPreco(id, novoPreco);
        return ResponseEntity.ok(ProdutoMapper.toDTO(produtoAtualizado));
    }

    @PostMapping("/aplicarDesconto/{id}")
    public ResponseEntity<ProdutoDTO> aplicarDesconto(@PathVariable Integer id, @RequestParam Double desconto) {
        Produto produtoAtualizado = produtoService.aplicarDesconto(id, desconto);
        return ResponseEntity.ok(ProdutoMapper.toDTO(produtoAtualizado));
    }

    @GetMapping("/buscar-dinamico/{termo}")
    public ResponseEntity<List<ProdutoDTO>> buscarProdutosPorNomeOuDescricao(@PathVariable String termo) {
        return ResponseEntity.ok(produtoService.buscarProdutosPorNomeOuDescricao(termo)
                .stream().map(ProdutoMapper::toDTO).collect(Collectors.toList()));
    }

    @GetMapping("/promocao")
    public ResponseEntity<List<ProdutoDTO>> obterProdutosEmPromocao() {
        return ResponseEntity.ok(produtoService.obterProdutosEmPromocao()
                .stream().map(ProdutoMapper::toDTO).collect(Collectors.toList()));
    }

    @GetMapping("/filtrar-preco/{precoMin}/{precoMax}")
    public ResponseEntity<List<ProdutoDTO>> filtrarProdutosPorFaixaDePreco(@PathVariable BigDecimal precoMin, @PathVariable BigDecimal precoMax) {
        return ResponseEntity.ok(produtoService.filtrarProdutosPorFaixaDePreco(precoMin, precoMax)
                .stream().map(ProdutoMapper::toDTO).collect(Collectors.toList()));
    }
}
