package springthymeleaf.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springthymeleaf.dto.RequisicaoProduto;
import springthymeleaf.entities.Produto;
import springthymeleaf.repositories.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> findAllProdutos(){
        return produtoRepository.findAll();
    }

    public Optional<Produto> findProdutoById(Long id){
        return produtoRepository.findById(id);
    }

    public void saveProduto(Produto produto){
        produtoRepository.save(produto);
    }

    public void deleteProduto(Long id){
        produtoRepository.deleteById(id);
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
