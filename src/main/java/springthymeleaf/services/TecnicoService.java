package springthymeleaf.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springthymeleaf.entities.Tecnico;
import springthymeleaf.repositories.TecnicoRepository;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    public List<Tecnico> findAllTecnicos(){
        return tecnicoRepository.findAll();
    }
    
}
