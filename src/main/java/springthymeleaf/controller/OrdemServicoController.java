package springthymeleaf.controller;

import java.util.List;
import java.util.Optional;

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
import springthymeleaf.entities.StatusOrdemServico;
import springthymeleaf.entities.Tecnico;
import springthymeleaf.repositories.ClienteRepository;
import springthymeleaf.repositories.OrdemServicoRepository;
import springthymeleaf.repositories.TecnicoRepository;

@Controller
@RequestMapping("/ordemservico")
public class OrdemServicoController {

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;


    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping("")
    public ModelAndView paginaInicialOS() {
        List<OrdemServico> ordemServico = ordemServicoRepository.findAll();

        ModelAndView mv = new ModelAndView("ordemservico/index");
        mv.addObject("ordemServico", ordemServico);

        return mv;
    }

    
    @GetMapping("/new")
    public ModelAndView paginaCadastro(RequisicaoOrdemServico requisicao) {
        List<Cliente> clientes = clienteRepository.findAll();
        List<Tecnico> tecnicos = tecnicoRepository.findAll();


        ModelAndView mv = new ModelAndView("ordemservico/new");
        mv.addObject("statusOrdemServico", StatusOrdemServico.values());
        mv.addObject("clientes", clientes);
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

    @GetMapping("/{id}/edit")
    public ModelAndView editar(@PathVariable Long id, RequisicaoOrdemServico requisicao){
        Optional<OrdemServico> optional = this.ordemServicoRepository.findById(id);

        if(optional.isPresent()){
            OrdemServico ordemServico = optional.get();
            requisicao.fromOS(ordemServico);
            ModelAndView mv = new ModelAndView("ordemservico/edit");
            mv.addObject("ordemServicoId", ordemServico.getId());
            mv.addObject("statusOrdemServico", StatusOrdemServico.values());
            mv.addObject("statusOrdemServicoSelecionado", requisicao.getStatusOrdemServico().toString());
            return mv;
        }else{
            System.out.println("Ordem de Serviço Não Encontrada");
            return new ModelAndView("redirect:/ordemservico");
        }
        
    }

    @PostMapping("/{id}")
    public ModelAndView update(@PathVariable Long id, @Valid RequisicaoOrdemServico requisicao, BindingResult erro){

        if(erro.hasErrors()){
        ModelAndView mv = new ModelAndView("ordemservico/edit");
        mv.addObject("ordemServicoId", id);
        System.out.println(erro);
        return mv;
    }else{
        Optional<OrdemServico> optional = ordemServicoRepository.findById(id);
        if (optional.isPresent()) {
            OrdemServico ordemServico = requisicao.toOrdemServico(optional.get());
            System.out.println(ordemServico);
            this.ordemServicoRepository.save(ordemServico);
            return new ModelAndView("redirect:/ordemservico");
        }else{
            System.out.println("OS Não Encontrada!");
            return new ModelAndView("redirect:/ordemservico");
        }
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
