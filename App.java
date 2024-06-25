package tc.AutomataFinitoNoDeterministico;

/**
 * Proyecto de Teoria de la computacion - Ingenieria en Sistemas de Informacion
 * Lautaro Galeano y Marco Ferro
 */
public class App 
{
    public static void main( String[] args )
    {
    	Menu menu = new Menu();
    	
    	int opcion = menu.mostrarMenu();
    	menu.ejecutarOpcion(opcion);
    	
    }
}
