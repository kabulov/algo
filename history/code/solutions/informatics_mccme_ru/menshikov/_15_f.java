import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;


public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("input.txt"));
		out = new PrintWriter("output.txt");
		
		char[] a = in.readLine().toCharArray();
		char[] b = in.readLine().toCharArray();
		
		int alen = a.length, blen = b.length;
/*	
 * 		this is a case of code when whitespaces present in input strings

		String str1 = in.readLine();
		String str2 = in.readLine();
		
		char[] a = new char[str1.length()];
		char[] b = new char[str2.length()];

		int alen = 0, blen = 0;
		for (int i = 0; i < str1.length(); i++)
			if (str1.charAt(i) != ' ')
				a[alen++] = str1.charAt(i);
		
		for (int i = 0; i < str2.length(); i++)
			if (str2.charAt(i) != ' ')
				b[blen++] = str2.charAt(i);
*/		
		poly fst = new poly(10);
		poly scd = new poly(10);
		
		convert(a, alen, fst);
		convert(b, blen, scd);
		
		poly thd = mul(fst, scd);
		
		print(thd);
		
		out.close();
	}
	
	static void convert(char[] s, int len, poly p) {
		int pos = 0;
		int end = len;
		char cur = s[pos];

		int sgn = 1, deg, coef;
		while (cur != '\0') {
			if (cur == '+' || cur == '-') {
				sgn = 1;
				if (cur == '-')
					sgn = -1;
				
				cur = s[++pos];
			}
			
			if (cur == 'x') {
				coef = 1;
			} else {
				coef = cur - 48;
				while (++pos < end && '0' <= (cur = s[pos]) && cur <= '9') {
					coef = coef * 10 + cur - 48;
				}
				
				if (pos == end) {
					p.v[0] += sgn * coef;
					break;
				}
				
				if (cur != 'x') {
					p.v[0] += sgn * coef;
					continue;
				}
			}
			
			++pos;
			if (pos == end) {
				p.v[1] += sgn * coef;
				break;
			}
			
			cur = s[pos];
			if (cur != '^') {
				p.v[1] += sgn * coef;
				continue;
			}
			
			deg = 0;
			while (++pos < end && '0' <= (cur = s[pos]) && cur <= '9')
				deg = deg * 10 + cur - 48;
			
			p.v[deg] += sgn * coef;
			
			if (pos == end)
				cur = '\0';
		}
		
		p.deg = 10;
		while (p.deg > 0 && p.v[p.deg] == 0)
			--p.deg;			
	}
	
	static poly mul(poly a, poly b) {
		poly c = new poly(a.deg + b.deg);
		
		for (int i = 0; i <= a.deg; ++i)
			for (int j = 0; j <= b.deg; ++j)
				c.v[i + j] += a.v[i] * b.v[j];
		
		c.deg = a.deg + b.deg;
		return c;
	}
	
	static PrintWriter out;
	
	static void print(poly p) {
		if (p.deg == 0)
			out.println(p.v[0]);
		else {
			if (p.v[p.deg] < 0) {
				out.print("-");
				p.v[p.deg] *= -1;
			}
			if (p.v[p.deg] != 1)
				out.print(p.v[p.deg]);
			out.print("x");
			if (p.deg > 1) 
				out.print("^" + p.deg);
			
			for (int i = p.deg - 1; i > 0; --i) 
				if (p.v[i] != 0) {
					if (p.v[i] < 0) {
						out.print("-");
						p.v[i] *= -1;
					} else
						out.print("+");
					if (p.v[i] != 1)
						out.print(p.v[i]);
					out.print("x");
					if (i > 1)
						out.print("^" + i);
				}
			
			if (p.v[0] != 0) {
				if (p.v[0] > 0)
					out.print("+");
				out.print(p.v[0]);
			}
		}
	}
}

class poly {
	int[] v;
	int deg;
	
	poly(int sz) {
		deg = 0;
		v = new int[sz + 1];
		for (int i = 0; i <= sz; i++)
			v[i] = 0;
	}
}