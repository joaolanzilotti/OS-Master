
package springthymeleaf.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import springthymeleaf.entities.Cliente;
import springthymeleaf.repositories.ClienteRepository;

@Controller
public class LoginController {
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    @GetMapping("/login")
    public ModelAndView login(){
        ModelAndView mv = new ModelAndView("login"); // Nome do arquivo a ser carregado ou Renderizado , esse é o nome do arquivo .html
        return mv; //Aqui ele retorna o objeto mv, que renderiza o arquivo login.html
    }
    
    //Estou Criando um metodo que vai receber minha classe entidade Tasks
//    @PostMapping("/login")
//    public void logar(Cliente cliente){
//        List<Cliente> clientes = new ArrayList<>();
//    
//    clienteRepository.findAll();
        
//    }
    
}
