package main.db.DBDAO;

import java.util.Collection;

import main.Exceptions.CouponSystemException;
import main.beanes.Coupon;
import main.enums.CouponType;

public interface CouponDAO {

	/**
	 * Create new coupon in the DB
	 * @param coup Coupon to add
	 * @throws CouponSystemException
	 */
	void CreateCoupon(Coupon coup) throws CouponSystemException;
	
	/**
	 * Delete coupon from DB
	 * @param coup coupon to delete
	 * @throws CouponSystemException
	 */
	void RemoveCoupon(long id) throws CouponSystemException;
	
	/**
	 * Update coupon mete data
	 * @param coup coupon to update
	 * @throws CouponSystemException
	 */
	void UpdateCoupon(Coupon coup) throws CouponSystemException;
	
	/**
	 * Get coupon by id
	 * @param id
	 * @return Coupon
	 * @throws CouponSystemException
	 */
	Coupon GetCoupon(long id) throws CouponSystemException;
	
	/**
	 * Get all coupon from DB 
	 * @return Collection of all the coupons
	 * @throws CouponSystemException
	 */
	Collection<Coupon> GetAllCoupons() throws CouponSystemException;
	
	/**
	 * Get Coupons by type
	 * @param couponType Coupon Type
	 * @return Collection of all coupon by couponType 
	 * @throws CouponSystemException
	 */
	Collection<Coupon> GetCouponsByType(CouponType couponType) throws CouponSystemException;
	
}
