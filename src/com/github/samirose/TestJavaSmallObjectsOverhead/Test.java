package com.github.samirose.TestJavaSmallObjectsOverhead;

import java.math.BigInteger;
import java.util.Random;

import com.google.caliper.Param;
import com.google.caliper.Runner;
import com.google.caliper.SimpleBenchmark;

public class Test extends SimpleBenchmark {

	@Param({"100", "1000", "10000"})
	int size;
	private int[] testData;

	@Override
	protected void setUp() {
		Random rand = new Random();
		testData = new int[size];
		for (int i=0; i < size; ++i) {
			testData[i] = rand.nextInt();
		}
	}

	public EndResult timePrimitiveLocals(int reps) {
		long sum = 0, count = 0;
		for (int r = 0; r < reps; ++r) {
			for (int i : testData) {
				++count;
				sum += i;
			}
		}
		return new EndResult(sum, count);
	}

	public EndResult timeMutableObject(int reps) {
		MutableResult result = new MutableResult();
		for (int r = 0; r < reps; ++r) {
			for (int i : testData) {
				result.add(i);
			}
		}
		return result.asEndResult();
	}

	public EndResult timeFinalMutableObject(int reps) {
		FinalMutableResult result = new FinalMutableResult();
		for (int r = 0; r < reps; ++r) {
			for (int i : testData) {
				result.add(i);
			}
		}
		return result.asEndResult();
	}

	public EndResult timeImmutable(int reps) {
		ImmutableResult result = new ImmutableResult();
		for (int r = 0; r < reps; ++r) {
			for (int i : testData) {
				result = result.add(i);
			}
		}
		return result.asEndResult();
	}

	public EndResult timeFinalImmutable(int reps) {
		FinalImmutableResult result = new FinalImmutableResult();
		for (int r = 0; r < reps; ++r) {
			for (int i : testData) {
				result = result.add(i);
			}
		}
		return result.asEndResult();
	}

	public EndResult timeBigInteger(int reps) {
		BigInteger sum = BigInteger.ZERO, count = BigInteger.ZERO;
		for (int r = 0; r < reps; ++r) {
			for (int i : testData) {
				count = count.add(BigInteger.ONE);
				sum = sum.add(BigInteger.valueOf(i));
			}
		}
		return new EndResult(sum.longValue(), count.longValue());
	}

	public static void main(String[] args) throws Exception {
		Runner.main(Test.class, args);
	}
}
