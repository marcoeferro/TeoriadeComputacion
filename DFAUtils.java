package tc.AutomataFinitoNoDeterministico;

class DFAUtils {
    public static boolean pertenece(DFA dfa, String s) {
        String currentState = dfa.getEstadoInicial();
        for (char c : s.toCharArray()) {
            boolean transicionEncontrada = false;
            for (Transicion transicion : dfa.getTransiciones()) {
                if (transicion.getOrigen().equals(currentState) && transicion.getSimbolo() == c) {
                    currentState = transicion.getDestino();
                    transicionEncontrada = true;
                    break;
                }
            }
            if (!transicionEncontrada) {
                return false; // no hay transición definida para este carácter
            }
        }
        return dfa.getEstadosFinales().contains(currentState);
    }
}
