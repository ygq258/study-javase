package ygq.study.javase.io.bio;

/**
 * 斐波那契 算法实现
 * @author yang
 */
public class Fbnq {

	//斐波那契 使用递归算法
	public int compute(int n){
		
		//递归的出口
		if (n==1 || n ==2) {
			return 1;
		}
		
		return compute(n-1) + compute(n-2);
	}
	
	
	public static void main(String[] args) {
		
		Fbnq fabnq = new Fbnq();
		System.out.println(fabnq.compute(6));
	}
}
