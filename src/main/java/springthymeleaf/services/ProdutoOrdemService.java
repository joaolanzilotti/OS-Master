package springthymeleaf.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springthymeleaf.entities.ProdutoOrdem;
import springthymeleaf.repositories.ProdutoOrdemRepository;

@Service
public class ProdutoOrdemService {

    @Autowired
    private ProdutoOrdemRepository produtoOrdemRepository;

    public void saveProdutoOrdem(ProdutoOrdem produtoOrdem) {
        produtoOrdemRepository.save(produtoOrdem);
    }
    
}
