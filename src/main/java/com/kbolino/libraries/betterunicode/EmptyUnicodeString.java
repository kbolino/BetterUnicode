package com.kbolino.libraries.betterunicode;

import java.util.NoSuchElementException;

enum EmptyUnicodeString implements UnicodeString {
	INSTANCE;
	
	private static enum EmptyCodePointIterator implements CodePointIterator {
		INSTANCE;

		// @Override
		public boolean hasNext() {
			return false;
		}

		// @Override
		public int next() throws NoSuchElementException {
			throw new NoSuchElementException();
		}
		
	}
	
	// @Override
	public CodePointIterator iterator() {
		return EmptyCodePointIterator.INSTANCE;
	}

	// @Override
	public int length() {
		return 0;
	}
}
