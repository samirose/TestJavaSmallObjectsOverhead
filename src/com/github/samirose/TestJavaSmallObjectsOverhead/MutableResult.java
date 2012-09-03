package com.github.samirose.TestJavaSmallObjectsOverhead;

public final class MutableResult {
	private long sum, count;

	public MutableResult() {
		this(0, 0);
	}

	public MutableResult(long sum, long count) {
		this.sum = sum;
		this.count = count;
	}

	public EndResult asEndResult() {
		return new EndResult(sum, count);
	}

	public void add(int i) {
		sum += i;
		++count;
	}

}
