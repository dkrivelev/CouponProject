package main.Facade;

import java.util.Collection;

import main.Exceptions.CouponSystemException;
import main.beanes.Company;
import main.beanes.Customer;
import main.db.DBDAO.CompanyCouponDBDAO;
import main.db.DBDAO.CompanyDBDAO;
import main.db.DBDAO.CustomerCouponDBDAO;
import main.db.DBDAO.CustomerDBDAO;


public class AdminFacade implements ClientFacade{

	private CompanyDBDAO companyDBDAO = null;
	private CompanyCouponDBDAO companyCouponDBDAO = null;
	private CustomerCouponDBDAO customerCouponDBDAO = null;
	private CustomerDBDAO customerDBDAO = null;
	
	
	public AdminFacade() {
		companyDBDAO = new CompanyDBDAO();
		companyCouponDBDAO = new CompanyCouponDBDAO();
		customerCouponDBDAO = new CustomerCouponDBDAO();
		customerDBDAO = new CustomerDBDAO();
	}
		
	/**
	 * Create new company in DB
	 * @param comp Company to add
	 * @throws CouponSystemException
	 */
	public void CreateCompany(Company comp) throws CouponSystemException {
		companyDBDAO.createCompany(comp);
		
	}
	
	/**
	 * Delete company 
	 * Delete all company's coupons 
 	 * Delete all the customer coupons of the Company
	 * @param comp Comapny to delete its coupons
	 * @throws CouponSystemException
	 */
	public void RemoveCompany(long compID) throws CouponSystemException {
		
		//Delete all the customer coupons of the Company
		Collection<Long> allCompanyCoupons = companyCouponDBDAO.GetAllCompanyCoupons(compID);
		for (Long long1 : allCompanyCoupons) {
			customerCouponDBDAO.DeleteAllCouponsbyID(long1);
		}		
			
		//Delete all Company coupons
		companyCouponDBDAO.DeleteAllCompanyCoupons(compID);
		
		//Delete company
		companyDBDAO.removeCompany(compID);

	}
	
	/**
	 * Update company mete data
	 * @param comp Company to update
	 * @throws CouponSystemException
	 */
	public void UpdateCompany(Company comp) throws CouponSystemException {
		companyDBDAO.UpdateCompany(comp);

	}
	
	/**
	 * Get company by id
	 * @param companyID Company id
	 * @return Company
	 * @throws CouponSystemException
	 */
	public Company GetCompanyByID(long companyID) throws CouponSystemException {
		return companyDBDAO.getCompany(companyID);
	}
	
	/**
	 * Get Company by Company Name
	 * @param companyName Comapny Name
	 * @return
	 * @throws CouponSystemException
	 */
	public Company GetCompanyByName(String companyName) throws CouponSystemException {
		return companyDBDAO.getCompany(companyName);
	}

	/**
	 * Get all companies from DB
	 * @return Collection of company
	 * @throws CouponSystemException
	 */
	public Collection<Company> GetAllCompanies() throws CouponSystemException {		
		return companyDBDAO.getAllCompamies();
	}

	/**
	 * Create new customer in DB
	 * @param customer Customer to add
	 * @throws CouponSystemException
	 */
	public void CreateCustomer(Customer customer) throws CouponSystemException {
		customerDBDAO.CreateCustomer(customer);

	}

	/**
	 * Delete customer and all its coupons
	 * @param customer Customer to remove
	 * @throws CouponSystemException
	 */
	public void RemoveCustomer(long custID) throws CouponSystemException {
		customerCouponDBDAO.DeleteAllCoustomerCoupons(custID);
		customerDBDAO.RemoveCustomer(custID);
	}

	/**
	 * Update customer meta data
	 * @param customer Customer to update
	 * @throws CouponSystemException
	 */
	public void UpdateCustomer(Customer customer) throws CouponSystemException {
		customerDBDAO.UpdateCustomer(customer);

	}

	/**
	 * Get customer by id
	 * @param customerID
	 * @return Customer
	 * @throws CouponSystemException
	 */
	public Customer GetCustomerByID(long customerID)
			throws CouponSystemException {
		return customerDBDAO.GetCoustomer(customerID);
	}

	public Customer GetCustomerByName(String customerName) throws CouponSystemException {
		return customerDBDAO.GetCoustomer(customerName);
	}
	
	/**
	 * Get all customer in DB
	 * @return Collection of customer
	 * @throws CouponSystemException
	 */
	public Collection<Customer> GetAllCustomers() throws CouponSystemException {
			return customerDBDAO.GetAllCustomers();
	}

}
