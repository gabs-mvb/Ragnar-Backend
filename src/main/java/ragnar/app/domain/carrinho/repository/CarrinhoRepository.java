package ragnar.app.domain.carrinho.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ragnar.app.domain.carrinho.Carrinho;

@Repository
public interface CarrinhoRepository implements JpaRepository<Carrinho, Integer> {
}
