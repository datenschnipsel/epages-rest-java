package epages;


import java.util.ArrayList;
import java.util.List;


import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.SyncInvoker;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status.Family;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


/**
 * Represents a epages Shop.
 *
 * @author Bastian Klein(bastianklein92@gmail.com)
 */
public class Shop {


    private final String baseUrl;
    private final Client client;
    private final String token;


    /**
     * Constructor of this object.
     *
     * @param epagesBaseUrl
     *            Base url to the epages shop.
     * @param accessToken
     *            The credentials for accessing the API.
     */
    public Shop(final String epagesBaseUrl, final String accessToken) {

        if (epagesBaseUrl.charAt(epagesBaseUrl.length() - 1) != '/') {
            this.baseUrl = epagesBaseUrl + "/";
        } else {
            this.baseUrl = epagesBaseUrl;
        }
        this.client = ClientBuilder.newClient();
        this.token = accessToken;
    }

    /**
     * Generates a new request.
     *
     * @param target
     *            Target to be retrieved.
     * @return A new request.
     */
    private Invocation.Builder getRequest(final String target) {

        final WebTarget resource = client.target(baseUrl + target);
        final Invocation.Builder request = resource.request();
        request.accept("application/vnd.epages.v1+json");
        request.header("Authorization", "Bearer " + token);
        request.header("Content-Type", "application/json");

        return request;
    }

    /**
     * This function gets all products of the first page.
     *
     * @return List of products.
     */
    public List<Product> getProducts() {

        JSONObject products = null;
        final Invocation.Builder request = this.getRequest("products/");
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

    /**
     * Delete a product by its product id.
     *
     * @param productID
     *            Product ID of the product.
     */
    public void deleteProduct(final String productID) {

        final Invocation.Builder request = this.getRequest("products/" + productID);
        final Response response = ((SyncInvoker) request).delete();
        if (response.getStatusInfo().getFamily() == Family.SUCCESSFUL) {
            System.out.println("Success! " + response.getStatus());
            System.out.println(response.toString());
        } else {
            System.out.println("ERROR! " + response.getStatus());
            System.out.println(response.toString());
        }
    }
}
