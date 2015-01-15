import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		out = new PrintWriter("output.txt");
		in = new StreamTokenizer(new FileReader("input.txt"));
		
		explen = nextInt();
		stlen = explen / 2;
		expr = new char[explen];
		stack = new char[stlen];
		
		oplen = top = 0;
		
		solve(0);
		
		out.close();
	}

	static void solve(int pos) {
		if (pos == explen) {
			for (int i = 0; i < explen; i++) 
				out.print(expr[i]);
			out.println();
			return;
		}
		
		if (oplen == stlen) {
			char c = stack[--top];
			if (c == '[') {
				expr[pos] = ']';
			} else {
				expr[pos] = ')';
			}
			solve(pos + 1);
			stack[top++] = c;
			return;
		}
		
		if (top > 0) {
			char c = stack[--top];
			if (c == '[') {
				expr[pos] = ']';
			} else {
				expr[pos] = ')';
			}
			solve(pos + 1);
			stack[top++] = c;
		}
		
		oplen++;
		top++;
		
		stack[top - 1] = '[';
		expr[pos] = '[';
		solve(pos + 1);
		
		stack[top - 1] = '(';
		expr[pos] = '(';
		solve(pos + 1);
		
		top--;
		oplen--;
	}
	
	static char[] stack;
	static int stlen;
	static int top;

	static char[] expr;
	static int explen;
	static int oplen;
	
	static PrintWriter out;
	
	static StreamTokenizer in;
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}
