package springthymeleaf.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import springthymeleaf.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
    
}
