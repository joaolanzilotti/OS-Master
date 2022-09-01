package springthymeleaf.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import springthymeleaf.dto.RequisicaoCliente;
import springthymeleaf.entities.Cliente;
import springthymeleaf.repositories.ClienteRepository;

@Controller
public class CrudController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping("/clientes")
    public ModelAndView paginaClientes() {
        List<Cliente> clientes = clienteRepository.findAll();

        ModelAndView mv = new ModelAndView("clientes");
        mv.addObject("clientes", clientes);

        return mv;
    }

    @GetMapping("/inicio")
    public ModelAndView paginaInicio() {
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

    //Para o th:field e o th:object funcionar tem que estanciar o minha classe DTO no meu @getMapping e também no @Post 
    @GetMapping("/cadastro")
    public String paginaCadastro(RequisicaoCliente r) {

        return "cadastro";
    }

    //Estou Criando um metodo que vai receber minha classe entidade Clientes
    //neste caso eu chamei no metodo minha classe RequisicaoCliente para proteger os dados! eu poderia chamar diretamente a classe Cliente
    @PostMapping("/clientes") 
    public ModelAndView cadastro(@Valid RequisicaoCliente requisicao, BindingResult erro) {
        //Igualando os dados da classe cliente com a classe requisicao, para proteger os dados!
        Cliente cliente = requisicao.toCliente();

        if (erro.hasErrors()) {
            ModelAndView mv = new ModelAndView("/cadastro");
            return mv;

        } else {
            clienteRepository.save(cliente);
            return new ModelAndView("redirect:/clientes");
        }
    }

    //com o @PathVariable estou dizendo que o valor passado no meu parametro vai ser igual ao valor do id no meu GetMapping
    //Optional -> vai evitar algum erro de exception pois o id selecionado pode também nao existir , entao o optional nao deixa esse erro vir por o ID é opcional!
    @GetMapping("/clientes/{id}")
    public ModelAndView detalhes(@PathVariable Long id) {
        Optional<Cliente> optional = this.clienteRepository.findById(id);

        if (optional.isPresent()) {

            //eu usei optional.get pois eu ja chamei meu objeto cliente la!
            Cliente cliente = optional.get();
            
            ModelAndView mv = new ModelAndView("detalhes");
            mv.addObject("cliente", cliente);
            
            return mv;

        } else {

            System.out.println("ID DE CLIENTE NAO ENCONTRADO!");
            return new ModelAndView("redirect:/clientes");

        }

    }

}
