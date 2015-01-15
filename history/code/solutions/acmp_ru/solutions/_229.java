import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt"); 

		int n = in.nextInt();
		double[] fa, fb, sa, sb;
		
		fa = new double[n];
		fb = new double[n];
		sa = new double[n];
		sb = new double[n];
		
		for (int i = 0; i < n; ++i) {
			fa[i] = in.nextDouble();
			sa[i] = in.nextDouble();
		}
				
		{
			double mx = fa[0];
			for (int i = 1; i < n; ++i) 
				if (fa[i] > mx)
					mx = fa[i];

			for (int i = 0; i < n; ++i)
				fb[i] = fa[i] + 100 - mx;

			mx = 100 / mx;
			for (int i = 0; i < n; ++i)
				fa[i] *= mx;
		}
		
		{
			double mx = sa[0];
			for (int i = 1; i < n; ++i)
				if (sa[i] > mx)
					mx = sa[i];

			for (int i = 0; i < n; ++i)
				sb[i] = sa[i] + 100 - mx;
			
			mx = 100 / mx;
			for (int i = 0; i < n; ++i)
				sa[i] *= mx;
		}
		
		v = new boolean[1000];
		Arrays.fill(v, false);
		amt = 0;
		
		run(fa, sa, n);
		run(fa, sb, n);
		run(fb, sa, n);
		run(fb, sb, n);

		out.println(amt);
		for (int i = 0; i < 1000; ++i)
			if (v[i])
				out.print((i + 1) + " ");
		
		out.close();
	}	

	static void run(double[] fa, double[] sa, int n) {
		double[] vect = new double[n];

		for (int i = 0; i < n; ++i)
			vect[i] = fa[i] + sa[i];
		
		double mx = vect[0];
		for (int i = 1; i < n; ++i)
			if (vect[i] > mx)
				mx = vect[i];
		
		for (int i = 0; i < n; ++i)
			if (equal(vect[i], mx)) 
				if (!v[i]) {
					v[i] = true;
					++amt;
				}
	}
	
	static boolean[] v;
	static int amt;
	
	static double eps = 1e-5;	//!!
	static boolean equal(double a, double b) {
		return Math.abs(a - b) < eps;
	}
}
