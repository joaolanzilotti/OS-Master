package springthymeleaf.dto;

import springthymeleaf.entities.OrdemServico;
import springthymeleaf.entities.Produto;
import springthymeleaf.entities.ProdutoOrdem;

public class RequisicaoProdutoOrdem {
    
    private Long id;

    private Produto produto;

    private OrdemServico ordemServico;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public OrdemServico getOrdemServico() {
        return ordemServico;
    }

    public void setOrdemServico(OrdemServico ordemServico) {
        this.ordemServico = ordemServico;
    }

    public ProdutoOrdem toProdutoOrdem() {

        ProdutoOrdem produtoOrdem = new ProdutoOrdem();
        produtoOrdem.setProduto(this.produto);
        produtoOrdem.setOrdemServico(this.ordemServico);

        return produtoOrdem;
    }

}
