
package springthymeleaf.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import springthymeleaf.entities.Servico;


public interface ServicoRepository extends PagingAndSortingRepository<Servico, Long>{
    
}
