package it.polito.dp2.NFV.sol3.service;

import javax.ws.rs.core.Response;

public class My500Exception  extends ServiceException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1287576777324670177L;
	
	public My500Exception() {
		// TODO Auto-generated constructor stub
	}

	public My500Exception(Response response) {
		// TODO Auto-generated constructor stub
	}

	public My500Exception(String message, Response response) {
		
		// TODO Auto-generated constructor stub
	}

	public My500Exception(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public My500Exception(String message) {
		super(message);
	}


}
