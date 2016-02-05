package epages;


import java.util.ArrayList;
import java.util.HashMap;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


/**
 * This Class represents a product of an epages shop.
 *
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
     *
     * @param product
     *            The product as JSONObject.
     */
    public Product(final JSONObject product) {
        super(product);
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

        return this.get("description").toString();
    }

    /**
     * Getter for the product image.
     *
     * @return The ID of the product image.
     */
    public String getProductImage() {

        return this.get("productImage").toString();
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

        return price.get("currency").toString();
    }

    /**
     * Getter for the price with deposit.
     *
     * @return String containing the price with deposit of the product.
     */
    public String getDepositsPrice() {

        final JSONObject priceInfo = (JSONObject) this.get("priceInfo");
        final JSONObject price = (JSONObject) priceInfo.get("priceWithDeposits");

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

        return price.get("currency").toString();
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

        return this.get("manufacturer").toString();
    }

    /**
     * Getter for the manufacturer price of the product.
     *
     * @return String containing the manufacturers price.
     */
    public String getManufacturerPrice() {

        final JSONObject priceInfo = (JSONObject) this.get("priceInfo");

        if (priceInfo.get("manufacturerPrice") == null) {
            return null;
        }

        return priceInfo.get("manufacturerPrice").toString();
    }

    /**
     * Getter for the forSale field.
     *
     * @return {@True} if the product is for sale, else {@false}.
     */
    public boolean isForSale() {

        final String forSale = this.get("forSale").toString();

        return forSale.equals("true");
    }

    /**
     * Getter for the specialOffer field.
     *
     * @return {@True} if the product is a special offer, else {@false}.
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
     * @return {@true} if the product is not restricted to a special shipping
     *         method, else {@false}.
     */
    public boolean isShippingMethodRestricted() {

        final JSONObject isRestricted = (JSONObject) this.get("shippingMethodRestrictedTo");

        if (isRestricted == null) {
            return true;
        }

        return false;
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
}
