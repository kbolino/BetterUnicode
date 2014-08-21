package com.kbolino.libraries.betterunicode;

import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;

public enum UnicodeEncoding {
	/**
	 * UTF-8 encoding as specified by
	 * <a href="http://tools.ietf.org/html/rfc3629">RFC 3629</a>.
	 */
	UTF_8(1, 4, null),
	/**
	 * UTF-16 encoding with byte order mark, as specified by
	 * <a href="http://tools.ietf.org/html/rfc2781">RFC 2781</a>.
	 */
	UTF_16(2, 4, null),
	/**
	 * UTF-16 encoding without byte order mark in little endian byte order,
	 * as specified by <a href="http://tools.ietf.org/html/rfc2781">RFC 2781</a>.
	 */
	UTF_16LE(2, 4, ByteOrder.LITTLE_ENDIAN),
	/**
	 * UTF-16 encoding without byte order mark in big endian byte order,
	 * as specified by <a href="http://tools.ietf.org/html/rfc2781">RFC 2781</a>.
	 */
	UTF_16BE(2, 4, ByteOrder.BIG_ENDIAN),
	/**
	 * UTF-32 encoding with byte order mark, as specified by ISO 10646.
	 */
	UTF_32(4, 4, null),
	/**
	 * UTF-32 encoding without byte order mark in little endian byte order,
	 * as specified by ISO 10646.
	 */
	UTF_32LE(4, 4, ByteOrder.LITTLE_ENDIAN),
	/**
	 * UTF-32 encoding without byte order mark in big endian byte order,
	 * as specified by ISO 10646.
	 */
	UTF_32BE(4, 4, ByteOrder.BIG_ENDIAN);
	
	private final int minimumBytesPerCodePoint;
	private final int maximumBytesPerCodePoint;
	private final ByteOrder byteOrder;
	
	private UnicodeEncoding(int minimumBytesPerCodePoint,
			int maximumBytesPerCodePoint, ByteOrder byteOrder) {
		this.minimumBytesPerCodePoint = minimumBytesPerCodePoint;
		this.maximumBytesPerCodePoint = maximumBytesPerCodePoint;
		this.byteOrder = byteOrder;
	}
	public int getMinimumBytesPerCodePoint() {
		return minimumBytesPerCodePoint;
	}
	public int getMaximumBytesPerCodePoint() {
		return maximumBytesPerCodePoint;
	}
	public ByteOrder getByteOrder() {
		return byteOrder;
	}
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
