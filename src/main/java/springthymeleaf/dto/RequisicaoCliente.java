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

import lombok.Getter;
import lombok.Setter;
import springthymeleaf.entities.Cliente;
@Getter
@Setter
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
    @NotBlank
    @NotNull
    private String telefone;
    @NotBlank
    @NotNull
    private String celular;
    @NotBlank
    @NotNull
    private String cep;
    @NotBlank
    @NotNull
    private String logradouro;
    private String complemento;
    @NotBlank
    @NotNull
    private String bairro;
    private String localidade;
    @NotBlank
    @NotNull
    private String uf;
    @NotBlank
    @NotNull
    private String numero;

    // Aqui estou Dizendo que Meus Atributos Dessa Classe DTO , Esta Sendo setados
    // pela classe Entidade!
    public Cliente toCliente() {
        Cliente cliente = new Cliente();
        cliente.setNome(this.nome);
        cliente.setEmail(this.email);
        cliente.setCpf(this.cpf);
        cliente.setSenha(this.senha);
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
        cliente.setTelefone(this.telefone);
        cliente.setCelular(this.celular);
        cliente.setNascimento(this.nascimento);
        cliente.setSexo(this.sexo);
        cliente.setCep(this.cep);
        cliente.setLogradouro(this.logradouro);
        cliente.setNumero(this.numero);
        cliente.setComplemento(this.complemento);
        cliente.setBairro(this.bairro);
        cliente.setLocalidade(this.localidade);
        cliente.setUf(this.uf);

        return cliente;
    }

    // Aqui estou Recebendo os Valores Da minha Classe Entidade e passando para essa
    // classe DTO
    public void fromCliente(Cliente cliente) {
        this.nome = cliente.getNome();
        this.email = cliente.getEmail();
        this.telefone = cliente.getTelefone();
        this.celular = cliente.getCelular();
        this.nascimento = cliente.getNascimento();
        this.sexo = cliente.getSexo();
        this.cpf = cliente.getCpf();
        this.senha = cliente.getSenha();
        this.cep = cliente.getCep();
        this.logradouro = cliente.getLogradouro();
        this.numero = cliente.getNumero();
        this.complemento = cliente.getComplemento();
        this.bairro = cliente.getBairro();
        this.localidade = cliente.getLocalidade();
        this.uf = cliente.getUf();
    }

    @Override
    public String toString() {
        return "RequisicaoCliente [nome=" + nome + ", email=" + email + ", cpf=" + cpf + ", senha=" + senha + ", sexo="
                + sexo + ", nascimento=" + nascimento + ", telefone=" + telefone + ", celular=" + celular + ", cep="
                + cep + ", logradouro=" + logradouro + ", complemento=" + complemento + ", bairro=" + bairro
                + ", localidade=" + localidade + ", uf=" + uf + ", numero=" + numero + "]";
    }

}
