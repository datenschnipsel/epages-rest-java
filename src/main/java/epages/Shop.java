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

public class Shop {

	private String baseUrl;
	private Client client;
	
	
	public Shop(String baseUrl) {
		
		this.baseUrl = baseUrl;
		client = ClientBuilder.newClient();
	}
	
	public List<Product> getProducts() {
		
		WebTarget resource = client.target(baseUrl + "products/");
		Invocation.Builder request = resource.request();
		request.accept(MediaType.APPLICATION_JSON);
		JSONObject products = null;

		Response response = ((SyncInvoker) request).get();
		if (response.getStatusInfo().getFamily() == Family.SUCCESSFUL) {
		    System.out.println("Success! " + response.getStatus());
		    System.out.println(response.getEntity());
		    try {
				products = (JSONObject) new JSONParser().parse(response.readEntity(String.class));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		    
		} else {
		    System.out.println("ERROR! " + response.getStatus());    
		    System.out.println(response.getEntity());
		    products = null;
		}
		ArrayList<Product> listOfProducts = new ArrayList<Product>();
		JSONArray items = (JSONArray) products.get("items");
		
		for (int i=0; i<items.size()-1; i++) {
			listOfProducts.add(new Product((JSONObject) items.get(i)));
		}
		return listOfProducts;
	}
}
