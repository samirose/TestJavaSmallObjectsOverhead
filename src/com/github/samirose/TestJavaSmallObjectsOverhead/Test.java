package com.github.samirose.TestJavaSmallObjectsOverhead;

import java.math.BigInteger;

public enum Test {

	PRIMITIVES("Uses primitive local variables for intermediate results") {
		public EndResult run(int[] testData) {
			long sum = 0, count = 0;
			for (int i : testData) {
				++count;
				sum += i;
			}
			return new EndResult(sum, count);
		}
	},

	MUTABLE("Uses a single, mutable object for intermediate results") {
		public EndResult run(int[] testData) {
			MutableResult result = new MutableResult();
			for (int i : testData) {
				result.add(i);
			}
			return result.asEndResult();
		}
	},

	FINAL_MUTABLE("Uses a single mutable object of final class for intermediate results") {
		public EndResult run(int[] testData) {
			FinalMutableResult result = new FinalMutableResult();
			for (int i : testData) {
				result.add(i);
			}
			return result.asEndResult();
		}
	},

	IMMUTABLE("Uses reference to an immutable object for intermediate results") {
		public EndResult run(int[] testData) {
			ImmutableResult result = new ImmutableResult();
			for (int i : testData) {
				result = result.add(i);
			}
			return result.asEndResult();
		}
	},

	FINAL_IMMUTABLE("Uses reference to an immutable object of final class for intermediate results") {
		public EndResult run(int[] testData) {
			FinalImmutableResult result = new FinalImmutableResult();
			for (int i : testData) {
				result = result.add(i);
			}
			return result.asEndResult();
		}
	},

	BIGINTEGER("Uses local BigInteger variables for intermediate results") {
		public EndResult run(int[] testData) {
			BigInteger sum = BigInteger.ZERO, count = BigInteger.ZERO;
			for (int i : testData) {
				count = count.add(BigInteger.ONE);
				sum = sum.add(BigInteger.valueOf(i));
			}
			return new EndResult(sum.longValue(), count.longValue());
		}
	};

	private Test(String description) {
		this.description = description;
	}

	public final String description;
	public abstract EndResult run(int[] testData);
}
