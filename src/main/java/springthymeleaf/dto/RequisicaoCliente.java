package springthymeleaf.dto;

//É uma classe DTO (DATA Transfer Object) , para evitar que campos sem explorados! - classe de tratamento e garantir a seguranca dos dados!

import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;
import springthymeleaf.entities.Cliente;

public class RequisicaoCliente {

    @NotBlank
    @NotNull
    private String nome;
    @NotBlank
    @NotNull
    private String email;
    @CPF
    private String cpf;
    private String senha;
    @NotBlank
    @NotNull
    private String sexo;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date nascimento;
    private String telefone;

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
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

    // Aqui estou Dizendo que Meus Atributos Dessa Classe DTO , Esta Sendo setados
    // pela classe Entidade!
    public Cliente toCliente() {
        Cliente cliente = new Cliente();
        cliente.setNome(this.nome);
        cliente.setEmail(this.email);
        cliente.setCpf(this.cpf);
        cliente.setNascimento(this.nascimento);
        cliente.setTelefone(this.telefone);
        cliente.setSexo(this.sexo);
        return cliente;
    }

    public Cliente toCliente(Cliente cliente) {

        cliente.setNome(this.nome);
        cliente.setEmail(this.email);
        cliente.setNascimento(this.nascimento);
        cliente.setSexo(this.sexo);

        return cliente;
    }

    // Aqui estou Recebendo os Valores Da minha Classe Entidade e passando para essa
    // classe DTO
    public void fromCliente(Cliente cliente) {
        this.nome = cliente.getNome();
        this.email = cliente.getEmail();
        this.nascimento = cliente.getNascimento();
        this.sexo = cliente.getSexo();
    }

    @Override
    public String toString() {
        return "RequisicaoCliente{" + "nome=" + nome + ", email=" + email + ", cpf=" + cpf + ", senha=" + senha
                + ", sexo=" + sexo + ", nascimento=" + nascimento + '}';
    }

}
