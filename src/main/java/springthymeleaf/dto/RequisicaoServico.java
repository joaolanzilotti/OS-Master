
package springthymeleaf.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import springthymeleaf.entities.Servicos;


public class RequisicaoServico {
    
    @NotBlank
    @NotNull
    private String nome;
    
    private String descricao;

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
    public Servicos toServicos() {
        Servicos servicos = new Servicos();
        servicos.setNome(this.nome);
        servicos.setDescricao(this.descricao);
        return servicos;
    }
    
}
