package springthymeleaf.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import springthymeleaf.entities.Cliente;
import springthymeleaf.repositories.ClienteRepository;

@Controller
public class CrudController {
    
    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping("/crud")
    public ModelAndView index() {       
        List<Cliente> clientes = clienteRepository.findAll();
        
        ModelAndView mv = new ModelAndView("crud/index");
        mv.addObject("clientes", clientes);
        return mv;
    }
    
//    @GetMapping("/cadastro")
//    public String cadastro(){
//    return "cadastro";
//    }
}
