package springthymeleaf.dto;

import lombok.Getter;
import lombok.Setter;
import springthymeleaf.entities.OrdemServico;
import springthymeleaf.entities.Servico;
import springthymeleaf.entities.ServicoOrdem;

@Getter
@Setter
public class RequisicaoServicoOrdem {
    
    private Long id;

    private Servico servico;

    private OrdemServico ordemServico;

    public ServicoOrdem toServicoOrdem(){

        ServicoOrdem servicoOrdem = new ServicoOrdem();
        servicoOrdem.setServico(this.servico);
        servicoOrdem.setOrdemServico(this.ordemServico);

        return servicoOrdem;
    }

}
