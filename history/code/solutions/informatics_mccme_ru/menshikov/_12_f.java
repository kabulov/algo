import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
	
		init();
		
		left = right = in.readLine();
		prepare();
		formula();
		
		for (int i = 0; i < 26; i++)
			for (int j = 0; j < 27; j++)
				ltmtx[i][j] = rtmtx[0][i][j];
		
		int n = Integer.parseInt(in.readLine());
		
		for (int i = 0; i < n; i++) {
			right = in.readLine();
			prepare();
			formula();
			
			out.print(left);
			out.print((equal() ? "==" : "!="));
			out.println(right);
		}
		
		out.close();
	}
	
	static void element(int p) {
		if (cur == '(') {
			isPoint[p] = false;
			nextChar();
			sequence(p);
			nextChar();//')'
		} else 
		if (upperAlpha(cur)){//alpha cur
			isPoint[p] = true;
			getElem(p);
		}
	}
	
	static void sequence(int p) {
		for (int i = 0; i < 26; i++)
			for (int j = 0; j < 27; j++)
				rtmtx[p][i][j] = 0;
		
		element(p + 1);
		int coef = digit(cur) ? nextInt() : 1;
		
		if (isPoint[p + 1]) {
			rtmtx[p][vi[p + 1]][vj[p + 1]] += coef;
		} else 
			add(p, p + 1, coef);
		
		while (cur == '(' || upperAlpha(cur)) {
			element(p + 1);
			coef = digit(cur) ? nextInt() : 1;
			
			if (isPoint[p + 1]) {
				rtmtx[p][vi[p + 1]][vj[p + 1]] += coef;
			} else 
				add(p, p + 1, coef);
		}
	}
	
	static void formula() {
		for (int i = 0; i < 26; i++)
			for (int j = 0; j < 27; j++)
				rtmtx[0][i][j] = 0;
		
		int coef = digit(cur) ? nextInt() : 1;
		
		sequence(1);
		add(0, 1, coef);
		
		while(cur == '+') {
			nextChar();
			coef = digit(cur) ? nextInt() : 1;
			sequence(1);
			add(0, 1, coef);
		}
	}
	
	static void getElem(int p) {
		vi[p] = cur - 'A';
		nextChar();
		if (lowerAlpha(cur)) {
			vj[p] = cur - 'a' + 1;
			nextChar();
		} else
			vj[p] = 0;
	}
	
	static int nextInt() {
		int integer = 0;
		while (digit(cur)) {
			integer = integer * 10 + cur - '0';
			nextChar();
		}
		return integer;
	}
	
	static void nextChar() {
		++pos;
		cur = pos >= len ? '\0' : expr[pos];
	}
	
	static boolean digit(char c) {
		return '0' <= c && c <= '9';
	}
	
	static boolean lowerAlpha(char c) {
		return 'a' <= c && c <= 'z';
	}
	
	static boolean upperAlpha(char c) {
		return 'A' <= c && c <= 'Z';
	}
	
	static void prepare() {
		len = right.length();
		for(int i = 0; i < len; i++)
			expr[i] = right.charAt(i);
		
		pos = 0;
		cur = expr[0];
		
		for (int i = 0; i < 26; i++)
			for (int j = 0; j < 27; j++)
				rtmtx[0][i][j] = 0;
	}
	
	static String left, right;
	
	static int pos;
	static int len;
	static char cur;
	static char[] expr;

	static void add(int a, int b, int c) {
		for (int i = 0; i < 26; i++)
			for (int j = 0; j < 27; j++)
				rtmtx[a][i][j] += c * rtmtx[b][i][j];
	}
	
	static void init() {
		vi = new int[101];
		vj = new int[101];
		isPoint = new boolean[101];

		ltmtx = new int[26][27];
		rtmtx = new int[51][26][27]; //!!! new int[50] ... ->50 is not enough
		
		expr = new char[101];
	}
	
	static boolean equal() {
		for (int i = 0; i < 26; i++)
			for (int j = 0; j < 27; j++)
				if (ltmtx[i][j] != rtmtx[0][i][j])
					return false;
		
		return true;
	}

	static int[] vi, vj;
	static int[][][] rtmtx;//left  matrix
	static boolean[] isPoint;

	static int[][] ltmtx;//right matrix
}