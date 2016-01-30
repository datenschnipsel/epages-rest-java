package sandbox;


import epages.Shop;


/**
 * Testclass for testing the framework.
 * @author Bastian
 */
public final class Test {


    /**
     * Private constructor of this class.
     */
    private Test() {
    }


    /**
     * Entrypoint to the program.
     * @param args Given arguments.
     */
    public static void main(final String[] args) {

        Shop shop = new Shop("http://unstable-main.epages.com/rs/shops/bklein/");
        System.out.println(shop.getProducts().toString());
    }
}
