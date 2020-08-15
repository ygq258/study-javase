package ygq.study.javase.io.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.DatagramChannel;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * 该实例讲解代码执行过程，特别提到OP_WRITE使用方式
 * 管道，操作系统中的管道是对进程到进程之间通信的，Java中的管道是Java进程内部通信的
 * 在window中实现Selector.open()创建一个TCP回环地址
 * @author yang_gui_quan
 */
public class SocketChannelSample {

	@SuppressWarnings({ "unused", "resource" })
	public static void sample() throws IOException {
		Channel channel = null;
		// WritableByteChannel
		// ReadableByteChannel
		// InterruptibleChannel
		// NetworkChannel
		// SocketChannel
		// FileChannel
		// ServerSocketChannel
		// DatagramChannel

		SocketChannel sc = SocketChannel.open();

		sc.connect(new InetSocketAddress("127.0.0.1", 36000));

		ServerSocketChannel ssc = ServerSocketChannel.open();

		ssc.socket().bind(new InetSocketAddress("127.0.0.1", 36000));

		DatagramChannel dc = DatagramChannel.open();

		RandomAccessFile raf = new RandomAccessFile("", "r");

		FileChannel fc = raf.getChannel();
	}
	
	/**
	 * 在window中实现Selector.open()创建一个TCP回环地址
	 * @throws IOException
	 */
	public static void sample2() throws IOException{
		
		List<Selector> selectorList = new LinkedList<Selector>();
		try {
			for (int i = 0; i < 100; i++) {
				selectorList.add(Selector.open());
			}
			System.out.println("创建100个选择器.........");
			System.out.println("请等待30秒.........");
			Thread.sleep(30000);
			for (int i = 0; i < selectorList.size(); i++) {
				selectorList.get(i).close();
			}
			System.out.println("关闭100个选择器.........");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static void sample3() throws IOException {
		
		SocketChannel sc = SocketChannel.open();
		
		Object obj = sc.blockingLock();
		
		synchronized (obj) {
			sc.configureBlocking(false);
		}
		
		ServerSocketChannel ssc = ServerSocketChannel.open();
		
		ServerSocket socket = ssc.socket();
		
		socket.bind(new InetSocketAddress(1234));
		
		InetSocketAddress addr = new InetSocketAddress("127.0.0.1", 1234);
		
		SocketChannel sc2 = SocketChannel.open();
		
		sc2.connect(addr);
		
		while(!sc2.isConnected()){
			//doSomethingElse
		}
		//doSomethingWidthChannel(sc2);
		sc2.close();
	}
	
	public static void sample4() throws IOException {

		SocketChannel channel = SocketChannel.open();
		// 设置为非阻塞模式
		channel.configureBlocking(false);
		// 对于非阻塞模式，立刻返回false，表示连接正在建立中
		channel.connect(new InetSocketAddress("127.0.0.1", 6000));
		Selector selector = Selector.open();
		// 向channel注册selector以及感兴趣的连接事件
		channel.register(selector, SelectionKey.OP_CONNECT);
		// 阻塞至有感兴趣的IO事件发生，或到达超时时间，如果希望一直等至有感兴趣的IO事件发生，可调用无参数的select方法，如果希望不阻塞直接返回目前是否有感兴趣的事件发生，可调用selectNow方法
		int nKeys = selector.select(10);// 以毫秒为单位的超时时间
		// 如nKeys大于零，说明有感兴趣的IO事件发生
		SelectionKey sKey = null;
		if (nKeys > 0) {
			Set<SelectionKey> keys = selector.selectedKeys();
			for (SelectionKey key : keys) {
				// 对于发生连接的事件
				if (key.isConnectable()) {
					SocketChannel sc = (SocketChannel) key.channel();
					sc.configureBlocking(false);
					// 注册感兴趣的IO读事件，通常不直接注册写事件，在发送缓冲区未满的情况下，一直是可写的，因此如注册了写事件，而又不用写数据，很容易造成CPU消耗100%的现象
					sKey = sc.register(selector, SelectionKey.OP_READ);
					// 完成连接的建立
					sc.finishConnect();
				}
				// 有流可读取
				else if (key.isReadable()) {
					ByteBuffer buffer = ByteBuffer.allocate(1024);
					SocketChannel sc = (SocketChannel) key.channel();
					@SuppressWarnings("unused")
					int readBytes = 0;
					try {
						int ret = 0;
						try {
							// 读取目前可读的流，sc.read
							// 返回的为成功复制到bytebuffer中的字节数，此步为阻塞操作，
							// 值可能为0；当已经是流的结尾时，返回-1
							while ((ret = sc.read(buffer)) > 0) {
								readBytes += ret;
							}
						} finally {
							buffer.flip();
						}
					} finally {
						if (buffer != null) {
							buffer.clear();
						}
					}
				}
				// 可写入流
				else if (key.isWritable()) {
					// 取消对OP_WRITE事件的注册
					key.interestOps(key.interestOps()
							& (~SelectionKey.OP_WRITE));
					SocketChannel sc = (SocketChannel) key.channel();
					// 此步为阻塞操作，直到写入操作系统发送缓冲区或网络IO出现异常，返回的为成功写入的字节数，当操作系统的发送缓冲区已满，此处会返回0
					int writtenedSize = sc.write(ByteBuffer.allocate(12));
					// 如未写入，则继续注册感兴趣的OP_WRITE事件
					if (writtenedSize == 0) {
						key.interestOps(key.interestOps()
								| SelectionKey.OP_WRITE);
					}
				}
			}
			selector.selectedKeys().clear();
		}
		// 对于要写入的流，可直接调用channel.write来完成，
		// 只有在写入未成功时才要注册OP_WRITE事件
		int wSize = channel.write(ByteBuffer.allocate(12));
		if (wSize == 0) {
			sKey.interestOps(sKey.interestOps() | SelectionKey.OP_WRITE);
		}
	}
	
	public static void main(String[] args) throws IOException {
		sample();
	}
}
