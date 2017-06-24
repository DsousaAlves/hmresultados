package com.hm.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import com.hm.model.Exame;
import com.hm.repository.IExames;

@Component
public class ExameStringConverter implements Converter<Exame, String>{

	@Autowired
	private IExames exames;
	
	@Override
	public String convert(Exame exame) {
		return exames.getOne(exame.getId()).getDescricao();
	}

}
