package ygq.study.javase.io.bio;

/**
 * 递归算法示例
 * @author yang
 */
public class RecursionSample {

	public int compute(int number) {

		int result = 1;

		for (int i = number; i > 0; i--) {
			result = result * i;
		}
		return result;
	}

	public int compute2(int number) {

		if (number == 1) {
			return number;
		}
		return number * compute(number - 1);
	}

	public static void main(String[] args) {
		
		RecursionSample test = new RecursionSample();

		System.out.println(test.compute2(5));
	}
}
