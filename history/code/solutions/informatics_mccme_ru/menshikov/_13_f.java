import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		File f = new File("input.txt");
		FileReader in = new FileReader(f);
		
		bufsize = (int)f.length();
		buffer = new char[bufsize];
		bufsize = in.read(buffer);										///
		
		pos = 0;
		cur = buffer[0];														////
		
		int n = nextInt();
		poly = new int[11];
		
		out = new PrintWriter("output.txt");
		
		for (int i = 1; i <= n; i++) {
			for (int j = 0; j < 11; j++) poly[j] = 0;
			nextChar();
			solve(0, 1);
			print(i);
		}
		
		out.close();
	}
	
	static void operator(int p, int coef) {
		if (cur == 'L') {
			skip();
			nextChar();
			if (cur == 'n') {
				nextChar();
				nextChar();
				operator(p + 1, coef);
				while(cur == 'L' || cur == 'O')
					operator(p + 1, coef);
			} else {
				int ctr = nextInt();
				nextChar();
				operator(p, coef * ctr);
				while(cur == 'L' || cur == 'O')
					operator(p, ctr * coef);
			}
			skip();
			nextChar();
		} else {//cur == 'O'
			skip();
			nextChar();
			if (cur == 'n') {
				poly[p + 1] += coef;
				nextChar();
				nextChar();
			} else {
				poly[p] += coef * nextInt();
				nextChar();
			}
		}
	}
	
	static void solve(int p, int coef) {
		skip();//begin
		nextChar();
		operator(p, coef);
		while (cur != 'E') operator(p, coef);
		skip();//end
	}
	
	static void skip() {
		while ('A' <= cur && cur <= 'Z')
			nextChar();
	}
	
	static int nextInt() {
		int integer = 0;
		while ('0' <= cur && cur <= '9') {
			integer = integer * 10 + cur - 48;
			nextChar();
		}
		return integer;
	}
	
	static void nextChar() {
		if (!Character.isLetterOrDigit(cur)) {
			while (!Character.isLetterOrDigit(cur)) {
				++pos;
				cur = ((pos >= bufsize) ? '\0' : buffer[pos]);
			}
			return;
		}
		++pos;
		cur = ((pos >= bufsize) ? '\0' : buffer[pos]);
	}
	
	static int bufsize;
	static char[] buffer;
	
	static int pos;
	static char cur;

	static PrintWriter out;
	static int[] poly;
	
	static void print(int p) {
		out.println("Program #" + p);
		out.print("Runtime = ");
		
		int deg = 10;
		while (deg > 0 && poly[deg] == 0)
			--deg;
		
		printTerm(deg);
		
		for (deg = deg - 1; deg >= 0; --deg) {
			if (poly[deg] == 0)
				continue;

			out.print("+");
			
			printTerm(deg);
		}
		
		out.println("\n");				///
	}
	
	static void printTerm(int p) {
		int coef = poly[p];
		
		if (p == 0)
			out.print(coef);
		else {
			if (coef != 1) {
				out.print(coef);
				out.print("*");
			}
				
			out.print('n');
			
			if (p > 1)
				out.print("^" + p);
		}
	}
}