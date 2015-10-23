package com.kbolino.libraries.betterunicode;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.NoSuchElementException;

public final class Utf32String extends AbstractUnicodeString
		implements UnicodeString {
	private static final int BYTE_ORDER_MARK = 0xfeff;
	
	public static Utf32String copyOf(UnicodeString string) {
		int[] codePoints = new int[string.length()];
		CodePointIterator iterator = string.iterator();
		for (int i = 0; iterator.hasNext(); i++) {
			codePoints[i] = iterator.next();
		}
		return new Utf32String(codePoints);
	}
	
	public static Utf32String ofCodePoints(int... codePoints) {
		for (int codePoint : codePoints) {
			if (!Character.isValidCodePoint(codePoint)) {
				throw new IllegalArgumentException();
			}
		}
		int[] copy = new int[codePoints.length];
		System.arraycopy(codePoints, 0, copy, 0, codePoints.length);
		return new Utf32String(copy);
	}
	
	public static Utf32String decode(byte[] bytes) {
		return decode(bytes, 0, bytes.length);
	}
	
	public static Utf32String decode(byte[] bytes, int offset, int length) {
		// TODO
		return null;
	}
	
	private final int[] codePoints;
	
	private Utf32String(int[] codePoints) {
		assert codePoints != null;
		this.codePoints = codePoints;
	}
	
	private final class Iterator implements CodePointIterator {
		private int index = 0;
		
		@Override
		public boolean hasNext() {
			return index < codePoints.length;
		}

		@Override
		public int next() throws NoSuchElementException {
			if (index < codePoints.length) {
				return codePoints[index++];
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
		return codePoints.length;
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
		UnicodeEncoding encoding = UnicodeEncoding.UTF_32;
		int sizeInBytes = 4 * codePoints.length + (includeBom ? 4 : 0);
		byte[] bytes = new byte[sizeInBytes];
		ByteBuffer buffer = ByteBuffer.wrap(bytes);
		buffer.order(byteOrder);
		if (includeBom) {
			buffer.putInt(BYTE_ORDER_MARK);
		} else {
			if (byteOrder == ByteOrder.LITTLE_ENDIAN) {
				encoding = UnicodeEncoding.UTF_32LE;
			} else {
				encoding = UnicodeEncoding.UTF_32BE;
			}
		}
		for (int codePoint : codePoints) {
			buffer.putInt(codePoint);
		}
		return new EncodedString(encoding, bytes);
	}
	
	public int codePointAt(int index) throws IndexOutOfBoundsException {
		return codePoints[index];
	}
	
	// TODO other operations

}
