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
    private ServicoService servicoService;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private OrdemServicoService ordemServicoService;

    @GetMapping("/inicio")
    public ModelAndView paginaInicio() {

        int contagemCliente = clienteService.findAllClientes().size();
        int contagemServico = servicoService.findAllServicos().size();
        int contagemProduto = produtoService.findAllProdutos().size();
        int contagemOrdemServico = ordemServicoService.findAllOrdemServico().size();

        ModelAndView mv = new ModelAndView("index");
        mv.addObject("contagemCliente", contagemCliente);
        mv.addObject("contagemServico", contagemServico);
        mv.addObject("contagemProduto", contagemProduto); 
        mv.addObject("contagemOrdemServico", contagemOrdemServico);
        return mv;
    }
    
}
