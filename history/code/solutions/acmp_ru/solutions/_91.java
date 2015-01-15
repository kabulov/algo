import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int n = in.nextInt();
		
		int[] a, b;
		
		a = new int[Math.max(4, n)];
		b = new int[Math.max(4, n)];
		
		a[0] = 2;
		a[1] = 3;
		a[2] = 4;
		a[3] = 7;
		
		b[0] = 1;
		b[1] = 5;
		b[2] = 6;
		b[3] = 8;
		
		int p = 4;
		for (int i = 4; i < n; ++i) {
			a[i] = b[i-1]+b[i-3];
			for(int j = Math.max(a[i-1], b[p-1])+1; p < n && j < a[i]; ++j)
				b[p++] = j;
		}
		
		out.println(a[n-1]);
		out.println(b[n-1]);
		
		in.close();
		out.close();
	}
}