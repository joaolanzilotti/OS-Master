package springthymeleaf.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springthymeleaf.dto.RequisicaoOrdemServico;
import springthymeleaf.entities.OrdemServico;
import springthymeleaf.repositories.OrdemServicoRepository;

@Service
public class OrdemServicoService {

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    public List<OrdemServico> findAllOrdemServico(){
        return this.ordemServicoRepository.findAll(); 
    }

    public void saveOrdemServico(OrdemServico ordemServico){
        this.ordemServicoRepository.save(ordemServico);
    }

    public List<OrdemServico> findOrderByStatusAberto(){
        return this.ordemServicoRepository.findOrderByStatusAberto();
    }

    public List<OrdemServico> findOrderByStatusAndamento(){
        return this.ordemServicoRepository.findOrderByStatusAndamento();
    }

    public List<OrdemServico> findOrderByStatusFinalizado(){
        return this.ordemServicoRepository.findOrderByStatusFinalizado();
    }

    public Optional<OrdemServico> findOrdemServicoById(Long id){
        return this.ordemServicoRepository.findById(id);
    }

    public List<OrdemServico> findOrdemServicoByIdList(Long id){
        return this.ordemServicoRepository.findAllActiveUsers(id);
    }

    public void deleteOrdemServico(Long id){
        this.ordemServicoRepository.deleteById(id);
    }

    public boolean isVerificadorData(@Valid RequisicaoOrdemServico requisicaoOrdemServico) {
        boolean isValid = false;
        OrdemServico ordemServico = requisicaoOrdemServico.toOS();
        if (ordemServico.getDataInicial().isAfter(ordemServico.getDataFinal())) {
            isValid = true;
        }
        
        return isValid;
    }

    
}
