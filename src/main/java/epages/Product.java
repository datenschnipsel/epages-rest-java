package epages;


import org.json.simple.JSONObject;


/**
 * This Class represents a product of an epages shop.
 * @author Bastian
 *
 */
public class Product extends JSONObject {


    /**
     * Serial version ID.
     */
    private static final long serialVersionUID = 1;

    /**
     * Constructor of this class.
     * @param product The product as JSONObject.
     */
    public Product(final JSONObject product) {
        super(product);
    }
}
