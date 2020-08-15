package ygq.study.javase.test.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import ygq.study.javase.test.TestExecutor;
import ygq.study.javase.test.TestTask;

public class RMITest extends TestTask {

	private World world = null;
	
	@Override
	public void init() {
		try {
			//SslRMIClientSocketFactory scf = new CustomSslRMIClientSocketFactory();
			Registry registry = LocateRegistry.getRegistry("192.168.254.99", 28000);
			world = (World)registry.lookup("worldSsl");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int count() {
		return 100000;
	}

	@Override
	public void execution() {
		try {
			@SuppressWarnings("unused")
			String sayWorld = world.sayWorld();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		//本机: 100000/9703 - 1000/217
		//远程: 100000/134078 - 1000/2251
		//远程10: 100000/19556 - 1000/1077
		//远程20: 100000/11802 - 1000/1329
		//远程40: 100000/7551 - 1000/1028
		RMITest rmiTest = new RMITest();
		TestExecutor te = new TestExecutor(rmiTest);
		te.execute(100);
		te.printReport();
	}

}

interface World extends Remote{
	public String sayWorld() throws RemoteException;
}

