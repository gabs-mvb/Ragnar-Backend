package studenty.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import studenty.api.entity.Produto;
import studenty.api.viewer.produtos.NomeProduto;

public interface ProdutosRepository extends JpaRepository<Produto,Long> {
}
