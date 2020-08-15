package ygq.study.javase.io.nio;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

/**
 * ByteBuffer 字节缓冲区操作示例
 * @author yang
 */
public class ByteBufferSample {

	/**
	 * 字节缓冲区的创建HeapByteBuffer
	 */
	public static void sample1(){
		//分配10个容量的字节缓冲区
		ByteBuffer buffer = ByteBuffer.allocate(10);
		buffer.put((byte)11);
		buffer.put((byte)12);
		buffer.put((byte)13);
		buffer.put((byte)14);
		buffer.put((byte)15);
		
		//buffer.limit(buffer.position()).position(0);
		//buffer.flip();
		while (buffer.hasRemaining()) {
			System.out.println(buffer.get());
		}
		buffer.position(3);
		buffer.limit(8);
		System.out.println(buffer);
		System.out.println(Arrays.toString(buffer.array()));
//		buffer.flip();
//		System.out.println(buffer);
//		System.out.println(Arrays.toString(buffer.array()));
		buffer.compact();
		System.out.println(buffer);
		System.out.println(Arrays.toString(buffer.array()));
		System.out.println("-----------------------------");
		
		byte[] temp = new byte[2];
		
		while (buffer.hasRemaining()) {
			int min = Math.min(buffer.remaining(), temp.length);
			buffer.get(temp, 0, min);
			//对temp进行处理
		}
		byte[] wraps = new byte[20];
		ByteBuffer byte1 = ByteBuffer.wrap(wraps);
		ByteBuffer byte2 = ByteBuffer.wrap(wraps, 2, 12);
		System.out.println("byte1:"+byte1);
		System.out.println("byte2:"+byte2);
		
		ByteBuffer byte3 = ByteBuffer.wrap(wraps, 3, 12);
		System.out.println("byte3:"+byte3);
		System.out.println("slice:"+byte3.slice());
		System.out.println("duplicate:"+byte3.duplicate());
		
		System.out.println("----------------------");
		byte3.order(ByteOrder.nativeOrder());		
		
		
		ByteBuffer byte4 = ByteBuffer.allocateDirect(10);
		System.out.println(byte3.isDirect());
		System.out.println(byte4.isDirect());
	}
	
	/**
	 * 字节缓冲区的创建DirectByteBuffer
	 */
	public static void sample2(){
		//分配10个容量的字节缓冲区
		ByteBuffer buffer = ByteBuffer.allocate(10);
		buffer.put((byte)11);
		buffer.put((byte)12);
		buffer.put((byte)13);
		buffer.put((byte)14);
		buffer.put((byte)15);
		System.out.println(buffer.limit(5));
		System.out.println("position:"+buffer.position());	// 返回此缓冲区的位置
		System.out.println("limit:"+buffer.limit());	//返回此缓冲区的限制
		System.out.println("capacity:"+buffer.capacity());	//返回此缓冲区的容量
		System.out.println("hasRemaining:"+buffer.hasRemaining());	//是否有剩余元素
		//System.out.println("clear:"+buffer.clear());	//清除缓冲区,不是实际上的清楚，而是重置position limit mark参数
		//System.out.println(Arrays.toString(buffer.array()));	//仅支持heapbytebuffer
		System.out.println("hasArray"+buffer.hasArray());	//是否具有可访问的底层实现数组
		System.out.println("isReadOnly"+buffer.isReadOnly());	//是否为只读缓冲区
		System.out.println("isDirect"+buffer.isDirect());	//是否为直接缓冲区
		System.out.println("mark:"+buffer.mark());	//在此缓冲区的位置设置标记
		System.out.println("reset:"+buffer.reset());	//将此缓冲区的位置重置为以前标记的位置
		buffer.flip();	//翻转这个缓冲区。该限制被设置为当前位置 该位置设置为零。如果该标记被定义为丢弃。
		buffer.rewind();	//倒回 该位置被设置为零，标记为丢弃。
		//buffer.limit(buffer.position()).position(0);
		//buffer.flip();
	}
	
	/**
	 * 字节缓冲区的创建ByteBuffer其他方法
	 */
	public static void sample3(){
		//分配10个容量的字节缓冲区
		ByteBuffer buffer = ByteBuffer.allocate(10);
		buffer.put((byte)11);
		buffer.put((byte)12);
		buffer.put((byte)13);
		buffer.put((byte)14);
		buffer.put((byte)15);
		
//		buffer.compact();	//压缩缓冲区 position到limit 的元素 移到0索引位置，并把当前的索引设置为 limit-positon	
//		buffer.duplicate();	//创建共享此缓冲区内容的新的字节缓冲区
//		buffer.slice();	//创建新的字节缓冲区，其内容是此缓冲区内容的共享子序列
//		buffer.asReadOnlyBuffer();	//创建一个只读的缓冲区
//		buffer.arrayOffset();	//返回此缓冲区中的第一个元素在缓冲区的底层实现数组中的偏移量
		System.out.println("order:" + buffer.order()); // 获取此缓冲区的字节顺序
		System.out.println(Arrays.toString(buffer.array()));
//		buffer.order(ByteOrder.LITTLE_ENDIAN);	//设置字节流的顺序
		System.out.println(Arrays.toString(buffer.array()));
		//大端字节顺序：将高序字节存储在起始地址
		//小端字节顺序：将低序字节存储在起始地址
		ByteBuffer.wrap(new byte[] { 21, 22, 24, 27, 28, 29 });	//将 byte 数组包装到缓冲区中
		//[3] limit 5 limt - 3 positon - 3
		// 0,1,[2],3,[4],5,6,
		//position = 4 limit 6
		
		buffer.get(1);
	}
	
	public static void main(String[] args) {
		
//		sample1();
//		sample2();
		sample3();
	}
}
