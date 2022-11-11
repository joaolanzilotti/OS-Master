package springthymeleaf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import springthymeleaf.entities.Cliente;
import springthymeleaf.entities.OrdemServico;
import springthymeleaf.entities.Produto;
import springthymeleaf.entities.Servico;
import springthymeleaf.repositories.ClienteRepository;
import springthymeleaf.repositories.OrdemServicoRepository;
import springthymeleaf.repositories.ProdutoRepository;
import springthymeleaf.repositories.ServicoRepository;

@Controller
public class HomeController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ServicoRepository servicoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    @GetMapping("/inicio")
    public ModelAndView paginaInicio() {

        List<Cliente> clientes = clienteRepository.findAll();
        List<Servico> servicos = servicoRepository.findAll();
        List<Produto> produtos = produtoRepository.findAll();
        List<OrdemServico> ordemServicos = ordemServicoRepository.findAll();

        int contagemCliente = clientes.size();
        int contagemServico = servicos.size();
        int contagemProduto = produtos.size();
        int contagemOrdemServico = ordemServicos.size();

        ModelAndView mv = new ModelAndView("index");
        mv.addObject("contagemCliente", contagemCliente);
        mv.addObject("contagemServico", contagemServico);
        mv.addObject("contagemProduto", contagemProduto);
        mv.addObject("contagemOrdemServico", contagemOrdemServico);
        return mv;
    }
    
}
