package springthymeleaf.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import springthymeleaf.entities.ProdutoOrdem;

@Repository
public interface ProdutoOrdemRepository extends JpaRepository<ProdutoOrdem, Long>{

    @Query("SELECT po FROM ProdutoOrdem po INNER JOIN po.ordemServico os INNER JOIN po.produto WHERE po.ordemServico = 1")
    List<ProdutoOrdem> findProdutoOrdem();
    
    /*
     SELECT 
p.nome as Nome,
p.valor as Valor
from
produtoordem po
INNER JOIN ordemservico os ON os.id = po.ordemservico_id
INNER JOIN produto p ON p.id = po.produto_id
WHERE po.ordemservico_id = 4;
     */

}
