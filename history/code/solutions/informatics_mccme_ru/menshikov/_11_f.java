import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		frac f1 = new frac(in.readLine());
		char op = in.readLine().charAt(0);
		frac f2 = new frac(in.readLine());
		
		switch(op) {
		case '+':
			f1.add(f2);
			break;
		case '-':
			f1.sub(f2);
			break;
		case '*':
			f1.mul(f2);
			break;
		case '/':
			f1.div(f2);
			break;
		}
		
		out.println(f1.prn());
		out.close();
	}

	static class frac {
		int sgn, num, den;
		frac(String str) {
			if (str.charAt(0) == '-') {
				sgn = -1;
				str = str.substring(1);
			} else
				sgn = 1;
			
			if (str.charAt(0) == '0') {
				num = 0;
				den = 1;
			} else
			if (str.indexOf(' ') > 0) {
				String[] tmp = str.split(" ");
				int whole = Integer.parseInt(tmp[0]);
				tmp = tmp[1].split("[/]");
				num = Integer.parseInt(tmp[0]);
				den = Integer.parseInt(tmp[1]);
				num += whole * den;
			} else {
				if (str.indexOf('/') > 0) {
					String[] tmp = str.split("[/]");
					num = Integer.parseInt(tmp[0]);
					den = Integer.parseInt(tmp[1]);
				} else {
					den = 1;
					num = Integer.parseInt(str);
				}
			}
			int tmp = gcd(num, den);
			num /= tmp;
			den /= tmp;
		}
		
		String prn() {
			if (num == 0)
				return new String("0");
			
			StringBuilder tmp = new StringBuilder();
			int whole = num / den;
			num %= den;
			
			if (sgn < 0)
				tmp.append('-');
			if (whole != 0)
				tmp.append(whole);
			
			if (num != 0) {
				if (whole != 0)
					tmp.append(' ');
				tmp.append(num);
				tmp.append('/');
				tmp.append(den);
			}
			
			return new String(tmp);
		}
		
		void swap(frac f) {
			int tmp = sgn;
			sgn = f.sgn;
			f.sgn = tmp;
			
			tmp = num;
			num = f.num;
			f.num = tmp;
			
			tmp = den;
			den = f.den;
			f.den = tmp;
		}
		
		void add(frac f) {
			if (sgn * f.sgn > 0) {
				int tmp = den / gcd(den, f.den) * f.den;
				num *= tmp / den;
				f.num *= tmp / f.den;
				num += f.num;
				den = tmp;
				tmp = gcd(num, den);
				num /= tmp;
				den /= tmp;
			} else {
				if (sgn < 0)
					swap(f);
			
				f.sgn = 1;
				sub(f);				
			}
		}
		
		void sub(frac f) {
			if (sgn * f.sgn > 0) {
				if (sgn < 0) {
					sgn = f.sgn = 1;
					swap(f);
				}
						
				int tmp = den / gcd(den, f.den) * f.den;
				num *= tmp / den;
				f.num *= tmp / f.den;
				num -= f.num;
				if (num < 0) {
					sgn = -1;
					num = -num;
				}
				den = tmp;
				tmp = gcd(num, den);
				num /= tmp;
				den /= tmp;
			} else {
				f.sgn *= -1;
				add(f);
			}
		}
		
		void mul(frac f) {
			sgn *= f.sgn;
			
			int tmp = gcd(num, f.den);
			num /= tmp;
			f.den /= tmp;
			
			tmp = gcd(den, f.num);
			den /= tmp;
			f.num /= tmp;
			
			num *= f.num;
			den *= f.den;
		}
		
		void div(frac f) {
			sgn *= f.sgn;
			
			int tmp = gcd(num, f.num);
			num /= tmp;
			f.num /= tmp;
			
			tmp = gcd(den, f.den);
			den /= tmp;
			f.den /= tmp;
			
			num *= f.den;
			den *= f.num;
		}
		
		int gcd(int a, int b) {
			return b == 0 ? a : gcd(b, a % b);
		}
	}
}