package main;


import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import main.Exceptions.ErrorProvider;
import main.api.CompanyFacadeResource;
import main.api.AdminFacadeResource;
import main.api.CustomerFacadeResource;
import main.api.LogIN;

@ApplicationPath("/rest")
public class CouponApllication extends Application{

	@Override
	public Set<Object> getSingletons() {
		Set<Object> singletons = new HashSet<>();
		
		// add singletons to set
		singletons.add(new CompanyFacadeResource());
		singletons.add(new AdminFacadeResource());
		singletons.add(new CustomerFacadeResource());
		singletons.add(new LogIN());
		singletons.add(new ErrorProvider());
		return singletons;
	}
	
	

}
