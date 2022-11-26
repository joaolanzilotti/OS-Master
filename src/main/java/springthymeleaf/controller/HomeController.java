package springthymeleaf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import springthymeleaf.services.HomeService;

@Controller
public class HomeController {

    @Autowired
    private HomeService homeService;

    @GetMapping("/inicio")
    public ModelAndView paginaInicio() {

        int contagemCliente = homeService.findAllClientes().size();
        int contagemServico = homeService.findAllServicos().size();
        int contagemProduto = homeService.findAllProdutos().size();
        int contagemOrdemServico = homeService.findAllOrdensServico().size();

        ModelAndView mv = new ModelAndView("index");
        mv.addObject("contagemCliente", contagemCliente);
        mv.addObject("contagemServico", contagemServico);
        mv.addObject("contagemProduto", contagemProduto);
        mv.addObject("contagemOrdemServico", contagemOrdemServico);
        return mv;
    }
    
}
