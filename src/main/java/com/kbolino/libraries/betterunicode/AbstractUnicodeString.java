package com.kbolino.libraries.betterunicode;

public abstract class AbstractUnicodeString implements UnicodeString {

	@Override
	public int length() {
		int length;
		CodePointIterator iterator = iterator();
		for (length = 0; iterator.hasNext(); length++) {
			iterator.next();
		}
		return length;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (CodePointIterator iterator = iterator(); iterator.hasNext();) {
			sb.appendCodePoint(iterator.next());
		}
		return sb.toString();
	}
	
	@Override
	public int hashCode() {
		final int PRIME = 92821;
		int hash = PRIME;
		for (CodePointIterator iterator = iterator(); iterator.hasNext();) {
			hash += PRIME * iterator.next();
		}
		return hash;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof UnicodeString) {
			UnicodeString other = (UnicodeString) obj;
			if (length() != other.length()) { return false; }
			CodePointIterator iterator = iterator();
			CodePointIterator otherIterator = other.iterator();
			while (iterator.hasNext() || otherIterator.hasNext()) {
				if (!otherIterator.hasNext() || !iterator.hasNext()) {
					return false;
				}
				if (iterator.next() != otherIterator.next()) {
					return false;
				}
			}
			return true;
		} else {
			return false;
		}
	}

}
