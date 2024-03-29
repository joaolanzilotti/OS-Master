package springthymeleaf.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import springthymeleaf.entities.OrdemServico;

import java.util.List;

@Repository
public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long>{

    @Query("SELECT os FROM OrdemServico os WHERE os.id = :id")
    List<OrdemServico> findAllActiveUsers(@Param("id") Long id);

    @Query("SELECT os FROM OrdemServico os WHERE os.statusOrdemServico.status = 'Aberto'")
    List<OrdemServico> findOrderByStatusAberto();

    @Query("SELECT os FROM OrdemServico os WHERE os.statusOrdemServico.status = 'Em Andamento'")
    List<OrdemServico> findOrderByStatusAndamento();

    @Query("SELECT os FROM OrdemServico os WHERE os.statusOrdemServico.status = 'Finalizado'")
    List<OrdemServico> findOrderByStatusFinalizado();
    
}
