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

        ModelAndView mv = new ModelAndView("/clientes/index");
        mv.addObject("clientes", clientes);

        return mv;
    }

    @GetMapping("/inicio")
    public ModelAndView paginaInicio() {
        ModelAndView mv = new ModelAndView("home");
        return mv;
    }

    //Para o th:field e o th:object funcionar tem que estanciar o minha classe DTO no meu @getMapping e também no @Post 
    @GetMapping("/new")
    public String paginaCadastro(RequisicaoCliente r) {

        return "clientes/new";
    }

    //Estou Criando um metodo que vai receber minha classe entidade Clientes
    //neste caso eu chamei no metodo minha classe RequisicaoCliente para proteger os dados! eu poderia chamar diretamente a classe Cliente
    @PostMapping("/clientes")
    public ModelAndView cadastro(@Valid RequisicaoCliente requisicao, BindingResult erro) {
        //Igualando os dados da classe cliente com a classe requisicao, para proteger os dados!
        Cliente cliente = requisicao.toCliente();

        if (erro.hasErrors()) {
            ModelAndView mv = new ModelAndView("clientes/new");
            return mv;

        } else {
            clienteRepository.save(cliente);
            return new ModelAndView("redirect:/clientes/" + cliente.getId());
        }       
    }

    //{id} -> criei uma variavel http que vai esperar um ID
    //@pathVariable -> Serve para transformar minha viriavel Long id na variavel do http
    //Optional -> Com o OPTIONAL ele informa que é opcional e não obrigatório, no meu caso é opcional ter o ID, mas caso não tenha não vai estourar um Erro!
    //Para Acessar uma variavel ou um valor pelo thymeleaf se usa o ${cliente.senha}
    @GetMapping("/clientes/{id}")
    public ModelAndView detalhes(@PathVariable Long id){
        Optional<Cliente> optional = this.clienteRepository.findById(id);

        if(optional.isPresent()){

            Cliente cliente = optional.get();
            ModelAndView mv = new ModelAndView("clientes/show");
            mv.addObject("cliente", cliente);
            return mv;

        }else{
            System.out.println("Cliente Não encontrado!" + id);
            return new ModelAndView("redirect:/clientes");
        }

    }
    

}
