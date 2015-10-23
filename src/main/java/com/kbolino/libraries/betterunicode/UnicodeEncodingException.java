package com.kbolino.libraries.betterunicode;

public class UnicodeEncodingException extends RuntimeException {
	private static final long serialVersionUID = -974088913468292038L;

	public UnicodeEncodingException() {
		super();
	}

	public UnicodeEncodingException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnicodeEncodingException(String message) {
		super(message);
	}

	public UnicodeEncodingException(Throwable cause) {
		super(cause);
	}
	
}
