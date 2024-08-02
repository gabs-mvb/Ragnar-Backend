package ragnar.app.domain.protudos.categorias.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ragnar.app.domain.protudos.categorias.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    Categoria findByNome(String nome);
}
