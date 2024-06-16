package tc.AutomataFinitoNoDeterministico;

import java.util.*;

public class AFN {
	 private Set<Transicion> transiciones;
	 private String estadoInicial;
	 private Set<String> estadosFinales;
	 
	 public AFN() {
		 transiciones = new HashSet<>(); //conjunto de transiciones
		 estadosFinales = new HashSet<>(); //conjunto de estados finales
	 }
	 
	 public String getEstadoInicial(){
		 return this.estadoInicial;
	 }
	 public Set<String> getEstadosFinales(){
		 return this.estadosFinales;
	 }
	 public Set<Transicion> getTransiciones(){
		 return this.transiciones;
	 }
	 public void agregarEstadoFinal(String estado) {
		 estadosFinales.add(estado);
	 }
	 public void agregarEstadoInicial(String estado) {
		 estadoInicial = estado;
	 }
	 
	 public void agregarTransicion(Transicion transicion) {
		 transiciones.add(transicion);
	 }
	 
	 public Set<String> stepState(Set<String> estados, Character simbolo){
		 Set<String> resultado = new HashSet<>(); //aqui vamos a poner los posibles estados "destino" de cada transicion
		 for (String estado : estados) {
			 for (Transicion transicion : transiciones) {
				 //System.out.println(transicion.getOrigen()+" =? "+estado+" / "+transicion.getSimbolo()+"=?"+simbolo);
				 if (transicion.getOrigen().equals(estado) && transicion.getSimbolo() == simbolo) {
					 resultado.add(transicion.getDestino());
					 System.out.println(transicion.getOrigen()+"-"+simbolo+"->"+transicion.getDestino()+";"+transicion.getDestino()+" agregado.");
				 } else if(transicion.getOrigen().equals(estado) && transicion.getSimbolo()=='e') {
					 System.out.println("e encontrado");
					 Set<String> temp = new HashSet<>();
					 temp.add(transicion.getDestino());
					 resultado.addAll(stepState(temp, simbolo));
				 }
			 }
		 }
		 return resultado;
	 }
	 
	 public boolean correrCadena(String cadena) {
		 Set<String> estadosActuales = new HashSet<>();
		 estadosActuales.add(estadoInicial);
		 
		 for (Character simbolo : cadena.toCharArray()) {
			 /*System.out.println("----------estados actuales----------");
			 for(String estado : estadosActuales) {
				 System.out.println(estado);
			 }
			 System.out.println("-----------------------------------");*/
			 estadosActuales = stepState(estadosActuales, simbolo);
		 }
		 
		 for(String estado : estadosActuales) {
			 for(String estadoFinal : estadosFinales) {
				 if(estado.equals(estadoFinal)) {
					 //System.out.println(estado+" es un estado final");
					 return true;
				 }
			 }
			 
		 }
		 //System.out.println("no se encontraron estados finales");
		 return false;
	 }
	 

	 
	 
}
