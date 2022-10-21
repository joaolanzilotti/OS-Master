package springthymeleaf.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import springthymeleaf.entities.ProdutoOrdem;

@Repository
public interface ProdutoOrdemRepository extends JpaRepository<ProdutoOrdem, Long>{
    
}
