
package springthymeleaf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import springthymeleaf.entities.Cliente;
import springthymeleaf.repositories.ClienteRepository;

@Controller
public class LoginController {
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    @GetMapping("/login")
    public String home(){
        return "login";
    }
    
    //Estou Criando um metodo que vai receber minha classe entidade Tasks
    @PostMapping("/login")
    public void logar(Cliente cliente){
    
//        System.out.println("O Nome da Tarefa é: " + cliente.getNome());
//         System.out.println("O Nome da Tarefa é: " + cliente.getNascimento());
//          clienteRepository.save(cliente);
        
    }
    
}
