package com.hm.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DataUtil {
	

	
	/*	aux[1] = ano
	 * 	aux[0] = mes
	 * 	
	 * 	String[0] = dataInicio
	 * 	String[1] = datafim
	 * 	
	 */
	public static String[] extrairData(String mes){
		
		
		String aux[] = mes.split("/");
		int ano = Integer.parseInt(aux[1]);
		int mesInt = Integer.parseInt(aux[0]) - 1;
		Calendar c = new GregorianCalendar(Integer.parseInt(aux[1]), Integer.parseInt(aux[0]) - 1, 1);
	
		//calendar usa o mes começando com zero
		//mysql usa começando 1
		return new String[]{ano+"/"+(mesInt + 1)+"/"+c.getActualMinimum(Calendar.DAY_OF_MONTH),
							ano+"/"+(mesInt + 1)+"/"+c.getActualMaximum(Calendar.DAY_OF_MONTH)};
	}
	
	public static Date[] extrairDataReport(String mes){
		String aux[] = mes.split("/");
		Calendar dataInicio = new GregorianCalendar(Integer.parseInt(aux[1]), Integer.parseInt(aux[0]) - 1, 1);
		Calendar dataFim = new GregorianCalendar(Integer.parseInt(aux[1]), Integer.parseInt(aux[0]) - 1, 
				dataInicio.getActualMaximum(Calendar.DAY_OF_MONTH));
		return new Date[]{dataInicio.getTime(),dataFim.getTime()};
	}
	
	public static void main(String[] args) {
		System.out.println(extrairDataReport("07/2017")[1]);
	}
	
}