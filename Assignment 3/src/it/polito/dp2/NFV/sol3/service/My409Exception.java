package it.polito.dp2.NFV.sol3.service;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.Response;

public class My409Exception extends ClientErrorException{



	/**
	 * 
	 */
	private static final long serialVersionUID = 5189695109063034785L;

	public My409Exception() {
        super(Response.Status.CONFLICT); 
    }

	public My409Exception(Throwable cause) {
		super(Response.Status.CONFLICT, cause);
	}

	public My409Exception(Response response) {
		super(response);
	}

	public My409Exception(Response response, Throwable cause) {
		super(response, cause);
	}

	public My409Exception(String message) {
		super(message, Response.Status.CONFLICT);
	}
}
