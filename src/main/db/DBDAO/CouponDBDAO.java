package main.db.DBDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import main.Connection.ConnectionPool;
import main.Exceptions.CouponSystemException;
import main.beanes.Coupon;
import main.enums.CouponType;

public class CouponDBDAO implements CouponDAO {

	@Override
	public void CreateCoupon(Coupon coup) throws CouponSystemException {
		Connection con = null;
		ConnectionPool connPool = null;
		try {
			connPool = ConnectionPool.getInstance();
			con = connPool.getConnection();

			String sql = "INSERT INTO Coupon(Title, Start_Date, End_Date, "
					+ "Amount, Type, Massage, Price, Image) VALUES(?,?,?,?,?,?,?,?)";

			PreparedStatement preStatement = con.prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS);
			preStatement.setString(1, coup.getTitle());
			preStatement.setDate(2, coup.getStartDate());
			preStatement.setDate(3, coup.getEndDate());
			preStatement.setInt(4, coup.getAmount());
			preStatement.setString(5, coup.getCouponType().toString());
			preStatement.setString(6, coup.getMsg());
			preStatement.setDouble(7, coup.getPrice());
			preStatement.setString(8, coup.getImage());

			// stmt.executeUpdate(sql);
			preStatement.execute();
			ResultSet rs = preStatement.getGeneratedKeys();
			if (rs.next()) {
				coup.setID(rs.getLong(1));
			}

		} catch (SQLException e) {
			throw new CouponSystemException("Create coupon failed", e);
		} finally {
			if (connPool != null && con != null)
				connPool.returnConnection(con);
		}

	}

	@Override
	public void RemoveCoupon(long coupID) throws CouponSystemException {

		Connection con = null;
		ConnectionPool connPool = null;
		try {
			connPool = ConnectionPool.getInstance();
			con = connPool.getConnection();

			Statement stmt = con.createStatement();
			String sql = "DELETE FROM Coupon WHERE ID=" + coupID;
			stmt.executeUpdate(sql);

		} catch (SQLException e) {
			throw new CouponSystemException("Remove coupon failed", e);
		} finally {
			if (connPool != null && con != null)
				connPool.returnConnection(con);
		}

	}

	public void UpdateCoupon(long couponID, Date endDate, double price)
			throws CouponSystemException {

		Connection con = null;
		ConnectionPool connPool = null;
		try {
			connPool = ConnectionPool.getInstance();
			con = connPool.getConnection();

			String sql = "UPDATE Coupon SET End_Date=?,Price=? WHERE ID=?";

			PreparedStatement preStatment = con.prepareStatement(sql);
			preStatment.setDate(1, endDate);
			preStatment.setDouble(2, price);
			preStatment.setLong(3, couponID);

			if (preStatment.executeUpdate() == 0)
				throw new CouponSystemException(
						"Coupon was not Update, please check coupon details again");

		} catch (SQLException e) {
			throw new CouponSystemException("Update coupon failed", e);

		} finally {
			if (connPool != null && con != null)
				connPool.returnConnection(con);
		}

	}

	@Override
	public void UpdateCoupon(Coupon coup) throws CouponSystemException {

		Connection con = null;
		ConnectionPool connPool = null;
		try {
			connPool = ConnectionPool.getInstance();
			con = connPool.getConnection();
			String sql = "UPDATE Coupon SET Title=?,Start_Date=?,End_Date=?,Amount=?,Type=?,Massage=?,Price=?,Image=? WHERE ID=?";

			PreparedStatement preStatement = con.prepareStatement(sql);
			preStatement.setString(1, coup.getTitle());
			preStatement.setDate(2, coup.getStartDate());
			preStatement.setDate(3, coup.getEndDate());
			preStatement.setInt(4, coup.getAmount());
			preStatement.setString(5, coup.getCouponType().toString());
			preStatement.setString(6, coup.getMsg());
			preStatement.setDouble(7, coup.getPrice());
			preStatement.setString(8, coup.getImage());
			preStatement.setLong(9, coup.getId());

		} catch (SQLException e) {
			throw new CouponSystemException("Update coupon failed", e);

		} finally {
			if (connPool != null && con != null)
				connPool.returnConnection(con);
		}

	}

	@Override
	public Coupon GetCoupon(long id) throws CouponSystemException {
		Connection con = null;
		ConnectionPool connPool = null;
		Coupon retCoupon = null;
		try {
			connPool = ConnectionPool.getInstance();
			con = connPool.getConnection();

			Statement stmt = con.createStatement();
			String sql = "Select * FROM Coupon WHERE ID=" + id;
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				retCoupon = new Coupon(rs.getString("Title"),
						rs.getDate("Start_Date"), rs.getDate("End_Date"),
						rs.getInt("Amount"), rs.getString("Massage"),
						rs.getDouble("Price"), rs.getString("Image"),
						rs.getString("Type"));
				retCoupon.setID(rs.getLong("ID"));
			}

		} catch (SQLException e) {
			throw new CouponSystemException("Get coupon(id= " + id + ")failed",
					e);
		} finally {
			if (connPool != null && con != null)
				connPool.returnConnection(con);
		}
		return retCoupon;

	}
	
	public Coupon GetCoupon(String couponTitle) throws CouponSystemException {
		Connection con = null;
		ConnectionPool connPool = null;
		Coupon retCoupon = null;
		try {
			connPool = ConnectionPool.getInstance();
			con = connPool.getConnection();
			
			String sql = "Select * FROM Coupon WHERE Title=?";
			PreparedStatement preStatment = con.prepareStatement(sql);
			preStatment.setString(1, couponTitle);
			ResultSet rs = preStatment.executeQuery();
			if (rs.next()) {
				retCoupon = new Coupon(rs.getString("Title"),
						rs.getDate("Start_Date"), rs.getDate("End_Date"),
						rs.getInt("Amount"), rs.getString("Massage"),
						rs.getDouble("Price"), rs.getString("Image"),
						rs.getString("Type"));
				retCoupon.setID(rs.getLong("ID"));
			}

		} catch (SQLException e) {
			throw new CouponSystemException("Get coupon(Title= " + couponTitle + ")failed",
					e);
		} finally {
			if (connPool != null && con != null)
				connPool.returnConnection(con);
		}
		return retCoupon;

	}

	@Override
	public Collection<Coupon> GetAllCoupons() throws CouponSystemException {
		Connection con = null;
		ConnectionPool connPool = null;
		Collection<Coupon> allCoupons = new ArrayList<Coupon>();
		try {
			connPool = ConnectionPool.getInstance();
			con = connPool.getConnection();

			Statement stmt = con.createStatement();
			String sql = "Select * FROM Coupon";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Coupon c = new Coupon(rs.getString("Title"),
						rs.getDate("Start_Date"), rs.getDate("End_Date"),
						rs.getInt("Amount"), rs.getString("Massage"),
						rs.getDouble("Price"), rs.getString("Image"),
						rs.getString("Type"));
				c.setID(rs.getLong("ID"));
				allCoupons.add(c);
			}

		} catch (SQLException e) {
			throw new CouponSystemException("Get all coupons failed", e);
		} finally {
			if (connPool != null && con != null)
				connPool.returnConnection(con);
		}
		return allCoupons;

	}

	@Override
	public Collection<Coupon> GetCouponsByType(CouponType couponType)
			throws CouponSystemException {
		Connection con = null;
		ConnectionPool connPool = null;
		Collection<Coupon> Coupons = new ArrayList<Coupon>();
		try {
			connPool = ConnectionPool.getInstance();
			con = connPool.getConnection();

			Statement stmt = con.createStatement();
			String sql = "Select * FROM Coupon WHERE TYPE='"
					+ couponType.toString() + "'";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Coupon c = new Coupon(rs.getString("Title"),
						rs.getDate("Start_Date"), rs.getDate("End_Date"),
						rs.getInt("Amount"), rs.getString("Massage"),
						rs.getDouble("Price"), rs.getString("Image"),
						rs.getString("Type"));
				c.setID(rs.getLong("ID"));
				Coupons.add(c);
			}

		} catch (SQLException e) {
			throw new CouponSystemException("Get all coupons by type failed", e);
		} finally {
			if (connPool != null && con != null)
				connPool.returnConnection(con);
		}
		return Coupons;

	}

	/**
	 * Check if coupon exists in DB
	 * 
	 * @param id
	 *            - coupon id the check
	 * @return true if coupon is exist else false
	 * @throws CouponSystemException
	 */
	public boolean IsCouponExists(long id) throws CouponSystemException {
		Connection con = null;
		ConnectionPool connPool = null;
		boolean isCouponExists = false;
		try {
			connPool = ConnectionPool.getInstance();
			con = connPool.getConnection();

			Statement stmt = con.createStatement();
			String sql = "Select * FROM Coupon WHERE ID=" + id;
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				isCouponExists = true;
			}

		} catch (SQLException e) {
			throw new CouponSystemException("Is Coupon Exists check failed", e);
		} finally {
			if (connPool != null && con != null)
				connPool.returnConnection(con);
		}
		return isCouponExists;
	}
	
	/**
	 * Check if coupon exists in DB
	 * 
	 * @param couponTitle Check if exits by coupon title(title is uniqe)
	 *            
	 * @return true if coupon is exist else false
	 * @throws CouponSystemException
	 */
	public boolean IsCouponExists(String couponTitle) throws CouponSystemException {
		Connection con = null;
		ConnectionPool connPool = null;
		boolean isCouponExists = false;
		try {
			connPool = ConnectionPool.getInstance();
			con = connPool.getConnection();
			
			String sql = "Select * FROM Coupon WHERE Title=?";
			PreparedStatement preStatement = con.prepareStatement(sql);
			preStatement.setString(1, couponTitle);
			
			ResultSet rs = preStatement.executeQuery();
			if (rs.next()) {
				isCouponExists = true;
			}

		} catch (SQLException e) {
			throw new CouponSystemException("Is Coupon Exists check failed", e);
		} finally {
			if (connPool != null && con != null)
				connPool.returnConnection(con);
		}
		return isCouponExists;

	}

}
