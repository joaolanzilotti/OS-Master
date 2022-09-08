
package springthymeleaf.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import springthymeleaf.entities.Produto;


public interface ProdutoRepository extends JpaRepository<Produto, Long>{
    
}
