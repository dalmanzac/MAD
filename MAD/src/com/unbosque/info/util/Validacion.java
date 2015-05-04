package com.unbosque.info.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Validacion {
	
	
	static public boolean validarEmail(String email){
		if(!email.isEmpty()){
			return email.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		}else{
			return false;
		}
	}
	
	/*
	 * El metodo retorna "true" si y solo si el parametro dato es
	 * diferente de vacio y cumple con el parametro establecido.
	 * 
	 *  Retorna falese en caso contrario, sin embargo no identifica
	 *  si se debe a un dato no válido o que el dato no tiene
	 *  un valor
	 *  
	 */
	
		
	static public boolean validarDatoAlfabetico(String dato){
		if(!dato.isEmpty()){
			return dato.matches("[a-zA-Z]+");
		}else{
			return false;
		}
	}
	
	static public boolean validarContraseña(String dato){
		if(!dato.isEmpty()){
			return dato.matches("(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$");
		}else{
			return false;
		}
	}
	
	static public boolean validarNombreApellido(String dato){
		if(!dato.isEmpty()){
			return dato.matches("[a-zA-Z]+\\s[a-zA-Z]+");
		}else{
			return false;
		}
	}
	
	static public boolean validarDatoNumerico(String dato){
		if(!dato.isEmpty()){
			return dato.matches("\\d+");
		}else{
			return false;
		}
	}
	
	static public boolean validarFecha(int dia, int mes, int anio){
		boolean validacion_dia = false;
		boolean validacion_mes = true;
		boolean validacion_anio = true;
		
		if(anio%4 == 0 && anio%100 == 0 && anio%400 == 0 || anio%4 == 0 && anio%100 != 0 ){
			if(mes == 2 && dia <=29 || mes == 4 && dia <= 30|| mes == 6 && dia <= 30 || mes == 9 && dia <= 30|| mes == 11 && dia <= 30){
				validacion_dia = true;
			}else if(mes == 2 && dia <=28 || mes == 4 && dia <= 30|| mes == 6 && dia <= 30 || mes == 9 && dia <= 30|| mes == 11 && dia <= 30){
				validacion_dia = true;
			}
		}else{
			validacion_dia = true;
		}
		
		if(validacion_dia == true && validacion_mes == true && validacion_anio == true){
			return true;
		}else{
			return false;
		}
	}
}
