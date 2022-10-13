package springthymeleaf.entities;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy="roles")
    private Collection<Tecnico> tecnicos;


    @ManyToMany
    @JoinTable(
        name = "roles_privileges", 
        joinColumns = @JoinColumn(
          name = "role_id", referencedColumnName = "id"), 
        inverseJoinColumns = @JoinColumn(
          name = "privilege_id", referencedColumnName = "id"))
    private Collection<Privilege> privileges;

    public Role() {
    }

    public Role(String name) {
      this.name = name;
    }

    public Role(Long id, String name, Collection<Tecnico> tecnicos, Collection<Privilege> privileges) {
        this.id = id;
        this.name = name;
        this.tecnicos = tecnicos;
        this.privileges = privileges;
    }

    

    
    
}
