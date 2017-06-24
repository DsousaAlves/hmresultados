package com.hm.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.hm.model.Exame;
import com.hm.model.Resultado;
import com.hm.model.Status;
import com.hm.repository.IExames;
import com.hm.repository.filter.ResultadoFilter;
import com.hm.service.ResultadoService;
import com.hm.util.RelatorioUtil;

@Controller
@RequestMapping("/resultados")
public class ResultadoController {


	
	@Autowired
	private IExames exames;
	
	@Autowired
	private ResultadoService resultadoService;
	
	
	
	
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
	
	@RequestMapping(path = "/pdf", method = RequestMethod.GET)
	public ModelAndView gerarPdf(){
		RelatorioUtil u = new RelatorioUtil();
		u.geraRelatorio(null, null, null);
		
		return new ModelAndView("index");
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
