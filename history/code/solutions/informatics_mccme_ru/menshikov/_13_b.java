import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] argv) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		long i = in.nextInt();
		long fi = in.nextInt();
		
		long j = in.nextInt();
		long fj = in.nextInt();
		
		long n = in.nextInt();
		
		if (n == i)
			out.println(fi);
		else {
			if (i > j) {
				long tmp = i;
				i = j;
				j = tmp;
				
				tmp = fi;
				fi = fj;
				fj = tmp;
			}
			
			if (j - i > 1) {
				fj = getFib(i, fi, j, fj);				
				j = i + 1;
			}
			
			if (n == j)
				out.println(fj);
			else {
				long tmp;
				if (n > j) {
					for (long p = j + 1; p <= n; ++p) {
						tmp = fi + fj;
						fi = fj;
						fj = tmp;
					}
					out.println(fj);
				} else { // n < i
					for (long p = i - 1; p >= n; --p) {
						tmp = fj - fi;
						fj = fi;
						fi = tmp;
					}
					out.println(fi);
				}
			}
		}
		
		out.close();
	}
	
	static long getFib(long i, long fi, long j, long fj) {
		if (i + 2 == j)
			return fj - fi;
		
		long a, b;
		a = b = 1;
		
		for (long p = j - 3; p >= i; --p) {
			long tmp = b;
			b += a;
			a = tmp;
		}
		
		return (fj - a * fi) / b;
	}
}
