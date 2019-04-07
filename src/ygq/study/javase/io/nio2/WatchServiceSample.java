package ygq.study.javase.io.nio2;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class WatchServiceSample {

	@SuppressWarnings({ "rawtypes", "unused" })
	public static void main(String[] args) throws IOException {

		Path path = Paths.get("D:\\Temp\\zip");

		WatchService watchService = FileSystems.getDefault().newWatchService();

		WatchKey watchKey = path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE,
				StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY);

		WatchKey key = null;
		
		while (true) { // important - create an indefinite loop to watch the
						// file system changes.
			try {
				key = watchService.take();
				for (WatchEvent event : key.pollEvents()) {
					Kind kind = event.kind();
					System.out.println("Event on " + event.context().toString() + " is " + kind);
				}
			} catch (InterruptedException e) {
				System.out.println("InterruptedException: " + e.getMessage());
			}
			boolean reset = key.reset();
			if (!reset)
				break;
		}

	}
}
