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
import main.beanes.Customer;

public class CustomerDBDAO implements CustomerDAO {

	@Override
	public void CreateCustomer(Customer cust) throws CouponSystemException {
		Connection con = null;
		ConnectionPool connPool = null;
		try {
			connPool = ConnectionPool.getInstance();
			con = connPool.getConnection();

			String sql = "INSERT INTO Customer(Cust_Name, Password) VALUES(?,?)";
			PreparedStatement preStatement = con.prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS);
			preStatement.setString(1, cust.getCustName());
			preStatement.setString(2, cust.getPassword());

			// stmt.executeUpdate(sql);
			preStatement.execute();
			ResultSet rs = preStatement.getGeneratedKeys();
			if (rs.next()) {
				cust.setID(rs.getLong(1));
			}

		} catch (SQLException e) {
			throw new CouponSystemException("Create customer failed", e);
		} finally {
			if (connPool != null && con != null)
				connPool.returnConnection(con);
		}

	}

	@Override
	public void RemoveCustomer(long custID) throws CouponSystemException {
		Connection con = null;
		ConnectionPool connPool = null;
		try {
			connPool = ConnectionPool.getInstance();
			con = connPool.getConnection();

			Statement stmt = con.createStatement();
			String sql = "DELETE FROM Customer WHERE ID=" + custID;
			stmt.executeUpdate(sql);

		} catch (SQLException e) {
			throw new CouponSystemException("Remove customer failed", e);
		} finally {
			if (connPool != null && con != null)
				connPool.returnConnection(con);
		}

	}

	@Override
	public void UpdateCustomer(Customer cust) throws CouponSystemException {

		Connection con = null;
		ConnectionPool connPool = null;
		try {
			connPool = ConnectionPool.getInstance();
			con = connPool.getConnection();

			String sql = "UPDATE Customer SET Cust_Name=?,Password=? WHERE ID=?";

			PreparedStatement preStatment = con.prepareStatement(sql);
			preStatment.setString(1, cust.getCustName());
			preStatment.setString(2, cust.getPassword());
			preStatment.setLong(3, cust.getId());

			if (preStatment.executeUpdate() == 0)
				throw new CouponSystemException("Customer was not Update, please check customer details again");
			
		} catch (SQLException e) {
			throw new CouponSystemException("Update customer failed", e);

		} finally {
			if (connPool != null && con != null)
				connPool.returnConnection(con);
		}

	}

	@Override
	public Customer GetCoustomer(long id) throws CouponSystemException {
		Connection con = null;
		ConnectionPool connPool = null;
		Customer retCoustomer = null;
		try {
			connPool = ConnectionPool.getInstance();
			con = connPool.getConnection();

			Statement stmt = con.createStatement();
			String sql = "Select * FROM Customer WHERE ID=" + id;
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				retCoustomer = new Customer(rs.getString("Cust_Name"),
						rs.getString("Password"));
				retCoustomer.setID(rs.getLong("ID"));
			}

		} catch (SQLException e) {
			throw new CouponSystemException("Get customer(id= " + id
					+ ")failed", e);
		} finally {
			if (connPool != null && con != null)
				connPool.returnConnection(con);
		}
		return retCoustomer;

	}

	@Override
	public Customer GetCoustomer(String coustomerName)
			throws CouponSystemException {
		Connection con = null;
		ConnectionPool connPool = null;
		Customer retCoustomer = null;
		try {
			connPool = ConnectionPool.getInstance();
			con = connPool.getConnection();
			
			String sql = "Select * FROM Customer WHERE Cust_Name=?";
			PreparedStatement preStatement = con.prepareStatement(sql);
			preStatement.setString(1, coustomerName);			
			ResultSet rs = preStatement.executeQuery();
			
			if (rs.next()) {
				retCoustomer = new Customer(rs.getString("Cust_Name"),
						rs.getString("Password"));
				retCoustomer.setID(rs.getLong("ID"));
			}

		} catch (SQLException e) {
			throw new CouponSystemException("Get customer(name= "
					+ coustomerName + ")failed", e);
		} finally {
			if (connPool != null && con != null)
				connPool.returnConnection(con);
		}
		return retCoustomer;

	}

	@Override
	public Collection<Customer> GetAllCustomers() throws CouponSystemException {

		Connection con = null;
		ConnectionPool connPool = null;
		Collection<Customer> allCustomers = new ArrayList<Customer>();
		try {
			connPool = ConnectionPool.getInstance();
			con = connPool.getConnection();

			Statement stmt = con.createStatement();
			String sql = "Select * FROM Customer";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Customer coustomer = new Customer(rs.getString("Cust_Name"),
						rs.getString("Password"));
				coustomer.setID(rs.getLong("ID"));
				allCustomers.add(coustomer);
			}

		} catch (SQLException e) {
			throw new CouponSystemException("Get all customers failed", e);
		} finally {
			if (connPool != null && con != null)
				connPool.returnConnection(con);
		}
		return allCustomers;

	}

	@Override
	public boolean login(String customerName, String password) {
		Connection con = null;
		ConnectionPool connPool = null;
		boolean isSucceed = false;
		try {
			connPool = ConnectionPool.getInstance();
			con = connPool.getConnection();
			
			String sql = "Select * FROM Customer WHERE Cust_Name=? AND Password=?";
			
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, customerName);
			preparedStatement.setString(2, password);			
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				isSucceed = true;
			}

		} catch (SQLException e) {
			throw new CouponSystemException("login error", e);
		} finally {
			if (connPool != null && con != null)
				connPool.returnConnection(con);
		}
		return isSucceed;

	}

	public boolean IsCustomerExists(long id) throws CouponSystemException {
		Connection con = null;
		ConnectionPool connPool = null;
		boolean isCustomerExists = false;
		try {
			connPool = ConnectionPool.getInstance();
			con = connPool.getConnection();

			Statement stmt = con.createStatement();
			String sql = "Select * FROM Customer WHERE ID=" + id;
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				isCustomerExists = true;
			}

		} catch (SQLException e) {
			throw new CouponSystemException("Is Customer Exists check failed",
					e);
		} finally {
			if (connPool != null && con != null)
				connPool.returnConnection(con);
		}
		return isCustomerExists;

	}

}
