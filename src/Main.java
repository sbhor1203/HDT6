public class Main {
    private static final String PATH = "PON EL PATH AQUI";

    /**
     * Driver program
     * @param argas
     */
    public static void main(String[] argas){
        HashMapController controller = new HashMapController(PATH);
        controller.run();
    }
}
