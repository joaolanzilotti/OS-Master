package springthymeleaf.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import springthymeleaf.dto.RequisicaoOrdemServico;
import springthymeleaf.entities.Cliente;
import springthymeleaf.entities.OrdemServico;
import springthymeleaf.entities.Produto;
import springthymeleaf.entities.Servico;
import springthymeleaf.entities.StatusOrdemServico;
import springthymeleaf.entities.Tecnico;
import springthymeleaf.repositories.ClienteRepository;
import springthymeleaf.repositories.OrdemServicoRepository;
import springthymeleaf.repositories.ProdutoRepository;
import springthymeleaf.repositories.ServicoRepository;
import springthymeleaf.repositories.TecnicoRepository;

@Controller
@RequestMapping("/ordemservico")
public class OrdemServicoController {

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ServicoRepository servicoRepository;

    @Autowired
    private TecnicoRepository tecnicoRepository;

    @GetMapping("")
    public ModelAndView paginaInicialOS() {
        Iterable<OrdemServico> ordemServico = ordemServicoRepository.findAll();

        ModelAndView mv = new ModelAndView("ordemservico/index");
        mv.addObject("ordemServico", ordemServico);

        return mv;
    }

    
    @GetMapping("/new")
    public ModelAndView paginaCadastro(RequisicaoOrdemServico requisicao) {
        Iterable<Cliente> clientes = clienteRepository.findAll();
        Iterable<Produto> produtos = produtoRepository.findAll();
        Iterable<Servico> servicos = servicoRepository.findAll();
        List<Tecnico> tecnicos = tecnicoRepository.findAll();

        ModelAndView mv = new ModelAndView("ordemservico/new");
        mv.addObject("statusOrdemServico", StatusOrdemServico.values());
        mv.addObject("clientes", clientes);
        mv.addObject("produtos", produtos);
        mv.addObject("servicos", servicos);
        mv.addObject("tecnicos", tecnicos);
        return mv;

    }

    @PostMapping("")
    public ModelAndView cadastro(@Valid RequisicaoOrdemServico requisicao, BindingResult erro) {
        
        OrdemServico ordemServico = requisicao.toOS();

        if (erro.hasErrors()) {
            ModelAndView mv = new ModelAndView("ordemservico/new");
            System.out.println(erro);
            return mv;

        } else {
            ordemServicoRepository.save(ordemServico);
            return new ModelAndView("redirect:/ordemservico");
        }

    }
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        try {
            this.ordemServicoRepository.deleteById(id);
            return "redirect:/ordemservico";
        } catch (EmptyResultDataAccessException e) {
            System.out.println("Os Nao Encontrada");
            return "redirect:/ordemservico";

        }
    }
    
}
