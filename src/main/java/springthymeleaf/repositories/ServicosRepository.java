
package springthymeleaf.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import springthymeleaf.entities.Servicos;


public interface ServicosRepository extends JpaRepository<Servicos, Long>{
    
}
