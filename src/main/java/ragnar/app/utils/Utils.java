package ragnar.app.utils;

import java.util.function.Consumer;

public class Utils {
    public static String formataCep(String cep){
        int tamanho = cep.length();

        String parte1 = cep.substring(0, tamanho - 3);
        String parte2 = cep.substring(tamanho - 3);

        return parte1 + "-" + parte2;
    }

    public static <T> void atualizarCampo(T novoValor, Consumer<T> setter) {
        if (novoValor != null && (!(novoValor instanceof String) || !((String) novoValor).isEmpty())) {
            setter.accept(novoValor);
        }
    }
}
