package springthymeleaf.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springthymeleaf.repositories.ServicoOrdemRepository;

@Service
public class ServicoOrdemService {

    @Autowired
    private ServicoOrdemRepository servicoOrdemRepository;

    
}
