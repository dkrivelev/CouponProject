package main.Facade;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import main.Exceptions.CouponSystemException;
import main.beanes.Company;
import main.beanes.Coupon;
import main.db.DBDAO.CompanyCouponDBDAO;
import main.db.DBDAO.CompanyDBDAO;
import main.db.DBDAO.CouponDBDAO;
import main.db.DBDAO.CustomerCouponDBDAO;
import main.enums.CouponType;

public class CompanyFacade implements ClientFacade {

	private long companyID;
	private CouponDBDAO couponDBDAO = null;
	private CompanyCouponDBDAO companyCouponDBDAO = null;
	private CustomerCouponDBDAO customerCouponDBDAO = null;
	private CompanyDBDAO companyDBDA = null;

	public CompanyFacade(long companyID) {
		super();
		this.companyID = companyID;
		couponDBDAO = new CouponDBDAO();
		companyCouponDBDAO = new CompanyCouponDBDAO();
		customerCouponDBDAO = new CustomerCouponDBDAO();
		companyDBDA = new CompanyDBDAO();
	}

	/**
	 * If coupon not exists create it and connect it to the company
	 * 
	 * @param coupon
	 *            Coupon to add to Company
	 * @throws CouponSystemException
	 */
	public void CreateCoupon(Coupon coupon) throws CouponSystemException {
		if (!couponDBDAO.IsCouponExists(coupon.getTitle())) {
			couponDBDAO.CreateCoupon(coupon);
			
		}
		companyCouponDBDAO.AddCouponToCompany(companyID, coupon.getId());

	}

	/**
	 * Delete coupon Delete the coupon from Company Delete the coupon from all
	 * the customer who buy it
	 * 
	 * @param coupon
	 * @throws CouponSystemException
	 */
	public void RemoveCoupon(long couponID) throws CouponSystemException {
		couponDBDAO.RemoveCoupon(couponID);
		companyCouponDBDAO.DeleteCompanyCoupon(companyID, couponID);
		customerCouponDBDAO.DeleteAllCouponsbyID(couponID);
	}

	/**
	 * Update coupon Price and End Date
	 * 
	 * @param coupon
	 *            Coupon to update
	 * @throws CouponSystemException
	 */
	public void UpdateCoupon(Coupon coupon) throws CouponSystemException {
		couponDBDAO.UpdateCoupon(coupon.getId(), coupon.getEndDate(),
				coupon.getPrice());

	}

	/**
	 * Get all coupons of the company
	 * 
	 * @return Coupons collection
	 * @throws CouponSystemException
	 */
	public Collection<Coupon> GetAllCoupons() throws CouponSystemException {
		Collection<Long> couponsID = companyCouponDBDAO
				.GetAllCompanyCoupons(companyID);
		Collection<Coupon> coupons = new ArrayList<Coupon>();
		for (long id : couponsID) {
			coupons.add(couponDBDAO.GetCoupon(id));
		}

		return coupons;
	}

	/**
	 * Get all company coupon filter by: type, price is less then (@param price)
	 * and date is valid
	 * 
	 * @param couponType
	 *            Coupon type, can be null
	 * @param price
	 *            up to price
	 * @param endDate
	 *            End date of the coupon, can be null
	 * @return Coupon collection
	 * @throws CouponSystemException
	 */
	public Collection<Coupon> GetCouponsByTypePriceDate(CouponType couponType,
			double price, Date date) throws CouponSystemException {
		Collection<Coupon> coupons = GetAllCoupons();

		for (Iterator<Coupon> iterator = coupons.iterator(); iterator.hasNext();) {
			Coupon coupon = (Coupon) iterator.next();
			boolean isCouponIsInTheFilter = false;			
			if (couponType == null || coupon.getCouponType() == couponType) {
				//the if statement is in NOT because if it is in the exacly date it is ok
				if ((date == null || (!coupon.getStartDate().after(date) && !coupon
						.getEndDate().before(date)))) {
					
					if (coupon.getPrice() <= price)
						isCouponIsInTheFilter = true;
				}
			}

			if (!isCouponIsInTheFilter)
				iterator.remove();

		}

		return coupons;
	}

	/**
	 * Get Company
	 * 
	 * @return Company
	 * @throws CouponSystemException
	 */
	public Company GetCompany() throws CouponSystemException {
		return companyDBDA.getCompany(companyID);
	}
}
