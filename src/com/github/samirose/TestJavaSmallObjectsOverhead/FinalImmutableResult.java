package com.github.samirose.TestJavaSmallObjectsOverhead;

public final class FinalImmutableResult {
	public final long sum, count;

	public FinalImmutableResult() {
		this(0, 0);
	}

	public FinalImmutableResult(long sum, long count) {
		this.sum = sum;
		this.count = count;
	}

	public EndResult asEndResult() {
		return new EndResult(sum, count);
	}

	FinalImmutableResult add(int i) {
		return new FinalImmutableResult(sum+i, count+1);
	}

}
