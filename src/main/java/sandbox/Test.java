package sandbox;


import java.util.List;


import epages.Product;
import epages.Shop;


/**
 * Testclass for testing the framework.
 *
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
     *
     * @param args
     *            Given arguments.
     */
    public static void main(final String[] args) {

        final Shop shop = new Shop("http://sandbox.epages.com/rs/shops/EpagesDevD20160206T184335R191/");
        final List<Product> products = shop.getProducts();

        for (int i = 0; i < products.size(); i++) {
            System.out.println(products.get(i).getID());
            System.out.println(products.get(i).getName());
            System.out.println(products.get(i).getShortDescription());
            System.out.println(products.get(i).getDescription());
            System.out.println(products.get(i).getProductImage());
            System.out.println(products.get(i).getImages().toString());
            System.out.println(products.get(i).getUnit());
            System.out.println(products.get(i).getPrice());
            System.out.println(products.get(i).getCurrency());
            System.out.println(products.get(i).getDepositsPrice());
            System.out.println(products.get(i).getDepositsCurrency());
            System.out.println(products.get(i).getAvailability());
            System.out.println(products.get(i).getAvailabilityText());
            System.out.println(products.get(i).getStorefrontUrl());
            System.out.println(products.get(i).getProductNumber());
            System.out.println(products.get(i).getManufacturer());
            System.out.println(products.get(i).getManufacturerPrice());
            System.out.println(products.get(i).getManufacturerCurrency());
            System.out.println(products.get(i).getManufacturerTaxType());
            System.out.println(products.get(i).isForSale());
            System.out.println(products.get(i).isSpecialOffer());
            System.out.println(products.get(i).getDeliveryWeight());
            System.out.println(products.get(i).isShippingMethodRestricted());
            System.out.println(products.get(i).getShippingMethodRestrictedTo());
        }
    }
}
