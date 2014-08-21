package com.kbolino.libraries.betterunicode;

public final class UnicodeStrings {
	private static final UnicodeString EMPTY = EmptyUnicodeString.INSTANCE;
	
	public static UnicodeString empty() {
		return EMPTY;
	}
	
	public static UnicodeString of(int... codePoints) throws NullPointerException, IllegalArgumentException {
		if (codePoints.length == 0) {
			return EMPTY;
		}
		for (int codePoint : codePoints) {
			if (!Character.isValidCodePoint(codePoint)) {
				throw new IllegalArgumentException();
			}
		}
		int[] copy = new int[codePoints.length];
		System.arraycopy(codePoints, 0, copy, 0, codePoints.length);
		return new Utf32String(codePoints);
	}
	
	public static UnicodeString of(String string) throws NullPointerException {
		if (string.length() == 0) {
			return EMPTY;
		}
		return new Utf16String(string);
	}
	
	public static UnicodeString of(char... chars) throws NullPointerException {
		if (chars.length == 0) {
			return EMPTY;
		}
		return of(new String(chars));
	}
	
	public static Utf8String toUtf8(UnicodeString string) {
		if (string instanceof Utf8String) {
			return (Utf8String) string;
		} else {
			return Utf8String.copyOf(string);
		}
	}
	
	public static Utf16String toUtf16(UnicodeString string) {
		if (string instanceof Utf16String) {
			return (Utf16String) string;
		} else {
			return Utf16String.copyOf(string);
		}
	}
	
	public static Utf32String toUtf32(UnicodeString string) {
		if (string instanceof Utf32String) {
			return (Utf32String) string;
		} else {
			return Utf32String.copyOf(string);
		}
	}

	private UnicodeStrings() { }
}
