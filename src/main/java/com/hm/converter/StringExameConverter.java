package com.hm.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import com.hm.model.Exame;
import com.hm.repository.IExames;

@Component
public class StringExameConverter implements Converter<String, Exame> {

	@Autowired
	private IExames exames;
	
	@Override
	public Exame convert(String exame) {

		return exames.findByDescricao(exame);
	}

}
