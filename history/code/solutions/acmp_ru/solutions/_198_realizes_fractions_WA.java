import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		in = new StreamTokenizer(new FileReader("input.txt"));
		
		int n = nextInt();
		frac[][] vect = new frac[n][n + 1];
		for (int i = 0; i < n; i++)
			for (int j = 0; j <= n; j++)
				vect[i][j] = new frac(nextInt(), 1);
		
		int max;
		frac tmp;
		long num, den;
		frac buf = new frac(1, 1);
		for (int i = 0; i < n - 1; i++) {
			max = i;
			for (int p = i + 1; p < n; p++)
				if (Math.abs(vect[p][i].num) * vect[max][i].den > vect[p][i].den * Math.abs(vect[max][i].num)) {
					max = p;
				}
			
			if (max > i) {
				for (int j = i; j <= n; j++) {
					tmp = vect[i][j];
					vect[i][j] = vect[max][j];
					vect[max][j] = tmp;
				}
			}
			
			num = vect[i][i].den;
			den = vect[i][i].num;
			
			if (den < 0) {
				den = -den;
				num = -num;
			} 
			
			vect[i][i].num = 1;
			vect[i][i].den = 1;
			for (int j = i + 1; j <= n; j++)
				vect[i][j].mul(num, den);
				
			for (int p = i + 1; p < n; p++) {
				if (vect[p][i].num == 0)
					continue;
				
				num = vect[p][i].num;
				den = vect[p][i].den;
				
				vect[p][i].num = 0;
				//vect[p][i].den = natural;
								
				for (int j = i + 1; j <= n; j++) {
					buf.num = vect[i][j].num;
					buf.den = vect[i][j].den;
					buf.mul(num, den);
					vect[p][j].sub(buf.num, buf.den);
				}
			}
 		}
		
		for (int i = n - 1; i >= 0; i--) {
			num = vect[i][i].den;
			den = vect[i][i].num;
			
			if (den < 0) {
				den = -den;
				num = -num;
			} 
			
			vect[i][n].mul(num, den);
			num = vect[i][n].num;
			den = vect[i][n].den;
			for (int p = i - 1; p >= 0; p--) {
				vect[p][i].mul(num, den);
				vect[p][n].sub(vect[p][i].num, vect[p][i].den);
			}
		}
		
		PrintWriter out = new PrintWriter("output.txt");
		for (int i = 0; i < n; i++)
			out.print(vect[i][n].num / vect[i][n].den + " ");
		out.close();
	}
	
	static long gcd_tmp;
	static long gcd(long a, long b) {
		while (b != 0) {
			gcd_tmp = a;
			a = b;
			b = gcd_tmp % b;
		}
		return a;
	}
	
	static StreamTokenizer in;
	
	public static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}

class frac {
	long num, den;
	frac(long n, long d) {
		num = n;
		den = d;
	}
	
	void mul(long n, long d) {
		long sgn, gcd1, gcd2;
		
		if ((num > 0 && n > 0) || (num < 0 && n < 0))
			sgn = +1;
		else
			sgn = -1;
		
		if (num < 0)
			num = -num;
		if (n < 0)
			n = -n;
		
		gcd1 = Main.gcd(num, d);
		gcd2 = Main.gcd(den, n);
		
		num = num / gcd1 * n / gcd2;;
		den = den / gcd2 * d / gcd1;
		
		if (sgn < 0)
			num = -num;
	}
	
	void sub(long n, long d) {
		n = -n;
		long nok = den / Main.gcd(den, d) * d;
		
		num *= nok / den;
		n *= nok / d;
		
		den = nok;
		num += n;		
	}
}

