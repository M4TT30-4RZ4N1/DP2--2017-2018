package it.polito.dp2.NFV.sol3.service;

public class MyAlreadyLoadedException extends Exception {


	/**
	 * 
	 */
	private static final long serialVersionUID = -6699927397507980258L;

	public MyAlreadyLoadedException() {
	}

	public MyAlreadyLoadedException(String message) {
		super(message);
	}

	public MyAlreadyLoadedException(String message, Throwable cause) {
		super(message, cause);
	}

	public MyAlreadyLoadedException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public MyAlreadyLoadedException(Throwable cause) {
		super(cause);
	}

}
