package com.github.samirose.TestJavaSmallObjectsOverhead;

public final class FinalMutableResult {
	private long sum, count;

	public FinalMutableResult() {
		this(0, 0);
	}

	public FinalMutableResult(long sum, long count) {
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
