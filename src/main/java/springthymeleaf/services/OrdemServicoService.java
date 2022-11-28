package springthymeleaf.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springthymeleaf.entities.OrdemServico;
import springthymeleaf.repositories.ClienteRepository;
import springthymeleaf.repositories.OrdemServicoRepository;
import springthymeleaf.repositories.ProdutoOrdemRepository;
import springthymeleaf.repositories.ProdutoRepository;
import springthymeleaf.repositories.ServicoOrdemRepository;
import springthymeleaf.repositories.ServicoRepository;
import springthymeleaf.repositories.StatusOrdemServicoRepository;
import springthymeleaf.repositories.TecnicoRepository;

@Service
public class OrdemServicoService {

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    @Autowired
    private StatusOrdemServicoRepository statusOrdemServicoRepository;

    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ServicoRepository servicoRepository;

    @Autowired
    private ProdutoOrdemRepository produtoOrdemRepository;

    @Autowired
    private ServicoOrdemRepository servicoOrdemRepository;

    public List<OrdemServico> findAllOrdemServico(){
        return this.ordemServicoRepository.findAll(); 
    }

    public void saveOrdemServico(OrdemServico ordemServico){
        this.ordemServicoRepository.save(ordemServico);
    }

    public Optional<OrdemServico> findOrdemServicoById(Long id){
        return this.ordemServicoRepository.findById(id);
    }

    public void deleteOrdemServico(Long id){
        this.ordemServicoRepository.deleteById(id);
    }

    
}
