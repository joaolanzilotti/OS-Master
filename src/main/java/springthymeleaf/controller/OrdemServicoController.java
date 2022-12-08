package springthymeleaf.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import springthymeleaf.dto.RequisicaoOrdemServico;
import springthymeleaf.dto.RequisicaoProdutoOrdem;
import springthymeleaf.dto.RequisicaoServicoOrdem;
import springthymeleaf.entities.Cliente;
import springthymeleaf.entities.OrdemServico;
import springthymeleaf.entities.Produto;
import springthymeleaf.entities.ProdutoOrdem;
import springthymeleaf.entities.Servico;
import springthymeleaf.entities.ServicoOrdem;
import springthymeleaf.entities.StatusOrdemServico;
import springthymeleaf.entities.Tecnico;
import springthymeleaf.repositories.ProdutoOrdemRepository;
import springthymeleaf.services.ClienteService;
import springthymeleaf.services.OrdemServicoService;
import springthymeleaf.services.ProdutoOrdemService;
import springthymeleaf.services.ProdutoService;
import springthymeleaf.services.ServicoOrdemService;
import springthymeleaf.services.ServicoService;
import springthymeleaf.services.StatusOrdemServicoService;
import springthymeleaf.services.TecnicoService;

@Controller
@RequestMapping("/ordemservico")
public class OrdemServicoController {

    private boolean ordemServicoCriada = false;
    private boolean ordemServicoEditada = false;
    private boolean ordemServicoDeletada = false;
    private boolean ordemServicoProdutoAdd = false;
    private boolean ordemServicoServicoAdd = false;
    private boolean erroData = false;
    private boolean erroOrdemServicoNaoEncontrada = false;

    @Autowired
    private ProdutoOrdemRepository produtoOrdemRepository;

    @Autowired
    private OrdemServicoService ordemServicoService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private TecnicoService tecnicoService;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ServicoService servicoService;

    @Autowired
    private ProdutoOrdemService produtoOrdemService;

    @Autowired
    private StatusOrdemServicoService statusOrdemServicoService;

    @Autowired
    private ServicoOrdemService servicoOrdemService;

    @GetMapping("")
    public ModelAndView paginaInicialOS() {
        List<OrdemServico> ordemServico = this.ordemServicoService.findAllOrdemServico();

        ModelAndView mv = new ModelAndView("ordemservico/index");
        mv.addObject("ordemServico", ordemServico);
        mv.addObject("ordemServicoDeletada", ordemServicoDeletada);
        mv.addObject("erroOrdemServicoNaoEncontrada", erroOrdemServicoNaoEncontrada);
        ordemServicoDeletada = false;
        erroOrdemServicoNaoEncontrada = false;

        return mv;
    }

    @GetMapping("/new")
    public ModelAndView paginaCadastro(RequisicaoOrdemServico requisicao) {

        List<Cliente> clientes = this.clienteService.findAllClientes();
        List<Tecnico> tecnicos = this.tecnicoService.findAllTecnicos();
        List<StatusOrdemServico> status = this.statusOrdemServicoService.findAllStatusOrdemServico();

        ModelAndView mv = new ModelAndView("ordemservico/new");
        mv.addObject("status", status);
        mv.addObject("clientes", clientes);
        mv.addObject("tecnicos", tecnicos);
        return mv;

    }

    @PostMapping("")
    public ModelAndView cadastro(@Valid RequisicaoOrdemServico requisicao, BindingResult erro) {

        OrdemServico ordemServico = requisicao.toOS();

        if (erro.hasErrors()) {
            ModelAndView mv = new ModelAndView("ordemservico/new");
            System.out.println(erro);
            return mv;

        }

        if (ordemServicoService.isVerificadorData(requisicao)) {
            List<Cliente> clientes = this.clienteService.findAllClientes();
            List<Tecnico> tecnicos = this.tecnicoService.findAllTecnicos();
            List<StatusOrdemServico> status = this.statusOrdemServicoService.findAllStatusOrdemServico();
            ModelAndView mv = new ModelAndView("ordemservico/new");
            erroData = true;
            mv.addObject("status", status);
            mv.addObject("clientes", clientes);
            mv.addObject("tecnicos", tecnicos);
            mv.addObject("erroData", erroData);
            erroData = false;

            return mv;
        }

        this.ordemServicoService.saveOrdemServico(ordemServico);
        ordemServicoCriada = true;
        return new ModelAndView("redirect:/ordemservico/" + ordemServico.getId() + "/edit");

    }

    @GetMapping("/{id}/edit")
    public ModelAndView editar(@PathVariable Long id, RequisicaoOrdemServico requisicao) {
        Optional<OrdemServico> optional = this.ordemServicoService.findOrdemServicoById(id);
        List<StatusOrdemServico> status = this.statusOrdemServicoService.findAllStatusOrdemServico();
        List<Servico> servico = this.servicoService.findAllServicos();
        List<Produto> produto = this.produtoService.findAllProdutos();

        if (optional.isPresent()) {
            OrdemServico ordemServico = optional.get();
            requisicao.fromOS(ordemServico);
            ModelAndView mv = new ModelAndView("ordemservico/edit");
            mv.addObject("ordemServicoId", ordemServico.getId());
            mv.addObject("status", status);
            mv.addObject("statusSelecionado", requisicao.getStatusOrdemServico());
            mv.addObject("servico", servico);
            mv.addObject("produto", produto);
            mv.addObject("produtoSelecionado", requisicao.getProduto());
            mv.addObject("ordemServicoCriada", ordemServicoCriada);
            mv.addObject("ordemServicoEditada", ordemServicoEditada);
            mv.addObject("ordemServicoProdutoAdd", ordemServicoProdutoAdd);
            mv.addObject("ordemServicoServicoAdd", ordemServicoServicoAdd);
            ordemServicoCriada = false;
            ordemServicoEditada = false;
            ordemServicoProdutoAdd = false;
            ordemServicoServicoAdd = false;

            return mv;
        } else {
            System.out.println("Ordem de Serviço Não Encontrada");
            erroOrdemServicoNaoEncontrada = true;
            return new ModelAndView("redirect:/ordemservico");
        }

    }

    @PostMapping("/{id}")
    public ModelAndView update(@PathVariable Long id, @Valid RequisicaoOrdemServico requisicao, BindingResult erro) {

        if (erro.hasErrors()) {
            ModelAndView mv = new ModelAndView("ordemservico/edit");
            mv.addObject("ordemServicoId", id);
            System.out.println(erro);
            return mv;
        } else {
            Optional<OrdemServico> optional = this.ordemServicoService.findOrdemServicoById(id);
            if (optional.isPresent()) {
                OrdemServico ordemServico = requisicao.toOrdemServico(optional.get());
                this.ordemServicoService.saveOrdemServico(ordemServico);
                ordemServicoEditada = true;
                return new ModelAndView("redirect:/ordemservico/" + ordemServico.getId() + "/edit");
            } else {
                System.out.println("OS Não Encontrada!");
                erroOrdemServicoNaoEncontrada = true;
                return new ModelAndView("redirect:/ordemservico");
            }
        }

    }

    @GetMapping("/{id}")
    public ModelAndView detailsOrdemServico(@PathVariable Long id) {
        Optional<OrdemServico> optional = this.ordemServicoService.findOrdemServicoById(id);
        List<StatusOrdemServico> status = this.statusOrdemServicoService.findAllStatusOrdemServico();
        List<Servico> servico = this.servicoService.findAllServicos();
        List<Produto> produto = this.produtoService.findAllProdutos();
        List<ProdutoOrdem> listaProdutos = produtoOrdemRepository.findddddProdutoOrdem();

        if (optional.isPresent()) {

            OrdemServico ordemServico = optional.get();
            ModelAndView mv = new ModelAndView("ordemservico/show");
            mv.addObject("ordemServico", ordemServico);
            mv.addObject("status", status);
            mv.addObject("servico", servico);
            mv.addObject("produto", produto);
            ordemServicoCriada = false;
            ordemServicoEditada = false;
            ordemServicoProdutoAdd = false;
            ordemServicoServicoAdd = false;
            return mv;

        } else {
            System.out.println("Ordem de Serviço Não encontrada!" + id);
            erroOrdemServicoNaoEncontrada = true;
            return new ModelAndView("redirect:/ordemservico");
        }

    }

    @PostMapping("/{id}/produtoadd")
    public ModelAndView adicionarProduto(@PathVariable Long id, @Valid RequisicaoProdutoOrdem requisicaoProdutoOrdem, BindingResult erro, RequisicaoOrdemServico requisicaoOrdemServico) {

        ProdutoOrdem produtoOrdem = requisicaoProdutoOrdem.toProdutoOrdem();

        if (erro.hasErrors()) {
            ModelAndView mv = new ModelAndView("ordemservico/new");
            System.out.println(erro);
            return mv;

        } else {
            Optional<OrdemServico> optional = this.ordemServicoService.findOrdemServicoById(id);
            OrdemServico ordemServico = requisicaoOrdemServico.fromOSProdutoAdd(optional.get());
            produtoOrdem.setOrdemServico(ordemServico);
            this.produtoOrdemService.saveProdutoOrdem(produtoOrdem);
            ordemServicoProdutoAdd = true;
            return new ModelAndView("redirect:/ordemservico/{id}/edit");
        }

    }

    @PostMapping("/{id}/servicoadd")
    public ModelAndView adicionarServico(@PathVariable Long id, @Valid RequisicaoServicoOrdem requisicaoServicoOrdem, BindingResult erro, RequisicaoOrdemServico requisicaoOrdemServico) {
        ServicoOrdem servicoOrdem = requisicaoServicoOrdem.toServicoOrdem();

        if (erro.hasErrors()) {
            ModelAndView mv = new ModelAndView("ordemservico/new");
            System.out.println(erro);
            return mv;
        } else {
            Optional<OrdemServico> optional = this.ordemServicoService.findOrdemServicoById(id);
            OrdemServico ordemServico = requisicaoOrdemServico.fromOSServicoAdd(optional.get());
            servicoOrdem.setOrdemServico(ordemServico);
            this.servicoOrdemService.saveServicoOrdem(servicoOrdem);
            ordemServicoServicoAdd = true;
            return new ModelAndView("redirect:/ordemservico/{id}/edit");
        }
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        try {
            this.ordemServicoService.deleteOrdemServico(id);
            ordemServicoDeletada = true;
            return "redirect:/ordemservico";
        } catch (EmptyResultDataAccessException e) {
            System.out.println("Os Nao Encontrada");
            erroOrdemServicoNaoEncontrada = true;
            return "redirect:/ordemservico";

        }
    }

}
