package ragnar.app.domain.endereco.mapper;


import ragnar.app.domain.endereco.Endereco;
import ragnar.app.domain.endereco.dto.EnderecoDTO;
import ragnar.app.integration.ViaCep.dto.ViaCepResponse;

public class EnderecoMapper {
    public static EnderecoDTO toDTO(Endereco endereco) {
        return EnderecoDTO.builder()
                .id(endereco.getId())
                .uf(endereco.getUf())
                .cep(endereco.getCep())
                .numero(endereco.getNumero())
                .cidade(endereco.getCidade())
                .bairro(endereco.getBairro())
                .complemento(endereco.getComplemento())
                .logradouro(endereco.getLogradouro())
                .build();
    }

    public static Endereco reponseToEntity(ViaCepResponse response) {
        return Endereco.builder()
                .logradouro(response.getLogradouro())
                .cep(response.getCep())
                .cidade(response.getLocalidade())
                .uf(response.getUf())
                .bairro(response.getBairro())
                .build();
    }

}
