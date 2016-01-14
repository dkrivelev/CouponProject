package main.api.JsonUtil;

import java.sql.Date;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

import main.Exceptions.CouponSystemException;
import main.beanes.Coupon;

@XmlRootElement
public class JasonCoupon {

	@JsonProperty("id")
	private String id;
	
	@JsonProperty("title")
	private String title;
	
	@JsonProperty("startDate")
	private String startDate;
	
	@JsonProperty("endDate")
	private String endDate;
	
	@JsonProperty("amount")
	private int amount;
	
	@JsonProperty("msg")
	private String msg;
	
	@JsonProperty("price")
	private String price;
	
	@JsonProperty("imaged")
	private String image;
	
	@JsonProperty("couponType")
	private String couponType;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getCouponType() {
		return couponType;
	}

	public void setCouponType(String couponType) {
		this.couponType = couponType;
	}
	
	public Coupon toCoupon(){
		Coupon coupon = new Coupon(title,String2Date(startDate),String2Date(endDate),
			amount,msg,Double.parseDouble(price),image,couponType);
		
		return coupon;
	}
	
	public Coupon toCoupon(boolean includeCouponID){
		if (includeCouponID)
		{
			return new Coupon(Long.parseLong(id),title,String2Date(startDate),String2Date(endDate),
					amount,msg,Double.parseDouble(price),image,couponType);
		}
		else{
			return toCoupon();
			
		}			
		
	}
	
	private Date String2Date(String strDate)
	{
		try {
			return new Date(Long.parseLong(strDate));
		} catch (Exception e) {
			throw new CouponSystemException("Coupon Date is illegal ");
		}
	}
	
}
