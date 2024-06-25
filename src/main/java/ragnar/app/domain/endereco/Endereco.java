package ragnar.app.domain.endereco;

import jakarta.persistence.*;
import lombok.*;
import ragnar.app.domain.usuario.Usuario;
import ragnar.app.utils.enums.StatusEnum;

@Entity
@Table(name = "enderecos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 255)
    private String logradouro;

    @Column(length = 9)
    private String cep;

    @Column(length = 45)
    private String cidade;

    @Column(length = 2)
    private String uf;

    @Column(length = 45)
    private String bairro;

    @Column(length = 45)
    private String complemento;

    @Column
    private int numero;

    @Column(name = "endereco_atual", nullable = false)
    private StatusEnum enderecoAtual;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

}
