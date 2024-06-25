package ragnar.app.domain.usuario.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UsuarioSenhaDTO {
    private int id;
    private String senha;
}
