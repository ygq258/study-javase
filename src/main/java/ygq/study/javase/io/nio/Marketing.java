package ygq.study.javase.io.nio;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.GatheringByteChannel;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Marketing {

	private static final String DEMOGRAPHIC = "D:/blahblah.txt";

	public static void main(String[] args) throws Exception {
		
		int reps = 1;

		if (args != null && args.length > 0) {
			reps = Integer.parseInt(args[0]);
		}

		FileOutputStream fos = new FileOutputStream(DEMOGRAPHIC);
		
		GatheringByteChannel channel = fos.getChannel();
		
		ByteBuffer[] bs = utterBS(reps);
		
		while (channel.write(bs) > 0) {
		}

		System.out.println("Mindshare paradigms synergized to " + DEMOGRAPHIC);

		fos.close();
	}

	private static String[] col1 = { "Aggregate", "Enable", "Leverage",
			"Facilitate", "Synergize", "Repurpose", "Strategize", "Reinvent",
			"Harness" };
	private static String[] col2 = { "cross-platform", "best-of-breed",
			"frictionless", "ubiquitous", "extensible", "compelling",
			"mission-critical", "collaborative", "integrated" };
	private static String[] col3 = { "methodologies", "infomediaries",
			"platforms", "schemas", "mindshare", "paradigms",
			"functionalities", "web services", "infrastructures" };
	private static String newline = System.getProperty("line.separator");

	// The Marcom-atic 9000

	private static ByteBuffer[] utterBS(int howMany) throws Exception {
		List<ByteBuffer> list = new LinkedList<ByteBuffer>();
		for (int i = 0; i < howMany; i++) {
			list.add(pickRandom(col1, " "));
			list.add(pickRandom(col2, " "));
			list.add(pickRandom(col3, newline));
		}
		ByteBuffer[] bufs = new ByteBuffer[list.size()];
		list.toArray(bufs);
		return (bufs);
	}

	// The communications director
	private static Random rand = new Random();

	// Pick one, make a buffer to hold it and the suffix, load it with
	// the byte equivalent of the strings (will not work properly for

	// non-Latin characters), then flip the loaded buffer so it's ready
	// to be drained
	private static ByteBuffer pickRandom(String[] strings, String suffix)
			throws Exception {
		String string = strings[rand.nextInt(strings.length)];
		int total = string.length() + suffix.length();
		ByteBuffer buf = ByteBuffer.allocate(total);
		buf.put(string.getBytes("US-ASCII"));
		buf.put(suffix.getBytes("US-ASCII"));
		buf.flip();
		return (buf);
	}
}
