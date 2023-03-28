package springthymeleaf.controller;

import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springthymeleaf.entities.OrdemServico;
import springthymeleaf.services.OrdemServicoService;
import springthymeleaf.services.ReportService;

import java.util.Optional;

@RestController
@RequestMapping("/ordemservico")
public class OrdemServicoReport {

    @Autowired
    private ReportService reportService;


    @GetMapping("/{id}/report")
    public ResponseEntity<byte[]> empReport(@PathVariable Long id) throws JRException {

        return reportService.generateReport(id);
    }

}
