package com.hm.controller;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.loader.custom.Return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;

import com.hm.model.Exame;
import com.hm.model.Resultado;
import com.hm.model.Status;
import com.hm.repository.IExames;
import com.hm.repository.filter.ResultadoFilter;
import com.hm.service.ResultadoService;

@Controller
@RequestMapping("/resultados")
public class ResultadoController {

	@Autowired
	private ApplicationContext appContext;
	
	@Autowired
	private IExames exames;
	
	@Autowired
	private ResultadoService resultadoService;
	
	@RequestMapping(path = "/pdf", method = RequestMethod.GET)
	public ModelAndView gerarPdf(){
		JasperReportsPdfView view = new JasperReportsPdfView();
		view.setUrl("classpath:relatorios"+File.separator+"resultados_arquivo.jrxml");
		view.setApplicationContext(appContext);
		Map<String, Object> params = new HashMap<>();
	
		//params.put("datasource", resultadoService.buscaResultados(new ResultadoFilter()));
		
		return new ModelAndView(view);
	}
	
	
	//Home
	@RequestMapping()
	public ModelAndView home(@ModelAttribute("filter") ResultadoFilter filter) {
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("todosResultados", resultadoService.buscaResultados(filter));
		return mv;
	}
	
	//Editar o spring busca pelo id
	@RequestMapping("{id}")
	public ModelAndView alterar(@PathVariable("id") Resultado resultado){
		ModelAndView mv = new ModelAndView("cadastro");
		mv.addObject(resultado);
		return mv;
	}

	@RequestMapping("/cadastro")
	public ModelAndView cadastro() {
		ModelAndView mv = new ModelAndView("cadastro");
		mv.addObject(new Resultado());
		return mv;
	}
	
	@RequestMapping("/arquivar")
	public String lancarResultadosArquivo(String mesRef){
		
		resultadoService.lancarResultadosArquivo(mesRef);
		return "redirect:/resultados";
	} 

	@RequestMapping(value = "/cadastro", method = RequestMethod.POST)
	public String cadastro(@Validated Resultado resultado, Errors errors,RedirectAttributes attributes) {
		if(errors.hasErrors()){
			return "cadastro";
		}
		
		try {
			resultadoService.salvar(resultado);
			attributes.addFlashAttribute("mensagem", "Resultado salvo com sucesso");
			return "redirect:/resultados/cadastro";
		} catch (IllegalArgumentException e) {
			errors.rejectValue("dataExame", null, e.getMessage());
			return "cadastro";
		}
	}
	
	@RequestMapping(value = "/{id}/entregar", method = RequestMethod.PUT)
	public @ResponseBody String[] entregar(@PathVariable Long id){
		return resultadoService.entregar(id);
	}
	
	@ModelAttribute("todosExames")
	public List<Exame> allExames() {
	
		return exames.findAll();
	}
	
	@ModelAttribute("todosStatus")
	public List<Status> allStatus(){
		return Arrays.asList(Status.values());
	}

}
