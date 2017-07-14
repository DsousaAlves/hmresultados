package com.hm.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hm.util.DataUtil;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

@Controller
@RequestMapping("/relatorio")
public class RelatorioController {

	@Autowired
	private DataSource dbsoruce;

	@RequestMapping()
	public void getRpt1(HttpServletResponse response, String mesRef) throws JRException, IOException, SQLException {
		String nomeResultado = "lista_paciente_arquivo";
		InputStream jasperStream = this.getClass().getResourceAsStream("/jasperreports/resultados_arquivo.jasper");
		Map<String, Object> params = new HashMap<>();
	
		params.put("mesInicio", DataUtil.extrairDataReport(mesRef)[0]);
		params.put("mesFim", DataUtil.extrairDataReport(mesRef)[1]);

		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dbsoruce.getConnection());

		response.setContentType("application/x-pdf");
		response.setHeader("Content-disposition", "filename=" + nomeResultado + ".pdf");
		
		
		final OutputStream outStream = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
		
	}
	
	
}
