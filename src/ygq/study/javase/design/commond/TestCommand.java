package ygq.study.javase.design.commond;

import java.util.Iterator;
import java.util.List;

public class TestCommand {

	public static void main(String[] args) {
		// 这三个命令进入List中已经失去其外表特征
		List<Command> queue = Producer.produceRequests();
		
		for (Iterator<Command> it = queue.iterator(); it.hasNext(); ) {
			it.next().execute();
		}
		
	}
}
