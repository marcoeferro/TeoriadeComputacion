package tc.AutomataFinitoNoDeterministico;

import java.util.*;

public class Converter {
	
	private AFN automata;
	private List<String> texto;
	
	public Converter() {
		this.automata = new AFN();
		this.texto = new ArrayList<String>();
	}
	
	private void parserInicial(String linea) {
		//limpiador
		linea = linea.replace("inic->", "").replace(";", "");
		//agrega al AFN
		this.automata.agregarEstado(linea);
		this.automata.agregarEstadoInicial(linea);
	}
	
	private void parserTransicion(String linea) {
		//limpiador
		linea = linea.replace(";", "").trim();
		String[] partes = linea.split("->| \\[label=");
		String origen = partes[0];
		String destino = partes[1];
		String simbolo = partes[2].replace("\"", "").replace("]", "");
		//agrega al AFN
		this.automata.agregarTransicion(new Transicion(origen,destino,simbolo.charAt(0)));
		this.automata.agregarEstado(origen);
		this.automata.agregarEstado(destino);
		
	}
	private void parserFinal(String linea) {
		//limpiador
		linea = linea.replace("[shape=doublecircle];", "");
		//agrega al AFN
		this.automata.agregarEstadoFinal(linea);
	}
	
	public AFN textToAFN(List<String> texto) {
		//verifica que esten las primeras dos lineas "digraph{" y "inic[shape=point];"
		if(texto.get(0).equalsIgnoreCase("digraph{") && texto.get(1).equalsIgnoreCase("inic[shape=point];")) {
			//verifica que haya un estado inicial "inic->##;"
			if(texto.get(2).split("->")[0].equalsIgnoreCase("inic")) {
				
				texto.remove(0);texto.remove(0); //quita la basura del texto ("digraph{" y "inic[shape=point];")
				this.parserInicial(texto.get(0)); //parsea y añade el estado inicial al AFN
				texto.remove(0); //quita al nodo inicial del texto
				for(String linea : texto) {
					//se puede mejorar, deberia poner el formateo aqui*
					if(this.esTransicion(linea)) this.parserTransicion(linea);
					if(this.esEstadoFinal(linea)) this.parserFinal(linea);
				}
				
				return this.automata;
				
			} else {
				System.out.println("este automata no tiene nodo inicial :" +texto.get(2));
			}
		} else {
			System.out.println("este no es un automata valido");
		}
		
		return automata;
	}
	public boolean esTransicion(String cadena) { //HAY QUE CAMBIAR ESTO, ESTA AGARRADO DE LOS PELOS
		//q0->q1 [label="a"];
		String[] partes = cadena.split("->|\\[|\\]|\\s+|label=|\"|;|\\[shape=doublecircle\\]");
		if(partes.length == 6) return true;
		return false;
	}
	
	public boolean esEstadoFinal(String cadena) { //HAY QUE CAMBIAR ESTO, ESTA AGARRADO DE LOS PELOS
		//q2[shape=doublecircle];
		String[] partes = cadena.split("->|\\[|\\]|\\s+|label=|\"|;|\\[shape=doublecircle\\]");
		if(partes.length == 2) return true;
		return false;
	}
	public List<String> afn2text(AFN automata){
		texto.add("digraph{");
		texto.add("inic[shape=point];");
		texto.add("inic->"+automata.getEstadoInicial()+";");
		for(Transicion transicion : automata.getTransiciones()) {
			texto.add(transicion.getOrigen()+"->"+transicion.getDestino()+" [label=\""+transicion.getSimbolo()+"\"];");
		}
		for(String estadoFinal : automata.getEstadosFinales()) {
			texto.add(estadoFinal+"[shape=doublecircle];");
		}
		texto.add("}");
		return texto;
	}
}