package com.hm.model;

public enum Status {
	PENDENTE("Pendente"),
	ENTREGUE("Entregue"),
	ARQUIVO("Arquivo");
	
	private String desc;
	
	Status(String desc){
		this.desc = desc;
	}
	
	public String getDesc(){
		return desc;
	}
}
