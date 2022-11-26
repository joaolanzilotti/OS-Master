package springthymeleaf.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springthymeleaf.entities.Servico;
import springthymeleaf.repositories.ServicoRepository;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository servicoRepository;

    public List<Servico> findAllServicos(){
        return this.servicoRepository.findAll();
    }
    
    public void saveServico(Servico servico){
        servicoRepository.save(servico);
    }

    public Optional<Servico> findServicoById(Long id){
        return servicoRepository.findById(id);
    }

    public void deleteServicoById(Long id){
        servicoRepository.deleteById(id);
    }
}
