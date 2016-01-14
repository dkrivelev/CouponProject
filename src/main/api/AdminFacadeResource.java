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
import main.beanes.Company;
import main.beanes.Customer;

@Path("/admin")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class AdminFacadeResource {
	// TODO need to return Coupon in all the relevant function

	@DELETE
	@Path("/id/{id}")
	public Customer RemoveCustomer(@Context HttpServletRequest request, @PathParam("id") long custID) {
		HttpSession seesion = request.getSession(false);
		if (seesion != null) {
			AdminFacade adminFacade = (AdminFacade) seesion.getAttribute("adminFacade");

			if (adminFacade == null)
				throw new CouponSystemException("You are not authorized to perform that operation");
			Customer removeCust = adminFacade.GetCustomerByID(custID);
			adminFacade.RemoveCustomer(custID);
			return removeCust;
		}
		throw new CouponSystemException("You are not logged in, please log");

	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Customer UpdateCustomer(@Context HttpServletRequest request, Customer cust) {
		HttpSession seesion = request.getSession(false);
		if (seesion != null) {
			AdminFacade adminFacade = (AdminFacade) seesion.getAttribute("adminFacade");

			if (adminFacade == null)
				throw new CouponSystemException("You are not authorized to perform that operation");

			adminFacade.UpdateCustomer(cust);
			return adminFacade.GetCustomerByID(cust.getId());
		}
		throw new CouponSystemException("You are not logged in, please log");
	}

	@GET
	@Path("/id/{id}")
	public Customer GetCustomerByID(@Context HttpServletRequest request, @PathParam("id") long customerID) {
		HttpSession seesion = request.getSession(false);
		if (seesion != null) {
			AdminFacade adminFacade = (AdminFacade) seesion.getAttribute("adminFacade");

			if (adminFacade == null)
				throw new CouponSystemException("You are not authorized to perform that operation");

			return adminFacade.GetCustomerByID(customerID);
		}
		throw new CouponSystemException("You are not logged in, please log");
	}

	@GET
	@Path("/name/{name}")
	public Customer GetCustomerByName(@Context HttpServletRequest request, @PathParam("name") String customerName) {
		HttpSession seesion = request.getSession(false);
		if (seesion != null) {
			AdminFacade adminFacade = (AdminFacade) seesion.getAttribute("adminFacade");

			if (adminFacade == null)
				throw new CouponSystemException("You are not authorized to perform that operation");

			return adminFacade.GetCustomerByName(customerName);
		}
		throw new CouponSystemException("You are not logged in, please log");
	}

	@GET
	@Path("/all")
	public Customer[] GetAllCustomers(@Context HttpServletRequest request) {
		HttpSession seesion = request.getSession(false);
		if (seesion != null) {
			AdminFacade adminFacade = (AdminFacade) seesion.getAttribute("adminFacade");

			if (adminFacade == null)
				throw new CouponSystemException("You are not authorized to perform that operation");

			return adminFacade.GetAllCustomers().toArray(new Customer[0]);

		}
		throw new CouponSystemException("You are not logged in, please log");
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Customer CreateCustomer(@Context HttpServletRequest request, Customer cust) {

		HttpSession seesion = request.getSession(false);
		if (seesion != null) {
			AdminFacade adminFacade = (AdminFacade) seesion.getAttribute("adminFacade");

			if (adminFacade == null)
				throw new CouponSystemException("You are not authorized to perform that operation");

			adminFacade.CreateCustomer(cust);
			return adminFacade.GetCustomerByID(cust.getId());
		}
		throw new CouponSystemException("You are not logged in, please log");
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Company CreateCompany(@Context HttpServletRequest request, Company comp) {

		HttpSession seesion = request.getSession(false);
		if (seesion != null) {
			AdminFacade adminFacade = (AdminFacade) seesion.getAttribute("adminFacade");

			if (adminFacade == null)
				throw new CouponSystemException("You are not authorized to perform that operation");

			adminFacade.CreateCompany(comp);

			return adminFacade.GetCompanyByID(comp.getId());
		}
		throw new CouponSystemException("You are not logged in, please log");
	}

	@DELETE
	@Path("/id/{id}")
	public Company RemoveCompany(@Context HttpServletRequest request, @PathParam("id") long compID) {
		HttpSession seesion = request.getSession(false);
		if (seesion != null) {
			AdminFacade adminFacade = (AdminFacade) seesion.getAttribute("adminFacade");

			if (adminFacade == null)
				throw new CouponSystemException("You are not authorized to perform that operation");

			Company removedCompany = adminFacade.GetCompanyByID(compID);
			adminFacade.RemoveCompany(compID);
			return removedCompany;
		}
		throw new CouponSystemException("You are not logged in, please log");

	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Company UpdateCompany(@Context HttpServletRequest request, Company comp) {
		HttpSession seesion = request.getSession(false);
		if (seesion != null) {
			AdminFacade adminFacade = (AdminFacade) seesion.getAttribute("adminFacade");

			if (adminFacade == null)
				throw new CouponSystemException("You are not authorized to perform that operation");

			adminFacade.UpdateCompany(comp);
			return adminFacade.GetCompanyByID(comp.getId());
		}
		throw new CouponSystemException("You are not logged in, please log");
	}

	@GET
	@Path("/id/{id}")
	public Company GetCompanyByID(@Context HttpServletRequest request, @PathParam("id") long companyID) {
		HttpSession seesion = request.getSession(false);
		if (seesion != null) {
			AdminFacade adminFacade = (AdminFacade) seesion.getAttribute("adminFacade");

			if (adminFacade == null)
				throw new CouponSystemException("You are not authorized to perform that operation");

			return adminFacade.GetCompanyByID(companyID);
		}
		throw new CouponSystemException("You are not logged in, please log");
	}

	@GET
	@Path("/name/{name}")
	public Company GetCompanyByName(@Context HttpServletRequest request, @PathParam("name") String companyName) {
		HttpSession seesion = request.getSession(false);
		if (seesion != null) {
			AdminFacade adminFacade = (AdminFacade) seesion.getAttribute("adminFacade");

			if (adminFacade == null)
				throw new CouponSystemException("You are not authorized to perform that operation");

			return adminFacade.GetCompanyByName(companyName);
		}
		throw new CouponSystemException("You are not logged in, please log");
	}

	@GET
	@Path("/all")
	public Company[] GetAllCompanies(@Context HttpServletRequest request) {
		HttpSession seesion = request.getSession(false);
		if (seesion != null) {
			AdminFacade adminFacade = (AdminFacade) seesion.getAttribute("adminFacade");

			if (adminFacade == null)
				throw new CouponSystemException("You are not authorized to perform that operation");

			return adminFacade.GetAllCompanies().toArray(new Company[0]);
		}
		throw new CouponSystemException("You are not logged in, please log");
	}

}
