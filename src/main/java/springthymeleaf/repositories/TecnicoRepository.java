package springthymeleaf.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import springthymeleaf.entities.Tecnico;

public interface TecnicoRepository extends JpaRepository<Tecnico, Long> {

    Tecnico findByEmailTecnico(String email);
    
}
