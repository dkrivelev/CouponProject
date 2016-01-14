package main.beanes;

import java.sql.SQLException;

import main.Connection.ConnectionPool;
import main.Exceptions.CouponSystemException;
import main.Facade.AdminFacade;
import main.Facade.ClientFacade;
import main.Facade.CompanyFacade;
import main.Facade.CustomerFacade;
import main.db.DBDAO.CompanyDBDAO;
import main.db.DBDAO.CustomerDBDAO;
import main.enums.ClientType;

public class CouponSystem {

	private static CouponSystem instance = null;

	private CompanyDBDAO companyDBDAO = null;
	private CustomerDBDAO customerDBDAO = null;
	private DailyCouponExpirationTask runner = null;

	public static CouponSystem GetInstance() {
		if (instance == null)
			instance = new CouponSystem();

		return instance;
	}

	private CouponSystem() {
		//Thread is not needed in the phase
		//DelteExpierdCoupons();
		
		companyDBDAO = new CompanyDBDAO();
		customerDBDAO = new CustomerDBDAO();	
		
	}

	private void DelteExpierdCoupons(){
		runner = new DailyCouponExpirationTask();
		Thread tRunner = new Thread(runner);
		tRunner.start();
	}
	
	
	public ClientFacade Login(String name, String password,
			ClientType clientType) {

		switch (clientType) {
		case AdminFacade:
			if (name.equals("admin") && password.equals("1234"))
				return new AdminFacade();
			break;
		case CompanyFacade:
			if (companyDBDAO.login(name, password)) {
				return new CompanyFacade(companyDBDAO.getCompany(name).getId());
			}
			break;

		case CustomerFacade:
			if (customerDBDAO.login(name, password)) {
				return new CustomerFacade(customerDBDAO.GetCoustomer(name).getId());
			}
			break;
		default:
			return null;

		}
		
		return null;
	}
	
	public void shutdown()
	{
		if (runner != null)
			runner.StopRunning();
		
		try {
			ConnectionPool connectionPool= ConnectionPool.getInstance();
			connectionPool.closeAllConnection();
		} catch (SQLException e) {			
			throw new CouponSystemException("shutdown failed", e);
		}
		
	}

}
