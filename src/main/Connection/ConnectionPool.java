package main.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import main.Exceptions.CouponSystemException;

public class ConnectionPool {

	private static final int MAX_CONNECTION = 10;
	private static final String URL= "jdbc:derby://localhost:1527/CoponPrjectDB;";
	private Map<Connection, Boolean> connMap = new HashMap<>();	
	private static ConnectionPool instance = null;
		
	public static ConnectionPool getInstance() throws SQLException {
		if (instance == null) {
			instance = new ConnectionPool();
		}
		return instance;

	}
	
	private ConnectionPool() {
		super();
		
		try {
			Class.forName("org.apache.derby.jdbc.ClientDriver40");
			for (int i = 0; i < MAX_CONNECTION; i++) {				
				connMap.put(DriverManager.getConnection(URL), true);
			}
		} catch (SQLException e) {
			throw new CouponSystemException("Create Connection failed" , e);
		} catch (ClassNotFoundException e) {			
			throw new CouponSystemException("Create Connection failed , ClassNotFoundException " , e);
		} 
		
	}

	/**
	 * Check if there is available connection, if not wait
	 * if yes return the first available connection
	 * @return first available connection
	 * @throws Exception
	 */	
	public synchronized Connection getConnection() {
		
		while (!connMap.values().contains(true)) {
			try {
				wait();
			} catch (Exception e) {
				throw new CouponSystemException("Get Connection Wait error", e);
			}
			
		}
		
		Connection retConnection = null;
		for (Connection currConnection : connMap.keySet()) {
			if (connMap.get(currConnection)) {
				connMap.put(currConnection, false);
				retConnection =  currConnection;
				break;
			}
		}	
		
		return retConnection;
	}

	/**
	 * 
	 * @param con
	 */
	public synchronized void returnConnection(Connection con) {			
		connMap.put(con, true);
		notifyAll();
		
	}

	public void closeAllConnection() {
		try {
			for (Connection currConnection : connMap.keySet()) {
				if (currConnection != null)
					currConnection.close();
			}
		} catch (SQLException e) {
			throw new CouponSystemException("Closr all connection failed", e);
		}
		
	}

}
