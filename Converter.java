package tc.AutomataFinitoNoDeterministico;

import java.util.*;

public class Converter {

	private AFN automata;

	public Converter() {
		this.automata = new AFN();
	}

	private void parserInicial(String linea) {
		// limpiador
		linea = linea.replace("inic->", "").replace(";", "");
		// agrega al AFN
		this.automata.agregarEstadoInicial(linea);
	}

	private void parserTransicion(String linea) {
		// limpiador
		linea = linea.replace(";", "").trim();
		String[] partes = linea.split("->| \\[label=");
		String origen = partes[0];
		String destino = partes[1];
		String simbolo = partes[2].replace("\"", "").replace("]", "");
		// agrega al AFN
		this.automata.agregarTransicion(new Transicion(origen, destino, simbolo.charAt(0)));

	}

	private void parserFinal(String linea) {
		// limpiador
		linea = linea.replace("[shape=doublecircle];", "");
		// agrega al AFN
		this.automata.agregarEstadoFinal(linea);
	}

	public AFN textToAFN(List<String> texto) {
		AFN a;
		List<String> textoFiltrado = new ArrayList<String>();
		for (String linea : texto) {
			if (!(linea.contains("digraph{") ||
					linea.contains("inic[shape=point];") ||
					linea.contains("rankdir=LR;") ||
					linea.contains("}"))) {
				textoFiltrado.add(linea);
			}
		}
		for (String linea : textoFiltrado) {
			if (linea.split("->")[0].equalsIgnoreCase("inic"))
				this.parserInicial(linea);
			else if (this.esTransicion(linea))
				this.parserTransicion(linea);
			else if (this.esEstadoFinal(linea))
				this.parserFinal(linea);
		}
		a = this.automata;
		this.automata = new AFN();
		return a;
	}

	public boolean esTransicion(String cadena) { // HAY QUE CAMBIAR ESTO, ESTA AGARRADO DE LOS PELOS
		// q0->q1 [label="a"];
		String[] partes = cadena.split("->|\\[|\\]|\\s+|label=|\"|;|\\[shape=doublecircle\\]");
		if (partes.length == 6)
			return true;
		return false;
	}

	public boolean esEstadoFinal(String cadena) { // HAY QUE CAMBIAR ESTO, ESTA AGARRADO DE LOS PELOS
		// q2[shape=doublecircle];
		String[] partes = cadena.split("->|\\[|\\]|\\s+|label=|\"|;|\\[shape=doublecircle\\]");
		if (partes.length == 2)
			return true;
		return false;
	}

	public List<String> afn2text(AFN automata) {
		List<String> texto = new ArrayList<String>();
		texto.add("digraph{");
		texto.add("inic[shape=point];");
		texto.add("rankdir=LR;");
		texto.add("inic->\"" + automata.getEstadoInicial() + "\";");
		for (Transicion transicion : automata.getTransiciones()) {
			texto.add("\""+transicion.getOrigen()+"\"" + "->\"" + transicion.getDestino() + "\" [label=\"" + transicion.getSimbolo()
					+ "\"];");
		}
		for (String estadoFinal : automata.getEstadosFinales()) {
			texto.add("\""+estadoFinal + "\"[shape=doublecircle];");
		}
		texto.add("}");
		return texto;
	}
	
	public List<String> afd2text(DFA automata) {
		List<String> texto = new ArrayList<String>();
		texto.add("digraph{");
		texto.add("inic[shape=point];");
		texto.add("rankdir=LR;");
		texto.add("inic->\"" + automata.getEstadoInicial() + "\";");
		for (Transicion transicion : automata.getTransiciones()) {
			texto.add("\""+transicion.getOrigen()+"\"" + "->\"" + transicion.getDestino() + "\" [label=\"" + transicion.getSimbolo()
					+ "\"];");
		}
		for (String estadoFinal : automata.getEstadosFinales()) {
			texto.add("\""+estadoFinal + "\"[shape=doublecircle];");
		}
		texto.add("}");
		return texto;
	}

}