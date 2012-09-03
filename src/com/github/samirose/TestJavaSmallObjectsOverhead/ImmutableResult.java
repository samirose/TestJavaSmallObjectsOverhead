package com.github.samirose.TestJavaSmallObjectsOverhead;

public class ImmutableResult {
	public final long sum, count;

	public ImmutableResult() {
		this(0,0);
	}

	public ImmutableResult(long sum, long count) {
		this.sum = sum;
		this.count = count;
	}

	public EndResult asEndResult() {
		return new EndResult(sum, count);
	}

	ImmutableResult add(int i) {
		return new ImmutableResult(sum+i, count+1);
	}

}
