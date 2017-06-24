package com.hm.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.hm.model.Resultado;
import com.hm.model.Status;
import com.hm.repository.IResultados;
import com.hm.repository.filter.ResultadoFilter;

@Service
public class ResultadoService {
	
	private SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	
	@Autowired
	private IResultados resultados;
	
	public void salvar(Resultado resulado){
		try {
			resultados.save(resulado);
		} catch (DataIntegrityViolationException e) {
			throw new IllegalArgumentException("Data no formato inválido");
		}
	}

	public String[] entregar(Long id) {
		Resultado resultado = resultados.findOne(id);
		resultado.setStatus(Status.ENTREGUE);
		resultado.setDataEntrega(new Date());
		resultados.save(resultado);
		String[] e = {formato.format(resultado.getDataEntrega()),resultado.getStatus().getDesc()};
		return e;
	}
	
	public List<Resultado> buscaResultados(ResultadoFilter filter){

		String nomePaciente = filter.getNomePaciente() == null ? "%" : filter.getNomePaciente();
		
		return resultados.findByPacienteContaining(nomePaciente);
	}
	
	public void lancarResultadosArquivo(String mesRef){
		String aux[] = mesRef.split("/");
		Calendar c = new GregorianCalendar(Integer.parseInt(aux[1]), Integer.parseInt(aux[0]) - 1, 1);
//		System.out.println("----->>>>>>>>>>>>>>> "+c.get(Calendar.MONTH) + " " + c.getActualMaximum(Calendar.DAY_OF_MONTH) );
		
		resultados.lancarResultadosArquivos(aux[1]+"/"+aux[0]+"/"+c.getActualMinimum(Calendar.DAY_OF_MONTH),//data inicio 
											aux[1]+"/"+aux[0]+"/"+c.getActualMaximum(Calendar.DAY_OF_MONTH));//data fim
	}
}
