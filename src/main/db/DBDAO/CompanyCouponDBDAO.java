package main.db.DBDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import main.Connection.ConnectionPool;
import main.Exceptions.CouponSystemException;

public class CompanyCouponDBDAO implements CompanyCouponDAO{

	@Override
	public void AddCouponToCompany(long company_id, long coupon_id)
			throws CouponSystemException {
		Connection con = null;
		ConnectionPool connPool = null;
		try {
			CouponDBDAO couponDBDAO = new CouponDBDAO();
			if (!couponDBDAO.IsCouponExists(coupon_id))
				throw new CouponSystemException(
						"Add coupon to company failed - Coupon " + coupon_id
								+ " does not exists");

			CompanyDBDAO companyDBDAO = new CompanyDBDAO();
			if (!companyDBDAO.IsCompanyExists(company_id))
				throw new CouponSystemException(
						"Add coupon to company failed - Company " + company_id
								+ " does not exists");

			connPool = ConnectionPool.getInstance();
			con = connPool.getConnection();

			Statement stmt = con.createStatement();
			String sql = "INSERT INTO CompanyCoupon VALUES(" + company_id + ","
					+ coupon_id + ")";
			stmt.executeUpdate(sql);

		} catch (SQLException e) {
			throw new CouponSystemException("Add coupon " + coupon_id
					+ " to company " + company_id + " failed", e);
		} finally {
			if (connPool != null && con != null)
				connPool.returnConnection(con);
		}

	}

	@Override
	public Collection<Long> GetAllCompanyCoupons(long company_id)
			throws CouponSystemException {
		Connection con = null;
		ConnectionPool connPool = null;
		Collection<Long> Coupons = new ArrayList<Long>();
		try {
			connPool = ConnectionPool.getInstance();
			con = connPool.getConnection();

			Statement stmt = con.createStatement();
			String sql = "Select * FROM CompanyCoupon WHERE Comp_ID="
					+ company_id;
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Coupons.add(rs.getLong("Coupon_ID"));
			}

		} catch (SQLException e) {
			throw new CouponSystemException("Get company coupons failed", e);
		} finally {
			if (connPool != null && con != null)
				connPool.returnConnection(con);
		}
		return Coupons;

	}

	@Override
	public void DeleteCompanyCoupon(long company_id, long coupon_id)
			throws CouponSystemException {
		Connection con = null;
		ConnectionPool connPool = null;
		try {
			connPool = ConnectionPool.getInstance();
			con = connPool.getConnection();

			Statement stmt = con.createStatement();
			String sql = "DELETE FROM CompanyCoupon WHERE Comp_ID=" + company_id
					+ "AND Coupon_ID=" + coupon_id;
			stmt.executeUpdate(sql);

		} catch (SQLException e) {
			throw new CouponSystemException("Delete company coupon failed", e);
		} finally {
			if (connPool != null && con != null)
				connPool.returnConnection(con);
		}

	}
	
	
	@Override
	public void DeleteAllCompanyCoupons(long company_id)
			throws CouponSystemException {
		Connection con = null;
		ConnectionPool connPool = null;
		try {
			connPool = ConnectionPool.getInstance();
			con = connPool.getConnection();

			Statement stmt = con.createStatement();
			String sql = "DELETE FROM CompanyCoupon WHERE Comp_ID=" + company_id;
			stmt.executeUpdate(sql);

		} catch (SQLException e) {
			throw new CouponSystemException("Delete all company coupons failed", e);
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
			String sql = "DELETE FROM CompanyCoupon WHERE Coupon_ID=" + coupon_id;
			stmt.executeUpdate(sql);

		} catch (SQLException e) {
			throw new CouponSystemException("Delete all coupons by coupon id failed", e);
		} finally {
			if (connPool != null && con != null)
				connPool.returnConnection(con);
		}

	}

}
