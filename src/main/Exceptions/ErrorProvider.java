package main.Exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import main.beanes.ErrorMessage;


@Provider
public class ErrorProvider implements ExceptionMapper<Exception>{

	@Override
	public Response toResponse(Exception e) {
		
		return 
			// create HTTP response with error code 500
			Response.serverError().
			
			// add JSON of ErrorMessage to response
			entity(new ErrorMessage(e.getMessage())).
			
			// create HTTP response
			build();}

}
