package com.hm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.hm.model.Resultado;

public interface IResultados extends JpaRepository<Resultado, Long>{
	public List<Resultado> findByPacienteContaining(String nomePaciente);
	
	@Procedure(name = "arquivoProcedure")
	public void lancarResultadosArquivos(@Param("data_inicio")String data_inicio,@Param("data_fim") String data_fim);
}
