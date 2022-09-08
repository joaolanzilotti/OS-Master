
package springthymeleaf.dto;

import springthymeleaf.entities.Produto;


public class RequisicaoProduto {
    
    private Long id;
    private String nome;
    private String descricao;
    private int quantidade;
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

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
    
    public Produto toProduto() {
        Produto produto = new Produto();
        produto.setNome(this.nome);
        produto.setDescricao(this.descricao);
        produto.setQuantidade(this.quantidade);
        produto.setValor(this.valor);  
        return produto;
    }

    public Produto toProduto(Produto produto) {

        produto.setNome(this.nome);
        produto.setDescricao(this.descricao);
        produto.setQuantidade(this.quantidade);
        produto.setValor(this.valor);

        return produto;
    }

    public void fromProduto(Produto produto) {
        this.nome = produto.getNome();
        this.descricao = produto.getDescricao();
        this.quantidade = produto.getQuantidade();
        this.valor = produto.getValor();
    }

    @Override
    public String toString() {
        return "RequisicaoProduto{" + "id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", quantidade=" + quantidade + ", valor=" + valor + '}';
    }

    
    
    

    
}
