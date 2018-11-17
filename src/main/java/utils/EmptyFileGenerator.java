package utils;

import java.io.IOException;
import java.io.RandomAccessFile;

public class EmptyFileGenerator {

	public static final long KILO = 1024;
	
	public static void CreateFile(long size, String fileName) throws IOException {
		RandomAccessFile f = new RandomAccessFile(fileName, "rw");
        f.setLength(size * KILO);
	}

	public static byte[] CreateLocalFile(int size) throws IOException {
		int taille = size * 1024;
		byte[] out = new byte[taille];
		for (int i = 0; i < taille; i++) {
			out[i] = (byte) 1;
		}
		return out;
	}

	public static void main(String[] args) throws IOException {
		CreateFile(5,"test1");
	}

}


