package com.hm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.hm.model.Exame;

@Component
public interface IExames extends JpaRepository<Exame, Long>{
	Exame findByDescricao(String descricao);
}
