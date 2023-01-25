package springthymeleaf.services;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.context.request.FacesWebRequest;
import springthymeleaf.entities.OrdemServico;
import springthymeleaf.repositories.OrdemServicoRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReportService {

    private String dataFinalFormatada;

    private String dataInicialFormatada;
    @Autowired
    private OrdemServicoService ordemServicoService;


    public List<OrdemServico> listaOrdemServico;

    public String generateReport(Long id) {

        listaOrdemServico = ordemServicoService.findOrdemServicoByIdList(id);

        for (OrdemServico os : listaOrdemServico) {

            dataFinalFormatada = os.getDataFinal().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            dataInicialFormatada = os.getDataInicial().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        }

        try {

            String reportPath = "C:\\Report";

            // Compile the Jasper report from .jrxml to .japser
            JasperReport jasperReport = JasperCompileManager.compileReport(reportPath + "\\OrdemServico.jrxml");

            // Get your data source
            JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(listaOrdemServico);

            // Add parameters
            Map<String, Object> parameters = new HashMap<>();

            parameters.put("createdBy", "Websparrow.org");
            parameters.put("dataFinalParam", dataFinalFormatada);
            parameters.put("dataInicialParam", dataInicialFormatada);


            // Fill the report
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,
                    jrBeanCollectionDataSource);

            // Export the report to a PDF file
            JasperExportManager.exportReportToPdfFile(jasperPrint, reportPath + "\\Emp-Rpt.pdf");

            System.out.println("Done");

            return "Report successfully generated @path= " + reportPath;


        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}


