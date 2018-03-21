package it.polito.dp2.NFV.sol3.service;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.Response;

public class My422Exception extends ClientErrorException {



	/**
	 * 
	 */
	private static final long serialVersionUID = -1303376244173775851L;

	public My422Exception() {
		super(422);
		// TODO Auto-generated constructor stub
	}

	public My422Exception(Response response) {
		super(response);
		// TODO Auto-generated constructor stub
	}

	public My422Exception(String message, Response response) {
		super(message, response);
		// TODO Auto-generated constructor stub
	}

	public My422Exception(Throwable cause) {
		super(422, cause);
		// TODO Auto-generated constructor stub
	}

	public My422Exception(String message) {
		super(message, 422);
	}


}
