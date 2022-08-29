
package springthymeleaf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import springthymeleaf.entities.Cliente;
import springthymeleaf.repositories.ClienteRepository;

@Controller
public class CadastroController {
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    @GetMapping("/cadastro")
    public String paginaCadastro(){
        return "cadastro";
    }
    
    //Estou Criando um metodo que vai receber minha classe entidade Tasks
    @PostMapping("/cadastro")
    public void cadastro(Cliente cliente){
    
        System.out.println("O Nome da Tarefa é: " + cliente.getNome());
         System.out.println("O Nome da Tarefa é: " + cliente.getNascimento());
         try{
          clienteRepository.save(cliente);
         }catch(javax.validation.ConstraintViolationException e1){System.out.println("CPF INVALIDO");}
        
    }
    
}
