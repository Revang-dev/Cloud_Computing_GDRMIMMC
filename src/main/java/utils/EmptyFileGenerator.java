package utils;

import java.io.IOException;
import java.io.RandomAccessFile;

public class EmptyFileGenerator {

	public static final long KILO = 1024;
	
	public static void CreateFile(long size, String fileName) throws IOException {
		RandomAccessFile f = new RandomAccessFile(fileName, "rw");
        f.setLength(size);
	}
	
}
