
package springthymeleaf.dto;

import lombok.Getter;
import lombok.Setter;
import springthymeleaf.entities.Produto;

@Getter
@Setter
public class RequisicaoProduto {
    
    private Long id;
    private String codigoProduto;
    private String nome;
    private String descricao;
    private int estoque;
    private Double valor;

    
    public Produto toProduto() {
        Produto produto = new Produto();
        produto.setNome(this.nome);
        produto.setDescricao(this.descricao);
        produto.setEstoque(this.estoque);
        produto.setValor(this.valor); 
        produto.setCodigoProduto(this.codigoProduto);
        return produto;
    }

    public Produto toProduto(Produto produto) {

        produto.setNome(this.nome);
        produto.setDescricao(this.descricao);
        produto.setEstoque(this.estoque);
        produto.setValor(this.valor);

        return produto;
    }

    public void fromProduto(Produto produto) {
        this.nome = produto.getNome();
        this.descricao = produto.getDescricao();
        this.estoque = produto.getEstoque();
        this.valor = produto.getValor();
    }

    @Override
    public String toString() {
        return "RequisicaoProduto [descricao=" + descricao + ", estoque=" + estoque + ", id=" + id + ", nome=" + nome
                + ", valor=" + valor + "]";
    }
    
}
