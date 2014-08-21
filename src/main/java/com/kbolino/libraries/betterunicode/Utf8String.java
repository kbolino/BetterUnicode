package com.kbolino.libraries.betterunicode;

import java.nio.ByteBuffer;
import java.util.NoSuchElementException;

public final class Utf8String extends AbstractUnicodeString implements UnicodeString {
	public static int byteCount(int codePoint) throws IllegalArgumentException {
		// TODO
		return 0;
	}
	
	public static int codePointCount(byte[] bytes, int beginIndex, int endIndex)
			throws NullPointerException, IllegalArgumentException, UnicodeEncodingException {
		// TODO
		return 0;
	}
	
	public static int codePointAt(byte[] bytes, int index)
			throws NullPointerException, IllegalArgumentException, UnicodeEncodingException {
		// TODO
		return 0;
	}
	
	static Utf8String copyOf(UnicodeString string) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private final byte[] bytes;
	private final int length;
	
	Utf8String(byte[] bytes) {
		assert bytes != null;
		this.bytes = bytes;
		try {
			this.length = codePointCount(bytes, 0, bytes.length);
		} catch (UnicodeEncodingException e) {
			throw new AssertionError("Not valid UTF-8", e);
		}
	}
	
	private final class Utf8CodePointIterator implements CodePointIterator {
		private int index = 0;
		
		// @Override
		public boolean hasNext() {
			return index < bytes.length;
		}

		// @Override
		public int next() throws NoSuchElementException {
			if (index < bytes.length) {
				int codePoint = codePointAt(bytes, index);
				index += byteCount(codePoint);
				return codePoint;
			} else {
				throw new NoSuchElementException();
			}
		}
		
	}
	
	// @Override
	public CodePointIterator iterator() {
		return new Utf8CodePointIterator();
	}

	// @Override
	public int length() {
		return length;
	}
	
	public EncodedString toEncodedString() {
		return new EncodedString(UnicodeEncoding.UTF_8, ByteBuffer.wrap(bytes));
	}
}