package com.kbolino.libraries.betterunicode;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.NoSuchElementException;

public final class Utf16String extends AbstractUnicodeString
		implements UnicodeString {
	private static final char BYTE_ORDER_MARK = 0xfeff;
	
	public static Utf16String copyOf(UnicodeString string) {
		StringBuilder sb = new StringBuilder(2 * string.length());
		CodePointIterator iterator = string.iterator();
		while (iterator.hasNext()) {
			sb.appendCodePoint(iterator.next());
		}
		return wrap(sb.toString());
	}
	
	public static Utf16String wrap(String string) {
		int length = string.codePointCount(0, string.length());
		return new Utf16String(string, length);
	}
	
	public static Utf16String decode(byte[] bytes) {
		return decode(bytes, 0, bytes.length);
	}
	
	public static Utf16String decode(byte[] bytes, int offset, int length) {
		// TODO
		return null;
	}
	
	private final String wrapped;
	private final int length;
	
	private Utf16String(String wrapped, int length) {
		super();
		assert wrapped != null;
		assert length > 0;
		this.wrapped = wrapped;
		this.length = length;
	}
	
	private final class Iterator implements CodePointIterator {
		private int index = 0;
		@Override
		public boolean hasNext() {
			return index < wrapped.length();
		}

		@Override
		public int next() throws NoSuchElementException {
			if (index < wrapped.length()) {
				int codePoint = wrapped.codePointAt(index);
				index += Character.charCount(codePoint);
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
	
	@Override
	public String toString() {
		return wrapped;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Utf16String) {
			return wrapped.equals(((Utf16String) obj).wrapped);
		} else {
			return super.equals(obj);
		}
	}
	
	public EncodedString encodeWithBom() {
		return encode(true, ByteOrder.BIG_ENDIAN);
	}
	
	public EncodedString encodeLittleEndian() {
		return encode(false, ByteOrder.LITTLE_ENDIAN);
	}
	
	public EncodedString encodeBigEndian() {
		return encode(false, ByteOrder.BIG_ENDIAN);
	}
	
	private EncodedString encode(boolean includeBom, ByteOrder byteOrder) {
		assert byteOrder != null;
		UnicodeEncoding encoding = UnicodeEncoding.UTF_16;
		int sizeInBytes = 2 * wrapped.length() + (byteOrder == null ? 2 : 0);
		byte[] bytes = new byte[sizeInBytes];
		ByteBuffer buffer = ByteBuffer.wrap(bytes);
		buffer.order(byteOrder);
		if (includeBom) {
			buffer.putChar(BYTE_ORDER_MARK);
		} else {
			if (byteOrder == ByteOrder.LITTLE_ENDIAN) {
				encoding = UnicodeEncoding.UTF_16LE;
			} else {
				encoding = UnicodeEncoding.UTF_16BE;
			}
		}
		for (int i = 0; i < wrapped.length(); i++) {
			buffer.putChar(wrapped.charAt(i));
		}
		return new EncodedString(encoding, bytes);
	}
	
}
