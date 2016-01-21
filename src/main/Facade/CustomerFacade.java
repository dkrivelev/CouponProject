package main.Facade;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;

import main.Exceptions.CouponSystemException;
import main.beanes.Coupon;
import main.beanes.Customer;
import main.db.DBDAO.CouponDBDAO;
import main.db.DBDAO.CustomerCouponDBDAO;
import main.db.DBDAO.CustomerDBDAO;
import main.enums.CouponType;


public class CustomerFacade implements ClientFacade{

	private CouponDBDAO couponDBDAO = null;	
	private CustomerCouponDBDAO customerCouponDBDAO = null;
	private long customerID;
	
	
	
	public CustomerFacade(long customerID) {
		super();
		this.customerID = customerID;		
		couponDBDAO = new CouponDBDAO();		
		customerCouponDBDAO = new CustomerCouponDBDAO();
	}

	public Customer GetCustomer() throws CouponSystemException{
		CustomerDBDAO customerDBDAO = new CustomerDBDAO();
		return customerDBDAO.GetCoustomer(this.customerID);
	}
	
	/**
	 * Add coupon to Customer and change amount on the coupon.
	 * Cannot purchase coupon which is out of stock
	 * Cannot purchase coupon which end date is expired
	 * @param coupon
	 * @throws CouponSystemException
	 */
	public void PurchaseCoupon(long couponID) throws CouponSystemException {
		Coupon coupon = couponDBDAO.GetCoupon(couponID);
		if (coupon == null )
			throw new CouponSystemException("Cannot does not exists");
		int amount = coupon.getAmount();
		if ( amount <= 0 )
			throw new CouponSystemException("Cannot purchase coupon because it is out of stock");
		
		Date curDate = new Date(GregorianCalendar.getInstance().getTimeInMillis());
		if (coupon.getEndDate().before(curDate))
			throw new CouponSystemException("Cannot purchase coupon because end date is expired");
		
		
		customerCouponDBDAO.AddCouponToCoustomer(customerID, coupon.getId());
		
		amount--;
		coupon.setAmount(amount);
		couponDBDAO.UpdateCoupon(coupon);
	}

	/*
	 * Get all customer coupons
	 */
	public Collection<Coupon> GetAllPurchasedCoupons()
			throws CouponSystemException {
		Collection<Long> CouponsID = customerCouponDBDAO.GetCouponsByCoustmerId(customerID);
		Collection<Coupon> Coupons = new ArrayList<Coupon>();
		for (Long copuponsID : CouponsID) {
			Coupons.add(couponDBDAO.GetCoupon(copuponsID));
		}
		return Coupons;
	}

	/**
	 * Get all customer coupon by type
	 * @param type
	 * @return coupon collection
	 * @throws CouponSystemException
	 */
	public Collection<Coupon> GetAllPurchasedCouponsByType(CouponType type)
			throws CouponSystemException {		
		Collection<Coupon> coupons = GetAllPurchasedCoupons();
		
		for (Iterator<Coupon> iterator = coupons.iterator(); iterator.hasNext();) {
			Coupon coupon = (Coupon) iterator.next();
			if (coupon.getCouponType() != type)
				iterator.remove();
						
		}
		
		return coupons;
	}

	
	/**
	 * return Coupons collection of all the customer coupons up to price: <price>
	 * @param price
	 * @return Coupons collection
	 * @throws CouponSystemException
	 */
	public Collection<Coupon> GetAllPurchasedCouponsByPrice(double price)
			throws CouponSystemException {
		Collection<Coupon> coupons = GetAllPurchasedCoupons();
		for (Iterator<Coupon> iterator = coupons.iterator(); iterator.hasNext();) {
			Coupon coupon = (Coupon) iterator.next();
			if (coupon.getPrice() > price)
				iterator.remove();
						
		}		
		
		return coupons;
	}

}
