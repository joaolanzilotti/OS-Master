
package springthymeleaf.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import springthymeleaf.entities.Servico;


public interface ServicoRepository extends JpaRepository<Servico, Long>{
    
}
