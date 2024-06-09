package tc.AutomataFinitoNoDeterministico;

import java.util.*;

public class AFN {
	 private Set<String> estados; //puede no ser necesario, el conjunto de estados puede ser definido por las transiciones unicamente
	 private Set<Transicion> transiciones;
	 private String estadoInicial;
	 private Set<String> estadosFinales;
	 private Set<Character> alfabeto;
	 
	 public AFN() {
		 estados = new HashSet<>(); //conjunto de estados
		 transiciones = new HashSet<>(); //conjunto de transiciones
		 estadosFinales = new HashSet<>(); //conjunto de estados finales
	     alfabeto = new HashSet<>(); //estado inicial del AFN
	 }
	 
	 public Set<String> getEstados(){
		 return this.estados;
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
	 public void agregarEstado(String estado) {
		 estados.add(estado);
	 }
	 public void agregarEstadoFinal(String estado) {
		 estadosFinales.add(estado);
	 }
	 public void agregarEstadoInicial(String estado) {
		 estadoInicial = estado;
	 }
	 
	 public void agregarTransicion(Transicion transicion) {
		 transiciones.add(transicion);
		 alfabeto.add(transicion.getSimbolo()); //agrega el simbolo de la transicion al alfabeto
	 }
	 
	 public Set<String> stepState(Set<String> estados, char simbolo){
		 Set<String> resultado = new HashSet<>(); //aqui vamos a poner los posibles estados "destino" de cada transicion
		 for (String estado : estados) {
			 for (Transicion transicion : transiciones) {
				 if (transicion.getOrigen().equals(estado) && transicion.getSimbolo() == simbolo) {
					 resultado.add(transicion.getDestino());
					 //System.out.println(transicion.getOrigen()+"-"+simbolo+"->"+transicion.getDestino()+";"+transicion.getDestino()+" agregado.");
				 }
			 }
		 }
		 return resultado;
	 }
	 
	 public boolean correrCadena(String cadena) {
		 Set<String> estadosActuales = new HashSet<>();
		 estadosActuales.add(estadoInicial);
		 
		 for (char simbolo : cadena.toCharArray()) {
			 estadosActuales = stepState(estadosActuales, simbolo);
			 /*System.out.println("----------estados actuales----------");
			 for(String estado : estadosActuales) {
				 System.out.println(estado);
			 }
			 System.out.println("-----------------------------------");*/
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
