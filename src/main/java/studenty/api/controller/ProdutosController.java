package studenty.api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import studenty.api.entity.Produto;
import studenty.api.repository.ProdutosRepository;
import studenty.api.viewer.produtos.DTOListaProdutos;
import studenty.api.viewer.produtos.DTOProdutos;

import java.util.List;


@RestController
@RequestMapping("/produtos")
public class ProdutosController {

    @Autowired
    private ProdutosRepository repository;

    @PostMapping
    @Transactional
    public void cadastrarProdutos(@RequestBody @Valid DTOProdutos dados){
//         if(repository.existsByName(dados.nome())){
//                    return;
//         }
         repository.save(new Produto(dados));
    }

    @GetMapping
    public Page<DTOListaProdutos> listarProdutos(Pageable paginacao){
        return repository.findAll(paginacao).map(DTOListaProdutos::new);
    }
}