package springthymeleaf.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import springthymeleaf.entities.Cliente;
import springthymeleaf.repositories.ClienteRepository;

public class CostumUsuarioDetailsServices implements UserDetailsService{

    @Autowired
    private ClienteRepository clienteRepository;
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Cliente clienteExistente = clienteRepository.findByEmail(email);

        if(clienteExistente != null){
            throw new Error("Usuario Já Existente!");
        }
        return null;
    }
    
}
