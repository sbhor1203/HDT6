import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {
    private ArrayList<String> lines = new ArrayList<String>(29);


    /**
     *Lee el archivo .txt
     */
    public FileManager(String path){
        String fileName = path;
        try (Scanner scanner = new Scanner(new File(fileName))) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lines.add(line);
            }

        } catch (FileNotFoundException e) {
            System.out.println("IMPORTANTE!:");
            System.out.println("El path en el programa no ha funcionado, configura la variable PATH en la clase Main\n");
            System.err.format("FileNotFoundException: %s%n", e);
        }
    }


    /**
     * Devuelve cada linea en un arreglo de Strings
     * @return
     */
    private ArrayList<String> getLines(){
        return lines;
    }


    /**
     * Toma cada linea, y las separa en dos indices. El primero es la llave y el segundo el valor
     * @return devuelve un arreglo de Strings
     */
    public ArrayList<String[]> getKeyValueArray(){
        ArrayList<String> lines = getLines();
        ArrayList<String[]> result = new ArrayList<String[]>();

        for (String line: lines) {
            String[] element = line.split("\\|");
            element[1] = element[1].strip();
            result.add(element);
        }

        return result;
    }
}


