package ygq.study.javase.bases.enums;
/**
 * Enum 枚举的常见应用场景
 * @author yang
 */
public enum EnumSampleCoin {
	
	penny("hello"), nickel("world"), dime("wecome"),quarter("hello world");
	
	private String value;
	
	
	public String show(){
		return nickel.value;
	}

	public String getValue() {
		return value;
	}

	private EnumSampleCoin(String value) {
		this.value = value;
	}
	
	public static void main(String[] args) {
		
		EnumSampleCoin coin = EnumSampleCoin.quarter;
		
		System.out.println(coin.ordinal());
		System.out.println(coin.name());
		System.out.println(EnumSampleCoin.valueOf("quarter"));
		System.out.println(coin.name());
		System.out.println(coin.getValue());
	}
}