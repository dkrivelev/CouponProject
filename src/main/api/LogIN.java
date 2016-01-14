package main.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import main.Exceptions.CouponSystemException;
import main.Facade.AdminFacade;
import main.Facade.ClientFacade;
import main.Facade.CompanyFacade;
import main.Facade.CustomerFacade;
import main.api.JsonUtil.JsonLogIn;
import main.beanes.CouponSystem;
import main.enums.ClientType;

@Path("/login")

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LogIN {
	
	
	@POST	
	public String checkLogIn(@Context HttpServletRequest request , JsonLogIn jsnLogIN) throws CouponSystemException
	{
		//Json example: {"userName":"comp1","password":"123","clientType":"CompanyFacade"}
		//url: localhost:8080/CoponProject_Phase2/rest/login
		
		ClientType clientType = ClientType.valueOf(jsnLogIN.getClientType());
		CouponSystem couponSystem = CouponSystem.GetInstance();
		ClientFacade clientFacade = couponSystem.Login(jsnLogIN.getUserName(), jsnLogIN.getPassword(), clientType);
		if (clientFacade != null)
		{
			HttpSession session = request.getSession(true);
			switch (clientType) {
			case AdminFacade:
				session.setAttribute("adminFacade", (AdminFacade)clientFacade);
				break;
			case CompanyFacade:		
				session.setAttribute("companyFacade", (CompanyFacade)clientFacade);
				break;
			case CustomerFacade:
				session.setAttribute("customerFacade", (CustomerFacade)clientFacade);				
				break;							
			}
			return ("Suessfully LogedIn");
		}
		else
		{
			throw new CouponSystemException("Username and password incorrect");
		}
		//TODO what to do if the login failed- throw error?
		
		
	}

}
