package com.kbolino.libraries.betterunicode;

public class UnicodeEncodingException extends RuntimeException {
	private static final long serialVersionUID = -974088913468292038L;

	public UnicodeEncodingException() {
		super();
	}

	public UnicodeEncodingException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public UnicodeEncodingException(String arg0) {
		super(arg0);
	}

	public UnicodeEncodingException(Throwable arg0) {
		super(arg0);
	}
	
}
