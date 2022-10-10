package springthymeleaf.dto;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;
import springthymeleaf.entities.Cliente;
import springthymeleaf.entities.OrdemServico;
import springthymeleaf.entities.Produto;
import springthymeleaf.entities.Servico;
import springthymeleaf.entities.StatusOrdemServico;
import springthymeleaf.entities.Tecnico;
@Getter
@Setter
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

}
