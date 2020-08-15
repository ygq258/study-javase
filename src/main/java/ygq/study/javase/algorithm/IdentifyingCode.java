package ygq.study.javase.algorithm;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import javax.imageio.ImageIO;

/**
 * 简单验证码生成工具,用于生成验证码
 * @author yang
 */
public class IdentifyingCode {

	private static final char[] chars = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
			'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
			'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U',
			'V', 'W', 'X', 'Y', 'Z' };
	private static final int SIZE = 4; // 字符数
	private static final int LINES = 14; // 干扰线数量
	private static final int WIDTH = 100; // 生成的验证码图片的宽度
	private static final int HEIGHT = 45; // 生成的验证码图片的长度
	private static final int FONT_SIZE = 32; // 字体大小

	public static IdentifyCode getIdentifyCode() {
		// 用户保存字符串
		StringBuffer sb = new StringBuffer();
		// BufferImage类型的验证码
		BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		// 获得画笔
		Graphics g = image.getGraphics();
		g.setColor(Color.LIGHT_GRAY); // 设置背景色
		g.fillRect(0, 0, WIDTH, HEIGHT);// 将背景色填充到图片中
		Random ran = new Random(); // 获得一个Random对象

		// 画字符
		for (int i = 1; i <= SIZE; i++) {
			int r = ran.nextInt(chars.length); // 得到一个随机的下标, chars
												// 是保存着若干字符的char字符
			g.setColor(getRandomColor()); // 得到一个随机的颜色
			g.setFont(new Font(null, Font.BOLD + Font.ITALIC, FONT_SIZE)); // 设置字体大小
			g.drawString(chars[r] + "", (i - 1) * WIDTH / SIZE,
					(int) (FONT_SIZE + (HEIGHT - FONT_SIZE) * ran.nextDouble())); // 画字符
			sb.append(chars[r]);
		}

		// 画干扰线
		for (int i = 1; i <= LINES; i++) {
			g.setColor(getRandomColor()); // 同样，干扰线也是用随机的颜色
			g.drawLine(ran.nextInt(WIDTH), ran.nextInt(HEIGHT), ran.nextInt(WIDTH), ran.nextInt(HEIGHT));// 随机设置干扰线的方向
		}

		IdentifyCode ic = new IdentifyCode();
		ic.setAnswer(sb.toString());
		ic.setImage(image);
		return ic;
	}

	/**
	 * 获得一个随机的颜色 返回 Color对象
	 * 
	 * @return
	 */
	private static Color getRandomColor() {
		Random ran = new Random();
		Color color = new Color(ran.nextInt(256), ran.nextInt(256), ran.nextInt(256));
		return color;
	}

	/**
	 * 将 BufferImage转成InputStream类型
	 * 
	 * @param image
	 * @return
	 * @throws IOException
	 */
	public static InputStream getInputStream(BufferedImage image) throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		// JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(bos);
		// encoder.encode(image);
		byte[] imageBts = bos.toByteArray();
		InputStream in = new ByteArrayInputStream(imageBts);
		return in;
	}

	/**
	 * 验证码生成封装类
	 * 
	 * @author ygq
	 * @CreateDate 2014-11-26 下午4:14:35
	 */
	public static class IdentifyCode {

		private BufferedImage image; // 图片数据

		private String answer; //

		/**
		 * @return the image
		 */
		public BufferedImage getImage() {
			return image;
		}

		/**
		 * @param image
		 *            the image to set
		 */
		public void setImage(BufferedImage image) {
			this.image = image;
		}

		/**
		 * @return the answer
		 */
		public String getAnswer() {
			return answer;
		}

		/**
		 * @param answer
		 *            the answer to set
		 */
		public void setAnswer(String answer) {
			this.answer = answer;
		}
	}

	public static void main(String[] args) throws Exception {

		IdentifyCode identifyCode = IdentifyingCode.getIdentifyCode();

		System.out.println(identifyCode.getAnswer());

		ImageIO.write(identifyCode.getImage(), "JPEG", new File("E:\\1.jpg"));

	}
}
