package springthymeleaf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import springthymeleaf.services.ClienteService;
import springthymeleaf.services.OrdemServicoService;
import springthymeleaf.services.ProdutoService;
import springthymeleaf.services.ServicoService;

@Controller
public class HomeController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private OrdemServicoService ordemServicoService;

    @GetMapping("/inicio")
    public ModelAndView paginaInicio() {

        int contagemCliente = clienteService.findAllClientes().size();
        int contagemOrderByStatusAberto = ordemServicoService.findOrderByStatusAberto().size();
        int contagemOrderByStatusAndamento = ordemServicoService.findOrderByStatusAndamento().size();
        int contagemOrderByStatusFinalizado = ordemServicoService.findOrderByStatusFinalizado().size();

        ModelAndView mv = new ModelAndView("index");
        mv.addObject("contagemCliente", contagemCliente);
        mv.addObject("contagemOrderByStatusAberto", contagemOrderByStatusAberto);
        mv.addObject("contagemOrderByStatusAndamento", contagemOrderByStatusAndamento);
        mv.addObject("contagemOrderByStatusFinalizado", contagemOrderByStatusFinalizado);
        return mv;
    }
    
}
