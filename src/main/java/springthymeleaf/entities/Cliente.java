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
    private String senha;
    private String sexo;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date nascimento;
    @Temporal(TemporalType.DATE)
    private Date diacadastro = new Date();
    
    //Um para Muitos - Um Cliente Para Muitas Ordem de Servicos - usar o mappedby para mapear o cliente la da classe OrdemServico - o cascade = cascadeType.ALL é para quando for deletar um cliente ou um servico, conseguir deletar tranquilamente
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<OrdemServico> ordemServico;

    public Cliente() {
    }

    public Cliente(Long id, String nome, String email, String cpf, String senha, String sexo, Date nascimento) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.senha = senha;
        this.sexo = sexo;
        this.nascimento = nascimento;
    }

    public Cliente(Long id, String nome, String email, String cpf, String senha, String sexo, Date nascimento, List<OrdemServico> ordemServico) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.senha = senha;
        this.sexo = sexo;
        this.nascimento = nascimento;
        this.ordemServico = ordemServico;
    }
    
    

    public List<OrdemServico> getOrdemServico() {
        return ordemServico;
    }

    public void setOrdemServico(List<OrdemServico> ordemServico) {
        this.ordemServico = ordemServico;
    }
    
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public Date getHoraCadastro() {
        return diacadastro;
    }

    public void setHoraCadastro(Date diacadastro) {
        this.diacadastro = diacadastro;
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
