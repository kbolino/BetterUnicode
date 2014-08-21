package com.kbolino.libraries.betterunicode;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.NoSuchElementException;

public final class Utf32String extends AbstractUnicodeString implements UnicodeString {
	private static final int BYTE_ORDER_MARK = 0xfeff;
	
	static Utf32String copyOf(UnicodeString string) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private final int[] codePoints;
	
	Utf32String(int[] codePoints) {
		assert codePoints != null;
		this.codePoints = codePoints;
	}
	
	private final class Utf32CodePointIterator implements CodePointIterator {
		private int index = 0;
		
		// @Override
		public boolean hasNext() {
			return index < codePoints.length;
		}

		// @Override
		public int next() throws NoSuchElementException {
			if (index < codePoints.length) {
				return codePoints[index++];
			} else {
				throw new NoSuchElementException();
			}
		}
	}
	
	
	// @Override
	public CodePointIterator iterator() {
		return new Utf32CodePointIterator();
	}
	
	// @Override
	public int length() {
		return codePoints.length;
	}
	
	public EncodedString toEncodedString(ByteOrder byteOrder) {
		UnicodeEncoding encoding = UnicodeEncoding.UTF_32;
		int sizeInBytes = 4 * codePoints.length + (byteOrder == null ? 4 : 0);
		ByteBuffer buffer = ByteBuffer.allocate(sizeInBytes);
		if (byteOrder == null) {
			buffer.putInt(BYTE_ORDER_MARK);
		} else {
			buffer.order(byteOrder);
			if (byteOrder == ByteOrder.LITTLE_ENDIAN) {
				encoding = UnicodeEncoding.UTF_32LE;
			} else {
				encoding = UnicodeEncoding.UTF_32BE;
			}
		}
		for (int codePoint : codePoints) {
			buffer.putInt(codePoint);
		}
		buffer.rewind();
		return new EncodedString(encoding, buffer);
	}
	
	public int codePointAt(int index) throws IndexOutOfBoundsException {
		return codePoints[index];
	}
	
	// TODO other operations

}
