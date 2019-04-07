package ygq.study.javase.bases.base;

public class NumberSample {
	
	public static void sampleBinary(){
		int value = 0x6C49;
		String binaryString = Integer.toBinaryString(value & 0x0FF);
		System.out.println(binaryString);
	}
	
	public static void main(String[] args) {
		sampleBinary();
	}
}
