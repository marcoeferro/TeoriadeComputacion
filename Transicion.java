package tc.AutomataFinitoNoDeterministico;

public class Transicion {
	private String origen;
    private String destino;
    private Character simbolo;

    public Transicion(String origen, String destino, Character simbolo) {
        this.origen = origen;
        this.destino = destino;
        this.simbolo = simbolo;
    }

    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
    }

    public Character getSimbolo() {
        return simbolo;
    }
}
