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
import springthymeleaf.dto.RequisicaoServico;
import springthymeleaf.entities.Servico;
import springthymeleaf.repositories.ServicoRepository;

@Controller
@RequestMapping("/servicos")
public class ServicoController {

    @Autowired
    private ServicoRepository servicoRepository;

    @GetMapping("")
    public ModelAndView paginaClientes() {
        List<Servico> servicos = servicoRepository.findAll();

        ModelAndView mv = new ModelAndView("/servicos/index");
        mv.addObject("servicos", servicos);

        return mv;
    }

    @GetMapping("/new")
    public ModelAndView paginaCriarServico(RequisicaoServico r) {
        ModelAndView mv = new ModelAndView("servicos/new");
        return mv;
    }

    @PostMapping("")
    public ModelAndView cadastrarServico(@Valid RequisicaoServico requisicao, BindingResult erro) {
        Servico servicos = requisicao.toServicos();

        if (erro.hasErrors()) {
            System.out.println("ERRO");
            ModelAndView mv = new ModelAndView("servicos/new");
            return mv;
        } else {
            servicoRepository.save(servicos);
            ModelAndView mv = new ModelAndView("redirect:/servicos");
            return mv;
        }
    }
    
    @GetMapping("/{id}/edit")
    public ModelAndView editar(@PathVariable Long id, RequisicaoServico requisicao) {
        Optional<Servico> optional = this.servicoRepository.findById(id);

        if (optional.isPresent()) {
            Servico servico = optional.get();
            //aqui eu carrego todos valores de cliente no meu fromClientes, Ja feito um metodo para isso na classe DTO!
            requisicao.fromServicos(servico);

            ModelAndView mv = new ModelAndView("servicos/edit");
            //Adicionando um objeto para conseguir utilizado dentro do html, com o valor cliente.getId()
            mv.addObject("servicoId", servico.getId());

            return mv;

        } else {
            System.out.println("Cliente Não Encontrado!");
            return new ModelAndView("redirect:/servicos");
        }
    }
    
    @PostMapping("/{id}")
    public ModelAndView update(@PathVariable Long id, @Valid RequisicaoServico requisicao, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            ModelAndView mv = new ModelAndView("servicos/edit");
            mv.addObject("servicosId", id);
            System.out.println(bindingResult);
            return mv;
        }
        else{

            Optional<Servico> optional = servicoRepository.findById(id);
            
            if(optional.isPresent()){
                System.out.println("chegou AQUI");
                Servico servico = requisicao.toServico(optional.get());

                System.out.println(servico);
                this.servicoRepository.save(servico);

                return new ModelAndView("redirect:/servicos");

            }else{
                System.out.println("Cliente Não Encontrado!");
                return new ModelAndView("redirect:/servicos");
            }
        }
    }
    
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id){
        try{
        this.servicoRepository.deleteById(id);
        return "redirect:/servicos";
        }
        catch(EmptyResultDataAccessException e){
            System.out.println("Cliente Nao Encontrado");
            return "redirect:/servicos";

        }       
    }

    

}
