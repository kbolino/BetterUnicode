package com.kbolino.libraries.betterunicode;

import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;

public enum UnicodeEncoding {
	/**
	 * UTF-8 encoding as specified by
	 * <a href="http://tools.ietf.org/html/rfc3629">RFC 3629</a>.
	 */
	UTF_8,
	/**
	 * UTF-16 encoding with byte order mark, as specified by
	 * <a href="http://tools.ietf.org/html/rfc2781">RFC 2781</a>.
	 */
	UTF_16,
	/**
	 * UTF-16 encoding without byte order mark in little endian byte order,
	 * as specified by <a href="http://tools.ietf.org/html/rfc2781">RFC 2781</a>.
	 */
	UTF_16LE,
	/**
	 * UTF-16 encoding without byte order mark in big endian byte order,
	 * as specified by <a href="http://tools.ietf.org/html/rfc2781">RFC 2781</a>.
	 */
	UTF_16BE,
	/**
	 * UTF-32 encoding with byte order mark, as specified by ISO 10646.
	 */
	UTF_32,
	/**
	 * UTF-32 encoding without byte order mark in little endian byte order,
	 * as specified by ISO 10646.
	 */
	UTF_32LE,
	/**
	 * UTF-32 encoding without byte order mark in big endian byte order,
	 * as specified by ISO 10646.
	 */
	UTF_32BE;
	
	public int bytesForCodePoint(int codePoint) {
		switch (this) {
		case UTF_8:
			return Utf8String.byteCount(codePoint);
		case UTF_16:
		case UTF_16LE:
		case UTF_16BE:
			return 2 * Character.charCount(codePoint);
		case UTF_32:
		case UTF_32LE:
		case UTF_32BE:
			return 4;
		default:
			throw new Error();
		}
	}
	public Charset toCharset() throws UnsupportedCharsetException {
		return Charset.forName(toString().replace('_', '-'));
	}
}
