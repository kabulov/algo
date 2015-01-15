import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		int n = in.nextInt();
		int[] v = new int[n+1];
		
		v[0] = 1;
		for (int i = 1; i <= n; ++i) v[i] = (v[i-1] * 10) % n;
		
		int i = n - 1;
		while (v[i] != v[n]) --i;
	
		int period = n - i;		
		while (i >= 0 && v[i] == v[i+period]) --i;
		
		out.println(++i + " " + period);		
		out.close();
	}
}