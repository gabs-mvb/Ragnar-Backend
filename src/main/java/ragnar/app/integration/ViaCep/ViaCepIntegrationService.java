package ragnar.app.integration.ViaCep;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ragnar.app.integration.ViaCep.client.ViaCepClient;
import ragnar.app.integration.ViaCep.dto.ViaCepResponse;

@Service
@RequiredArgsConstructor
public class ViaCepIntegrationService {
    private final ViaCepClient viaCepClient;

    public ViaCepResponse getCEP(String cep) {
        try {
            return this.viaCepClient.getCEP(cep);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cep não encontrado");
        }
    }
}
