package sandbox;


import java.util.HashMap;
import java.util.List;


import com.epages.rest.domain.Product;
import com.epages.rest.EpagesShopClient;
import com.epages.rest.util.PatchBuilder;


/**
 * Testclass for testing the framework.
 *
 * @author Bastian Klein(bastianklein92@gmail.com)
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

        final EpagesShopClient epagesShopClient = new EpagesShopClient("http://sandbox.epages.com/rs/shops/EpagesDevD20160206T184335R191/",
                "zm3z1EwsWRvvq3arr2q5rumHeAEXoyuX");
        final HashMap<String, String> hm = new HashMap<String, String>();
        hm.put("page", "1");
        hm.put("resultsPerPage", "6");
        hm.put("direction", "desc");

        final List<Product> products = epagesShopClient.getProductPageWithQueryParams(hm);

        for (int i = 0; i < products.size(); i++) {
            System.out.println("---------------------------------------------------------------------------------");
            System.out.println(products.get(i).getID());
            System.out.println(products.get(i).getName());
            System.out.println(products.get(i).getShortDescription());
            System.out.println(products.get(i).getDescription());
            System.out.println(products.get(i).getProductImage());
            System.out.println(products.get(i).getImages().toString());
            System.out.println(products.get(i).getUnit());
            System.out.println(products.get(i).getPrice());
            System.out.println(products.get(i).getCurrency());
            System.out.println(products.get(i).getTaxType());
            System.out.println(products.get(i).getDepositsPrice());
            System.out.println(products.get(i).getDepositsCurrency());
            System.out.println(products.get(i).getDepositsTaxType());
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
        System.out.println(products.size());
        System.out.println(products.get(0).getStocklevel());
        products.get(0).addToPatch("name", "Testproduct ForPatch");
        products.set(0, products.get(0).patch());
        System.out.println(products.get(0).getName());

        final PatchBuilder pb = new PatchBuilder();
        pb.add("name", "blaablub");
        pb.add("test", "blaablub");
        pb.add("blablubb", "blaablub");
        System.out.println(pb.toString());
    }
}
