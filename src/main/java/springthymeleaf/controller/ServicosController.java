package springthymeleaf.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import springthymeleaf.dto.RequisicaoServico;
import springthymeleaf.entities.Servicos;
import springthymeleaf.repositories.ServicosRepository;

@Controller
@RequestMapping("/servicos")
public class ServicosController {

    @Autowired
    private ServicosRepository servicosRepository;

    @GetMapping("")
    public ModelAndView paginaServicos() {
        ModelAndView mv = new ModelAndView("servicos/index");
        return mv;
    }

    @GetMapping("/new")
    public ModelAndView paginaCriarServico(RequisicaoServico r) {
        ModelAndView mv = new ModelAndView("servicos/new");
        return mv;
    }

    @PostMapping("")
    public ModelAndView cadastrarServico(@Valid RequisicaoServico requisicao, BindingResult erro) {
        Servicos servicos = requisicao.toServicos();

        if (erro.hasErrors()) {
            System.out.println("ERRO");
            ModelAndView mv = new ModelAndView("servicos/new");
            return mv;
        } else {
            servicosRepository.save(servicos);
            ModelAndView mv = new ModelAndView("redirect:/servicos/");
            return mv;
        }
    }

}
