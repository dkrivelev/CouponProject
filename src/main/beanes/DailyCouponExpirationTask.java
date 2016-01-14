package main.beanes;

import java.sql.Date;
import java.util.Collection;
import java.util.GregorianCalendar;

import main.db.DBDAO.CompanyCouponDBDAO;
import main.db.DBDAO.CouponDBDAO;
import main.db.DBDAO.CustomerCouponDBDAO;

public class DailyCouponExpirationTask implements Runnable{

	private boolean stopThread = false;
	
	@Override
	public void run() {
		CouponDBDAO couponDBDAO = new CouponDBDAO();
		Collection<Coupon> coupons = couponDBDAO.GetAllCoupons();
		for (Coupon coupon : coupons) {
			if (stopThread)
				return;
			
			Date curDate = new Date(GregorianCalendar.getInstance().getTimeInMillis());
			if (coupon.getEndDate().before(curDate))
			{
				couponDBDAO.RemoveCoupon(coupon.getId());
				
				CustomerCouponDBDAO customerCouponDBDAO = new CustomerCouponDBDAO();
				customerCouponDBDAO.DeleteAllCouponsbyID(coupon.getId());
				
				CompanyCouponDBDAO companyCouponDBDAO = new  CompanyCouponDBDAO();
				companyCouponDBDAO.DeleteAllCouponsbyID(coupon.getId());
			}
		}
		StopRunning();	
	}
	
	public void StopRunning() {
		stopThread = true;
	}

}
