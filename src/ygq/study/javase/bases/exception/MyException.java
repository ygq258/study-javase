package ygq.study.javase.bases.exception;

/**
 * 自定义异常
 * @author yang
 */
public class MyException extends Exception{

	private static final long serialVersionUID = 6017909156740914636L;

	public MyException() {
		
	}
	
	MyException(String message){
		super(message);
	}
}
