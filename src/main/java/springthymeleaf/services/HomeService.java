package springthymeleaf.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springthymeleaf.entities.Cliente;
import springthymeleaf.entities.OrdemServico;
import springthymeleaf.entities.Produto;
import springthymeleaf.entities.Servico;
import springthymeleaf.repositories.ClienteRepository;
import springthymeleaf.repositories.OrdemServicoRepository;
import springthymeleaf.repositories.ProdutoRepository;
import springthymeleaf.repositories.ServicoRepository;
@Service
public class HomeService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ServicoRepository servicoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    public List<Cliente> findAllClientes(){
        return this.clienteRepository.findAll();
    }

    public List<Servico> findAllServicos(){
        return this.servicoRepository.findAll();
    }

    public List<Produto> findAllProdutos(){
        return this.produtoRepository.findAll();
    }

    public List<OrdemServico> findAllOrdensServico(){
        return this.ordemServicoRepository.findAll();
    }
    
}
