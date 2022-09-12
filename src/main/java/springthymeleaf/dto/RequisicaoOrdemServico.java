package springthymeleaf.dto;

import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import springthymeleaf.entities.Cliente;
import springthymeleaf.entities.OrdemServico;
import springthymeleaf.entities.Produto;
import springthymeleaf.entities.Servico;
import springthymeleaf.entities.StatusOrdemServico;

public class RequisicaoOrdemServico {

    private Long id;
    @Enumerated(EnumType.STRING)
    private StatusOrdemServico statusOrdemServico;
    private Cliente cliente;
    private Produto produto;
    private Servico servico;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date dataFinal;

    public OrdemServico toOS() {

        OrdemServico ordemServico = new OrdemServico();
        ordemServico.setCliente(this.cliente);
        ordemServico.setProduto(this.produto);
        ordemServico.setServico(this.servico);
        ordemServico.setStatusOrdemServico(this.statusOrdemServico);
        ordemServico.setDataFinal(this.dataFinal);
        
        return ordemServico;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StatusOrdemServico getStatusOrdemServico() {
        return statusOrdemServico;
    }

    public void setStatusOrdemServico(StatusOrdemServico statusOrdemServico) {
        this.statusOrdemServico = statusOrdemServico;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

}
