public class AFNUtils {

    // Método para unión de dos AFNs (3.a)
    public AFN union(AFN a, AFN b) {
        AFN result = new AFN();

        for (Transicion transicion : a.getTransiciones()) {
            result.agregarTransicion(new Transicion(
                    "a_" + transicion.getOrigen(),
                    "a_" + transicion.getDestino(),
                    transicion.getSimbolo()));
        }
        for (Transicion transicion : b.getTransiciones()) {
            result.agregarTransicion(new Transicion(
                    "b_" + transicion.getOrigen(),
                    "b_" + transicion.getDestino(),
                    transicion.getSimbolo()));
        }

        String estadoInicialUnion = "q_inicial";
        result.agregarEstadoInicial(estadoInicialUnion);
        result.agregarTransicion(new Transicion(estadoInicialUnion, "a_" + a.getEstadoInicial(), 'e'));
        result.agregarTransicion(new Transicion(estadoInicialUnion, "b_" + b.getEstadoInicial(), 'e'));

        for (String estadoFinal : a.getEstadosFinales()) {
            result.agregarEstadoFinal("a_" + estadoFinal);
        }
        for (String estadoFinal : b.getEstadosFinales()) {
            result.agregarEstadoFinal("b_" + estadoFinal);
        }

        return result;
    }

    // Método para concatenación de dos AFNs (3.b)
    public AFN concatenate(AFN a, AFN b) {
        AFN result = new AFN();

        for (Transicion transicion : a.getTransiciones()) {
            result.agregarTransicion(new Transicion(
                    "a_" + transicion.getOrigen(),
                    "a_" + transicion.getDestino(),
                    transicion.getSimbolo()));
        }
        for (Transicion transicion : b.getTransiciones()) {
            result.agregarTransicion(new Transicion(
                    "b_" + transicion.getOrigen(),
                    "b_" + transicion.getDestino(),
                    transicion.getSimbolo()));
        }

        result.agregarEstadoInicial("a_" + a.getEstadoInicial());

        for (String estadoFinal : a.getEstadosFinales()) {
            result.agregarTransicion(new Transicion("a_" + estadoFinal, "b_" + b.getEstadoInicial(), 'e'));
        }

        for (String estadoFinal : b.getEstadosFinales()) {
            result.agregarEstadoFinal("b_" + estadoFinal);
        }

        return result;
    }

    // Método para clausura de Kleene
    public AFN kleeneStar(AFN a) {
        AFN result = new AFN();

        for (Transicion transicion : a.getTransiciones()) {
            result.agregarTransicion(new Transicion(
                    transicion.getOrigen(),
                    transicion.getDestino(),
                    transicion.getSimbolo()));
        }

        String estadoInicialKleene = "qk";
        result.agregarEstadoInicial(estadoInicialKleene);
        result.agregarEstadoFinal(estadoInicialKleene);

        result.agregarTransicion(new Transicion(estadoInicialKleene, a.getEstadoInicial(), 'e'));

        for (String estadoFinal : a.getEstadosFinales()) {
            result.agregarTransicion(new Transicion(estadoFinal, a.getEstadoInicial(), 'e'));
            result.agregarEstadoFinal(estadoFinal);
        }

        return result;
    }

}
