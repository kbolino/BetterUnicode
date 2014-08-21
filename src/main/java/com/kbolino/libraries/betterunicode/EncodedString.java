package com.kbolino.libraries.betterunicode;

import java.nio.ByteBuffer;

public final class EncodedString {
	private final UnicodeEncoding encoding;
	private final ByteBuffer buffer;
	
	EncodedString(UnicodeEncoding encoding, ByteBuffer buffer) {
		assert encoding != null;
		assert buffer != null;
		assert buffer.hasArray();
		assert buffer.limit() == buffer.capacity();
		this.encoding = encoding;
		this.buffer = buffer;
	}
	
	public UnicodeEncoding encoding() {
		return encoding;
	}
	
	public int sizeInBytes() {
		return buffer.capacity();
	}
	
	public byte[] toByteArray() {
		byte[] copy = new byte[buffer.capacity()];
		System.arraycopy(buffer.array(), 0, copy, 0, buffer.capacity());
		return copy;
	}
	
	public int putBytes(ByteBuffer target) {
		buffer.rewind();
		target.put(buffer);
		return buffer.capacity();
	}
	
	public ByteBuffer toReadOnlyBuffer() {
		buffer.rewind();
		return buffer.asReadOnlyBuffer();
	}
}
