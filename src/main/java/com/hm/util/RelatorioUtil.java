package com.hm.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

public class RelatorioUtil {
	
	public File geraRelatorio(HashMap parametros, String nomeRelatorioJasper, String nomeRelatorioSaida){
		
		String path = this.getClass().getClassLoader().getResource("").getPath();
		String localRelatorio = path + File.separator + "resources";
		System.out.println(localRelatorio);
		return null;
	}
}


//try{
//	String caminhoRelatorios = "/relatorios";
//	String caminhoArquivoJasper = caminhoRelatorios + File.separator + nomeRelatorioJasper + ".jasper";
//	
//	String caminhoRelativoRelatorio = caminhoRelatorios + File.separator + nomeRelatorioSaida;
//	
//	JasperReport relatorioJasper = (JasperReport) JRLoader.loadObjectFromFile(caminhoArquivoJasper);
//	JasperPrint impressoraJasper = JasperFillManager.fillReport(relatorioJasper, parametros);
//	
//	String extensao = "pdf";
//	File arquivoGerado = null;
//	
//	JRPdfExporter pdfExportado = new JRPdfExporter();
//	arquivoGerado = new File(caminhoRelativoRelatorio+"."+ extensao); 
//	
//	pdfExportado.setExporterInput(new SimpleExporterInput(impressoraJasper));
//	pdfExportado.setExporterOutput(new SimpleOutputStreamExporterOutput(arquivoGerado));
//	pdfExportado.exportReport();
//	
//	arquivoGerado.deleteOnExit();
//	
//	InputStream conteudoRelatorio = new FileInputStream(arquivoGerado);
//	
//	
//	
//}catch (JRException e) {
//	throw new RuntimeException("Erro ao gerar o relatório",e);
//}catch(FileNotFoundException e){
//	throw new RuntimeException("Arquivo do relatório não encontrado",e);
//}
