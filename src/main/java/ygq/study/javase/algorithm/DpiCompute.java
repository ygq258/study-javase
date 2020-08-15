package ygq.study.javase.algorithm;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * 计算屏幕的DPI
 * @author yang
 */
public class DpiCompute {

	public static String dpi(double screenLength, double widthScale, double heightScale){
		double x = Math.pow(screenLength, 2) / (Math.pow(widthScale, 2)+Math.pow(heightScale, 2));
		NumberFormat instance = DecimalFormat.getInstance();
		instance.setMaximumFractionDigits(2);
		x = widthScale / (Math.sqrt(x) * widthScale);
		return instance.format(x);
	}
	
	public static void main(String[] args) {
		//3840*2160
		//2560*1440
		//1920*1080
		//1600*900
		//1440*810
		//1366*768 
		//1.27 1.33
		//92.56->117.49->123.41->141.21
		double screenLength = 15.6f;
		long widthResolution = 1920;
		long heightResolution = 1080;
		
		System.out.println(dpi(screenLength, widthResolution, heightResolution));;
	}
}