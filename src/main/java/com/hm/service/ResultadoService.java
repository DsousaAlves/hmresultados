package com.hm.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.hm.model.Resultado;
import com.hm.model.Status;
import com.hm.repository.IResultados;
import com.hm.repository.filter.ResultadoFilter;
import com.hm.util.DataUtil;

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
	
	public Page<Resultado> buscaResultados(ResultadoFilter filter,Pageable pagina){

		String nomePaciente = filter.getNomePaciente() == null ? "%" : filter.getNomePaciente();
		
		
		Page<Resultado> findByPacienteContaining = (Page<Resultado>) resultados.findByPacienteContaining(nomePaciente, pagina);
		return findByPacienteContaining;
	}
	
	public int lancarResultadosArquivo(String mesRef){

		return resultados.lancarResultadosArquivos(DataUtil.extrairData(mesRef)[0],//data inicio 
													DataUtil.extrairData(mesRef)[1]);//data fim
	}
}
