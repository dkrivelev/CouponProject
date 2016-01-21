package main.api;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import main.Exceptions.CouponSystemException;
import main.Facade.CustomerFacade;
import main.beanes.Coupon;
import main.beanes.Customer;
import main.enums.CouponType;


@Path("/customer")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class CustomerFacadeResource {

	@GET
	public Customer GetCustomer(@Context HttpServletRequest request) {
		HttpSession seesion = request.getSession(false);
		if (seesion != null) {
			CustomerFacade customerFacade = (CustomerFacade) seesion
					.getAttribute("customerFacade");
			
			if (customerFacade == null)
				throw new CouponSystemException("You are not authorized to perform that operation");
			
			return customerFacade.GetCustomer();
		}
		
		throw new CouponSystemException("You are not logged in, please log");
	}
	
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/buy/id/{id}")
	public void PurchaseCoupon(@Context HttpServletRequest request ,@PathParam("id")long couponID){
		HttpSession seesion = request.getSession(false);
		if (seesion != null)
		{
			CustomerFacade custFacade = (CustomerFacade)seesion.getAttribute("customerFacade");
			
			if (custFacade == null)
				throw new CouponSystemException("You are not authorized to perform that operation");
			
			
			custFacade.PurchaseCoupon(couponID);
			return;
		}
		throw new CouponSystemException("You are not logged in, please log");
	}
	
	@GET
	@Path("/purchased")
	public Coupon[] GetAllPurchasedCoupons(@Context HttpServletRequest request){
		HttpSession seesion = request.getSession(false);
		if (seesion != null)
		{
			CustomerFacade custFacade = (CustomerFacade)seesion.getAttribute("customerFacade");
			
			if (custFacade == null)
				throw new CouponSystemException("You are not authorized to perform that operation");
			
						
			return custFacade.GetAllPurchasedCoupons().toArray(new Coupon[0]);
		}
		throw new CouponSystemException("You are not logged in, please log");
	}
	
	@GET
	@Path("/purchased/type/{type}")
	public Coupon[] GetAllPurchasedCouponsByType(@Context HttpServletRequest request ,
			@PathParam("type") String strCouponType){
		HttpSession seesion = request.getSession(false);
		if (seesion != null)
		{
			CustomerFacade custFacade = (CustomerFacade)seesion.getAttribute("customerFacade");
			
			if (custFacade == null)
				throw new CouponSystemException("You are not authorized to perform that operation");
			
			CouponType couponType =null;
			 try {
				 couponType  = CouponType.valueOf(strCouponType);
			} catch (Exception e) {
				throw new CouponSystemException("Coupon Type value is illegal ");
			}
			 				 
			return custFacade.GetAllPurchasedCouponsByType(couponType).toArray(new Coupon[0]);
		}
		throw new CouponSystemException("You are not logged in, please log");
	}
	
	@GET
	@Path("/purchased/price/{price}")
	public Coupon[] GetAllPurchasedCouponsByPrice(@Context HttpServletRequest request ,
			@PathParam("price") String strPrice){
		HttpSession seesion = request.getSession(false);					
		
		if (seesion != null)
		{
			CustomerFacade custFacade = (CustomerFacade)seesion.getAttribute("customerFacade");
			
			if (custFacade == null)
				throw new CouponSystemException("You are not authorized to perform that operation");
			
			double price;
			try {
				price = Double.parseDouble(strPrice);
			} catch (Exception e) {
				throw new CouponSystemException("Price value is illegal ");
			}
				 
			return custFacade.GetAllPurchasedCouponsByPrice(price).toArray(new Coupon[0]);
		}
		throw new CouponSystemException("You are not logged in, please log");
	}
	
}
