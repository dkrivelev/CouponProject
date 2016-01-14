package main.api;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import main.Exceptions.CouponSystemException;
import main.Facade.AdminFacade;
import main.Facade.CustomerFacade;
import main.api.JsonUtil.JasonCustomer;
import main.api.JsonUtil.JsonUtil;


@Path("/customer")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class CustomerAPI {

	@GET
	public String  GetCustomer(@Context HttpServletRequest request) {
		HttpSession seesion = request.getSession(false);
		if (seesion != null) {
			CustomerFacade customerFacade = (CustomerFacade) seesion
					.getAttribute("customerFacade");
			
			if (customerFacade == null)
				throw new CouponSystemException("You are not authorized to perform that operation");
			
			return JsonUtil.convertToJason(customerFacade.GetCustomer());
		}
		
		throw new CouponSystemException("You are not logged in, please log");
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void CreateCustomer(@Context HttpServletRequest request, JasonCustomer cust) {

		HttpSession seesion = request.getSession(false);
		if (seesion != null) {
			AdminFacade adminFacade = (AdminFacade) seesion
					.getAttribute("adminFacade");
			
			if (adminFacade == null)
				throw new CouponSystemException("You are not authorized to perform that operation");
			
			adminFacade.CreateCustomer(cust.toCustomer());
			return;
		}
		throw new CouponSystemException("You are not logged in, please log");
	}
	
	@DELETE
	@Path("/id/{id}")
	public void RemoveCustomer(@Context HttpServletRequest request,
			@PathParam("id") long custID) {
		HttpSession seesion = request.getSession(false);
		if (seesion != null) {
			AdminFacade adminFacade = (AdminFacade) seesion
					.getAttribute("adminFacade");
			
			if (adminFacade == null)
				throw new CouponSystemException("You are not authorized to perform that operation");
				
			adminFacade.RemoveCustomer(custID);
			return;
		}
		throw new CouponSystemException("You are not logged in, please log");
		
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void UpdateCustomer(@Context HttpServletRequest request, JasonCustomer cust) {
		HttpSession seesion = request.getSession(false);
		if (seesion != null) {
			AdminFacade adminFacade = (AdminFacade) seesion
					.getAttribute("adminFacade");
			
			if (adminFacade == null)
				throw new CouponSystemException("You are not authorized to perform that operation");
			
			adminFacade.UpdateCustomer(cust.toCustomer(true));
			return;
		}
		throw new CouponSystemException("You are not logged in, please log");
	}
	
	@GET
	@Path("/id/{id}")
	public String GetCustomerByID(@Context HttpServletRequest request,
			@PathParam("id") long customerID) {
		HttpSession seesion = request.getSession(false);
		if (seesion != null) {
			AdminFacade adminFacade = (AdminFacade) seesion
					.getAttribute("adminFacade");
			
			if (adminFacade == null)
				throw new CouponSystemException("You are not authorized to perform that operation");
			
			return JsonUtil.convertToJason(adminFacade.GetCustomerByID(customerID));
		}
		throw new CouponSystemException("You are not logged in, please log");
	}
	
	
	@GET
	@Path("/name/{name}")
	public String GetCustomerByName(@Context HttpServletRequest request,
			@PathParam("name") String customerName) {
		HttpSession seesion = request.getSession(false);
		if (seesion != null) {
			AdminFacade adminFacade = (AdminFacade) seesion
					.getAttribute("adminFacade");
			
			if (adminFacade == null)
				throw new CouponSystemException("You are not authorized to perform that operation");
			
			return JsonUtil.convertToJason(adminFacade.GetCustomerByName(customerName));
		}
		throw new CouponSystemException("You are not logged in, please log");
	}
	
	@GET
	@Path("/all")
	public String GetAllCustomers(@Context HttpServletRequest request){
		HttpSession seesion = request.getSession(false);
		if (seesion != null) {
			AdminFacade adminFacade = (AdminFacade) seesion
					.getAttribute("adminFacade");
			
			if (adminFacade == null)
				throw new CouponSystemException("You are not authorized to perform that operation");
			
			
			return JsonUtil.convertToJason(adminFacade.GetAllCustomers());
					
			
		}
		throw new CouponSystemException("You are not logged in, please log");
	}
	
}
