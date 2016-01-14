package main.db.DBDAO;

import java.util.Collection;

import main.Exceptions.CouponSystemException;
import main.beanes.Customer;

public interface CustomerDAO {
	
	/**
	 * Create new customer
	 * @param cust Customer to add
	 * @throws CouponSystemException
	 */
	void CreateCustomer(Customer cust) throws CouponSystemException;
	
	/**
	 * Remove customer from DB
	 * @param cust Customer to remove
	 * @throws CouponSystemException
	 */
	void RemoveCustomer(long id) throws CouponSystemException;
	
	/**
	 * Update customer meta data
	 * @param cust customer to update
	 * @throws CouponSystemException
	 */
	void UpdateCustomer(Customer cust) throws CouponSystemException;
	
	/**
	 * Get customer by id
	 * @param id Customer id
	 * @return Customer
	 * @throws CouponSystemException
	 */
	Customer GetCoustomer(long id) throws CouponSystemException;
	
	/**
	 * Get customer by name
	 * @param coustomerName Customer name
	 * @return Customer
	 * @throws CouponSystemException
	 */
	Customer GetCoustomer(String coustomerName) throws CouponSystemException;
	
	/**
	 * Get all customer
	 * @return Collection of all the customers
	 * @throws CouponSystemException
	 */
	Collection<Customer> GetAllCustomers() throws CouponSystemException;
	
	/**
	 * Check login by Customer name and password
	 * @param customerName customer name
	 * @param password Customer password
	 * @return true if Customer name and password are correct else return false
	 */
	boolean login(String customerName, String password);
	

}
