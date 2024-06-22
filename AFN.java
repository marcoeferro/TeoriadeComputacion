package tc.AutomataFinitoNoDeterministico;

import java.util.*;

public class AFN {
	private Set<Transicion> transiciones;
	private String estadoInicial;
	private Set<String> estadosFinales;

	public AFN() {
		transiciones = new HashSet<>(); // conjunto de transiciones
		estadosFinales = new HashSet<>(); // conjunto de estados finales
	}

	public AFN(Character c) {
		this.transiciones = new HashSet<>();
		this.estadosFinales = new HashSet<>();
		this.estadoInicial = "q0_"+c;
		this.estadosFinales.add("qf_"+c);
		this.transiciones.add(new Transicion("q0_"+c,"qf_"+c, c));
	}

	public String getEstadoInicial() {
		return this.estadoInicial;
	}

	public Set<String> getEstadosFinales() {
		return this.estadosFinales;
	}

	public Set<Transicion> getTransiciones() {
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

	public Set<String> getEstados() {
		Set<String> estados = new HashSet<String>();
		for (Transicion t : transiciones) {
			estados.add(t.getOrigen());
			estados.add(t.getDestino());
		}
		return estados;
	}

	public Set<Character> getAlfabeto() {
		Set<Character> alfabeto = new HashSet<Character>();
		for (Transicion t : transiciones)
			alfabeto.add(t.getSimbolo());
		return alfabeto;
	}

	public Set<String> stepState(Set<String> estados, Character simbolo) {
		Set<String> resultado = new HashSet<>(); // aqui vamos a poner los posibles estados "destino" de cada transicion
		for (String estado : estados) {
			for (Transicion transicion : transiciones) {
				if (transicion.getOrigen().equals(estado) && transicion.getSimbolo() == simbolo) {
					resultado.add(transicion.getDestino());
				} else if (transicion.getOrigen().equals(estado) && transicion.getSimbolo() == 'e') {
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
			estadosActuales = stepState(estadosActuales, simbolo);
		}

		for (String estado : estadosActuales) {
			for (String estadoFinal : estadosFinales) {
				if (estado.equals(estadoFinal)) {
					return true;
				}
			}

		}
		return false;
	}

}
