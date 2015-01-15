import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int n = in.nextInt();
		int k = 1, s;
		while ((s=S(k)) < n) {
			n -= s;
			k++;
		}
		
		int l = 1, r = n, m;
		while (r - l > 1) {
			m = (l+r)/2;
			if (n<S(m))
				r = m;
			else
				l = m;			
		}
		
		n -= S(l);
		if (n == 0)
			out.println(l % 10);
		else 
			out.println(get(l+1, n));
		
		in.close();
		out.close();
	}
	
	static int S(int k) {
		int ans = 0;
		int n = 1, p = 1;
		while (10*n <= k) {
			ans += p*9*n;
			
			n *= 10;
			p++;
		}
		return ans+(k-n+1)*p;
	}
	
	static int get(int m, int n) {
		int d = 0, k = m;
		while (k > 0) {
			d++;
			k/=10;
		}
		d-=n;
		while (d > 0) {
			m/=10;
			d--;
		}
		return m % 10;
	}
}