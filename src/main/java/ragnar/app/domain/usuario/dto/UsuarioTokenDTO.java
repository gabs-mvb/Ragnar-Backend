package ragnar.app.domain.usuario.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ragnar.app.utils.enums.PermissaoEnum;
import ragnar.app.utils.enums.StatusEnum;

@Getter
@Setter
@Builder
public class UsuarioTokenDTO {
    private int id;
    private String nome;
    private String sobrenome;
    private String email;
    private String token;
    @Enumerated(EnumType.STRING)
    private StatusEnum status;
    @Enumerated(EnumType.STRING)
    private PermissaoEnum permissao;
}
