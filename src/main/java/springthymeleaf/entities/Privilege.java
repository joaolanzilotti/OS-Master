package springthymeleaf.entities;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Privilege {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy="privileges")
    private Collection<Role> roles;

    public Privilege() {
    }
    
    public Privilege(String name) {
        this.name = name;
    }

    public Privilege(Long id, String name, Collection<Role> roles) {
        this.id = id;
        this.name = name;
        this.roles = roles;
    }

    
}
