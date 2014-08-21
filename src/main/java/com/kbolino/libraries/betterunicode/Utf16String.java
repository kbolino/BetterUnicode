package com.kbolino.libraries.betterunicode;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.NoSuchElementException;

public final class Utf16String extends AbstractUnicodeString implements UnicodeString {
	private static final char BYTE_ORDER_MARK = 0xfeff;
	
	static Utf16String copyOf(UnicodeString string) {
		// TODO
		return null;
	}
	
	private final String wrapped;
	private final int length;
	
	Utf16String(String wrapped) {
		assert wrapped != null;
		this.wrapped = wrapped;
		this.length = wrapped.codePointCount(0, wrapped.length());
	}
	
	private final class Utf16CodePointIterator implements CodePointIterator {
		private int index = 0;
		// @Override
		public boolean hasNext() {
			return index < wrapped.length();
		}

		// @Override
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

	// @Override
	public CodePointIterator iterator() {
		return new Utf16CodePointIterator();
	}

	// @Override
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
	
	public EncodedString toEncodedString(ByteOrder byteOrder) {
		UnicodeEncoding encoding = UnicodeEncoding.UTF_16;
		int sizeInBytes = 2 * wrapped.length() + (byteOrder == null ? 2 : 0);
		ByteBuffer buffer = ByteBuffer.allocate(sizeInBytes);
		if (byteOrder == null) {
			buffer.putChar(BYTE_ORDER_MARK);
		} else {
			buffer.order(byteOrder);
			if (byteOrder == ByteOrder.LITTLE_ENDIAN) {
				encoding = UnicodeEncoding.UTF_16LE;
			} else {
				encoding = UnicodeEncoding.UTF_16BE;
			}
		}
		for (int i = 0; i < wrapped.length(); i++) {
			buffer.putChar(wrapped.charAt(i));
		}
		buffer.rewind();
		return new EncodedString(encoding, buffer);
	}
	
}
