package epages;


import java.util.ArrayList;
import java.util.List;


import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.SyncInvoker;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status.Family;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


/**
 * Represents a epages Shop.
 *
 * @author Bastian
 */
public class Shop {


    private final String baseUrl;
    private final Client client;


    /**
     * Constructor of this object.
     *
     * @param epagesBaseUrl
     *            to the epages shop.
     */
    public Shop(final String epagesBaseUrl) {

        this.baseUrl = epagesBaseUrl;
        client = ClientBuilder.newClient();
    }

    /**
     * This function gets all products of the first page.
     *
     * @return List of products.
     */
    public List<Product> getProducts() {

        final WebTarget resource = client.target(baseUrl + "products/");
        final Invocation.Builder request = resource.request();
        request.accept(MediaType.APPLICATION_JSON);
        JSONObject products = null;

        final Response response = ((SyncInvoker) request).get();
        if (response.getStatusInfo().getFamily() == Family.SUCCESSFUL) {
            System.out.println("Success! " + response.getStatus());
            System.out.println(response.getEntity());
            try {
                products = (JSONObject) new JSONParser().parse(response.readEntity(String.class));
            } catch (final ParseException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("ERROR! " + response.getStatus());
            System.out.println(response.getEntity());
            products = null;
        }
        final ArrayList<Product> listOfProducts = new ArrayList<Product>();
        final JSONArray items = (JSONArray) products.get("items");

        for (int i = 0; i < items.size() - 1; i++) {
            listOfProducts.add(new Product((JSONObject) items.get(i)));
        }
        return listOfProducts;
    }
}
