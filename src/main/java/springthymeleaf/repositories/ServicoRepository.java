
package springthymeleaf.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import springthymeleaf.entities.Servico;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long>{
    
}
