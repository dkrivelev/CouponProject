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
import main.Facade.CompanyFacade;
import main.api.JsonUtil.JasonCompany;
import main.api.JsonUtil.JsonUtil;

@Path("/company")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class CompanyAPI {

	@GET
	public String GetCompany(@Context HttpServletRequest request) {
		HttpSession seesion = request.getSession(false);
		if (seesion != null) {
			CompanyFacade companyFacade = (CompanyFacade) seesion
					.getAttribute("companyFacade");
			
			if (companyFacade == null)
				throw new CouponSystemException("You are not authorized to perform that operation");
			
			return JsonUtil.convertToJason(companyFacade.GetCompany());
		}
		
		throw new CouponSystemException("You are not logged in, please log");
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void CreateCompany(@Context HttpServletRequest request, JasonCompany comp) {

		HttpSession seesion = request.getSession(false);
		if (seesion != null) {
			AdminFacade adminFacade = (AdminFacade) seesion
					.getAttribute("adminFacade");
			
			if (adminFacade == null)
				throw new CouponSystemException("You are not authorized to perform that operation");
			
			adminFacade.CreateCompany(comp.toCompany());
			return;
		}
		throw new CouponSystemException("You are not logged in, please log");
	}

	@DELETE
	@Path("/id/{id}")
	public void RemoveCompany(@Context HttpServletRequest request,
			@PathParam("id") long compID) {
		HttpSession seesion = request.getSession(false);
		if (seesion != null) {
			AdminFacade adminFacade = (AdminFacade) seesion
					.getAttribute("adminFacade");
			
			if (adminFacade == null)
				throw new CouponSystemException("You are not authorized to perform that operation");
				
			adminFacade.RemoveCompany(compID);
			return;
		}
		throw new CouponSystemException("You are not logged in, please log");
		
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void UpdateCompany(@Context HttpServletRequest request, JasonCompany comp) {
		HttpSession seesion = request.getSession(false);
		if (seesion != null) {
			AdminFacade adminFacade = (AdminFacade) seesion
					.getAttribute("adminFacade");
			
			if (adminFacade == null)
				throw new CouponSystemException("You are not authorized to perform that operation");
			
			adminFacade.UpdateCompany(comp.toCompany(true));
			return;
		}
		throw new CouponSystemException("You are not logged in, please log");
	}

	@GET
	@Path("/id/{id}")
	public String GetCompanyByID(@Context HttpServletRequest request,
			@PathParam("id") long companyID) {
		HttpSession seesion = request.getSession(false);
		if (seesion != null) {
			AdminFacade adminFacade = (AdminFacade) seesion
					.getAttribute("adminFacade");
			
			if (adminFacade == null)
				throw new CouponSystemException("You are not authorized to perform that operation");
			
			return JsonUtil.convertToJason(adminFacade
					.GetCompanyByID(companyID));
		}
		throw new CouponSystemException("You are not logged in, please log");
	}

	@GET
	@Path("/name/{name}")
	public String GetCompanyByName(@Context HttpServletRequest request,
			@PathParam("name") String companyName) {
		HttpSession seesion = request.getSession(false);
		if (seesion != null) {
			AdminFacade adminFacade = (AdminFacade) seesion
					.getAttribute("adminFacade");
			
			if (adminFacade == null)
				throw new CouponSystemException("You are not authorized to perform that operation");
			
			return JsonUtil.convertToJason(adminFacade.GetCompanyByName(companyName));
		}
		throw new CouponSystemException("You are not logged in, please log");
	}
	
	@GET
	@Path("/all")
	public String GetAllCompanies(@Context HttpServletRequest request){
		HttpSession seesion = request.getSession(false);
		if (seesion != null) {
			AdminFacade adminFacade = (AdminFacade) seesion
					.getAttribute("adminFacade");
			
			if (adminFacade == null)
				throw new CouponSystemException("You are not authorized to perform that operation");
				
			return JsonUtil.convertToJason(adminFacade.GetAllCompanies());
		}
		throw new CouponSystemException("You are not logged in, please log");
	}
	
	
	
	// TODO: Can defind context as global parm in the class insted off in each
	// function?
}
