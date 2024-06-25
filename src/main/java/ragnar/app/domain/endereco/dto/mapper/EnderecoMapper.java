package ragnar.app.domain.endereco.dto.mapper;


import ragnar.app.domain.endereco.Endereco;
import ragnar.app.domain.endereco.dto.EnderecoExibicaoDTO;

public class EnderecoMapper {
    public static EnderecoExibicaoDTO toDTO(Endereco endereco){
       return EnderecoExibicaoDTO.builder()
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

}
