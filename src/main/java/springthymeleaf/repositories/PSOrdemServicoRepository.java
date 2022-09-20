package springthymeleaf.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import springthymeleaf.entities.PSOrdemServico;

public interface PSOrdemServicoRepository extends JpaRepository<PSOrdemServico, Long>{
    
}
