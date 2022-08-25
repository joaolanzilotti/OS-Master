
package springthymeleaf.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import springthymeleaf.entities.Tasks;


public interface TasksRepository extends JpaRepository<Tasks, Long>{
    
}
