package ragnar.app.domain.endereco.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnderecoCadastroDTO {
    private String cep;
    private String complemento;
    private int numero;
}
