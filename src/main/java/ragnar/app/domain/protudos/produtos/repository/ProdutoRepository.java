package ragnar.app.domain.protudos.produtos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ragnar.app.domain.protudos.produtos.Produto;

import java.math.BigDecimal;
import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    @Query("SELECT p FROM Produto p WHERE p.nome LIKE %:searchTerm% OR p.descricao LIKE %:searchTerm%")
    List<Produto> findByNomeOrDescricao(@Param("searchTerm") String searchTerm);

    List<Produto> findByPrecoBetween(BigDecimal precoMin, BigDecimal precoMax);

    List<Produto> findByDescontoGreaterThan(BigDecimal zero);

    List<Produto> findByCategoriaId(Integer categoriaId);
}
