package tc.AutomataFinitoNoDeterministico;
import java.util.*;

public class AFNtoDFA {

	public DFA convert(AFN afn) {
	    DFA dfa = new DFA(); 
	    String estadoInicial = this.epsilonClosure(afn);
	    dfa.addEstadoInicial(estadoInicial);

	    Stack<String> pila = new Stack<>();
	    pila.push(estadoInicial);
	    Set<String> estadosProcesados = new HashSet<>();
	    Set<Transicion> transiciones = afn.getTransiciones();
	    Set<String> estadosFinales = afn.getEstadosFinales();
	    Set<String> subEstados = new HashSet<>();
	    String OrigenActual, DestinoActual;

	    while (!pila.isEmpty()) {
	        OrigenActual = pila.pop();
	        estadosProcesados.add(OrigenActual);

	        for (Character simbolo : afn.getAlfabeto()) {
	            subEstados = this.toSetString(OrigenActual);
	            DestinoActual = this.getTransiciones(subEstados, simbolo, transiciones);
	            if(this.hasEstadoFinal(estadosFinales, subEstados)) dfa.addEstadoFinal(OrigenActual);
	            if (!DestinoActual.isEmpty()) {
	                dfa.addTransicion(OrigenActual, DestinoActual, simbolo);
	                System.out.println("transicion añadida: " + OrigenActual + "-" + simbolo + ">" + DestinoActual);
	                
	                if (!estadosProcesados.contains(DestinoActual) && !pila.contains(DestinoActual)) {
	                    pila.push(DestinoActual);
	                }
	            }
	        }
	    }

	    return dfa;
	}
	private Boolean hasEstadoFinal(Set<String> estadosFinales, Set<String> estados) {
		
		for(String estado : estados) {
			if(estadosFinales.contains(estado)) {
				return true;
			}
		}
		
		return false;
	}
	
	private String getTransiciones(Set<String> estados , Character simbolo, Set<Transicion> transiciones) {
		Set<String> result = new HashSet<>();
		
		for(String estado : estados) {
			for(Transicion transicion : transiciones) {
				if(transicion.getOrigen().equals(estado) && transicion.getSimbolo() == simbolo && simbolo!='e') {
					result.add(transicion.getDestino());
				}
			}
		}
		if(result.isEmpty())return "";
		return this.setToString(result);
	}
	
	private Set<String> toSetString(String cadena){
		Set<String> set = new HashSet<>();
		cadena = cadena.replace("{", "").replace("}", "");
		String[] partes = cadena.split(",");
		for(String parte : partes) set.add(parte);
		return set;
	}
	
	private String setToString(Set<String> set) {
	    StringBuilder cadena = new StringBuilder();
	    for (String s : set) {
	        if (cadena.length() > 0) {
	            cadena.append(",");
	        }
	        cadena.append(s);
	    }
	    return "{" + cadena.toString() + "}";
	}
	
	
	public String epsilonClosure(AFN afn) {
		String estadoInicial = afn.getEstadoInicial();
		String clausura = estadoInicial;
		Set<String> estadosClausura = new HashSet<String>();
		estadosClausura.add(estadoInicial);
		estadosClausura.addAll(stepState(estadosClausura,afn.getTransiciones()));
		estadosClausura.remove(estadoInicial);
		for(String estado : estadosClausura) {
			clausura = clausura+","+estado;
		}
		System.out.println("{"+clausura+"}");
		return "{"+clausura+"}";
	}
	
	private Set<String> stepState(Set<String> estados,Set<Transicion> transiciones){
		 Set<String> resultado = new HashSet<>(); //aqui vamos a poner los posibles estados "destino" de cada transicion
		 for (String estado : estados) {
			 for (Transicion transicion : transiciones) {
				 if(transicion.getOrigen().equals(estado) && transicion.getSimbolo()=='e') {
					 Set<String> DestinoActual = new HashSet<>();
					 DestinoActual.add(transicion.getDestino());
					 resultado.addAll(stepState(DestinoActual,transiciones));
					 resultado.add(transicion.getDestino());
				 }
			 }
		 }
		 return resultado;
	 }
	
}
