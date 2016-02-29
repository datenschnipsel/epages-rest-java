package com.epages.ws.rs.domain;


import java.util.ArrayList;
import java.util.HashMap;


import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.SyncInvoker;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status.Family;


import com.epages.ws.rs.EpagesShopClient;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


/**
 * This Class represents a product of an epages shop.
 *
 * @author Bastian Klein(bastianklein92@gmail.com)
 *
 */
public class Product extends JSONObject {


    /**
     * Serial version ID.
     */
    private static final long serialVersionUID = 1;

    /**
     * Shop of the product.
     */
    private final EpagesShopClient epagesShopClient;

    /**
     * String containing the JSON patch for this product.
     */
    private final StringBuilder patch = new StringBuilder();


    /**
     * Constructor of this class.
     *
     * @param product
     *            The product as JSONObject.
     * @param epagesShopClientObject
     *            The product's shop..
     */
    public Product(final JSONObject product, final EpagesShopClient epagesShopClientObject) {
        super(product);
        this.epagesShopClient = epagesShopClientObject;
    }

    /**
     * Getter for the product ID.
     *
     * @return The product ID.
     */
    public String getID() {

        return this.get("productId").toString();
    }

    /**
     * Getter of the product name.
     *
     * @return The product name.
     */
    public String getName() {

        return this.get("name").toString();
    }

    /**
     * Getter for the short description.
     *
     * @return The short description of the product.
     */
    public String getShortDescription() {

        final Object shortDescription = this.get("shortDescription");

        if (shortDescription == null) {
            return null;
        }
        return shortDescription.toString();
    }

    /**
     * Getter for the description of the product.
     *
     * @return The description of the product.
     */
    public String getDescription() {

        final Object description = this.get("description");

        if (description == null) {
            return null;
        }

        return description.toString();
    }

    /**
     * Getter for the product image.
     *
     * @return The ID of the product image.
     */
    public String getProductImage() {

        final Object productImage = this.get("productImage");

        if (productImage == null) {
            return null;
        }

        return productImage.toString();
    }

    /**
     * Getter for the image gallery.
     *
     * @return An array with Classifiers and URLs of the image gallery as
     *         HashMap.
     */
    public ArrayList<HashMap<String, String>> getImages() {

        final ArrayList<HashMap<String, String>> listOfImages = new ArrayList<HashMap<String, String>>();
        final JSONArray images = (JSONArray) this.get("images");
        final HashMap<String, String> image = new HashMap<String, String>();

        for (int i = 0; i < images.size(); i++) {
            image.put("url", ((JSONObject) images.get(i)).get("url").toString());
            image.put("classifier", ((JSONObject) images.get(i)).get("classifier").toString());
            listOfImages.add(image);
        }

        return listOfImages;
    }

    /**
     * Getter for the unit of the product.
     *
     * @return Stringg containing the unit of the product (e.g. piece(s)).
     */
    public String getUnit() {

        final JSONObject priceInfo = (JSONObject) this.get("priceInfo");
        final JSONObject quantity = (JSONObject) priceInfo.get("quantity");

        return quantity.get("unit").toString();
    }

    /**
     * Getter for the price.
     *
     * @return String containing the price of the product.
     */
    public String getPrice() {

        final JSONObject priceInfo = (JSONObject) this.get("priceInfo");
        final JSONObject price = (JSONObject) priceInfo.get("price");

        if (price == null) {
            return null;
        }

        return price.get("amount").toString();
    }

    /**
     * Getter for the currency.
     *
     * @return String containing the currency of the product's price.
     */
    public String getCurrency() {

        final JSONObject priceInfo = (JSONObject) this.get("priceInfo");
        final JSONObject price = (JSONObject) priceInfo.get("price");

        if (price == null) {
            return null;
        }

        return price.get("currency").toString();
    }

    /**
     * Getter for the tax type.
     *
     * @return String containing the tax type of the product.
     */
    public String getTaxType() {

        final JSONObject priceInfo = (JSONObject) this.get("priceInfo");
        final JSONObject price = (JSONObject) priceInfo.get("price");

        if (price == null) {
            return null;
        }

        return price.get("taxType").toString();
    }

    /**
     * Getter for the price with deposit.
     *
     * @return String containing the price with deposit of the product.
     */
    public String getDepositsPrice() {

        final JSONObject priceInfo = (JSONObject) this.get("priceInfo");
        final JSONObject price = (JSONObject) priceInfo.get("priceWithDeposits");

        if (price == null) {
            return null;
        }

        return price.get("amount").toString();
    }

    /**
     * Getter for the currency of the price with deposit.
     *
     * @return String containing the currency of the product's price with
     *         deposit.
     */
    public String getDepositsCurrency() {

        final JSONObject priceInfo = (JSONObject) this.get("priceInfo");
        final JSONObject price = (JSONObject) priceInfo.get("priceWithDeposits");

        if (price == null) {
            return null;
        }

        return price.get("currency").toString();
    }

    /**
     * Getter for the tax type with deposit.
     *
     * @return String containing the tax type with deposit of the product.
     */
    public String getDepositsTaxType() {

        final JSONObject priceInfo = (JSONObject) this.get("priceInfo");
        final JSONObject price = (JSONObject) priceInfo.get("priceWithDeposits");

        if (price == null) {
            return null;
        }

        return price.get("taxType").toString();
    }

    /**
     * Getter for the availability.
     *
     * @return String containing the availability.
     */
    public String getAvailability() {

        if (this.get("availibility") != null) {
            return this.get("availibility").toString();
        }

        return null;
    }

    /**
     * Getter for the availability text .
     *
     * @return String containing the availability text.
     */
    public String getAvailabilityText() {

        if (this.get("availibilityText") != null) {
            return this.get("availibilityText").toString();
        }

        return null;
    }

    /**
     * Getter for the storefront url.
     *
     * @return String containing the sf url.
     */
    public String getStorefrontUrl() {

        return this.get("sfUrl").toString();
    }

    /**
     * Getter for the product number.
     *
     * @return String containing the product number.
     */
    public String getProductNumber() {

        return this.get("productNumber").toString();
    }

    /**
     * Getter for the manufacturer.
     *
     * @return String containing the manufacturer.
     */
    public String getManufacturer() {

        final Object manufacturer = this.get("manufacturer");

        if (manufacturer == null) {
            return null;
        }

        return manufacturer.toString();
    }

    /**
     * Getter for the manufacturer price of the product.
     *
     * @return String containing the manufacturers price.
     */
    public String getManufacturerPrice() {

        final JSONObject priceInfo = (JSONObject) this.get("priceInfo");
        final JSONObject manufacturerPrice = (JSONObject) priceInfo.get("manufacturerPrice");

        if (manufacturerPrice == null) {
            return null;
        }

        return manufacturerPrice.get("amount").toString();
    }

    /**
     * Getter for the manufacturer currency of the product.
     *
     * @return String containing the manufacturers price.
     */
    public String getManufacturerCurrency() {

        final JSONObject priceInfo = (JSONObject) this.get("priceInfo");
        final JSONObject manufacturerPrice = (JSONObject) priceInfo.get("manufacturerPrice");

        if (manufacturerPrice == null) {
            return null;
        }

        return manufacturerPrice.get("currency").toString();
    }

    /**
     * Getter for the manufacturer tax type of the product.
     *
     * @return String containing the manufacturers tax type.
     */
    public String getManufacturerTaxType() {

        final JSONObject priceInfo = (JSONObject) this.get("priceInfo");
        final JSONObject manufacturerPrice = (JSONObject) priceInfo.get("manufacturerPrice");

        if (manufacturerPrice == null) {
            return null;
        }

        return manufacturerPrice.get("taxType").toString();
    }

    /**
     * Getter for the forSale field.
     *
     * @return True if the product is for sale, else False.
     */
    public boolean isForSale() {

        final String forSale = this.get("forSale").toString();

        return forSale.equals("true");
    }

    /**
     * Getter for the specialOffer field.
     *
     * @return True if the product is a special offer, else False.
     */
    public boolean isSpecialOffer() {

        final String specialOffer = this.get("forSale").toString();

        return specialOffer.equals("true");
    }

    /**
     * Getter for the delivery weight.
     *
     * @return String containing the delivery weight.
     */
    public String getDeliveryWeight() {

        final JSONObject deliveryWeight = (JSONObject) this.get("deliveryWeight");

        if (deliveryWeight == null) {
            return null;
        }

        return deliveryWeight.toString();
    }

    /**
     * Checks if the product is restricted to a special shipping method.
     *
     * @return True if the product is not restricted to a special shipping
     *         method, else False.
     */
    public boolean isShippingMethodRestricted() {

        final JSONObject isRestricted = (JSONObject) this.get("shippingMethodRestrictedTo");

        if (isRestricted == null) {
            return false;
        }

        return true;
    }

    /**
     * Getter for the field shippingMethodRestrictedTo.
     *
     * @return string containing the shipping method, or null.
     */
    public String getShippingMethodRestrictedTo() {

        final JSONObject restrictedTo = (JSONObject) this.get("shippingMethodRestrictedTo");

        if (restrictedTo == null) {
            return null;
        } else {
            return restrictedTo.toString();
        }
    }

    /**
     * Getter for the stocklevel of the product.
     *
     * @return Float containing the stocklevel.
     */
    public Double getStocklevel() {

        final Invocation.Builder request = epagesShopClient.getRequest("products/" + this.getID() + "/stock-level/");
        Double stocklevel = null;

        final Response response = ((SyncInvoker) request).get();
        if (response.getStatusInfo().getFamily() == Family.SUCCESSFUL) {
            System.out.println("Success! " + response.getStatus());
            System.out.println(response.getEntity());
            try {
                stocklevel = (Double) ((JSONObject) new JSONParser().parse(response.readEntity(String.class)))
                        .get("stocklevel");
            } catch (final ParseException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("ERROR! " + response.getStatus());
            System.out.println(response.getEntity());
            stocklevel = null;
        }

        return stocklevel;
    }

    /**
     * Increments the stocklevel by the given value, if value >= 0.
     *
     * @param value
     *            Value of the increment.
     * @return The new stocklevel if value >= 0, else null.
     */
    public Double incrementStocklevel(final double value) {

        Double stocklevel = null;

        if (value > 0) {
            final Invocation.Builder request = epagesShopClient.getRequest("products/" + this.getID() + "/stock-level/");

            final Response response = ((SyncInvoker) request)
                    .put(Entity.entity("{\"changeStocklevel\": " + value + "}", MediaType.APPLICATION_JSON));
            if (response.getStatusInfo().getFamily() == Family.SUCCESSFUL) {
                System.out.println("Success! " + response.getStatus());
                System.out.println(response.getEntity());
                try {
                    stocklevel = (Double) ((JSONObject) new JSONParser().parse(response.readEntity(String.class)))
                            .get("stocklevel");
                } catch (final ParseException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("ERROR! " + response.getStatus());
                System.out.println(response.getEntity());
                stocklevel = null;
            }
        }

        return stocklevel;
    }

    /**
     * Decrements the stocklevel by the given value, if value >= 0.
     *
     * @param value
     *            Value of the decrement.
     * @return The new stocklevel if value >= 0, else null.
     */
    public Double decrementStocklevel(final double value) {

        Double stocklevel = null;

        if (value > 0) {
            final Invocation.Builder request = epagesShopClient.getRequest("products/" + this.getID() + "/stock-level/");

            final Response response = ((SyncInvoker) request)
                    .put(Entity.entity("{\"changeStocklevel\": -" + value + "}", MediaType.APPLICATION_JSON));
            if (response.getStatusInfo().getFamily() == Family.SUCCESSFUL) {
                try {
                    stocklevel = (Double) ((JSONObject) new JSONParser().parse(response.readEntity(String.class)))
                            .get("stocklevel");
                } catch (final ParseException e) {
                    e.printStackTrace();
                }
            } else {
                stocklevel = null;
            }
        }

        return stocklevel;
    }

    /**
     * Adding one key to the actual patch.
     *
     * @param key
     *            JSON key that should be patched.
     * @param value
     *            New value of the specified key.
     */
    public void addToPatch(final String key, final String value) {

        this.patch.append("[{\n");
        this.patch.append("    \"op\": \"add\",\n");
        this.patch.append("    \"path\": \"/" + key + "\",\n");
        this.patch.append("    \"value\": \"" + value + "\"\n");
        this.patch.append("},\n");
    }

    /**
     * Patches the product.
     *
     * @return A new Product with the patch.
     */
    public Product patch() {

        this.patch.delete(this.patch.length() - 2, this.patch.length());
        this.patch.append("]");
        JSONObject product = null;

        final Invocation.Builder request = epagesShopClient.getPatchRequest("products/" + this.getID());
        final Response response = ((SyncInvoker) request).method("PATCH",
                Entity.entity(patch.toString(), "application/json-patch+json"));

        try {
            product = (JSONObject) new JSONParser().parse(response.readEntity(String.class));
        } catch (final Exception e) {
            System.out.println(e.toString());
        }

        return new Product(product, this.epagesShopClient);
    }
}
