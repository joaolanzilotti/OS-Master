
package springthymeleaf.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class OrdemServico implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@Enumerated -> vai ser enumerada e com o EnumType.STRING vai fazer ele vir em forma de Texto no banco de Dados!
    @Enumerated(EnumType.STRING)
    private StatusOrdemServico statusOrdemServico;

    //Muitos para UM - Muitas Listas de Compras para um Cliente -  e la na classe Cliente tem que usar o @OneToMany(mappedby = "cliente")
    //@JoinColumn(name = "") -> Especifica qual nome da foreign key
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    
    //Muitos para UM - Muitas Listas de Compras para um Cliente - e la na classe Servicos tem que usar o @OneToMany(mappedby = "servicos")
    //@JoinColumn(name = "") -> Especifica qual nome da foreign key
    @ManyToOne
    @JoinColumn(name = "servicos_id")
    private Servico servicos;

    @ManyToOne
    @JoinColumn(name = "produtos_id")
    private Produto produtos;


    public OrdemServico() {
    }

    public OrdemServico(Long id, Cliente cliente, Servico servicos, Produto produtos, StatusOrdemServico statusOrdemServico) {
        this.id = id;
        this.cliente = cliente;
        this.servicos = servicos;
        this.produtos = produtos;
        this.statusOrdemServico = statusOrdemServico;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StatusOrdemServico statusOrdemServico() {
        return statusOrdemServico;
    }

    public void setOrdemServico(StatusOrdemServico statusOrdemServico) {
        this.statusOrdemServico = statusOrdemServico;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Servico getServicos() {
        return servicos;
    }

    public void setServicos(Servico servicos) {
        this.servicos = servicos;
    }

    public Produto getProdutos() {
        return produtos;
    }

    public void setProdutos(Produto produtos) {
        this.produtos = produtos;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OrdemServico other = (OrdemServico) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "OrdemServico{" + "id=" + id + ", cliente=" + cliente + ", servicos=" + servicos + '}';
    }
    
    
    
    
}
