package ragnar.app.service.endereco;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ragnar.app.domain.endereco.Endereco;
import ragnar.app.domain.endereco.dto.EnderecoDTO;
import ragnar.app.domain.endereco.mapper.EnderecoMapper;
import ragnar.app.domain.endereco.repository.EnderecoRepository;
import ragnar.app.domain.usuario.Usuario;
import ragnar.app.domain.usuario.repository.UsuarioRepository;
import ragnar.app.integration.ViaCep.client.ViaCepClient;
import ragnar.app.integration.ViaCep.dto.ViaCepResponse;
import ragnar.app.utils.enums.StatusEnum;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EnderecoService {
    private final EnderecoRepository enderecoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ViaCepClient viaCepClient;
    public Endereco getEnderecoPeloCep(String cep) {
        ViaCepResponse response = this.viaCepClient.getCEP(cep);
        if(response.getCep()==null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"CEP não encontrado");
        }
        return EnderecoMapper.reponseToEntity(response);
    }

    private void atualizarListaDeEnderecosComoInativo(Usuario usuario){
        List<Endereco> list = enderecoRepository.findAll()
                .stream()
                .filter(endereco -> endereco.getUsuario().getId() == usuario.getId())
                .toList();

        if(list.isEmpty()){
            return;
        }

        for (Endereco endereco : list) {
            endereco.setEnderecoAtual(StatusEnum.INATIVO);
            enderecoRepository.save(endereco);
        }
    }

    private Boolean verificaSeEnderecoJaExiste(Endereco endereco, Usuario usuario) {
        List<Endereco> enderecoOptional = enderecoRepository.findByCepAndUsuario_Id(endereco.getCep(), usuario.getId());
        if(!enderecoOptional.isEmpty()){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public List<EnderecoDTO> listarTodosOsEnderecos() {
        return enderecoRepository.findAll().stream().map(EnderecoMapper::toDTO).toList();
    }

    public List<Endereco> listarEnderecoUsuario(int idUsuario) {
        return enderecoRepository.findAll().stream()
                .filter(endereco -> endereco.getUsuario().getId() == idUsuario)
                .toList();
    }

    public Endereco cadastrarEndereco(String cep, String complemento, int numero, int idUsuario) {
        Endereco endereco = getEnderecoPeloCep(cep);
        Usuario usuario = usuarioRepository.findById(idUsuario).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Usuário não encontrado")
        );

        if(verificaSeEnderecoJaExiste(endereco, usuario)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Endereço já cadastrado");
        }

        atualizarListaDeEnderecosComoInativo(usuario);

        endereco.setNumero(numero);
        endereco.setComplemento(complemento);
        endereco.setUsuario(usuario);
        endereco.setEnderecoAtual(StatusEnum.ATIVO);
        enderecoRepository.save(endereco);
        return endereco;
    }

    public void deletarEndereco(int idEndereco) {
        Endereco endereco = enderecoRepository.findById(idEndereco)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Endereço não encontrado")
                );

        enderecoRepository.delete(endereco);
    }


    public void marcarComoAtual(int idEndereco) {
        Endereco endereco = enderecoRepository.findById(idEndereco)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Endereço não encontrado")
                );

        atualizarListaDeEnderecosComoInativo(endereco.getUsuario());
        endereco.setEnderecoAtual(StatusEnum.ATIVO);
        enderecoRepository.save(endereco);
    }
}
