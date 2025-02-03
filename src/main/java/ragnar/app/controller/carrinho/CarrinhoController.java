package ragnar.app.controller.carrinho;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ragnar.app.domain.carrinho.dto.CarrinhoDTO;

@RestController
@RequestMapping("/carrinho")
public class CarrinhoController {

    @RequestMapping("/adicionar")
    public ResponseEntity<CarrinhoDTO> adicionar() {
        return "Adicionado ao carrinho";
    }

    @RequestMapping("/remover")
    public String remover() {
        return "Removido do carrinho";
    }

    @RequestMapping("/finalizar")
    public String finalizar() {
        return "Compra finalizada";
    }

    @RequestMapping("/cancelar")
    public String cancelar() {
        return "Compra cancelada";
    }

    @RequestMapping("/listar")
    public String listar() {
        return "Listando itens do carrinho";
    }

    @RequestMapping("/limpar")
    public String limpar() {
        return "Carrinho limpo";
    }
}
