import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.TreeMap;
import java.util.Map;

/**
 *
 * @param <K> El tipo de dato de la llave
 * @param <V> El tipo de dato del valor
 */
public class MapFactory<K,V>{

    /**
     * Devuelve un tipo de Mapa segun su parametro
     * @param option la opcion elegida por el usuario
     * @return un mapa, ya sea de tipo HashMap, TreeMap, o LinkedHashMap
     */
    public  Map<K, V> factory(int option){
        switch(option){
            case 1:
                return new HashMap<K,V>();
            case 2:
                return new TreeMap<K,V>();
            case 3:
                return new LinkedHashMap<K,V>();
            default:
                throw new IllegalArgumentException();
        }
    }
}
