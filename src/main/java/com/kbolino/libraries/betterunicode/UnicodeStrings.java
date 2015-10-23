package com.kbolino.libraries.betterunicode;

public final class UnicodeStrings {
	
	public static UnicodeString emptyString() {
		return EmptyUnicodeString.INSTANCE;
	}
	
	public static UnicodeString ofCodePoints(int... codePoints)
			throws NullPointerException, IllegalArgumentException {
		if (codePoints.length == 0) {
			return EmptyUnicodeString.INSTANCE;
		} else {
			return Utf32String.ofCodePoints(codePoints);
		}
	}
	
	public static UnicodeString ofChars(char... chars)
			throws NullPointerException {
		if (chars.length == 0) {
			return EmptyUnicodeString.INSTANCE;
		} else {
			return Utf16String.wrap(new String(chars));
		}
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
