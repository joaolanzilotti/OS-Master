package springthymeleaf.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import springthymeleaf.entities.Privilege;
import springthymeleaf.entities.Role;
import springthymeleaf.entities.Tecnico;
import springthymeleaf.repositories.PrivilegeRepository;
import springthymeleaf.repositories.RoleRepository;
import springthymeleaf.repositories.TecnicoRepository;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        
        if (alreadySetup)
            return;
        Privilege readPrivilege
          = createPrivilegeIfNotFound("READ_PRIVILEGE");
        Privilege writePrivilege
          = createPrivilegeIfNotFound("WRITE_PRIVILEGE");
 
        List<Privilege> adminPrivileges = Arrays.asList(
          readPrivilege, writePrivilege);
        createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
        createRoleIfNotFound("ROLE_USER", Arrays.asList(readPrivilege));

        Role adminRole = roleRepository.findByName("ROLE_ADMIN");
        Tecnico tecnico = new Tecnico();
        tecnico.setNome("admin");
        tecnico.setSenha("admin");
        tecnico.setEmail("admin@osmaster.com");
        tecnico.setRoles(Arrays.asList(adminRole));
        tecnico.setEnabled(true);
        tecnicoRepository.save(tecnico);

        alreadySetup = true;
    }

    @Transactional
    Privilege createPrivilegeIfNotFound(String nome) {
 
        Privilege privilege = privilegeRepository.findByName(nome);
        if (privilege == null) {
            privilege = new Privilege(nome);
            privilegeRepository.save(privilege);
        }
        return privilege;
    }

    @Transactional
    Role createRoleIfNotFound(
      String nome, Collection<Privilege> privileges) {
 
        Role role = roleRepository.findByName(nome);
        if (role == null) {
            role = new Role(nome);
            role.setPrivileges(privileges);
            roleRepository.save(role);
        }
        return role;
    }
}
