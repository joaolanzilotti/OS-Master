package springthymeleaf.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springthymeleaf.dto.RequisicaoCliente;
import springthymeleaf.entities.Cliente;
import springthymeleaf.repositories.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> findAllClientes() {
        return this.clienteRepository.findAll();
    }

    public Optional<Cliente> findClienteById(Long id){
        return this.clienteRepository.findById(id);
    }

    public void saveCliente(Cliente cliente){
        this.clienteRepository.save(cliente);
    }

    public void deleteCliente(Long id){
        this.clienteRepository.deleteById(id);
    }

    public boolean isVerificadorCpfCadastrado(@Valid RequisicaoCliente requisicao) {
        boolean isValid = false;
        Cliente cliente = requisicao.toCliente();
        List<Cliente> listaCliente = clienteRepository.findAll();
        for (Cliente clientes : listaCliente) {
            if (clientes.getCpf().equals(cliente.getCpf())) {
                isValid = true;
            }

        }
        return isValid;
    }

    public boolean isVerificadorEmailCadastrado(@Valid RequisicaoCliente requisicaoCliente) {
        boolean isValid = false;
        Cliente cliente = requisicaoCliente.toCliente();
        List<Cliente> listaCliente = clienteRepository.findAll();
        for (Cliente clientes : listaCliente) {
            if (clientes.getEmail().equals(cliente.getEmail())) {
                isValid = true;
            }

        }
        return isValid;
    }

    public boolean isVerificadorCampoVazio(@Valid RequisicaoCliente requisicaoCliente) {
        boolean isValid = false;
        if (requisicaoCliente.getNome().equals("")
                || requisicaoCliente.getEmail().equals("")
                || requisicaoCliente.getSenha().equals("")
                || requisicaoCliente.getCpf().equals("")
                || requisicaoCliente.getTelefone().equals("")
                || requisicaoCliente.getSexo().equals("")
                || requisicaoCliente.getNascimento().equals("")
                || requisicaoCliente.getCelular().equals("")
                || requisicaoCliente.getBairro().equals("")
                || requisicaoCliente.getCep().equals("")
                || requisicaoCliente.getLocalidade().equals("")
                || requisicaoCliente.getLogradouro().equals("")
                || requisicaoCliente.getNumero().equals("")
                || requisicaoCliente.getUf().equals("")) {
            isValid = true;
        }
        return isValid;
    }

}
