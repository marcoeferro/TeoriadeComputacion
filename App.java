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
		AFNUtils util = new AFNUtils();
		AFN kleene = util.kleeneStar(automata);
		//test escritura
		
		AFNtoDFA conversor = new AFNtoDFA();
		//automataDeterminista = conversor.convert(automata);
		
		// Test REGEXtoAFN
		
		RegexToAfnAlt rgx2afn = new RegexToAfnAlt();
		automata2 = rgx2afn.parseExpression(rgx2afn.StringtoQueue("*(|(.ab)c)"));
		
		gestor.escribir(converter.afn2text(automata2),"/Desktop/AFN-nuevo.txt");
        // Probar la cadena
		
        /*String cadena = "abb";
        if (automata.correrCadena(cadena)) {
            System.out.println("La cadena " + cadena + " es aceptada por el automata.");
        } else {
            System.out.println("La cadena " + cadena + " no es aceptada por el automata.");
        }*/
    }
}
