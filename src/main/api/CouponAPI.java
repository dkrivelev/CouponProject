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
import main.Facade.CustomerFacade;
import main.api.JsonUtil.JasonCoupon;
import main.api.JsonUtil.JsonUtil;
import main.enums.CouponType;

@Path("/coupon")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class CouponAPI {
	
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
	public String GetAllPurchasedCoupons(@Context HttpServletRequest request){
		HttpSession seesion = request.getSession(false);
		if (seesion != null)
		{
			CustomerFacade custFacade = (CustomerFacade)seesion.getAttribute("customerFacade");
			
			if (custFacade == null)
				throw new CouponSystemException("You are not authorized to perform that operation");
			
						
			return JsonUtil.convertToJason(custFacade.GetAllPurchasedCoupons());
		}
		throw new CouponSystemException("You are not logged in, please log");
	}
	
	@GET
	@Path("/purchased/type/{type}")
	public String GetAllPurchasedCouponsByType(@Context HttpServletRequest request ,
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
			 				 
			return JsonUtil.convertToJason(custFacade.GetAllPurchasedCouponsByType(couponType));
		}
		throw new CouponSystemException("You are not logged in, please log");
	}
	
	@GET
	@Path("/purchased/price/{price}")
	public String GetAllPurchasedCouponsByPrice(@Context HttpServletRequest request ,
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
				 
			return JsonUtil.convertToJason(custFacade.GetAllPurchasedCouponsByPrice(price));
		}
		throw new CouponSystemException("You are not logged in, please log");
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void CreateCoupon(@Context HttpServletRequest request, JasonCoupon coupon) {

		HttpSession seesion = request.getSession(false);
		if (seesion != null) {
			CompanyFacade compFacade = (CompanyFacade)seesion.getAttribute("companyFacade");
			
			
			if (compFacade == null)
				throw new CouponSystemException("You are not authorized to perform that operation");
			
			compFacade.CreateCoupon(coupon.toCoupon());
			return;
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
	public void UpdateCoupon(@Context HttpServletRequest request, JasonCoupon coupon) {
		HttpSession seesion = request.getSession(false);
		if (seesion != null) {
			CompanyFacade compFacade = (CompanyFacade)seesion.getAttribute("companyFacade");
			
			if (compFacade == null)
				throw new CouponSystemException("You are not authorized to perform that operation");
			
			compFacade.UpdateCoupon(coupon.toCoupon(true)); 
			return;
		}
		throw new CouponSystemException("You are not logged in, please log");
	}
	
	@GET
	@Path("/company")
	public String GetAllCompanyCoupons(@Context HttpServletRequest request){
		HttpSession seesion = request.getSession(false);
		if (seesion != null) {
			CompanyFacade compFacade = (CompanyFacade)seesion.getAttribute("companyFacade");
			
			if (compFacade == null)
				throw new CouponSystemException("You are not authorized to perform that operation");
				
			return JsonUtil.convertToJason(compFacade.GetAllCoupons());
		}
		throw new CouponSystemException("You are not logged in, please log");
	}
	
	@GET
	@Path("/company/type/{type}/price/{price}/date/{date}")
	public String GetCompanyCouponsByTypePriceDate(@Context HttpServletRequest request ,
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
			return JsonUtil.convertToJason(compFacade.GetCouponsByTypePriceDate(couponType, price, date));
		}
		throw new CouponSystemException("You are not logged in, please log");
	
	}
			
	
	
	
	
}
