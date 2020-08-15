package ygq.study.javase.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Hello extends Remote{
	/**
	 * 该方法必须声明抛出 RemoteException 因为网络出现类似机器故障或者网络阻塞等问题时会产生RemoteException
	 * @throws RemoteException
	 */
	public int helloWorld() throws RemoteException;
}
