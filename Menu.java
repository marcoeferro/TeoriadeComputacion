package tc.AutomataFinitoNoDeterministico;


import javax.swing.JOptionPane;

public class Menu {

	public int mostrarMenu() {
		int opcion = 0;

		String menu = "### MENU ###\n"
				+ "1) ingresar un AFN desde archivo de texto (AFN.txt), y convertirlo a un AFD\n"
				+ "2) a partir de dos automatas en archivos de texto(AFN.txt y AFN2.txt), unir ambos automatas\n"
				+ "3) a partir de dos automatas en archivos de texto(AFN.txt y AFN2.txt), concatenar ambos automatas\n"
				+ "4) ingresar un AFN desde archivo de texto, y devolver el automata clausura del mismo\n"
				+ "5) Probar si una cadena ingresada es aceptada por el automata guardado en un archivo de texto\n"
				+ "6) Crear un AFN a partir de una expresion regular\n"
				+ "#############\n"
				+ "Por favor, ingrese una opcion:";

		try {
			String input = JOptionPane.showInputDialog(null, menu, "Menu", JOptionPane.QUESTION_MESSAGE);
			opcion = Integer.parseInt(input);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Por favor, ingrese una opcion valida", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		return opcion;
	}
	
	public void ejecutarOpcion(int opcion) {
		TxtManager gestor = new TxtManager();
    	Converter converter = new Converter();
    	AFNUtils utils = new AFNUtils();
		switch(opcion) {
		case 1:
			AFN automata1 = converter.textToAFN(gestor.leer("/AFN.txt"));
			AFNtoDFA afn2dfa = new AFNtoDFA();
			DFA automataDeterminista = afn2dfa.convert(automata1);
			gestor.escribir(converter.afd2text(automataDeterminista), "/AFN-resultado.txt");
			JOptionPane.showMessageDialog(null,"Archivo guardado en : /AFN-resultado.txt");
			break;	
		case 2:
			AFN automata_a = converter.textToAFN(gestor.leer("/AFN.txt"));
			AFN automata_b = converter.textToAFN(gestor.leer("/AFN2.txt"));
			
			AFN automata_union = utils.union(automata_a, automata_b);
			gestor.escribir(converter.afn2text(automata_union),"/AFN-resultado.txt" );
			JOptionPane.showMessageDialog(null,"Archivo guardado en : /AFN-resultado.txt");
			break;
		case 3:
			AFN automata_1 = converter.textToAFN(gestor.leer("/AFN.txt"));
			AFN automata_2 = converter.textToAFN(gestor.leer("/AFN2.txt"));
			AFN automata_concatenado = utils.concatenate(automata_1, automata_2);
			gestor.escribir(converter.afn2text(automata_concatenado),"/AFN-resultado.txt" );
			JOptionPane.showMessageDialog(null,"Archivo guardado en : /AFN-resultado.txt");
			break;
		case 4:
			AFN kleeneInput = converter.textToAFN(gestor.leer("/AFN.txt"));
			AFN kleeneOutput = utils.kleeneStar(kleeneInput);
			gestor.escribir(converter.afn2text(kleeneOutput),"/AFN-resultado.txt");
			JOptionPane.showMessageDialog(null,"Archivo guardado en : /AFN-resultado.txt");
			break;
		case 5:
			AFN automataTest = converter.textToAFN(gestor.leer("/AFN.txt"));
			String cadena = JOptionPane.showInputDialog("ingrese la cadena a evaluar - por ejemplo: abb o 123");
	        if (automataTest.correrCadena(cadena)) {
	        	JOptionPane.showMessageDialog(null,"La cadena " + cadena + " es aceptada por el automata.");
	        } else {
	        	JOptionPane.showMessageDialog(null,"La cadena " + cadena + " NO es aceptada por el automata.");
	        }
			break;
		case 6:
			RegexToAfnAlt rgx2afn = new RegexToAfnAlt();
			//System.out.println("ingrese la expresion regular a convertir:"); //*(|(.ab)c)
			String regex = JOptionPane.showInputDialog("ingrese la expresion regular a convertir - por ejemplo: *(|(.ab)c)");
			AFN automataRegEx = rgx2afn.parseExpression(rgx2afn.StringtoQueue(regex));
			gestor.escribir(converter.afn2text(automataRegEx),"/AFN-resultado.txt");
			JOptionPane.showMessageDialog(null,"Archivo guardado en : /AFN-resultado.txt");
			break;
		default:
			JOptionPane.showMessageDialog(null,"opcion no valida","Error",JOptionPane.ERROR_MESSAGE);
			break;
		}
		
	}
	
}
