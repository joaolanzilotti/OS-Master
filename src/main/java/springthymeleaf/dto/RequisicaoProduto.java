
package springthymeleaf.dto;

import springthymeleaf.entities.Produto;


public class RequisicaoProduto {
    
    private Long id;
    private String nome;
    private String descricao;
    private int estoque;
    private Double valor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }
    
    public Produto toProduto() {
        Produto produto = new Produto();
        produto.setNome(this.nome);
        produto.setDescricao(this.descricao);
        produto.setEstoque(this.estoque);
        produto.setValor(this.valor);  
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
