import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FileManagerTest {
    @Test
    public void getKeyValueArrayTest(){
        FileManager manager = new FileManager("/Users/gustavogonzalez/Universidad/Tutorias/HDTSamBohr/HDT6/src/ListadoProducto.txt");
        ArrayList<String[]> lineas = manager.getKeyValueArray();

        for (String[] par: lineas) {
            System.out.println(par[1]);
            System.out.println(par[0]);
            System.out.println("\n");
        }
    }
}