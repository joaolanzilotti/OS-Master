
package springthymeleaf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import springthymeleaf.entities.Tasks;
import springthymeleaf.repositories.TasksRepository;

@Controller
public class TaskController {
    
    @Autowired
    private TasksRepository taskrepository;
    
    @GetMapping("/create")
    public String home(){
        return "create";
    }
    
    //Estou Criando um metodo que vai receber minha classe entidade Tasks
    @PostMapping("/create")
    public void create(Tasks task){
    
        System.out.println("O Nome da Tarefa é: " + task.getName());
        System.out.println("A Data é: " + task.getDataH());
        
        taskrepository.save(task);
        
    }
    
}
