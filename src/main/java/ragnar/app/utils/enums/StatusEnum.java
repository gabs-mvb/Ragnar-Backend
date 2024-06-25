package ragnar.app.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum StatusEnum {
    ATIVO("ATIVO"),
    INATIVO("INATIVO")
    ;
    private String status;
}
