package com.kbolino.libraries.betterunicode;

import static com.kbolino.libraries.betterunicode.UnicodeEncoding.UTF_8;

import java.util.NoSuchElementException;

public final class Utf8String extends AbstractUnicodeString implements UnicodeString {
	public static int byteCount(int codePoint) {
		if (codePoint < 0x80) {
			return 1;
		} else if (codePoint < 0x800) {
			return 2;
		} else if (codePoint < 0x10000) {
			return 3;
		} else if (codePoint < 0x200000) {
			return 4;
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	public static Utf8String copyOf(UnicodeString string) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private static int checkBytes(byte[] bytes, int offset, int length)
			throws UnicodeEncodingException {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public static Utf8String decodeUnsigned(int... unsignedBytes)
			throws UnicodeEncodingException {
		byte[] bytes = new byte[unsignedBytes.length];
		for (int i = 0; i < unsignedBytes.length; i++) {
			bytes[i] = (byte) unsignedBytes[i];
		}
		int length = checkBytes(bytes, 0, bytes.length);
		return new Utf8String(bytes, length);
	}
	
	public static Utf8String decode(byte[] bytes)
			throws UnicodeEncodingException {
		return decode(bytes, 0, bytes.length);
	}
	
	public static Utf8String decode(byte[] bytes, int offset, int length)
			throws UnicodeEncodingException {
		// TODO
		return null;
	}
	
	private final byte[] bytes;
	private final int length;
	
	private Utf8String(byte[] bytes, int length) {
		super();
		assert bytes != null;
		assert length > 0;
		this.bytes = bytes;
		this.length = length;
	}
	
	private int codePointAt(int offset) {
		// TODO
		return 0;
	}
	
	private final class Iterator implements CodePointIterator {
		private int index = 0;
		
		@Override
		public boolean hasNext() {
			return index < bytes.length;
		}

		@Override
		public int next() throws NoSuchElementException {
			if (index < bytes.length) {
				int codePoint = codePointAt(index);
				index += byteCount(codePoint);
				return codePoint;
			} else {
				throw new NoSuchElementException();
			}
		}
		
	}
	
	@Override
	public CodePointIterator iterator() {
		return new Iterator();
	}

	@Override
	public int length() {
		return length;
	}
	
	public EncodedString encode() {
		return new EncodedString(UTF_8, bytes);
	}
}