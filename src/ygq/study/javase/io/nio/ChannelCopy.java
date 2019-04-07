package ygq.study.javase.io.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

public class ChannelCopy {

	public static void main(String[] args) throws IOException {

		ReadableByteChannel source = Channels.newChannel(System.in);

		WritableByteChannel dest = Channels.newChannel(System.out);

		channelCopy1(source, dest);

		source.close();

		dest.close();

	}

	public static void channelCopy1(ReadableByteChannel source,
			WritableByteChannel dest) throws IOException {

		ByteBuffer buffer = ByteBuffer.allocate(10 * 1024);

		while (source.read(buffer) != -1) {

			buffer.flip();
			// 1,2,3,4,5|,0,0,0,0,0
			dest.write(buffer);
			//
			buffer.compact();
		}

		buffer.flip();

		while (buffer.hasRemaining()) {
			dest.write(buffer);
		}
	}

	public static void channelCopy2(ReadableByteChannel source,
			WritableByteChannel dest) throws IOException {

		ByteBuffer buffer = ByteBuffer.allocate(16 * 1024);

		while (source.read(buffer) != -1) {
			
			buffer.flip();

			while (buffer.hasRemaining()) {

				dest.write(buffer);
			}
		}

		buffer.clear();
	}
}
