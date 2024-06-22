package tc.AutomataFinitoNoDeterministico;

import java.util.*;

public class DFA {
	    Set<Transicion> transiciones;
	    String estadoInicial;
	    Set<String> estadosFinales;

	    public DFA() {
	        this.transiciones = new HashSet<>();
	        this.estadosFinales = new HashSet<>();
	    }

	    public void addTransicion(String origen, String destino, Character simbolo) {
	        transiciones.add(new Transicion(origen,destino,simbolo));
	    }

	    public void addEstadoFinal(String state) {
	        estadosFinales.add(state);
	    }
	    public void addEstadoInicial(String estado) {
	    	estadoInicial = estado;
	    }
	    public Set<Transicion> getTransiciones(){
	    	return this.transiciones;
	    }
	    public Set<String> getEstadosFinales(){
	    	return this.estadosFinales;
	    }
	    public String getEstadoInicial() {
	    	return this.estadoInicial;
	    }
	    
}
