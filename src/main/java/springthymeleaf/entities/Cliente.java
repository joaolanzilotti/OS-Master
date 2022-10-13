package springthymeleaf.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Cliente implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Email
    private String email;
    @CPF
    private String cpf;
    private String telefone;
    private String celular;
    private String sexo;
    private String senha;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date nascimento;
    @Temporal(TemporalType.DATE)
    private Date diacadastro = new Date();
    private String cep;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
    
    //Um para Muitos - Um Cliente Para Muitas Ordem de Servicos - usar o mappedby para mapear o cliente la da classe OrdemServico - o cascade = cascadeType.ALL é para quando for deletar um cliente ou um servico, conseguir deletar tranquilamente
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<OrdemServico> ordemServico;

    public Cliente() {
    }

    public Cliente(Long id, String nome, String email, String cpf, String telefone, String sexo, Date nascimento, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.telefone = telefone;
        this.sexo = sexo;
        this.nascimento = nascimento;
        this.senha = senha;
    }

    public Cliente(Long id, String nome, String email, String cpf, String telefone, String sexo, Date nascimento, List<OrdemServico> ordemServico, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.telefone = telefone;
        this.sexo = sexo;
        this.nascimento = nascimento;
        this.ordemServico = ordemServico;
        this.senha = senha;
    }  

    public Cliente(Long id, String nome, String email, String cpf, String telefone, String celular, String sexo,
            String senha, Date nascimento, Date diacadastro, String cep, String logradouro, String numero,
            String complemento, String bairro, String localidade, String uf, List<OrdemServico> ordemServico) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.telefone = telefone;
        this.celular = celular;
        this.sexo = sexo;
        this.senha = senha;
        this.nascimento = nascimento;
        this.diacadastro = diacadastro;
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.localidade = localidade;
        this.uf = uf;
        this.ordemServico = ordemServico;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final Cliente other = (Cliente) obj;
        return Objects.equals(this.id, other.id);
    }

   
}
