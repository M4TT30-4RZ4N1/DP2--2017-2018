package it.polito.dp2.NFV.sol3.service;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

public class My404Exception extends NotFoundException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8991239908473535651L;
	

	public My404Exception() {
		
		// TODO Auto-generated constructor stub
	}
	
	public My404Exception(Response response) {
		
		super(response);
		// TODO Auto-generated constructor stub
	}

}
