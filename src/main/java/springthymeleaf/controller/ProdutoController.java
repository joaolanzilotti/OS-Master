package springthymeleaf.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import springthymeleaf.dto.RequisicaoProduto;
import springthymeleaf.entities.Produto;
import springthymeleaf.repositories.ProdutoRepository;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping("")
    public ModelAndView paginaInicialProduto() {
        List<Produto> produtos = produtoRepository.findAll();

        ModelAndView mv = new ModelAndView("/produtos/index");
        mv.addObject("produtos", produtos);
        return mv;
    }
    
    @GetMapping("/new")
    public String paginaCadastro(RequisicaoProduto requisicao) {

        return "produtos/new";
    }
    
    @PostMapping("")
    public ModelAndView cadastro(@Valid RequisicaoProduto requisicao, BindingResult erro) {
        // Igualando os dados da classe cliente com a classe requisicao, para proteger
        // os dados!
        Produto produto = requisicao.toProduto();

        if (erro.hasErrors()) {
            ModelAndView mv = new ModelAndView("produtos/new");
            return mv;

        } else {
            produtoRepository.save(produto);
            return new ModelAndView("redirect:/produtos/");
        }
    }

}
