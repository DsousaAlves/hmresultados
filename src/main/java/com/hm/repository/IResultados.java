package com.hm.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.hm.model.Resultado;

public interface IResultados extends PagingAndSortingRepository<Resultado, Long>{
	
	@Query("select r from Resultado r where r.paciente like %?1% order by r.paciente")
	public Page<Resultado> findByPacienteContaining(String nomePaciente,Pageable pagina);
	
	@Procedure(name = "arquivoProcedure")
	public Integer lancarResultadosArquivos(@Param("data_inicio")String data_inicio,@Param("data_fim") String data_fim);
}
