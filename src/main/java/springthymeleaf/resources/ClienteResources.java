package springthymeleaf.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import springthymeleaf.entities.Cliente;
import springthymeleaf.repositories.ClienteRepository;

@RestController
public class ClienteResources {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping("/listaClientes")
    public ResponseEntity<Iterable<Cliente>> buscarTodos(){
        Iterable<Cliente> listaCliente = clienteRepository.findAll();
        return ResponseEntity.ok().body(listaCliente);
    }
    
}
