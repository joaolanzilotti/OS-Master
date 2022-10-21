package springthymeleaf.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import springthymeleaf.entities.StatusOrdemServico;
@Repository
public interface StatusOrdemServicoRepository extends JpaRepository<StatusOrdemServico, Long>{
    
}
