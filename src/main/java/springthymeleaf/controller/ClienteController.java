package springthymeleaf.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import springthymeleaf.dto.RequisicaoCliente;
import springthymeleaf.entities.Cliente;
import springthymeleaf.repositories.ClienteRepository;

//@requestMapping("\clientes") -> Ele vai Definir por padrào qual requisicao voce vai querer, assim evitando voce toda hora digitar /clientes
@Controller
@RequestMapping("/clientes")
public class ClienteController {

    

    @Autowired
    private ClienteRepository clienteRepository; 

    @GetMapping("")
    public ModelAndView paginaClientes(Pageable pageable) {
        
        Iterable<Cliente> clientes = clienteRepository.findAll(pageable);

        ModelAndView mv = new ModelAndView("/clientes/index");
        mv.addObject("clientes", clientes);

        return mv;
    }

    // Para o th:field e o th:object funcionar tem que estanciar o minha classe DTO
    // no meu @getMapping e também no @Post
    @GetMapping("/new")
    public String paginaCadastro(RequisicaoCliente r) {

        return "clientes/new";
    }

    // Estou Criando um metodo que vai receber minha classe entidade Clientes
    // neste caso eu chamei no metodo minha classe RequisicaoCliente para proteger
    // os dados! eu poderia chamar diretamente a classe Cliente
    @PostMapping("")
    public ModelAndView cadastro(@Valid RequisicaoCliente requisicao, BindingResult erro) {
        // Igualando os dados da classe cliente com a classe requisicao, para proteger
        // os dados!
        Cliente cliente = requisicao.toCliente();

        if (erro.hasErrors()) {
            ModelAndView mv = new ModelAndView("clientes/new");
            return mv;

        } else {
            clienteRepository.save(cliente);
            return new ModelAndView("redirect:/clientes/" + cliente.getId());
        }
    }

    // {id} -> criei uma variavel http que vai esperar um ID
    // @pathVariable -> Serve para transformar minha viriavel Long id na variavel do
    // http
    // Optional -> Com o OPTIONAL ele informa que é opcional e não obrigatório, no
    // meu caso é opcional ter o ID, mas caso não tenha não vai estourar um Erro!
    // Para Acessar uma variavel ou um valor pelo thymeleaf se usa o
    // ${cliente.senha}
    @GetMapping("/{id}")
    public ModelAndView detalhes(@PathVariable Long id) {
        Optional<Cliente> optional = this.clienteRepository.findById(id);

        if (optional.isPresent()) {

            Cliente cliente = optional.get();
            ModelAndView mv = new ModelAndView("clientes/show");
            mv.addObject("cliente", cliente);
            return mv;

        } else {
            System.out.println("Cliente Não encontrado!" + id);
            return new ModelAndView("redirect:/clientes?page=0&size=10");
        }

    }

    //estou Usando o Optional.get() para pegar o valor da busca por ID e passar para o cliente
    @GetMapping("/{id}/edit")
    public ModelAndView editar(@PathVariable Long id, RequisicaoCliente requisicao) {
        Optional<Cliente> optional = this.clienteRepository.findById(id);

        if (optional.isPresent()) {
            Cliente cliente = optional.get();
            //aqui eu carrego todos valores de cliente no meu fromClientes, Ja feito um metodo para isso na classe DTO!
            requisicao.fromCliente(cliente);

            ModelAndView mv = new ModelAndView("clientes/edit");
            //Adicionando um objeto para conseguir utilizado dentro do html, com o valor cliente.getId()
            mv.addObject("clienteId", cliente.getId());

            return mv;

        } else {
            System.out.println("Cliente Não Encontrado!");
            return new ModelAndView("redirect:/clientes?page=0&size=10");
        }
    }

    @PostMapping("/{id}")
    public ModelAndView update(@PathVariable Long id, @Valid RequisicaoCliente requisicao, BindingResult bindingResult) /*throws IOException*/ {
        //URL url = new URL("viacep.com.br/ws/"+requisicao.getCep()+"/json/");
        //URLConnection connection = url.openConnection();
        //InputStream is = connection.getInputStream();
        //BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
        if (bindingResult.hasErrors()) {
            ModelAndView mv = new ModelAndView("clientes/edit");
            mv.addObject("clienteId", id);
            System.out.println(bindingResult);
            return mv;
        } else {

            Optional<Cliente> optional = clienteRepository.findById(id);

            if (optional.isPresent()) {
                System.out.println("chegou AQUI");
                Cliente cliente = requisicao.toCliente(optional.get());

                System.out.println(cliente);
                this.clienteRepository.save(cliente);

                return new ModelAndView("redirect:/clientes/" + cliente.getId());

            } else {
                System.out.println("Cliente Não Encontrado!");
                return new ModelAndView("redirect:/clientes?page=0&size=10");
            }
        }
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        try {
            this.clienteRepository.deleteById(id);
            return "redirect:/clientes?page=0&size=10";
        } catch (EmptyResultDataAccessException e) {
            System.out.println("Cliente Nao Encontrado");
            return "redirect:/clientes?page=0&size=10";

        }
    }

}
