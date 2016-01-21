package main.beanes;


import main.Exceptions.CouponSystemException;
import main.db.DBDAO.CustomerDBDAO;

public class Customer {

	private long id;
	private String custName;
	private String password;

	public Customer(){
		super();
		this.id = Long.MIN_VALUE; // for make sure the id is set only once, check is made in setID
		
	}
	
	public Customer(long id , String custName, String password) {
		super();
		this.id = id; // for make sure the id is set only once, check is made in setID
		this.custName = custName;
		this.password = password;
	}
	
	public Customer(String custName, String password) {
		super();
		this.id = Long.MIN_VALUE; // for make sure the id is set only once, check is made in setID
		this.custName = custName;
		this.password = password;
	}
	
	public Customer(Customer customer) {
		super();
		this.id = customer.getId();
		this.custName = customer.getCustName();
		this.password = customer.getPassword();
	}

	// {{ getters

	public long getId() {
		if (id == Long.MIN_VALUE || id == Long.MIN_VALUE)
		{
			CustomerDBDAO custDBDAO = new CustomerDBDAO();
			Customer temp = custDBDAO.GetCoustomer(custName);
			if (temp != null)
				setID(temp.getId());
		}
		
		return id;
	}

	public String getCustName() {
		return custName;
	}

	public String getPassword() {
		return password;
	}

	// }}

	// {{ setters
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setID(long id) {
		 if (this.id != Long.MIN_VALUE)	
			 throw new CouponSystemException("Customer ID can be set only once");
			 
		 this.id = id;
	}
	
	// }}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", custName=" + custName + ", password="
				+ password + "]";
	}
	
}
