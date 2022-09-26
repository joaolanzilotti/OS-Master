
package springthymeleaf.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import springthymeleaf.entities.Cliente;


public interface ClienteRepository extends JpaRepository<Cliente, Long>{

    
}
