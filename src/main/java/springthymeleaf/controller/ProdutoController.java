package springthymeleaf.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Pageable;
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
    public ModelAndView paginaInicialProduto(Pageable pageable) {
        Iterable<Produto> produtos = produtoRepository.findAll(pageable);

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
    public ModelAndView cadastro(@Valid RequisicaoProduto requisicao, BindingResult erro) {
        // Igualando os dados da classe cliente com a classe requisicao, para proteger
        // os dados!
        Produto produto = requisicao.toProduto();

        if (erro.hasErrors()) {
            System.out.println("AQIUIIIIIIIIIIII");
            System.out.println(erro);
            ModelAndView mv = new ModelAndView("produtos/new");
            return mv;

        } else {
            System.out.println("AQIUIdsdsIIIIIIIIIII");
            produtoRepository.save(produto);
            return new ModelAndView("redirect:/produtos?page=0&size=10");
        }
    }

    @GetMapping("/{id}/edit")
    public ModelAndView editar(@PathVariable Long id, RequisicaoProduto requisicao) {
        Optional<Produto> optional = this.produtoRepository.findById(id);

        if (optional.isPresent()) {
            Produto produto = optional.get();
            //aqui eu carrego todos valores de cliente no meu fromClientes, Ja feito um metodo para isso na classe DTO!
            requisicao.fromProduto(produto);

            ModelAndView mv = new ModelAndView("produtos/edit");
            //Adicionando um objeto para conseguir utilizado dentro do html, com o valor cliente.getId()
            mv.addObject("produtoId", produto.getId());

            return mv;

        } else {
            System.out.println("Cliente Não Encontrado!");
            return new ModelAndView("redirect:/produtos?page=0&size=10");
        }
    }

    @PostMapping("/{id}/edit")
    public ModelAndView update(@PathVariable Long id, @Valid RequisicaoProduto requisicao, BindingResult erro){
        if(erro.hasErrors()){
            ModelAndView mv = new ModelAndView("produtos/edit");
            mv.addObject("produtosId", id);
            System.out.println(erro);
            return mv;
        }
        else{

            Optional<Produto> optional = produtoRepository.findById(id);
            
            if(optional.isPresent()){
                System.out.println("chegou AQUI");
                Produto produto = requisicao.toProduto(optional.get());

                System.out.println(produto);
                this.produtoRepository.save(produto);

                return new ModelAndView("redirect:/produtos/" + produto.getId());

            }else{
                System.out.println("Cliente Não Encontrado!");
                return new ModelAndView("redirect:/produtos?page=0&size=10");
            }
        }
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id){
        try{
        this.produtoRepository.deleteById(id);
        return "redirect:/produtos?page=0&size=10";
        }
        catch(EmptyResultDataAccessException e){
            System.out.println("Cliente Nao Encontrado");
            return "redirect:/produtos?page=0&size=10";

        }       
    }

}