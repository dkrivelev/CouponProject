package main.db.DBDAO;

import java.util.Collection;

import main.Exceptions.CouponSystemException;

public interface CustomerCouponDAO {

	/**
	 * Add existing coupon to existing customer 
	 * @param cust_id Customer ID
	 * @param coupon_id Coupun ID
	 * @throws CouponSystemException
	 */
	void AddCouponToCoustomer(long cust_id, long coupon_id) throws CouponSystemException;
	
	/**
	 * Get all customer coupons
	 * @param cust_id Coustomer id
	 * @return Collection of all the coupon's id if the customer
	 * @throws CouponSystemException
	 */
	Collection<Long> GetCouponsByCoustmerId(long cust_id) throws CouponSystemException;
	
	/**
	 * Delete specific coupon of specific customer, without delete coupon from DB
	 * @param cust_id - Coustomer id
	 * @param coupon_id coupon id
	 * @throws CouponSystemException
	 */
	void DeleteCoustomerCoupon(long cust_id, long coupon_id) throws CouponSystemException;
	
	/**
	 * Delete all coupons of the customer, without delete coupons from DB
	 * @param cust_id Customer id
	 * @throws CouponSystemException
	 */
	void DeleteAllCoustomerCoupons(long cust_id) throws CouponSystemException;
	
	/**
	 * Delete coupon from all the customer, without delete coupon from DB
	 * @param coupon_id Coupon id to delete
	 * @throws CouponSystemException
	 */
	void DeleteAllCouponsbyID(long coupon_id) throws CouponSystemException;
	
	
	
}
