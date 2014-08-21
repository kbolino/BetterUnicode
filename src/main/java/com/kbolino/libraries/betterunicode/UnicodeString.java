package com.kbolino.libraries.betterunicode;

/**
 * An immutable string of Unicode code points.
 */
public interface UnicodeString {
	/**
	 * Creates an iterator over the code points in this string.
	 * The operation of this iterator must not interfere with other
	 * iterators.
	 * @return  An {@link CodePointIterator} over the content of this
	 *   string.
	 */
	CodePointIterator iterator();
	/**
	 * The length of this string.
	 * @return  The number of times {@link CodePointIterator#next() next()}
	 *   may be safely invoked on a fresh {@link #iterator() iterator} of
	 *   this string.
	 */
	int length();
}
