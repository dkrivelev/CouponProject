package main.beanes;

import java.sql.Date;

import main.Exceptions.CouponSystemException;
import main.db.DBDAO.CouponDBDAO;
import main.enums.CouponType;

public class Coupon {

	private long id;
	private String title;
	private Date startDate;
	private Date endDate;
	private int amount;
	private String msg;
	private double price;
	private String image;
	private CouponType couponType;

	public Coupon(){super();}
	
	public Coupon(long id, String title, Date startDate, Date endDate,
			int amount, String msg, double price, String image,
			String couponType) {
		initParams(title, startDate, endDate, amount, msg, price, image);
		this.couponType = CouponType.valueOf(couponType.toUpperCase());
		this.id = id;
	}
	
	public Coupon(String title, Date startDate, Date endDate,
			int amount, String msg, double price, String image,
			CouponType couponType) {		
		
		initParams(title, startDate, endDate, amount, msg, price, image);
		this.couponType =  couponType;
		
	}

	public Coupon(String title, Date startDate, Date endDate,
			int amount, String msg, double price, String image,
			String couponType) {
		initParams(title, startDate, endDate, amount, msg, price, image);
		this.couponType = CouponType.valueOf(couponType.toUpperCase());
	}

	private void initParams(String title, Date startDate, Date endDate,
			int amount, String msg, double price, String image)
	{
		this.id = Long.MIN_VALUE; // for make sure the id is set only once, check is made in setID
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.msg = msg;
		this.price = price;
		this.image = image;
	}
	
	// {{ Getters

	
	public long getId() {
		if (id == Long.MIN_VALUE || id == Long.MIN_VALUE)
		{
			CouponDBDAO coupDBDAO = new CouponDBDAO();
			Coupon temp = coupDBDAO.GetCoupon(title);
			if (temp != null)
				setID(temp.getId());
		}
		
		return id;
	}

	public String getTitle() {
		return title;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public int getAmount() {
		return amount;
	}

	public String getMsg() {
		return msg;
	}

	public double getPrice() {
		return price;
	}

	public String getImage() {
		return image;
	}

	public CouponType getCouponType() {
		return couponType;
	}

	// }}

	// {{ Setters

	public void setTitle(String title) {
		this.title = title;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setCouponType(CouponType couponType) {
		this.couponType = couponType;
	}

	public void setID(long id) {
		 if (this.id != Long.MIN_VALUE)	
			 throw new CouponSystemException("Coupon ID can be set only once");
			 
		 this.id = id;
	}
	// }}

	@Override
	public String toString() {
		return "Coupon [id=" + id + ", title=" + title + ", startDate="
				+ startDate + ", endDate=" + endDate + ", amount=" + amount
				+ ", msg=" + msg + ", price=" + price + ", image=" + image
				+ ", couponType=" + couponType + "]";
	}

}
