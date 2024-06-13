import java.util.*;

public class AFN {
    private Set<String> estados; 
    private Set<Transicion> transiciones;
    private String estadoInicial;
    private Set<String> estadosFinales;
    private Set<Character> alfabeto;

    public AFN() {
        estados = new HashSet<>(); 
        transiciones = new HashSet<>(); 
        estadosFinales = new HashSet<>(); 
        alfabeto = new HashSet<>(); 
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
        alfabeto.add(transicion.getSimbolo());
    }

    public Set<String> stepState(Set<String> estados, char simbolo){
        Set<String> resultado = new HashSet<>(); 
        for (String estado : estados) {
            for (Transicion transicion : transiciones) {
                if (transicion.getOrigen().equals(estado) && transicion.getSimbolo() == simbolo) {
                    resultado.add(transicion.getDestino());
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
        }

        for(String estado : estadosActuales) {
            if(estadosFinales.contains(estado)) {
                return true;
            }
        }
        return false;
    }

    // Método para unión de dos AFNs
    public static AFN union(AFN a, AFN b) {
        AFN result = new AFN();

        result.alfabeto.addAll(a.alfabeto);
        result.alfabeto.addAll(b.alfabeto);

        result.estados.addAll(a.estados);
        result.estados.addAll(b.estados);

        String newInitialState = "q_union_initial";
        result.agregarEstado(newInitialState);
        result.agregarEstadoInicial(newInitialState);

        for (String finalState : a.estadosFinales) {
            result.agregarEstadoFinal(finalState);
        }
        for (String finalState : b.estadosFinales) {
            result.agregarEstadoFinal(finalState);
        }

        result.transiciones.addAll(a.transiciones);
        result.transiciones.addAll(b.transiciones);

        result.agregarTransicion(new Transicion(newInitialState, a.estadoInicial, null));
        result.agregarTransicion(new Transicion(newInitialState, b.estadoInicial, null));

        return result;
    }

    // Método para concatenación de dos AFNs
    public static AFN concatenate(AFN a, AFN b) {
        AFN result = new AFN();

        result.alfabeto.addAll(a.alfabeto);
        result.alfabeto.addAll(b.alfabeto);

        result.estados.addAll(a.estados);
        result.estados.addAll(b.estados);

        result.agregarEstadoInicial(a.estadoInicial);
        
        for (String finalState : a.estadosFinales) {
            result.transiciones.add(new Transicion(finalState, b.estadoInicial, null));
        }

        result.estadosFinales.addAll(b.estadosFinales);

        result.transiciones.addAll(a.transiciones);
        result.transiciones.addAll(b.transiciones);

        return result;
    }

    // Método para clausura de Kleene
    public static AFN kleeneStar(AFN a) {
        AFN result = new AFN();

        result.alfabeto.addAll(a.alfabeto);

        result.estados.addAll(a.estados);

        String newInitialState = "q_kleene_initial";
        result.agregarEstado(newInitialState);
        result.agregarEstadoInicial(newInitialState);
        result.agregarEstadoFinal(newInitialState);

        result.transiciones.addAll(a.transiciones);

        result.agregarTransicion(new Transicion(newInitialState, a.estadoInicial, null));

        for (String finalState : a.estadosFinales) {
            result.agregarTransicion(new Transicion(finalState, a.estadoInicial, null));
            result.agregarEstadoFinal(finalState);
        }

        return result;
    }
}