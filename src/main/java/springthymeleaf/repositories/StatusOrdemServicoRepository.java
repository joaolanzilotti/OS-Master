package springthymeleaf.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import springthymeleaf.entities.StatusOrdemServico;

public interface StatusOrdemServicoRepository extends JpaRepository<StatusOrdemServico, Long>{
    
}
