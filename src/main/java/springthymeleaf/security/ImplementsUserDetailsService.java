package springthymeleaf.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import springthymeleaf.entities.Tecnico;
import springthymeleaf.repositories.TecnicoRepository;

@Repository
public class ImplementsUserDetailsService implements UserDetailsService{
    
    @Autowired
    private TecnicoRepository tecnicoRepository;
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Tecnico tecnico = tecnicoRepository.findByEmail(email);
        if(tecnico == null){
            throw new UsernameNotFoundException("Usuário Não Encontrado!");
        }
        return tecnico;
    }
    
}
