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

import springthymeleaf.dto.RequisicaoProduto;
import springthymeleaf.entities.Produto;
import springthymeleaf.services.ProdutoService;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

    private boolean produtoCadastrado = false;
    private boolean produtoRemovido = false;
    private boolean produtoEditado = false;
    private boolean erroCodigoProdutoJaCadastrado = false;
    private boolean erroProdutoNaoEncontrado = false;

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("")
    public ModelAndView pageHomeProduto() {
        List<Produto> produtos = this.produtoService.findAllProdutos();

        ModelAndView mv = new ModelAndView("produtos/index");
        mv.addObject("produtos", produtos);
        mv.addObject("produtoEditado", produtoEditado);
        mv.addObject("produtoRemovido", produtoRemovido);
        mv.addObject("produtoCadastrado", produtoCadastrado);
        mv.addObject("erroProdutoNaoEncontrado", erroProdutoNaoEncontrado);
        produtoEditado = false;
        produtoRemovido = false;
        produtoCadastrado = false;
        erroProdutoNaoEncontrado = false;
        return mv;
    }

    @GetMapping("/new")
    public ModelAndView pageRegisterProduto(RequisicaoProduto requisicao) {

        ModelAndView mv = new ModelAndView("produtos/new");
        return mv;
    }

    @PostMapping("")
    public ModelAndView registerProduto(@Valid RequisicaoProduto requisicaoProduto, BindingResult erro) {
        // Igualando os dados da classe cliente com a classe requisicao, para proteger
        // os dados!
        Produto produto = requisicaoProduto.toProduto();

        if (erro.hasErrors()) {
            System.out.println(erro);
            ModelAndView mv = new ModelAndView("produtos/new");
            return mv;
        }

        if (produtoService.codigoProdutoCadastrado(requisicaoProduto)) {
            erroCodigoProdutoJaCadastrado = true;
            ModelAndView mv = new ModelAndView("produtos/new");
            mv.addObject("erroCodigoProdutoJaCadastrado", erroCodigoProdutoJaCadastrado);
            erroCodigoProdutoJaCadastrado = false;
            return mv;
        }
        this.produtoService.saveProduto(produto);
        produtoCadastrado = true;
        return new ModelAndView("redirect:/produtos");

    }

    @GetMapping("/{id}/edit")
    public ModelAndView pageEditProduto(@PathVariable Long id, RequisicaoProduto requisicaoProduto) {
        Optional<Produto> optional = this.produtoService.findProdutoById(id);

        if (optional.isPresent()) {
            Produto produto = optional.get();
            //aqui eu carrego todos valores de cliente no meu fromClientes, Ja feito um metodo para isso na classe DTO!
            requisicaoProduto.fromProduto(produto);

            ModelAndView mv = new ModelAndView("produtos/edit");
            //Adicionando um objeto para conseguir utilizado dentro do html, com o valor cliente.getId()
            mv.addObject("produtosId", produto.getId());

            return mv;

        } else {
            System.out.println("Produto Não Encontrado!");
            erroProdutoNaoEncontrado = true;
            return new ModelAndView("redirect:/produtos");
        }
    }

    @PostMapping("/{id}")
    public ModelAndView editProduto(@PathVariable Long id, @Valid RequisicaoProduto requisicaoProduto, BindingResult erro) {
        if (erro.hasErrors()) {
            ModelAndView mv = new ModelAndView("produtos/edit");
            mv.addObject("produtosId", id);
            System.out.println(erro);
            return mv;
        } else {

            Optional<Produto> optional = this.produtoService.findProdutoById(id);

            if (optional.isPresent()) {
                Produto produto = requisicaoProduto.toProduto(optional.get());

                this.produtoService.saveProduto(produto);
                produtoEditado = true;

                return new ModelAndView("redirect:/produtos");

            } else {
                System.out.println("Produto Não Encontrado!");
                erroProdutoNaoEncontrado = true;
                return new ModelAndView("redirect:/produtos");
            }
        }
    }

    @GetMapping("/{id}/delete")
    public String deleteProduto(@PathVariable Long id) {
        try {
            this.produtoService.deleteProduto(id);
            produtoRemovido = true;
            return "redirect:/produtos";
        } catch (EmptyResultDataAccessException e) {
            System.out.println("Produto Nao Encontrado");
            erroProdutoNaoEncontrado = true;
            return "redirect:/produtos";

        }
    }

}
