
package springthymeleaf.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springthymeleaf.entities.Tasks;
import springthymeleaf.repositories.TasksRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
    
//    @Autowired
//    private TasksRepository taskRepository;

    @Override
    public void run(String... args) throws Exception {
        
//        Tasks task = new Tasks(null, "joao", null);
//        
//        taskRepository.save(task);
        
    }
    
}
