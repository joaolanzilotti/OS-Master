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
import springthymeleaf.repositories.ProdutoRepository;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping("")
    public ModelAndView paginaInicialProduto() {
        List<Produto> produtos = produtoRepository.findAll();

        ModelAndView mv = new ModelAndView("produtos/index");
        mv.addObject("produtos", produtos);
        return mv;
    }

    @GetMapping("/new")
    public ModelAndView paginaCadastro(RequisicaoProduto requisicao) {
        ModelAndView mv = new ModelAndView("produtos/new");
        return mv;
    }

    @PostMapping("")
    public ModelAndView cadastro(@Valid RequisicaoProduto requisicaoProduto, BindingResult erro) {
        // Igualando os dados da classe cliente com a classe requisicao, para proteger
        // os dados!
        Produto produto = requisicaoProduto.toProduto();

        if (erro.hasErrors()) {
            System.out.println(erro);
            ModelAndView mv = new ModelAndView("produtos/new");
            return mv;
        }

        if (codigoProdutoCadastrado(requisicaoProduto)) {
            ModelAndView mv = new ModelAndView("produtos/new");
            String erroCodigoProdutoJaCadastrado = "Codigo do Produto Já Cadastrado";
            mv.addObject("erroCodigoProdutoJaCadastrado", erroCodigoProdutoJaCadastrado);
            return mv;
            
        }

        produtoRepository.save(produto);
        return new ModelAndView("redirect:/produtos");

    }

    @GetMapping("/{id}/edit")
    public ModelAndView editar(@PathVariable Long id, RequisicaoProduto requisicaoProduto) {
        Optional<Produto> optional = this.produtoRepository.findById(id);

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
            return new ModelAndView("redirect:/produtos");
        }
    }

    @PostMapping("/{id}")
    public ModelAndView update(@PathVariable Long id, @Valid RequisicaoProduto requisicaoProduto, BindingResult erro) {
        if (erro.hasErrors()) {
            ModelAndView mv = new ModelAndView("produtos/edit");
            mv.addObject("produtosId", id);
            System.out.println(erro);
            return mv;
        } else {

            Optional<Produto> optional = produtoRepository.findById(id);

            if (optional.isPresent()) {
                Produto produto = requisicaoProduto.toProduto(optional.get());

                System.out.println(produto);
                this.produtoRepository.save(produto);

                return new ModelAndView("redirect:/produtos");

            } else {
                System.out.println("Cliente Não Encontrado!");
                return new ModelAndView("redirect:/produtos");
            }
        }
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        try {
            this.produtoRepository.deleteById(id);
            return "redirect:/produtos";
        } catch (EmptyResultDataAccessException e) {
            System.out.println("Cliente Nao Encontrado");
            return "redirect:/produtos";

        }
    }

    public boolean codigoProdutoCadastrado(@Valid RequisicaoProduto requisicaoProduto) {
        boolean isValid = false;
        List<Produto> listaProduto = produtoRepository.findAll();

        for (Produto produto : listaProduto) {

            if (produto.getCodigoProduto().equals(requisicaoProduto.getCodigoProduto())) {

                System.out.println("\n\n Codigo de Produto Já Cadastrado! \n\n");
                isValid = true;

            }

        }
        return isValid;
    }

}
