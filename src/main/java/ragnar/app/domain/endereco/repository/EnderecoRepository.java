package ragnar.app.domain.endereco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ragnar.app.domain.endereco.Endereco;
import ragnar.app.domain.usuario.Usuario;
import ragnar.app.utils.StringUtils;

import java.util.List;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
    List<Endereco> findByCep(String cep);

    List<Endereco> findByCepAndUsuario_Id(String cep, int idUsuario);

    @Transactional
    @Query("SELECT e FROM Endereco e WHERE e.cep = :cep AND e.numero = :numero")
    List<Endereco> findByCepAndNumero(String cep, int numero);

    private String formataCEP(String cep){
        return StringUtils.formataCep(cep);
    }
}