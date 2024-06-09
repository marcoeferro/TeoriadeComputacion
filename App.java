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
    	AFN automata;
    	TxtManager gestor = new TxtManager();
    	List<String> lineas = gestor.leer();
    	Converter converter = new Converter();
		automata = converter.textToAFN(lineas);
    	
		//test
		gestor.escribir(converter.afn2text(automata));
		
        // Probar la cadena
        String cadena = "abb";
        if (automata.correrCadena(cadena)) {
            System.out.println("La cadena " + cadena + " es aceptada por el automata.");
        } else {
            System.out.println("La cadena " + cadena + " no es aceptada por el automata.");
        }
    }
}
