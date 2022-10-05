package springthymeleaf.dto;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import springthymeleaf.entities.Cliente;
import springthymeleaf.entities.OrdemServico;
import springthymeleaf.entities.Produto;
import springthymeleaf.entities.Servico;
import springthymeleaf.entities.StatusOrdemServico;
import springthymeleaf.entities.Tecnico;

public class RequisicaoOrdemServico {

    private Long id;
    private StatusOrdemServico statusOrdemServico;
    private Cliente cliente;
    private Produto produto;
    private Servico servico;
    private Tecnico tecnico;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date dataFinal;

    private String descricao;
    private String defeito;
    private String observacoes;
    private String laudotecnico;
    private int garantia;

    public OrdemServico toOS() {

        OrdemServico ordemServico = new OrdemServico();
        ordemServico.setCliente(this.cliente);
        ordemServico.setStatusOrdemServico(this.statusOrdemServico);
        ordemServico.setDataFinal(this.dataFinal);
        ordemServico.setDescricao(this.descricao);
        ordemServico.setDefeito(this.defeito);
        ordemServico.setObservacoes(this.observacoes);
        ordemServico.setLaudotecnico(this.laudotecnico);
        ordemServico.setTecnico(this.tecnico);
        ordemServico.setGarantia(this.garantia);
        
        return ordemServico;
    }

    public OrdemServico toOrdemServico(OrdemServico ordemServico) {

        ordemServico.setStatusOrdemServico(this.statusOrdemServico);
        ordemServico.setDataFinal(this.dataFinal);
        ordemServico.setDescricao(this.descricao);
        ordemServico.setDefeito(this.defeito);
        ordemServico.setObservacoes(this.observacoes);
        ordemServico.setLaudotecnico(this.laudotecnico);
        ordemServico.setGarantia(this.garantia);
        
        
        return ordemServico;
    }

    public void fromOS(OrdemServico ordemServico) {
        this.cliente = ordemServico.getCliente();
        this.statusOrdemServico = ordemServico.getStatusOrdemServico();
        this.defeito = ordemServico.getDefeito();
        this.dataFinal = ordemServico.getDataFinal();
        this.descricao = ordemServico.getDescricao();
        this.observacoes = ordemServico.getObservacoes();
        this.laudotecnico = ordemServico.getLaudotecnico();
        this.tecnico = ordemServico.getTecnico();
        this.garantia = ordemServico.getGarantia();
        
    }
    
    public OrdemServico fromOSProdutoAdd(OrdemServico ordemServico) {
        this.cliente = ordemServico.getCliente();
        this.statusOrdemServico = ordemServico.getStatusOrdemServico();
        this.defeito = ordemServico.getDefeito();
        this.dataFinal = ordemServico.getDataFinal();
        this.descricao = ordemServico.getDescricao();
        this.observacoes = ordemServico.getObservacoes();
        this.laudotecnico = ordemServico.getLaudotecnico();
        this.tecnico = ordemServico.getTecnico();
        this.garantia = ordemServico.getGarantia();
        return ordemServico;
        
    }

    

    public String getDescricao() {
        return descricao;
    }



    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }



    public String getDefeito() {
        return defeito;
    }



    public void setDefeito(String defeito) {
        this.defeito = defeito;
    }



    public String getObservacoes() {
        return observacoes;
    }



    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }



    public String getLaudotecnico() {
        return laudotecnico;
    }



    public void setLaudotecnico(String laudotecnico) {
        this.laudotecnico = laudotecnico;
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

    public Tecnico getTecnico() {
        return tecnico;
    }

    public void setTecnico(Tecnico tecnico) {
        this.tecnico = tecnico;
    }

    public int getGarantia() {
        return garantia;
    }

    public void setGarantia(int garantia) {
        this.garantia = garantia;
    }

}
