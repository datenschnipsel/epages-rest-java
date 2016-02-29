package com.epages.ws.rs.service;

import com.epages.ws.rs.EpagesShopClient;
import com.epages.ws.rs.domain.Product;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.SyncInvoker;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @date 2016-02-29
 */
public class ProductService {

    EpagesShopClient client;

    public ProductService (EpagesShopClient client) {

        this.client = client;
    }

    /**
     * This function gets all products of the first page.
     *
     * @return List List of products.
     */
    public List<Product> getFirstProductPage() {

        final Invocation.Builder request = client.getRequest("products/");
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

        final Invocation.Builder request = client.getRequest("products?page=" + pageNumber);
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

        final Invocation.Builder request = client.getRequest("products?page=" + pageNumber + "&resultsPerPage=" + resultsPerPage);
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

        final Invocation.Builder request = client.getRequest(sb.toString());
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
    public List<Product> createProducts(final Response response) {

        JSONObject products = null;

        if (response.getStatusInfo().getFamily() == Response.Status.Family.SUCCESSFUL) {
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
            listOfProducts.add(new Product((JSONObject) items.get(i), client));
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

        final Invocation.Builder request = client.getRequest("products/" + productID);
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
