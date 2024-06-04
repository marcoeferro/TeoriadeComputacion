import java.util.*;

class Estado {
    private String nombre;
    private boolean esInicial;
    private boolean esFinal;

    public Estado(String nombre, boolean esInicial, boolean esFinal) {
        this.nombre = nombre;
        this.esInicial = esInicial;
        this.esFinal = esFinal;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean esInicial() {
        return esInicial;
    }

    public boolean esFinal() {
        return esFinal;
    }
}

class Transicion {
    private Estado origen;
    private Estado destino;
    private char simbolo;

    public Transicion(Estado origen, Estado destino, char simbolo) {
        this.origen = origen;
        this.destino = destino;
        this.simbolo = simbolo;
    }

    public Estado getOrigen() {
        return origen;
    }

    public Estado getDestino() {
        return destino;
    }

    public char getSimbolo() {
        return simbolo;
    }
}

class NFA {
    private Set<Estado> estados;
    private Set<Transicion> transiciones;
    private Estado estadoInicial;
    private Set<Estado> estadosFinales;
    private Set<Character> alfabeto;

    public NFA() {
        estados = new HashSet<>();
        transiciones = new HashSet<>();
        estadosFinales = new HashSet<>();
        alfabeto = new HashSet<>();
    }

    public void agregarEstado(Estado estado) {
        estados.add(estado);
        if (estado.esInicial()) {
            estadoInicial = estado;
        }
        if (estado.esFinal()) {
            estadosFinales.add(estado);
        }
    }

    public void agregarTransicion(Transicion transicion) {
        transiciones.add(transicion);
        alfabeto.add(transicion.getSimbolo());
    }

    public Set<Estado> mover(Set<Estado> estados, char simbolo) {
        Set<Estado> resultado = new HashSet<>();
        for (Estado estado : estados) {
            for (Transicion transicion : transiciones) {
                if (transicion.getOrigen().equals(estado) && transicion.getSimbolo() == simbolo) {
                    resultado.add(transicion.getDestino());
                }
            }
        }
        return resultado;
    }

    public boolean aceptar(String cadena) {
        Set<Estado> estadosActuales = new HashSet<>();
        estadosActuales.add(estadoInicial);

        for (char simbolo : cadena.toCharArray()) {
            estadosActuales = mover(estadosActuales, simbolo);
        }

        for (Estado estado : estadosActuales) {
            if (estado.esFinal()) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        // Crear estados
        Estado q0 = new Estado("q0", true, false);
        Estado q1 = new Estado("q1", false, true);
        Estado q2 = new Estado("q2", false, true);

        // Crear autómata
        NFA nfa = new NFA();
        nfa.agregarEstado(q0);
        nfa.agregarEstado(q1);
        nfa.agregarEstado(q2);

        // Crear transiciones
        nfa.agregarTransicion(new Transicion(q0, q1, 'a'));
        nfa.agregarTransicion(new Transicion(q0, q2, 'a'));
        nfa.agregarTransicion(new Transicion(q1, q1, 'b'));
        nfa.agregarTransicion(new Transicion(q2, q2, 'b'));

        // Probar la cadena
        String cadena = "ab";
        if (nfa.aceptar(cadena)) {
            System.out.println("La cadena " + cadena + " es aceptada por el autómata.");
        } else {
            System.out.println("La cadena " + cadena + " no es aceptada por el autómata.");
        }
    }
}
