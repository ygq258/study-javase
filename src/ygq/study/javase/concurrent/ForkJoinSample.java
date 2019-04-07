package ygq.study.javase.concurrent;

import java.util.concurrent.RecursiveTask;

public class ForkJoinSample extends RecursiveTask<Integer> {

	private static final long serialVersionUID = 9221932577816432400L;
	private static final int THRESHOLD = 100;
	private int start;
	private int end;

	public ForkJoinSample(int start, int end) {
	        this.start = start;
	        this.end = end;
	    }

	@Override
	protected Integer compute() {
		int sum = 0;
		if ((start - end) < THRESHOLD) {
			for (int i = start; i < end; i++) {
				sum += i;
			}
		} else {
			int middle = (start + end) / 2;
			ForkJoinSample left = new ForkJoinSample(start, middle);
			ForkJoinSample right = new ForkJoinSample(middle + 1, end);
			left.fork();
			right.fork();

			sum = left.join() + right.join();
		}
		return sum;
	}

}
