package springthymeleaf.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import springthymeleaf.entities.ProdutoOrdem;

public interface ProdutoOrdemRepository extends JpaRepository<ProdutoOrdem, Long>{
    
}
