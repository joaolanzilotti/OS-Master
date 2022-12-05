package springthymeleaf.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import springthymeleaf.entities.ProdutoOrdem;

@Repository
public interface ProdutoOrdemRepository extends JpaRepository<ProdutoOrdem, Long>{

    //@Query("SELECT os FROM OrdemServico os JOIN os.produtoOrdem po")
    //List<OrdemServico> finddddd();

    //@Query("SELECT po FROM ProdutoOrdem po JOIN po.ordemServico os")
    //List<ProdutoOrdem> findddddProdutoOrdem();
    
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
