
package springthymeleaf.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import springthymeleaf.entities.Cliente;


public interface ClienteRepository extends PagingAndSortingRepository<Cliente, Long>{

    
}
