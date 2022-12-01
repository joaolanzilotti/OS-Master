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
import springthymeleaf.services.ServicoService;

@Controller
@RequestMapping("/servicos")
public class ServicoController {

    private boolean servicoCadastrado = false;
    private boolean servicoRemovido = false;
    private boolean servicoEditado = false;
    private boolean erroServicoNaoEncontrado = false;

    @Autowired
    private ServicoService servicoService;

    @GetMapping("")
    public ModelAndView pageServicos() {
        List<Servico> servicos = this.servicoService.findAllServicos();

        ModelAndView mv = new ModelAndView("servicos/index");
        mv.addObject("servicos", servicos);
        mv.addObject("servicoEditado", servicoEditado);
        mv.addObject("servicoRemovido", servicoRemovido);
        mv.addObject("servicoCadastrado", servicoCadastrado);
        mv.addObject("erroServicoNaoEncontrado", erroServicoNaoEncontrado);
        servicoEditado = false;
        servicoRemovido = false;
        servicoCadastrado= false;
        erroServicoNaoEncontrado = false;

        return mv;
    }

    @GetMapping("/new")
    public ModelAndView pageRegisterServicos(RequisicaoServico r) {
        ModelAndView mv = new ModelAndView("servicos/new");
        return mv;
    }

    @PostMapping("")
    public ModelAndView registerServicos(@Valid RequisicaoServico requisicao, BindingResult erro) {
        Servico servico = requisicao.toServicos();

        if (erro.hasErrors()) {
            ModelAndView mv = new ModelAndView("servicos/new");
            return mv;
        } else {
            this.servicoService.saveServico(servico);
            servicoCadastrado = true;
            ModelAndView mv = new ModelAndView("redirect:/servicos");
            return mv;
        }
    }
    
    @GetMapping("/{id}/edit")
    public ModelAndView pageEditServicos(@PathVariable Long id, RequisicaoServico requisicao) {
        Optional<Servico> optional = this.servicoService.findServicoById(id);

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
            erroServicoNaoEncontrado = true;
            return new ModelAndView("redirect:/servicos");
        }
    }
    
    @PostMapping("/{id}")
    public ModelAndView editServicos(@PathVariable Long id, @Valid RequisicaoServico requisicao, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            ModelAndView mv = new ModelAndView("servicos/edit");
            mv.addObject("servicosId", id);
            System.out.println(bindingResult);
            return mv;
        }
        else{

            Optional<Servico> optional = this.servicoService.findServicoById(id);
            
            if(optional.isPresent()){
                Servico servico = requisicao.toServico(optional.get());

                this.servicoService.saveServico(servico);
                servicoEditado = true;
                return new ModelAndView("redirect:/servicos");

            }else{
                System.out.println("Servico Não Encontrado!");
                erroServicoNaoEncontrado = true;
                return new ModelAndView("redirect:/servicos");
            }
        }
    }
    
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id){
        try{
        this.servicoService.deleteServicoById(id);
        servicoRemovido = true;
        return "redirect:/servicos";
        }
        catch(EmptyResultDataAccessException e){
            System.out.println("Cliente Nao Encontrado");
            erroServicoNaoEncontrado = true;
            return "redirect:/servicos";

        }       
    }

    

}
