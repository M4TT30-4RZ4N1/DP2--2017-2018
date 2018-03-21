package it.polito.dp2.NFV.sol3.service;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;

public class My400Exception extends BadRequestException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3847972740315648535L;
	
	public My400Exception() {
		
		// TODO Auto-generated constructor stub
	}
	
	public My400Exception(Response response) {
		super(response);
		// TODO Auto-generated constructor stub
	}

}
