package springthymeleaf.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

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
    private Produto produtos;
    private Servico servicos;

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

    public Produto getProdutos() {
        return produtos;
    }

    public void setProdutos(Produto produtos) {
        this.produtos = produtos;
    }

    public Servico getServicos() {
        return servicos;
    }

    public void setServicos(Servico servicos) {
        this.servicos = servicos;
    }

    public OrdemServico toOS() {

        OrdemServico ordemServico = new OrdemServico();
        ordemServico.setCliente(this.cliente);
        ordemServico.setProdutos(this.produtos);
        ordemServico.setServicos(this.servicos);
        ordemServico.setStatusOrdemServico(this.statusOrdemServico);
        
        return ordemServico;
    }

}
