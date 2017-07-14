package com.hm.repository.filter;

public class ResultadoFilter {

	private String nomePaciente;
	private int pagina;
	public static final int TAMANHO_PAGINA = 10;

	public String getNomePaciente() {
		return nomePaciente;
	}

	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}

	public int getPagina() {
		return pagina;
	}

	public void setPagina(int pagina) {
		this.pagina = pagina;
	}
	
	
}
