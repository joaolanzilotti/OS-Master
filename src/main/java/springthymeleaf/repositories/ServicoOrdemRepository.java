package springthymeleaf.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import springthymeleaf.entities.ProdutoOrdem;
import springthymeleaf.entities.ServicoOrdem;

import java.util.List;

@Repository
public interface ServicoOrdemRepository extends JpaRepository<ServicoOrdem, Long>{

    @Query("SELECT so FROM ServicoOrdem so INNER JOIN so.ordemServico os INNER JOIN so.servico WHERE so.ordemServico.id = :id")
    List<ServicoOrdem> findServicoOrdem(@Param("id") Long id);
    
}
