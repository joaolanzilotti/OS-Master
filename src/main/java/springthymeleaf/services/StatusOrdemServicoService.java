package springthymeleaf.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springthymeleaf.entities.StatusOrdemServico;
import springthymeleaf.repositories.StatusOrdemServicoRepository;

@Service
public class StatusOrdemServicoService {
   
    @Autowired
    private StatusOrdemServicoRepository statusOrdemServicoRepository;

    public List<StatusOrdemServico> findAllStatusOrdemServico(){
        return statusOrdemServicoRepository.findAll();
    }

}
