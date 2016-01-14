package main.db.DBDAO;

import java.util.Collection;

import main.Exceptions.CouponSystemException;
import main.beanes.Company;

public interface CompanyDAO {

	/**
	 * Create new company
	 * @param comp Company to create in DB	
	 * @throws CouponSystemException
	 */
	void createCompany(Company comp) throws CouponSystemException;
	
	/**
	 * Delete company from DB
	 * @param comp Company to delete
	 * @throws CouponSystemException
	 */
	void removeCompany(long companyID) throws CouponSystemException;
	
	/**
	 * Update company meta data
	 * @param comp
	 * @throws CouponSystemException
	 */
	void UpdateCompany(Company comp) throws CouponSystemException;
	
	/**
	 * Get company by id
	 * @param companyID - ID of comapny to return 
	 * @return Company
	 * @throws CouponSystemException
	 */
	Company getCompany(long companyID) throws CouponSystemException;
	
	/**
	 * Get company by company name
	 * @param companyName name of the company to return
	 * @return Company
	 * @throws CouponSystemException
	 */
	Company getCompany(String companyName) throws CouponSystemException;
	/**
	 * Get all companies
	 * @return Company collection
	 * @throws CouponSystemException
	 */
	Collection<Company> getAllCompamies() throws CouponSystemException;
	
	/**
	 * Check company name and password
	 * @param companyName Company name 
	 * @param password Company password
	 * @return return true if the name and password are correct, else return false
	 */
	boolean login(String companyName, String password);
	
}
