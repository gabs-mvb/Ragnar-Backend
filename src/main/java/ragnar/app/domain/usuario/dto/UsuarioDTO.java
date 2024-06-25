package ragnar.app.domain.usuario.dto;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import ragnar.app.utils.enums.PermissaoEnum;
import ragnar.app.utils.enums.StatusEnum;

@Data
@Builder
public class UsuarioDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    private String sobrenome;
    private String email;
    private String telefone;
    private String cpf;
    @Enumerated(EnumType.STRING)
    private StatusEnum status;
    @Enumerated(EnumType.STRING)
    private PermissaoEnum permissao;

}
