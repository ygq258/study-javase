package ygq.study.javase.test.rmi;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Set;

import ygq.study.javase.test.TestExecutor;
import ygq.study.javase.test.TestTask;

public class SocketTest extends TestTask {

	/* 缓冲区大小 */
	private static int BLOCK = 1;
	/* 接受数据缓冲区 */
	private static ByteBuffer receivebuffer = ByteBuffer.allocate(BLOCK);
	/* 发送数据缓冲区 */
	//private static ByteBuffer sendbuffer = ByteBuffer.allocate(BLOCK);
	/* 服务器端地址 */
//	private final static InetSocketAddress SERVER_ADDRESS = new InetSocketAddress("192.168.254.99", 38000);
	private final static InetSocketAddress SERVER_ADDRESS = new InetSocketAddress("127.0.0.1", 38000);
	// 打开选择器
	private Selector selector;
	// socket通道
	private SocketChannel socketChannel;
	
	@Override
	public void init() {
		try {
			// 打开socket通道
			socketChannel = SocketChannel.open();
			// 设置为非阻塞方式
			socketChannel.configureBlocking(false);
			// 打开选择器
			selector = Selector.open();
			// 注册连接服务端socket动作
			socketChannel.register(selector, SelectionKey.OP_CONNECT);
			// 连接
			socketChannel.connect(SERVER_ADDRESS);
			
			selector.select();

			// 返回此选择器的已选择键集。
			SelectionKey selectionKey = selector.selectedKeys().iterator().next();
			
			SocketChannel client = (SocketChannel) selectionKey.channel();
			// 完成套接字通道的连接过程。
			if (client.isConnectionPending()) {
				client.finishConnect();
				System.out.println("完成连接!");
			}
		} catch (ClosedChannelException e) {
			e.printStackTrace();
		} catch (IOException e) {
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
			get(ByteBuffer.wrap("c".getBytes()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public ByteBuffer get(ByteBuffer data) throws IOException {
		data.rewind();
		while(data.hasRemaining()){
			socketChannel.write(data);
		}
		socketChannel.register(selector, SelectionKey.OP_READ);
		selector.select();
		// 返回此选择器的已选择键集。
		Set<SelectionKey> selectionKeys = selector.selectedKeys();
		SocketChannel channel = (SocketChannel)selectionKeys.iterator().next().channel();
		receivebuffer.clear();
		while(receivebuffer.hasRemaining()){
			channel.read(receivebuffer);
		}
		return receivebuffer;
	}
	
	/**
	 * 10000-13568ms
	 * 远程100000-125166ms
	 * 本机100000-7800ms
	 * 局域网：发送到接收延迟1.2ms 传输延迟0.6ms
	 * 本机：发送到接收延迟：0.08ms 传输延迟0.04ms
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		SocketTest client = new SocketTest();
		TestExecutor te = new TestExecutor(client);
		te.execute(1);
		te.printReport();
	}
	
}
