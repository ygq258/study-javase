package ygq.study.javase.test.rmi;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.GZIPOutputStream;

import ygq.study.javase.test.TestExecutor;
import ygq.study.javase.test.TestTask;

public class GzipTest extends TestTask{

	private byte[] readAllBytes;
	@Override
	public void init() {
		try {
			readAllBytes = Files.readAllBytes( Paths.get( "F:\\新和兴\\【定稿】访谈成员名单.xlsx" ) );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public int count() {
		return 10000;
	}

	@Override
	public void execution() {
		try {
			this.addSendByteNumber(readAllBytes.length);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			
			GZIPOutputStream gzipOs = new GZIPOutputStream( baos );
			
			gzipOs.write( readAllBytes );
			
			gzipOs.finish();
			
			gzipOs.close();
			this.addReceiveByteNumber(baos.size());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception {
		//0.14ms
		//0.28ms
		//0.5 * 10000
		//40
		//10000000.0/11329 1
		//10000000.0/15965 2
		//10000000.0/11329 4
		TestExecutor executor = new TestExecutor(new GzipTest());
		executor.setBandwidthTest(true);
		executor.execute(1);
		executor.printReport();
		executor.execute(2);
		executor.printReport();
		executor.execute(4);
		executor.printReport();
		executor.execute(8);
		executor.printReport();
		executor.execute(16);
		executor.printReport();
	}
	
	
}
