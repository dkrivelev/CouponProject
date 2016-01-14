package main.beanes;

import main.Exceptions.CouponSystemException;
import main.db.DBDAO.CompanyDBDAO;

public class Company {
	
	private long id;
	private String compName;
	private String password;
	private String email;
		
	public Company(){super();}
	
	public Company(long id, String compName, String password, String email) {
		super();
		this.id = id;
		this.compName = compName;
		this.password = password;
		this.email = email;
	} 
	
	public Company(String compName, String password, String email) {
		super();
		this.id = Long.MIN_VALUE; // for make sure the id is set only once, check is made in setID
		this.compName = compName;
		this.password = password;
		this.email = email;
	}
	
	public Company(Company company) {
		super();
		this.id = company.getId();
		this.compName = company.getCompName();
		this.password = company.getPassword();
		this.email = company.getEmail();
	}

	// {{ Getters	
	public long getId() {
		if (id == Long.MIN_VALUE || id == Long.MIN_VALUE)
		{
			CompanyDBDAO compDBDAO = new CompanyDBDAO();
			Company temp = compDBDAO.getCompany(compName);
			if (temp != null)
				setID(temp.getId());
		}
		
		return id;
	}



	public String getCompName() {
		return compName;
	}



	public String getPassword() {
		return password;
	}



	public String getEmail() {
		return email;
	}
		
	// }}
	
	// {{ Setters
	public void setEmail(String email) {
		this.email = email;
	}
	
	 public void setPassword(String password) {
		this.password = password;
	}
	 
	 public void setID(long id) {
		 if (this.id != Long.MIN_VALUE)	
			 throw new CouponSystemException("Comany ID can be set only once");
			 
		 this.id = id;
		}
	 
	//Added because of phase 2:
	 public void setId(long id) {
			this.id = id;
		}

		public void setCompName(String compName) {
			this.compName = compName;
		}
	// }}
	
	

	@Override
	public String toString() {
		return "Company [id=" + id + ", compName=" + compName + ", password="
				+ password + ", email=" + email + "]";
	}

	
	
}
