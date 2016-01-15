package main.api;


import java.sql.Date;

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
import main.Facade.CompanyFacade;
import main.beanes.Company;
import main.beanes.Coupon;
import main.enums.CouponType;

@Path("/company")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class CompanyFacadeResource {

	
	@POST	
	public Coupon CreateCoupon(@Context HttpServletRequest request, Coupon coupon) {

		HttpSession seesion = request.getSession(false);
		if (seesion != null) {
			CompanyFacade compFacade = (CompanyFacade)seesion.getAttribute("companyFacade");
			
			
			if (compFacade == null)
				throw new CouponSystemException("You are not authorized to perform that operation");
			
			compFacade.CreateCoupon(coupon);
			return coupon;
		}
		throw new CouponSystemException("You are not logged in, please log");
	}
	
	@DELETE
	@Path("/id/{id}")
	public void RemoveCoupon(@Context HttpServletRequest request,
			@PathParam("id") long couponID) {
		HttpSession seesion = request.getSession(false);
		if (seesion != null) {
			
			CompanyFacade compFacade = (CompanyFacade)seesion.getAttribute("companyFacade");
			
			
			if (compFacade == null)
				throw new CouponSystemException("You are not authorized to perform that operation");
			
			compFacade.RemoveCoupon(couponID);
			return;
		}
		throw new CouponSystemException("You are not logged in, please log");
		
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Coupon UpdateCoupon(@Context HttpServletRequest request, Coupon coupon) {
		HttpSession seesion = request.getSession(false);
		if (seesion != null) {
			CompanyFacade compFacade = (CompanyFacade)seesion.getAttribute("companyFacade");
			
			if (compFacade == null)
				throw new CouponSystemException("You are not authorized to perform that operation");
			
			compFacade.UpdateCoupon(coupon); 
			return coupon;
		}
		throw new CouponSystemException("You are not logged in, please log");
	}
	
	@GET
	@Path("/all")
	public Coupon[] GetAllCompanyCoupons(@Context HttpServletRequest request){
		HttpSession seesion = request.getSession(false);
		if (seesion != null) {
			CompanyFacade compFacade = (CompanyFacade)seesion.getAttribute("companyFacade");
			
			if (compFacade == null)
				throw new CouponSystemException("You are not authorized to perform that operation");
				
			return compFacade.GetAllCoupons().toArray(new Coupon[0]);
		}
		throw new CouponSystemException("You are not logged in, please log");
	}
	
	@GET
	@Path("/type/{type}/price/{price}/date/{date}")
	public Coupon[] GetCompanyCouponsByTypePriceDate(@Context HttpServletRequest request ,
			@PathParam("type")String strCouponType,@PathParam("price")String strPrice, @PathParam("date")String strDate){
		
		HttpSession seesion = request.getSession(false);
		if (seesion != null) {
			CompanyFacade compFacade = (CompanyFacade)seesion.getAttribute("companyFacade");
			
			if (compFacade == null)
				throw new CouponSystemException("You are not authorized to perform that operation");
				
			Double price;
			try {
				price = Double.valueOf(strPrice);
			} catch (Exception e) {
				throw new CouponSystemException("Price value is illegal ");
			}
			
			Date date;
			try {
				date = new Date(Long.parseLong(strDate));
			} catch (Exception e) {
				date = null;
			}
			
			CouponType couponType;
			try {
				couponType = CouponType.valueOf(strCouponType);
			} catch (Exception e) {
				couponType= null;
			}
			return compFacade.GetCouponsByTypePriceDate(couponType, price, date).toArray(new Coupon[0]);
		}
		throw new CouponSystemException("You are not logged in, please log");
	
	}
		
	@GET
	public Company GetCompany(@Context HttpServletRequest request) {
		HttpSession seesion = request.getSession(false);
		if (seesion != null) {
			CompanyFacade companyFacade = (CompanyFacade) seesion
					.getAttribute("companyFacade");
			
			if (companyFacade == null)
				throw new CouponSystemException("You are not authorized to perform that operation");
			
			return companyFacade.GetCompany();
		}
		
		throw new CouponSystemException("You are not logged in, please log");
	}


	
	
	
	// TODO: Can defind context as global parm in the class insted off in each
	// function?
}
