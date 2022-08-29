package springthymeleaf.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import springthymeleaf.dto.RequisicaoCliente;
import springthymeleaf.entities.Cliente;
import springthymeleaf.repositories.ClienteRepository;

@Controller
public class CadastroController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping("/cadastro")
    public String paginaCadastro() {
        return "cadastro";
    }

    //Estou Criando um metodo que vai receber minha classe entidade Clientes
    //neste caso eu chamei no metodo minha classe RequisicaoCliente para proteger os dados! eu poderia chamar diretamente a classe Cliente
    @PostMapping("/cadastro")
    public ModelAndView cadastro(@Valid RequisicaoCliente requisicao, BindingResult bidingResult) {
        //Igualando os dados da classe cliente com a classe requisicao, para proteger os dados!
        Cliente cliente = requisicao.toCliente();

        System.out.println(requisicao);

        if (bidingResult.hasErrors()) {
            System.out.println("CPF INVALIDO");
            ModelAndView mv = new ModelAndView("/cadastro");
            return mv;

        } else {
            clienteRepository.save(cliente);
            return new ModelAndView("redirect:/clientes");
        }
        

        //catch(javax.validation.ConstraintViolationException e1){System.out.println("CPF INVALIDO");}
    }

}
