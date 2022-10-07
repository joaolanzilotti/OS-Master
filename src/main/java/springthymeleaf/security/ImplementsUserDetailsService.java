package springthymeleaf.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import springthymeleaf.entities.Cliente;
import springthymeleaf.repositories.ClienteRepository;

@Repository
public class ImplementsUserDetailsService implements UserDetailsService{
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Cliente cliente = clienteRepository.findByEmail(email);
        if(cliente == null){
            throw new UsernameNotFoundException("Usuário Não Encontrado!");
        }
        return cliente;
    }
    
}
