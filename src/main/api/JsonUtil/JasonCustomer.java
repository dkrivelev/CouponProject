package main.api.JsonUtil;

import javax.xml.bind.annotation.XmlRootElement;

import main.beanes.Customer;

import org.codehaus.jackson.annotate.JsonProperty;

@XmlRootElement
public class JasonCustomer {

	@JsonProperty("id")
	private String id;
	
	@JsonProperty("custName")
	private String custName;
	
	@JsonProperty("password")
	private String password;
	
	
	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public Customer toCustomer(){
		return new Customer(custName , password);
		
	}
	
	public Customer toCustomer(boolean includeCustomerID){
		if (includeCustomerID)
		{
			return new Customer(Long.parseLong(id), custName , password);			
		}
		else{
			return this.toCustomer();
		}
	}
	
}
