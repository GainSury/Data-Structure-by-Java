package DS;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class HashTableSq {
    public static void main(String[] args)
    {
    	System.out.println("hello world!");
    	byte[] buffer = new byte[10];
    	for (int i = 0;i< buffer.length;i++)
    	{
    		buffer[i] = (byte)i;
    	}
    	try {
			FileOutputStream out = new FileOutputStream("a.dat");
			out.write(buffer);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
