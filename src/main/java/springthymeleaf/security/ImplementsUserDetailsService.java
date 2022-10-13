package springthymeleaf.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import springthymeleaf.entities.Privilege;
import springthymeleaf.entities.Role;
import springthymeleaf.entities.Tecnico;
import springthymeleaf.repositories.RoleRepository;
import springthymeleaf.repositories.TecnicoRepository;

//@Repository
@Service("userDetailsService")
@Transactional
public class ImplementsUserDetailsService implements UserDetailsService{

    @Autowired
    private TecnicoRepository tecnicoRepository;
 
    @Autowired
    private MessageSource messages;
 
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String email)
      throws UsernameNotFoundException {
 
        Tecnico tecnico = tecnicoRepository.findByEmail(email);
        if (tecnico == null) {
            return new org.springframework.security.core.userdetails.User(
              " ", " ", true, true, true, true, 
              getAuthorities(Arrays.asList(
                roleRepository.findByName("ROLE_USER"))));
        }

        return new org.springframework.security.core.userdetails.User(
          tecnico.getEmail(), tecnico.getSenha(), tecnico.isEnabled(), true, true, 
          true, getAuthorities(tecnico.getRoles()));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(
      Collection<Role> roles) {
 
        return getGrantedAuthorities(getPrivileges(roles));
    }

    private List<String> getPrivileges(Collection<Role> roles) {
 
        List<String> privileges = new ArrayList<>();
        List<Privilege> collection = new ArrayList<>();
        for (Role role : roles) {
            privileges.add(role.getName());
            collection.addAll(role.getPrivileges());
        }
        for (Privilege item : collection) {
            privileges.add(item.getName());
        }
        return privileges;
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }
    
    //@Autowired
    //private TecnicoRepository tecnicoRepository;
    
    //@Override
    //public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
     //   Tecnico tecnico = tecnicoRepository.findByEmail(email);
     //   if(tecnico == null){
     //       throw new UsernameNotFoundException("Usuário Não Encontrado!");
     //   }
     //   return tecnico;
   // }
    
}
