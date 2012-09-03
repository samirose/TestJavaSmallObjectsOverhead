package com.github.samirose.TestJavaSmallObjectsOverhead;

import java.util.Arrays;

public class Runner {
	private static final int TEST_DATA_SIZE = 1024*1024;
	private static final int REPEATS = 1000;
	private final int testData[];
	private final long refSum;

	Runner(int testDataSize) {
		testData = new int[testDataSize];
		long sum = 0;
		for (int i = 0; i < testDataSize; ++i) {
			testData[i] = i;
			sum += testData[i];
		}
		this.refSum = sum;
	}

	public static Test createTest(String[] args) {
		Test test = Test.PRIMITIVES;
		if (args.length == 0) {
			System.err.println("Test not specified, defaulting to " + test);
			return test;
		}
		try {
			test = Test.valueOf(args[0]);
			return test;
		}
		catch (IllegalArgumentException e) {
			System.err.println("Unknwon test: " + test + "\nUse one of: " + Arrays.toString(Test.values()));
			return null;
		}
	}

	public void check(EndResult result) {
		if (result.sum != refSum)
			System.err.printf("Unexpected sum: %d, expected %d\n", result.sum, refSum);
		if (result.count!= testData.length)
			System.err.printf("Unexpected count: %d, expected %d\n", result.count, testData.length);
	}

	public void run(Test test) {
		System.out.printf("Running test %s, %s, %d repeats\n", test, test.description, REPEATS);
		for (int r = 0; r < REPEATS; ++r) {
			EndResult result = test.run(testData);
			check(result);
		}
		System.out.println("Test complete.");
	}

	public static void main(String[] args) {
		Test test = createTest(args);
		if (test != null)
			new Runner(TEST_DATA_SIZE).run(test);
	}
}
