
package springthymeleaf.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
public class Produto implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codigoProduto;
    private String nome;
    private String descricao;
    private int estoque;
    private Double valor;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    private List<ProdutoOrdem> produtoOrdem;

    public Produto() {
    }

    public Produto(Long id, String codigoProduto, String nome, String descricao, int estoque, Double valor,
            List<ProdutoOrdem> produtoOrdem) {
        this.id = id;
        this.codigoProduto = codigoProduto;
        this.nome = nome;
        this.descricao = descricao;
        this.estoque = estoque;
        this.valor = valor;
        this.produtoOrdem = produtoOrdem;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final Produto other = (Produto) obj;
        return Objects.equals(this.id, other.id);
    }

}
