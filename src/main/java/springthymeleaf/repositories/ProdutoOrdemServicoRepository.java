package springthymeleaf.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import springthymeleaf.entities.ProdutoOrdemServico;

public interface ProdutoOrdemServicoRepository extends JpaRepository<ProdutoOrdemServico, Long>{
    
}
