package main.api.JsonUtil;

import javax.xml.bind.annotation.XmlRootElement;

//import org.codehaus.jackson.annotate.JsonProperty;

//@XmlRootElement
public class JsonLogIn{
	
	
	//@JsonProperty("userName")
	private String userName;
	
	//@JsonProperty("password")
	private String password;
	
	//@JsonProperty("clientType")
	private String clientType;
	
	
	
	//@JsonProperty("userName")
	public String getUserName() {
		return userName;
	}

	//@JsonProperty("password")
	public String getPassword() {
		return password;
	}

	//@JsonProperty("clientType")
	public String getClientType() {
		return clientType;
	}

	//@JsonProperty("userName")
	public void setUserName(String userName) {
		this.userName = userName;
	}

	//@JsonProperty("password")
	public void setPassword(String password) {
		this.password = password;
	}

	//@JsonProperty("clientType")
	public void setClientType(String clientType) {
		this.clientType = clientType;
	}
	
	
	
	
	
}
