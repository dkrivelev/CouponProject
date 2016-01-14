package main.Exceptions;

public class CouponSystemException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public CouponSystemException(){
		
	}
	
	public CouponSystemException(String message , Exception e) {
		super(message + ": " + e.getMessage());
		//System.out.println(message + ": " + e.getMessage());
		
	}
	
	public CouponSystemException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public CouponSystemException(Throwable cause) {
		super(cause);
		
	}

	public CouponSystemException(String message) {
		super(message);
		//System.out.println(message);
	}
	
	

}
