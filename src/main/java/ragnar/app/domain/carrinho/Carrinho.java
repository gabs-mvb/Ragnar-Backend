package ragnar.app.domain.carrinho;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ragnar.app.domain.endereco.Endereco;
import ragnar.app.domain.protudos.produtos.Produto;
import ragnar.app.domain.usuario.Usuario;

@Entity
@Table(name = "carrinho")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Carrinho {

     private Integer id;
     private Integer quantidade;
     private BigDecimal valorTotal;
     private BigDecimal valorFrete;
     private BigDecimal valorDesconto;
     private double valorProdutos;

     @ManyToOne
     private List<Produto> produtos;

     @OneToMany
     private Usuario usuario;

}
