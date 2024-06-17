package tc.AutomataFinitoNoDeterministico;

import java.util.List;

/**
 * Proyecto de Teoria de la computacion - Ingenieria en Sistemas de Informacion
 * Lautaro Galeano y Marco Ferro
 */
public class App 
{
    public static void main( String[] args )
    {
    	AFN automata,automata2;
    	TxtManager gestor = new TxtManager();
    	Converter converter = new Converter();
		automata = converter.textToAFN(gestor.leer("/Desktop/AFN.txt"));
		automata2 = converter.textToAFN(gestor.leer("/Desktop/AFN2.txt"));
		DFA automataDeterminista;
		AFN kleene = converter.kleeneStar(automata);
		//test escritura
		gestor.escribir(converter.afn2text(kleene),"/Desktop/AFN-nuevo.txt");
		
		AFNtoDFA conversor = new AFNtoDFA();
		automataDeterminista = conversor.convert(automata);
		
        // Probar la cadena
        String cadena = "abb";
        if (automata.correrCadena(cadena)) {
            System.out.println("La cadena " + cadena + " es aceptada por el automata.");
        } else {
            System.out.println("La cadena " + cadena + " no es aceptada por el automata.");
        }
    }
}
