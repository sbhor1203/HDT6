import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class HashMapController {
    private String path;
    private FileManager manager;
    private Vista vista;
    private MapFactory<String,Integer> inventoryFactory;
    private MapFactory<String,String> categoryFactory;
    private MapFactory<String,Map<String,Integer>> wholeFactory;
    private Map<String,Map<String,Integer>> shopKartMap;
    private Map inventoryMap;
    private Map categoryMap;
    private Map wholeMap;
    private boolean toRun = true;
    private int mapOption;


    /**
     * Metodo constructor
     * @param path direccion del archivo txt en el disco duro
     */
    public HashMapController(String path){
        this.path = path;
        manager = new FileManager(this.path);
        vista = new Vista();
        inventoryFactory = new MapFactory<String,Integer>();
        categoryFactory = new MapFactory<String,String>();
        wholeFactory = new MapFactory<String,Map<String,Integer>>();
    }


    /**
     * Funcion que comienza la ejecucion del programa
     */
    public void run(){
        mapOption = vista.menuOpciones();
        ArrayList<String[]> keyValueArr = manager.getKeyValueArray();
        inventoryMap = inventoryFactory.factory(mapOption);
        categoryMap = categoryFactory.factory(mapOption);
        wholeMap = wholeFactory.factory(mapOption);
        shopKartMap = wholeFactory.factory(mapOption);

        initialInsertion(keyValueArr);
        menuManager();
    }


    /**
     * Maneja el menu de opciones funcionales
     */
    private void menuManager(){
        while (toRun){
            switch (vista.mainOptionsMenu()){
                case 1:
                    String[] answer = vista.askProductAndCategory();
                    int quantity = vista.askProductQuantity();
                    addProduct(answer[0],answer[1],quantity);
                    break;

                case 2:
                    String productName = vista.askProductName();
                    if(categoryMap.containsKey(productName)) {
                        vista.displayCategory(getProductCategory(productName));
                    }else{
                        vista.notAProductError();
                    }
                    break;

                case 3:
                    String productName2 = vista.askProductName();
                    if (categoryMap.containsKey(productName2) && inventoryMap.containsKey(productName2)){
                        String[] productData = getProductData(productName2);
                        vista.displayProductData(productData[0], productData[1]);
                    }else{
                        vista.notAProductError();
                    }
                    break;

                case 4:
                    orderedByTypeData(true, shopKartMap);
                    break;

                case 5:
                    orderedByTypeData(false, wholeMap);
                    break;

                case 6:
                    toRun = false;
                    break;
            }
        }
    }


    /**
     * Ordena los datos de los mapas por tipo
     * @param showQuantity determina si se va a mostrar la cantidad
     * @param mapa un mapa cuyo valor es otro mapa de tipo Map<String,Integer>
     */
    private void orderedByTypeData(boolean showQuantity, Map<String,Map<String,Integer>> mapa){
        if (showQuantity) {
            for (Object key : mapa.keySet()) {
                Map<String, Integer> value = (Map<String, Integer>) mapa.get(key);
                System.out.println(key + " = " + value); //TODO usar metodo de vista
            }
        }else{
            for (Object key : mapa.keySet()) {
                Map<String, Integer> map2 = (Map<String, Integer>) mapa.get(key);
                System.out.println(key);
                for (Object key2 : map2.keySet()){
                    System.out.println("\t" + key2);
                }
            }
        }
    }


    /**
     * Maneja el requisito del usuario de pedir la categoria de un producto
     * @param product el nombre del producto
     */
    private String getProductCategory(String product){
        String category = "";
        category = (String) categoryMap.get(product);
        return category;
    }


    /**
     * Devuelve una lista con la informacion de un producto determinado
     * @param product nombre del producto
     * @return String[] cuyo primer indice es la categoria y el segundo cantidad
     */
    private String[] getProductData(String product){
        String category = (String) categoryMap.get(product);
        String quantity = (String) inventoryMap.get(product);
        String[] productData = {category,quantity};

        return productData;
    }


    /**
     * Agrega un producto, con su categoria y las cantidades en existencia
     * @param productName  nombre del producto
     * @param category categoria del producto
     * @param quantity cantidad del producto en existencia
     */
    private void addProduct(String productName, String category, int quantity){
        if(wholeMap.containsKey(category)){
            Map<String,Integer> productos = (Map<String,Integer>) wholeMap.get(category);
            if(productos.containsKey(productName)){
                insertion(productName,category,quantity);
            }else{
                vista.notAProductError();
            }
        }else{
            vista.notACategoryError();
        }
    }


    /**
     * Agrega los valores del archivo txt al HashMap
     * @param keyValArray ArrayList de String[] que en su primer indice indica la categroia y en el segundo el producto
     */
    private void initialInsertion(ArrayList<String[]> keyValArray){

        for (String[] keyValueElement: keyValArray) {
            keyValueElement[0] = keyValueElement[0].strip();
            keyValueElement[1] = keyValueElement[1].strip();

            insertion(keyValueElement[1],keyValueElement[0]);
            insertion(keyValueElement[1], 1); //Todos los productos tienen una cantidad inicial de 1

            //Si la primer llave existe
            if (wholeMap.containsKey(keyValueElement[0])){
                Map<String, Integer> mapaProdQuant = (Map<String, Integer>) wholeMap.get(keyValueElement[0]);
                //Si la segunda llave no existe
                if(!mapaProdQuant.containsKey(keyValueElement[1])){
                    mapaProdQuant.put(keyValueElement[1], 1);
                }
            //Si primera llave no existe
            }else{
                Map<String, Integer> mapaProdQuant = inventoryFactory.factory(mapOption);
                mapaProdQuant.put(keyValueElement[1],1);
                wholeMap.put(keyValueElement[0],mapaProdQuant);
            }
        }
    }


    /**
     * Inserta un valor al mapa de categorias
     * @param key String llave, nombre del producto
     * @param value String valor, categoria
     */
    private void insertion(String key, String value){
        categoryMap.put(key,value);
    }


    /**
     * Inserta un valor al mapa de inventario
     * @param key String llave, nombre del producto
     * @param value Integer valor, cantidad de producto en existencia
     */
    private void insertion(String key, Integer value){
        inventoryMap.put(key,value);
    }


    /**
     * Inserta los valores al mapa
     * @param category categoria del producto
     * @param product nombre del producto
     * @param quantity cantidad del producto
     */
    private void insertion(String category, String product, int quantity) {
        if (shopKartMap.containsKey(category)){
            Map<String,Integer> ProductQuantityMap = (Map<String,Integer>) shopKartMap.get(category);
            if (ProductQuantityMap.containsKey(product)){
                Integer cantidad = ProductQuantityMap.get(product);
                cantidad = cantidad + quantity; //Se agrega una unidad
                ProductQuantityMap.put(product,cantidad);
            }
            ProductQuantityMap.put(product, quantity);
        }else{
            Map<String,Integer> ProductQuantityMap = inventoryFactory.factory(mapOption);
            ProductQuantityMap.put(product, 1);
            shopKartMap.put(category,ProductQuantityMap);
        }
    }
}
