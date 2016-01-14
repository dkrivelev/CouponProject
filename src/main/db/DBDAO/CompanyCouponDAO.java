package main.db.DBDAO;

import java.util.Collection;

import main.Exceptions.CouponSystemException;

public interface CompanyCouponDAO {

	/**
	 * Connect existing coupon to Company
	 * @param company_id Id of company to add the coupon
	 * @param coupon_id Coupon id
	 * @throws CouponSystemException
	 */
	void AddCouponToCompany(long company_id, long coupon_id) throws CouponSystemException;
	
	/**
	 * Get all Company's Coupons
	 * @param company_id
	 * @return Coupon id Collection, id of all the coupons of the Company
	 * @throws CouponSystemException
	 */
	Collection<Long> GetAllCompanyCoupons(long company_id) throws CouponSystemException;
	
	/**
	 * Remove coupon from company coupons, without delete the coupon from DB
	 * @param company_id - Id of the company to remove the coupon from
	 * @param coupon_id - Id of the coupon to remove
	 * @throws CouponSystemException
	 */
	void DeleteCompanyCoupon(long company_id, long coupon_id) throws CouponSystemException;
	
	/**
	 * Remove all company's coupons, without delete the coupons from DB
	 * @param company_id - company to remove its coupon
	 * @throws CouponSystemException
	 */
	void DeleteAllCompanyCoupons(long company_id) throws CouponSystemException;
	
	/**
	 * Delete the coupon from all the company it related, without delete the coupon from DB
	 * @param coupon_id
	 * @throws CouponSystemException
	 */
	void DeleteAllCouponsbyID(long coupon_id) throws CouponSystemException;
	
	
	
}
