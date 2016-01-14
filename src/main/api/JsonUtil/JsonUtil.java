package main.api.JsonUtil;

import java.util.Collection;
import main.beanes.Company;
import main.beanes.Coupon;
import main.beanes.Customer;

public class JsonUtil {

	static public String convertToJason(Object obj) {

		if (obj instanceof Company) {
			Company com = (Company) obj;
			return "{\"id\":\"" + com.getId() + "\"," + "\"compName\":\""
					+ com.getCompName() + "\"," + "\"password\":\""
					+ com.getPassword() + "\"," + "\"email\":\""
					+ com.getEmail() + "\"}";
			
		} else if (obj instanceof Customer) {

			Customer cust = (Customer) obj;
			return "{\"id\":\"" + cust.getId() + "\"," 
					+ "\"custName\":\"" + cust.getCustName() + "\","
					+ "\"password\":\"" + cust.getPassword() + "\"}";
		}
		else if (obj instanceof Coupon){
			
			Coupon coupon = (Coupon) obj;
			return "{\"id\":\"" 			+ coupon.getId() + "\"," 
					+ "\"title\":\"" 		+ coupon.getTitle() + "\","
					+ "\"startDate\":\""	+ coupon.getStartDate().getTime() + "\"," 
					+ "\"endDate\":\""		+ coupon.getEndDate().getTime() + "\"," 
					+ "\"amount\":" 		+ coupon.getAmount() + ","
					+ "\"msg\":\""  		+ coupon.getMsg() + "\","
					+ "\"price\":\"" 		+ coupon.getPrice() + "\","
					+ "\"image\":\"" 		+ coupon.getImage() + "\","
					+ "\"couponType\":\"" 	+ coupon.getCouponType().toString() +  "\"}";							
							
			
		}
		return "";
	}
	
	static public <T> String convertToJason(Collection<T> objects) {
		String jsonComps = "[";
		for (Object obj : objects) {
			if (obj != null)
			{
				jsonComps += JsonUtil.convertToJason(obj) + ",";
			}
		}
		if (jsonComps.endsWith(","))
		{	
			jsonComps = jsonComps.substring(0, jsonComps.length()-1);
		}
		jsonComps +="]";
		
		
		return jsonComps;
	}

}
