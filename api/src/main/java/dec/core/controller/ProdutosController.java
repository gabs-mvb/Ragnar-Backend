package dec.core.controller;

import dec.core.entity.Produto;
import dec.core.repository.ProdutosRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutosController {

    private final ProdutosRepository produtosRepository;

    @GetMapping
    public Page<Produto> findAll(Pageable pageable) {
        return produtosRepository.findAll(pageable);
    }

    @PostMapping
    @Transactional
    public Produto save(@RequestBody @Valid Produto produto) {
        return produtosRepository.save(produto);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable Long id) {
        produtosRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    @Transactional
    public Produto update(@PathVariable Long id, @RequestBody @Valid Produto produto) {
        return produtosRepository.save(produto);
    }
}