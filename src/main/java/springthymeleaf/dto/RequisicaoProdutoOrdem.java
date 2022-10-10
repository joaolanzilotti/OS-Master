package springthymeleaf.dto;

import lombok.Getter;
import lombok.Setter;
import springthymeleaf.entities.OrdemServico;
import springthymeleaf.entities.Produto;
import springthymeleaf.entities.ProdutoOrdem;
@Getter
@Setter
public class RequisicaoProdutoOrdem {
    
    private Long id;

    private Produto produto;

    private OrdemServico ordemServico;


    public ProdutoOrdem toProdutoOrdem() {

        ProdutoOrdem produtoOrdem = new ProdutoOrdem();
        produtoOrdem.setProduto(this.produto);
        produtoOrdem.setOrdemServico(this.ordemServico);

        return produtoOrdem;
    }


}
