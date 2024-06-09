package tc.AutomataFinitoNoDeterministico;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class TxtManager {
	private String rutaArchivo;
	private String rutaArchivoSalida;
	private Path path;
	public TxtManager() {
		this.rutaArchivo = System.getProperty("user.home") + "/Desktop/AFN.txt";
		this.rutaArchivoSalida = System.getProperty("user.home") + "/Desktop/AFN-nuevo.txt";
		this.path = null;
	}
	public List<String> leer() {
		try {
			this.path = Paths.get(rutaArchivo);
			List<String> lineas = Files.readAllLines(path);
			return lineas;
		} catch (IOException e) {
			e.printStackTrace(System.out);
			return null;
		}
	}
	public void escribir(List<String> texto) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivoSalida))) {
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
