package com.kbolino.libraries.betterunicode;

import java.nio.ByteBuffer;

public final class EncodedString {
	private final UnicodeEncoding encoding;
	private final byte[] bytes;
	
	public EncodedString(UnicodeEncoding encoding, byte[] bytes) {
		super();
		assert encoding != null;
		assert bytes != null;
		
		this.encoding = encoding;
		this.bytes = bytes;
	}
	
	public UnicodeEncoding getEncoding() {
		return encoding;
	}
	
	public int sizeInBytes() {
		return bytes.length;
	}
	
	public byte[] toByteArray() {
		byte[] copy = new byte[bytes.length];
		System.arraycopy(bytes, 0, copy, 0, bytes.length);
		return copy;
	}
	
	public int copyBytes(byte[] target, int offset) {
		System.arraycopy(bytes, 0, target, offset, bytes.length);
		return bytes.length;
	}
	
	public int copyBytes(ByteBuffer target) {
		target.put(bytes);
		return bytes.length;
	}
}
