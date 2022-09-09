package springthymeleaf.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import springthymeleaf.entities.OrdemServico;

public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long>{
    
}
