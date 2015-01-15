//!n = (n-1) (!(n-1)+!(n-2)) (таким же свойством обладает сам факториал)
//!n = f(n) - gde f - iskomaya v etoy zadache funkciya
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.math.BigInteger;

public class Main {
	public static void main(String[] args) throws IOException {
		out = new PrintWriter("output.txt");
		in = new StreamTokenizer(new FileReader("input.txt"));
		
		int n = nextInt();
		
		if (n <= 2) {
			out.println(n == 1 ? 0 : 1);
			out.close();
			return;
		}
			
		BigInteger fnm2, fnm1, fn, nm1, one = BigInteger.ONE;
		fnm1 = BigInteger.ZERO;
		fn = BigInteger.ONE;
		
		nm1 = BigInteger.ONE;
		for (int i = 3; i <= n; i++) {
			nm1 = nm1.add(one);

			fnm2 = fnm1;
			fnm1 = fn;
			fn = nm1.multiply(fnm2.add(fnm1));
		}
		
		out.println(fn);
		out.close();
	}
	
	static PrintWriter out;
	static StreamTokenizer in;
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}

/*
	//formula : f(n) = n * f(n - 1) + (-1)^n;
		int n = nextInt();
		
		BigInteger answer = BigInteger.ZERO;
		BigInteger one = BigInteger.ONE;
		BigInteger N = one;
		
		for (int i = 2; i <= n; i++) {
			N = N.add(one);
			answer = answer.multiply(N);
			
			if (i % 2 == 0) 
				answer = answer.add(one);
			else
				answer = answer.subtract(one);
		}
*/

//takje : f(n) = [(n! + 1) /e], gde [x] - celaya chast chisla
