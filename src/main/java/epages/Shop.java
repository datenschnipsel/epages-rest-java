package epages;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.SyncInvoker;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status.Family;


import org.glassfish.jersey.client.HttpUrlConnectorProvider;
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
    protected Invocation.Builder getRequest(final String target) {

        final WebTarget resource = client.target(baseUrl + target);
        final Invocation.Builder request = resource.request();
        request.accept("application/vnd.epages.v1+json");
        request.header("Authorization", "Bearer " + token);
        request.header("Content-Type", MediaType.APPLICATION_JSON);

        return request;
    }

    /**
     * Generates a new patch request.
     *
     * @param target
     *            Target to be retrieved.
     * @return A new patch request.
     */
    protected Invocation.Builder getPatchRequest(final String target) {

        client.property(HttpUrlConnectorProvider.SET_METHOD_WORKAROUND, true);
        final WebTarget resource = client.target(baseUrl + target);
        final Invocation.Builder request = resource.request();
        request.accept(MediaType.APPLICATION_JSON);
        request.header("Authorization", "Bearer " + token);
        request.header("Content-Type", MediaType.APPLICATION_JSON);
        request.header("X-HTTP-Method-Override", "PATCH");

        return request;
    }

    /**
     * This function gets all products of the first page.
     *
     * @return List List of products.
     */
    public List<Product> getFirstProductPage() {

        final Invocation.Builder request = this.getRequest("products/");
        final Response response = ((SyncInvoker) request).get();

        return createProducts(response);
    }

    /**
     * Gets a list with the products on a given page.
     *
     * @param pageNumber
     *            Number of the product page.
     * @return List of products.
     */
    public List<Product> getProductPage(final int pageNumber) {

        final Invocation.Builder request = this.getRequest("products?page=" + pageNumber);
        final Response response = ((SyncInvoker) request).get();

        return createProducts(response);
    }

    /**
     * Gets a list with the products on a given page.
     *
     * @param pageNumber
     *            Number of the product page.
     * @param resultsPerPage
     *            Number of results per page (max. 100).
     * @return List of products.
     */
    public List<Product> getProductPage(final int pageNumber, final int resultsPerPage) {

        final Invocation.Builder request = this
                .getRequest("products?page=" + pageNumber + "&resultsPerPage=" + resultsPerPage);
        final Response response = ((SyncInvoker) request).get();

        return createProducts(response);
    }

    /**
     * Gets a list with the products on a given page.
     *
     * @param queryParams
     *            Every query parameters that can be applied
     * @return List of products.
     * @see https://developer.epages.com/apps/api-reference/get-shops-shopid-
     *      products.html
     */
    public List<Product> getProductPageWithQueryParams(final HashMap<String, String> queryParams) {

        final boolean isLastParam = false;
        final StringBuilder sb = new StringBuilder("products");

        if (queryParams.size() > 0) {
            sb.append("?");
            for (final String key : queryParams.keySet()) {
                sb.append(key + "=");
                sb.append(queryParams.get(key));
                if (!isLastParam) {
                    sb.append("&");
                }
            }
        }

        final Invocation.Builder request = this.getRequest(sb.toString());
        final Response response = ((SyncInvoker) request).get();

        return createProducts(response);
    }

    /**
     * Creates a list of products from a HTTP response.
     *
     * @param response
     *            HTTP response.
     * @return A list of products.
     */
    protected List<Product> createProducts(final Response response) {

        JSONObject products = null;

        if (response.getStatusInfo().getFamily() == Family.SUCCESSFUL) {
            try {
                products = (JSONObject) new JSONParser().parse(response.readEntity(String.class));
            } catch (final ParseException e) {
                e.printStackTrace();
            }
        } else {
            products = null;
        }
        final ArrayList<Product> listOfProducts = new ArrayList<Product>();
        final JSONArray items = (JSONArray) products.get("items");

        for (int i = 0; i < items.size() - 1; i++) {
            listOfProducts.add(new Product((JSONObject) items.get(i), this));
        }
        return listOfProducts;
    }

    /**
     * Delete a product by its product id.
     *
     * @param productID
     *            Product ID of the product.
     * @return Status of the HTTP request (204 if succeeded).
     */
    public int deleteProduct(final String productID) {

        final Invocation.Builder request = this.getRequest("products/" + productID);
        final Response response = ((SyncInvoker) request).delete();
        return response.getStatus();
    }

    /**
     * Delete a Product.
     *
     * @param product
     *            Product that should be deleted.
     */
    public void deleteProduct(final Product product) {

        this.deleteProduct(product.getID());
    }
}
