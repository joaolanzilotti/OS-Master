package springthymeleaf.dto;

//É uma classe DTO (DATA Transfer Object) , para evitar que campos sem explorados! - classe de tratamento e garantir a seguranca dos dados!

import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;
import springthymeleaf.entities.Cliente;

public class RequisicaoCliente {

    private String nome;
    private String email;
    private String cpf;
    private String senha;
    private String sexo;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date nascimento;

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
    
    public Cliente toCliente(){
        Cliente cliente = new Cliente();
        cliente.setNome(this.nome);
        cliente.setEmail(this.email);
        cliente.setCpf(this.cpf);
        cliente.setNascimento(this.nascimento);
        cliente.setSenha(this.senha);
        cliente.setSexo(this.sexo);
        return cliente;
    }

    @Override
    public String toString() {
        return "RequisicaoCliente{" + "nome=" + nome + ", email=" + email + ", cpf=" + cpf + ", senha=" + senha + ", sexo=" + sexo + ", nascimento=" + nascimento + '}';
    }
    
    
    
    
    
    

}
