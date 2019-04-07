package ygq.study.javase.bases.util;

import java.util.Base64;

import javax.xml.bind.DatatypeConverter;

/***
 * base64 JDK提供的工具类
 * @author yang
 */
public class Base64Sample {
	
	public static String sample1(byte[] data){
		String name = "拉萨快递费拉萨快递费拉萨快递费拉萨快递费啦缩短开发阿隆索看到发拉开松岛枫阿里看到算法啊了缩短开发啊了缩短开发阿里第三方库阿里第三方松岛枫理论卡的法律三分老的思考法律的看法了速度飞快松岛枫了缩短开发";
		return DatatypeConverter.printBase64Binary(data==null?name.getBytes():data);//
	}
	public static String sample2(byte[] data){
		String name = "拉萨快递费拉萨快递费拉萨快递费拉萨快递费啦缩短开发阿隆索看到发拉开松岛枫阿里看到算法啊了缩短开发啊了缩短开发阿里第三方库阿里第三方松岛枫理论卡的法律三分老的思考法律的看法了速度飞快松岛枫了缩短开发";
		return Base64.getEncoder().encodeToString(data==null?name.getBytes():data);
	}
	public static void main(String[] args) {
		
		String name = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCZZThuvs/WkwXLmJ5ww4FOA7PF13d6hC1/2BiW\r\n" + 
				"UafIRglpLS2y5uF5hvFhPCQeoTnvNr1MMq4xclCADdauat2fuZHq5ZL27PUGtefEmAGkhUGZHffe\r\n" + 
				"3NKFosMtrjTWPUNroDc3v6yAAwa7x2TW7Xv5fb6PjvTJmGh6QWBx3Pot5QIDAQAB";
		System.out.println(Base64.getEncoder().encodeToString("微软深刻的法律".getBytes()));
	}
}
