package main.db.DBDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import main.Connection.ConnectionPool;
import main.Exceptions.CouponSystemException;

public class CustomerCouponDBDAO implements CustomerCouponDAO {

	@Override
	public void AddCouponToCoustomer(long cust_id, long coupon_id)
			throws CouponSystemException {
		Connection con = null;
		ConnectionPool connPool = null;
		try {
			CouponDBDAO couponDBDAO = new CouponDBDAO();
			if (!couponDBDAO.IsCouponExists(coupon_id))
				throw new CouponSystemException(
						"Add coupon to coustomer failed - Coupon " + coupon_id
								+ " does not exists");

			CustomerDBDAO customerDBDAO = new CustomerDBDAO();
			if (!customerDBDAO.IsCustomerExists(cust_id))
				throw new CouponSystemException(
						"Add coupon to coustomer failed - Customer " + cust_id
								+ " does not exists");

			connPool = ConnectionPool.getInstance();
			con = connPool.getConnection();

			Statement stmt = con.createStatement();
			String sql = "INSERT INTO CustomerCoupon VALUES(" + cust_id + ","
					+ coupon_id + ")";
			stmt.executeUpdate(sql);

		} catch (SQLException e) {
			throw new CouponSystemException("Add coupon " + coupon_id
					+ " to coustomer " + cust_id + " failed", e);
		} finally {
			if (connPool != null && con != null)
				connPool.returnConnection(con);
		}

	}

	@Override
	public Collection<Long> GetCouponsByCoustmerId(long cust_id)
			throws CouponSystemException {
		Connection con = null;
		ConnectionPool connPool = null;
		Collection<Long> Coupons = new ArrayList<Long>();
		try {
			connPool = ConnectionPool.getInstance();
			con = connPool.getConnection();

			Statement stmt = con.createStatement();
			String sql = "Select * FROM CustomerCoupon WHERE Cust_ID="
					+ cust_id;
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Coupons.add(rs.getLong("Coupon_ID"));
			}

		} catch (SQLException e) {
			throw new CouponSystemException("Get customer coupons failed", e);
		} finally {
			if (connPool != null && con != null)
				connPool.returnConnection(con);
		}
		return Coupons;

	}

	@Override
	public void DeleteCoustomerCoupon(long cust_id, long coupon_id)
			throws CouponSystemException {
		Connection con = null;
		ConnectionPool connPool = null;
		try {
			connPool = ConnectionPool.getInstance();
			con = connPool.getConnection();

			Statement stmt = con.createStatement();
			String sql = "DELETE FROM CustomerCoupon WHERE Cust_ID=" + cust_id
					+ "AND Coupon_ID=" + coupon_id;
			stmt.executeUpdate(sql);

		} catch (SQLException e) {
			throw new CouponSystemException("Delete customer coupon failed", e);
		} finally {
			if (connPool != null && con != null)
				connPool.returnConnection(con);
		}

	}

	@Override
	public void DeleteAllCoustomerCoupons(long cust_id)
			throws CouponSystemException {
		Connection con = null;
		ConnectionPool connPool = null;
		try {
			connPool = ConnectionPool.getInstance();
			con = connPool.getConnection();

			Statement stmt = con.createStatement();
			String sql = "DELETE FROM CustomerCoupon WHERE Cust_ID=" + cust_id;
			stmt.executeUpdate(sql);

		} catch (SQLException e) {
			throw new CouponSystemException("Delete all customer coupons failed", e);
		} finally {
			if (connPool != null && con != null)
				connPool.returnConnection(con);
		}

	}

	@Override
	public void DeleteAllCouponsbyID(long coupon_id)
			throws CouponSystemException {
		Connection con = null;
		ConnectionPool connPool = null;
		try {
			connPool = ConnectionPool.getInstance();
			con = connPool.getConnection();

			Statement stmt = con.createStatement();
			String sql = "DELETE FROM CustomerCoupon WHERE Coupon_ID=" + coupon_id;
			stmt.executeUpdate(sql);

		} catch (SQLException e) {
			throw new CouponSystemException("Delete all coupons by id failed", e);
		} finally {
			if (connPool != null && con != null)
				connPool.returnConnection(con);
		}

	}

}
