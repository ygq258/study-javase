package ygq.study.javase.rmi;

import java.io.Serializable;
import java.rmi.activation.Activatable;

/**
 * 
 * 实现类继承UnicastRemoteObject时，lookup出来的对象类型是$Proxy0，而不继承UnicastRemoteObject时类，对象类型是原来的实现类HelloImpl
 * 
 * 我们把继承UnicastRemoteObject类的对象叫做远程对象，我们lookup出来的对象，只是该远程对象的存根(Stub)对象,而远程对象永远在远方。
 * 客户端每一次的方法调用，最后都是仅有的那一个远程对象的方法调用。没有继UnicastRemoteObject类的对象，同样可以bind到Registry，
 * 但lookup出来了对象却是远程对象。如果不继承UnicastRemoteObject对象，可以用UnicastRemoteObject.exportObject(Remote, 0)静态方法
 * 导出stub类（就是Remote的代理类）绑定到Registry对象(Registry.bing(name, Remote))
 * 
 * @author yang
 */
public class HelloImpl implements Hello, Serializable{
	//extends UnicastRemoteObject
	//extends Activatable 激活模式
	private static final long serialVersionUID = -4240410033532071442L;
	
	private int index = 0;
	
	public HelloImpl() {
	}
	
	@Override
	public int helloWorld() {
		//return String.valueOf(System.currentTimeMillis());
		return index++;
	}
}
