package ragnar.app.integration.ViaCep.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ragnar.app.integration.ViaCep.dto.ViaCepResponse;

@FeignClient(name = "viaCepClient", url = "https://viacep.com.br/ws")
public interface ViaCepClient {

    @GetMapping("/{cep}/json")
    ViaCepResponse getCEP(@PathVariable String cep);
}
