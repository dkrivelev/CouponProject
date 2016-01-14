package main.db.DBDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import main.Connection.ConnectionPool;
import main.Exceptions.CouponSystemException;
import main.beanes.Company;

public class CompanyDBDAO implements CompanyDAO {

	@Override
	public void createCompany(Company comp) throws CouponSystemException {
		Connection con = null;
		ConnectionPool connPool = null;
		try {
			connPool = ConnectionPool.getInstance();
			con = connPool.getConnection();

			//Statement stmt = con.createStatement();
			String sql = "INSERT INTO Company(Comp_Name, Password, Email) VALUES(?, ?, ?) ";
			PreparedStatement preStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			preStatement.setString(1, comp.getCompName());
			preStatement.setString(2, comp.getPassword());
			preStatement.setString(3, comp.getEmail());
			
			//stmt.executeUpdate(sql);
			preStatement.execute();
			ResultSet rs = preStatement.getGeneratedKeys();			
			if (rs.next()) {
			    comp.setID(rs.getLong(1));			    
			}			

		} catch (SQLException e) {
			throw new CouponSystemException("Create comapany failed", e);
		} finally {
			if (connPool != null && con != null)
				connPool.returnConnection(con);
		}

	}

	@Override
	public void removeCompany(long compID) throws CouponSystemException {
		Connection con = null;
		ConnectionPool connPool = null;
		try {
			connPool = ConnectionPool.getInstance();
			con = connPool.getConnection();
				
			Statement stmt = con.createStatement();
			String sql = "DELETE FROM Company WHERE ID=" + compID;
			stmt.executeUpdate(sql);

		} catch (SQLException e) {
			throw new CouponSystemException("Remove comapany failed", e);
		} finally {
			if (connPool != null && con != null)
				connPool.returnConnection(con);
		}

	}

	@Override
	public void UpdateCompany(Company comp) throws CouponSystemException {

		Connection con = null;
		ConnectionPool connPool = null;		
		try {
			connPool = ConnectionPool.getInstance();
			con = connPool.getConnection();
			
			String sql = "UPDATE Company SET Comp_Name=?,Password=?,Email=? WHERE ID=?";
			PreparedStatement preStatment = con.prepareStatement(sql);
			preStatment.setString(1 , comp.getCompName());
			preStatment.setString(2 , comp.getPassword());
			preStatment.setString(3 , comp.getEmail());
			preStatment.setLong(4, comp.getId());
			
			if (preStatment.executeUpdate() == 0)
				throw new CouponSystemException("Comapny was not Update, please check company details again");
			
		} catch (SQLException e) {
			throw new CouponSystemException("Update comapany failed", e);

		} finally {
			if (connPool != null && con != null)
				connPool.returnConnection(con);
		}

	}

	public Company getCompany(long companyID) throws CouponSystemException {
		Connection con = null;
		ConnectionPool connPool = null;
		Company retComany = null;
		try {
			connPool = ConnectionPool.getInstance();
			con = connPool.getConnection();

			Statement stmt = con.createStatement();
			String sql = "Select * FROM Company WHERE ID=" + companyID;
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				//retComany = new Company(rs.getLong("ID"), rs.getString("Comp_Name"), rs.getString("Password"),rs.getString("Email"));
				retComany = new Company(rs.getString("Comp_Name"), rs.getString("Password"),rs.getString("Email"));
				retComany.setID(companyID);
			}

		} catch (SQLException e) {
			throw new CouponSystemException("Get comapany(id= " + companyID
					+ ")failed", e);
		} finally {
			if (connPool != null && con != null)
				connPool.returnConnection(con);
		}
		return retComany;

	}

	@Override
	public Company getCompany(String companyName) throws CouponSystemException {
		Connection con = null;
		ConnectionPool connPool = null;
		Company retComany = null;
		try {
			connPool = ConnectionPool.getInstance();
			con = connPool.getConnection();

			//Statement stmt = con.createStatement();
			String sql = "Select * FROM Company WHERE Comp_Name=?";
			PreparedStatement preStatement = con.prepareStatement(sql);
			preStatement.setString(1, companyName);
			
			ResultSet rs = preStatement.executeQuery();
			if (rs.next()) {
				//retComany = new Company(rs.getLong("ID"),rs.getString("Comp_Name"), rs.getString("Password"),rs.getString("Email"));
				retComany = new Company(rs.getString("Comp_Name"), rs.getString("Password"),rs.getString("Email"));
				retComany.setID(rs.getLong("ID"));
			}

		} catch (SQLException e) {
			throw new CouponSystemException("Get comapany(name= " + companyName
					+ ")failed", e);
		} finally {
			if (connPool != null && con != null)
				connPool.returnConnection(con);
		}
		return retComany;

	}

	@Override
	public Collection<Company> getAllCompamies() throws CouponSystemException {
		Connection con = null;
		ConnectionPool connPool = null;
		Collection<Company> allComapnies = new ArrayList<Company>();
		try {
			connPool = ConnectionPool.getInstance();
			con = connPool.getConnection();

			Statement stmt = con.createStatement();
			String sql = "Select * FROM Company";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Company comp = new Company(rs.getString("Comp_Name"), rs.getString("Password"), rs.getString("Email"));
				comp.setID(rs.getLong("ID"));
				allComapnies.add(comp);				
			}

		} catch (SQLException e) {
			throw new CouponSystemException("Get all comapanies failed", e);
		} finally {
			if (connPool != null && con != null)
				connPool.returnConnection(con);
		}
		return allComapnies;
	}

	@Override
	public boolean login(String companyName, String password) {
		Connection con = null;
		ConnectionPool connPool = null;
		boolean isSucceed = false;
		try {
			connPool = ConnectionPool.getInstance();
			con = connPool.getConnection();

			//Statement stmt = con.createStatement();
			// String sql = "Select * FROM Company WHERE Comp_Name='"
			// + companyName + "' AND Password='" + password + "'";
			String sql = "Select * FROM Company WHERE Comp_Name=? AND Password=?";
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, companyName);
			preparedStatement.setString(2, password);			
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				isSucceed = true;
			}

		} catch (SQLException e) {
			throw new CouponSystemException("Comapany login error", e);
		} finally {
			if (connPool != null && con != null)
				connPool.returnConnection(con);
		}
		return isSucceed;
	}

	/**
	 * Check if company exists in DB
	 * 
	 * @param companyID
	 *            - Id of company
	 * @return true if company exists in DB, else false
	 * @throws CouponSystemException
	 */
	public boolean IsCompanyExists(long companyID) throws CouponSystemException {
		Connection con = null;
		ConnectionPool connPool = null;
		boolean isCompanyExists = false;
		try {
			connPool = ConnectionPool.getInstance();
			con = connPool.getConnection();

			Statement stmt = con.createStatement();
			String sql = "Select * FROM Company WHERE ID=" + companyID;
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				isCompanyExists = true;
			}

		} catch (SQLException e) {
			throw new CouponSystemException("Is Company Exists check failed", e);
		} finally {
			if (connPool != null && con != null)
				connPool.returnConnection(con);
		}
		return isCompanyExists;

	}

}
