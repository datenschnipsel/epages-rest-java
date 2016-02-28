package com.epages.rest;

import com.epages.rest.domain.Product;
import com.epages.rest.util.PatchBuilder;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

/**
 * @date 2016-02-28
 */
public class EpagesShopClientTest {


    @Test
    public void sendRequests() {

        final EpagesShopClient epagesShopClient = new EpagesShopClient(
                "http://sandbox.epages.com/rs/shops/EpagesDevD20160206T184335R191/", "zm3z1EwsWRvvq3arr2q5rumHeAEXoyuX");
        final HashMap<String, String> hm = new HashMap<String, String>();

        hm.put("page", "1");
        hm.put("resultsPerPage", "6");
        hm.put("direction", "desc");

        final List<Product> products = epagesShopClient.getProductPageWithQueryParams(hm);

        for (Product product : products) {
            System.out.println("---------------------------------------------------------------------------------");
            System.out.println(product.getID());
            System.out.println(product.getName());
            System.out.println(product.getShortDescription());
            System.out.println(product.getDescription());
            System.out.println(product.getProductImage());
            System.out.println(product.getImages().toString());
            System.out.println(product.getUnit());
            System.out.println(product.getPrice());
            System.out.println(product.getCurrency());
            System.out.println(product.getTaxType());
            System.out.println(product.getDepositsPrice());
            System.out.println(product.getDepositsCurrency());
            System.out.println(product.getDepositsTaxType());
            System.out.println(product.getAvailability());
            System.out.println(product.getAvailabilityText());
            System.out.println(product.getStorefrontUrl());
            System.out.println(product.getProductNumber());
            System.out.println(product.getManufacturer());
            System.out.println(product.getManufacturerPrice());
            System.out.println(product.getManufacturerCurrency());
            System.out.println(product.getManufacturerTaxType());
            System.out.println(product.isForSale());
            System.out.println(product.isSpecialOffer());
            System.out.println(product.getDeliveryWeight());
            System.out.println(product.isShippingMethodRestricted());
            System.out.println(product.getShippingMethodRestrictedTo());

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