package springthymeleaf.dto;

//É uma classe DTO (DATA Transfer Object) , para evitar que campos sem explorados! - classe de tratamento e garantir a seguranca dos dados!

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import springthymeleaf.entities.Cliente;

public class RequisicaoCliente {

    @NotBlank
    @NotNull
    private String nome;
    @NotBlank
    @NotNull
    @Email
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
    private String celular;
    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
    private String numero;

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

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
        cliente.setCep(this.cep);
        cliente.setBairro(this.bairro);
        cliente.setComplemento(this.complemento);
        cliente.setLocalidade(this.localidade);
        cliente.setLogradouro(this.logradouro);
        cliente.setUf(this.uf);
        cliente.setNumero(this.numero);
        cliente.setCelular(this.celular);
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

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

}
