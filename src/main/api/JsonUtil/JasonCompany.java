package main.api.JsonUtil;

import javax.xml.bind.annotation.XmlRootElement;

import main.beanes.Company;

import org.codehaus.jackson.annotate.JsonProperty;

@XmlRootElement
public class JasonCompany {
	
	@JsonProperty("id")
	private String id;
	
	
	@JsonProperty("compName")
	private String compName;
	
	@JsonProperty("password")
	private String password;
	
	@JsonProperty("email")
	private String email;
	
		
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCompName() {
		return compName;
	}

	public void setCompName(String compName) {
		this.compName = compName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Company toCompany(){
		Company comp = new Company(compName,password,email);
		return comp;
	}
	
	public Company toCompany(boolean includeCompanyID){
		if (includeCompanyID)
		{
			Company comp = new Company(Long.parseLong(id), compName,password,email);
			return comp;
		}
		else{
			return toCompany();
			
		}
				
		
	}
	
	

}
