package studenty.api.entity;

import jakarta.persistence.*;
import lombok.*;
import studenty.api.viewer.produtos.DTOProdutos;
import studenty.api.viewer.produtos.NomeProduto;

@Table(name = "produtos")
@Entity(name = "Produto")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Produto {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private NomeProduto nome;

    private String marca;
    private String tamanho;
    private String fornecedor;
    private Double precoCompra;
    private Double  precoVenda;


    public Produto(DTOProdutos dados) {
    this.nome = dados.nome();
    this.marca = dados.marca();
    this.tamanho = dados.tamanho();
    this.fornecedor = dados.fornecedor();
    this.precoCompra = dados.precoCompra();
    this.precoVenda = dados.precoVenda();
    }
}
