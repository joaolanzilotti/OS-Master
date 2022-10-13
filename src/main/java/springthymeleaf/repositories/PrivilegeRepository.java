package springthymeleaf.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import springthymeleaf.entities.Privilege;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long>{

    Privilege findByName(String name);
    
}
