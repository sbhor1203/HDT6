import java.util.InputMismatchException;
import java.util.Scanner;


public class Vista {
    private Scanner scanner;

    /**
     * Metodo contructor de la clase
     */
    public Vista(){
        scanner = new Scanner(System.in);
    }


    /**
     * Abvreviacion de System.out.println
     * @param message mensaje que se desea imprimir
     */
    private void print(String message){
        System.out.println(message);
    }


    /**
     * Menu de opciones funcionales para el usuario
     * @return devuelve el numero de opcion seleccionada por el usuario
     */
    public int mainOptionsMenu(){
        int option = 0;

        print("MENU:\n"+
                "1. Agregar un producto\n" +
                "2. Mostrar categoria de producto\n"+
                "3. Mostrar datos de un producto\n" +
                "4. Mostrar datos de carrito de compras ordenados por tipo\n" +
                "5. Mostrar producto y categoria ordenado por tipo \n" +
                "6. Salir\n");
        System.out.print("Ingrese una opcion:");

        try{
            option = scanner.nextInt();
            scanner.nextLine();

            if(option > 6 || option < 1){
                throw new IndexOutOfBoundsException();
            }

        }catch (InputMismatchException e){
            print("Por favor ingrese un numero \n");
            scanner.nextLine();
            option = mainOptionsMenu();

        }catch (IndexOutOfBoundsException e){
            print("Por favor ingres un numero que corresponda a las opciones indicadas\n");
            option = mainOptionsMenu();
        }

        return option;
    }


    /**
     * Le pregunta al usuario el producto y su categoria
     * @return devuelve un String[] cuyo primer indice es el nombre del producto y el segundo su categoria
     */
    public String[] askProductAndCategory(){
        print("Ingrese el nombre del producto que desea agregar: ");
        String name = scanner.nextLine();
        print("Ingrese la categoria del producto: ");
        String category = scanner.nextLine();

        String[] answer = {name, category};
        return answer;
    }


    /**
     * Le pregunta al usuario acerca de la cantidad de un producto
     * @return devuelve la cantidad ingresada por el usuario
     */
    public int askProductQuantity(){
        int option = 0;
        print("Ingrese la cantidad del producto: ");

        try{
            option = scanner.nextInt();
            scanner.nextLine();
        }catch (InputMismatchException e){
            print("Favor ingresar un numero entero \n");
            scanner.nextLine();

            option = askProductQuantity();
        }

        return option;
    }


    /**
     * Menu de opciones
     * @return devuelve la opcion ingresada por el usuario
     */
    public int menuOpciones(){
        int option = 0;

        System.out.print("Bienvenido a el programa, elija el tipo de estructura de datos que quiere usar:\n"+
                "1. HashMap\n"+
                "2. TreeMap\n" +
                "3. LinkedHashMap\n");
        System.out.print("Ingrese una opcion: ");

        try{
            option = scanner.nextInt();
            scanner.nextLine();

            if(option > 3 || option < 1){
                throw new IndexOutOfBoundsException();
            }

        }catch (InputMismatchException e){
            print("Por favor ingrese un numero \n");
            scanner.nextLine();
            option = menuOpciones();

        }catch (IndexOutOfBoundsException e){
            print("Por favor ingres un numero que corresponda a las opciones indicadas\n");
            option = menuOpciones();
        }

        return option;
    }


    /**
     * Le pregunta al usuario el nombre del producto
     * @return devuelve un String
     */
    public String askProductName(){
        print("Ingrese el nombre del producto: ");
        String productName = scanner.nextLine();
        return productName;
    }


    /**
     * Despliega la categoria y la cantidad en pantalla
     * @param category la categoria del producto
     * @param quantity la cantidad del producto
     */
    public void displayProductData(String category, String quantity){
        print("La categoria del producto es: " + category);
        print("La cantidad del producto es: " + quantity);
    }


    /**
     * Despliega la categoria de un producto
     * @param category la categoria de un producto
     */
    public void displayCategory(String category){
        print("La categoria del producto que ingreso es: " + category);
    }


    /**
     * Le despliega al usuario el error al ingresar una categoria inexistente
     */
    public void notACategoryError(){
        print("La categoria que usted ingreso no existe, intente de nuevo \n");
    }


    /**
     * Se despliaga al usuario el error de ingresar un producto inexistente
     */
    public void notAProductError(){
        print("El producto que usted ingreso no existe, intente de nuevo \n");
    }

    public String getPath(){

        String path = scanner.nextLine();
        return path;
    }
}
