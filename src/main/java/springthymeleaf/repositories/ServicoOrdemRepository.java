package springthymeleaf.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import springthymeleaf.entities.ServicoOrdem;

@Repository
public interface ServicoOrdemRepository extends JpaRepository<ServicoOrdem, Long>{
    
}
