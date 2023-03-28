package springthymeleaf.services;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.context.request.FacesWebRequest;
import springthymeleaf.entities.OrdemServico;
import springthymeleaf.repositories.OrdemServicoRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
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

    public ResponseEntity<byte[]> generateReport(Long id) throws JRException {

        InputStream stream;
        stream = this.getClass().getResourceAsStream("/OrdemServico.jrxml");

        listaOrdemServico = ordemServicoService.findOrdemServicoByIdList(id);

        for (OrdemServico os : listaOrdemServico) {

            dataFinalFormatada = os.getDataFinal().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            dataInicialFormatada = os.getDataInicial().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        }


        String reportPath = "C:\\Report";

        // Compilar de .jrxml para .jasper
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(stream);

        // Criar a Base de Dados
        JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(listaOrdemServico);

        // Adicionar os Parametros do Relatorio
        Map<String, Object> parameters = new HashMap<>();

        parameters.put("createdBy", "Websparrow.org");
        parameters.put("dataFinalParam", dataFinalFormatada);
        parameters.put("dataInicialParam", dataInicialFormatada);


        // Filtrar o Relatorio
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,
                jrBeanCollectionDataSource);

        // Exportar para PDF
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, baos);
        byte[] bytes = baos.toByteArray();
        // JasperExportManager.exportReportToPdfFile(jasperPrint, reportPath + "\\Emp-Rpt.pdf");

        // Retorna o Relatorio em Uma Resposta Entity
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentLength(bytes.length);
        headers.setContentDispositionFormData("attachment", "Ordem de Servico " + id + ".pdf");
        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);

    }
}


