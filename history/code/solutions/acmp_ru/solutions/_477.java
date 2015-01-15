import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		char[] v = in.next().toCharArray();
		int s = 0;
		
		for (int i = 0; i < v.length; ++i)
			s += v[i] - '0';
		
		if (s % 3 == 0) {
			out.println(2);
		} else {
			out.println(1);
			out.println(s % 3);
		}
		
		out.close();
	}	
}
