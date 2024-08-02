package ragnar.app.domain.protudos.categorias;

import jakarta.persistence.*;
import lombok.*;
import ragnar.app.domain.protudos.produtos.Produto;

import java.util.List;

@Entity
@Table(name="categorias")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "descricao", columnDefinition = "TEXT")
    private String descricao;

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Produto> produtos;
}
