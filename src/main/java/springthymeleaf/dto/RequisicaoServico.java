
package springthymeleaf.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import springthymeleaf.entities.Servico;


public class RequisicaoServico {
    
    @NotBlank
    @NotNull
    private String nome;
    private String descricao;
    private Double valor;

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
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
    
    // Aqui estou Dizendo que Meus Atributos Dessa Classe DTO , Esta Sendo setados
    // pela classe Entidade!
    public Servico toServicos() {
        Servico servicos = new Servico();
        servicos.setNome(this.nome);
        servicos.setDescricao(this.descricao);
        servicos.setValor(this.valor);
        return servicos;
    }

    public Servico toServico(Servico servico) {

        servico.setNome(this.nome);
        servico.setDescricao(this.descricao);
        servico.setValor(this.valor);

        return servico;
    }

    public void fromServicos(Servico servico) {
        this.nome = servico.getNome();
        this.descricao = servico.getDescricao();
        this.valor = servico.getValor();
    }
    
}
