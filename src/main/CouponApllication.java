package main;


import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import main.Exceptions.ErrorProvider;
import main.api.CompanyAPI;
import main.api.CouponAPI;
import main.api.CustomerAPI;
import main.api.LogIN;

@ApplicationPath("/rest")
public class CouponApllication extends Application{

	@Override
	public Set<Object> getSingletons() {
		Set<Object> singletons = new HashSet<>();
		
		// add singletons to set
		singletons.add(new CompanyAPI());
		singletons.add(new CouponAPI());
		singletons.add(new CustomerAPI());
		singletons.add(new LogIN());
		singletons.add(new ErrorProvider());
		return singletons;
	}
	
	

}
