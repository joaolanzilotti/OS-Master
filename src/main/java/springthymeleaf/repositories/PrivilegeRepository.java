package springthymeleaf.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import springthymeleaf.entities.Privilege;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Long>{

    Privilege findByName(String name);
    
}
