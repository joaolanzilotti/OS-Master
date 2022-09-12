
package springthymeleaf.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import springthymeleaf.entities.Produto;


public interface ProdutoRepository extends PagingAndSortingRepository<Produto, Long>{
    
}
