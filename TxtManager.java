package tc.AutomataFinitoNoDeterministico;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class TxtManager {
	private Path path;
	public TxtManager() {
		//this.rutaArchivoSalida = System.getProperty("user.dir") + "/AFN-nuevo.txt";
		this.path = null;
	}
	public List<String> leer(String ruta) {
		try {
			this.path = Paths.get(System.getProperty("user.dir") + ruta);
			List<String> lineas = Files.readAllLines(path);
			System.out.println("archivo en "+ruta+"leido exitosamente");
			return lineas;
		} catch (IOException e) {
			e.printStackTrace(System.out);
			return null;
		}
	}
	public void escribir(List<String> texto,String ruta) {
		ruta = (System.getProperty("user.dir") + ruta);
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(ruta))) {
            for (String linea : texto) {
                writer.write(linea);
                writer.newLine();
            }
            //System.out.println("Archivo guardado en: " + rutaArchivoSalida);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
