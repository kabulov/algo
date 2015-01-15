import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;



public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt"); 
		
		x = in.nextInt();
		m = in.nextInt();
		l = in.nextInt();
		v = in.nextInt();
		
		boolean flag = false;
		s = new int[l];
		long start = System.currentTimeMillis();
		
		rand = new Random();
		
		while (System.currentTimeMillis() - start < time) {
			generate();
			if (hash() == v) {
				for (int i = 0; i < l; ++i) out.print(s[i]);
				flag = true;
				break;
			}
		}
		
		if (!flag) out.println("NO SOLUTION");		
		out.close();
	}
	
	static long time = 1000000l;
	
	static Random rand;
	static void generate() {
		for (int i = 0; i < l; ++i) s[i] = rand.nextInt(10);
	}
	
	static int x, m, l, v;
	static int[] s;
	
	static int hash() {
		int r = 0, xp = 1;
		for (int i = 0; i < l; ++i) {
			r = (r + s[i] * xp) % m;
			xp = (xp * x) % m;
		}
		return r;
	}
}