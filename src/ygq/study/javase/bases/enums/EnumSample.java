package ygq.study.javase.bases.enums;

public class EnumSample {
	
	public static void main(String[] args) {

		Color myColor = Color.Blue;

		System.out.println(myColor);
		System.out.println("---------------------");
		for (Color c : Color.values()) {
			System.out.println(c);
		}
		doOp(OpConstant.TURN_LEFT);
	}

	public static void doOp(OpConstant opConstant) {

		switch (opConstant) {
		case TURN_LEFT:
			System.out.println("向左转");
			break;
		case TURN_RIGHT:
			System.out.println("向右转");
			break;
		case SHOOT:
			System.out.println("射击");
			break;
		default:
			System.out.println("不知道");
			break;
		}
	}

	/**
	 * 定义枚举
	 * @author yang
	 */
	public static enum OpConstant {
		TURN_LEFT, TURN_RIGHT, SHOOT
	}
}


