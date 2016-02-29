package com.epages.ws.rs;


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


import com.epages.ws.rs.domain.Product;
import com.epages.ws.rs.service.ProductService;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.HttpUrlConnectorProvider;
import org.glassfish.jersey.client.JerseyClient;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


/**
 * Represents a epages Shop.
 *
 * @author Bastian Klein(bastianklein92@gmail.com)
 */
public class EpagesShopClient {


    private final String baseUrl;
    private final Client client;
    private final JerseyClient patchClient;
    private final String token;


    /**
     * Constructor of this object.
     *
     * @param epagesBaseUrl
     *            Base url to the epages shop.
     * @param accessToken
     *            The credentials for accessing the API.
     */
    public EpagesShopClient(final String epagesBaseUrl, final String accessToken) {

        if (epagesBaseUrl.charAt(epagesBaseUrl.length() - 1) != '/') {
            this.baseUrl = epagesBaseUrl + "/";
        } else {
            this.baseUrl = epagesBaseUrl;
        }
        this.client = ClientBuilder.newClient();
        final ClientConfig config = new ClientConfig();
        this.patchClient = JerseyClientBuilder.createClient(config);
        this.patchClient.property(HttpUrlConnectorProvider.SET_METHOD_WORKAROUND, true);
        this.token = accessToken;
    }

    /**
     * Generates a new request.
     *
     * @param target
     *            Target to be retrieved.
     * @return A new request.
     */
    public Invocation.Builder getRequest(final String target) {

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
    public Invocation.Builder getPatchRequest(final String target) {

        final WebTarget resource = this.patchClient.target(baseUrl + target);
        final Invocation.Builder request = resource.request();
        request.accept("application/vnd.epages.v1+json");
        request.header("Authorization", "Bearer " + token);
        request.header("Content-Type", MediaType.APPLICATION_JSON);

        return request;
    }

    /**
     * Get the product service.
     *
     * @return ProductService.
     */
    public ProductService getProductService() {

        return new ProductService(this);
    }

}
