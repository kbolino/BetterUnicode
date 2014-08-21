package com.kbolino.libraries.betterunicode;

import java.util.NoSuchElementException;

/**
 * An iterator for Unicode code points.
 * {@link CodePointIterator}s have mutable state but do not modify the
 * underlying source of code points.
 * The behavior of a {@link CodePointIterator} if the underlying source
 * of code points is modified is not defined by this interface but should
 * be specified by the implementation.
 * @see java.util.Iterator
 */
public interface CodePointIterator {
	/**
	 * Is there another code point?
	 * @return  True if and only if a single subsequent call to
	 *   {@link #next()} will succeed.
	 */
	boolean hasNext();
	/**
	 * Returns the next code point.
	 * @return  The next available Unicode code point, if any.
	 * @throws NoSuchElementException  If there are no more code points
	 *   available.
	 */
	int next() throws NoSuchElementException;
}
