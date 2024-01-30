package dec.core.repository;

import dec.core.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutosRepository extends JpaRepository<Produto,Long> {
}
