import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Stack;


public class Main {
	public static void main(String[] argv) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int n = Integer.parseInt(in.readLine());
		map = new elem[9][26];
		
		int i, j;
		String[] buf;
		
		int len;
		buffer = new char[256];
		for (int k = 0; k < n; k++) {
			buf = in.readLine().split("[=]");
			j = buf[0].trim().charAt(0) - 'A';
			i = buf[0].trim().charAt(1) - '0' - 1;
			
			buflen = 0;
			len = buf[1].length();
			for (int p = 0; p < len; ++p)
				if (buf[1].charAt(p) != ' ')
					buffer[buflen++] = buf[1].charAt(p);
			
			map[i][j] = new elem(buffer, buflen);
		}
		
		for (i = 0; i < 9; i++)
			for (j = 0; j < 26; j++)
				if (map[i][j] == null) {
					map[i][j] = new elem(buffer, 0);
				}
		
		if (map[0][0].state == -1)
			calc(0, 0);
		
		if (map[0][0].state == -1)
			out.println(1000000);
		else
			out.println(map[0][0].val);
		
		out.close();
	}
	
	static boolean good = true;
	static elem[][] map;
	
	static void calc(int i, int j) {
		map[i][j].state = 0;

		buffer = map[i][j].expr;
		buflen = buffer.length;
		
		pos = 0;
		cur = buffer[0];
		
		map[i][j].val = analyze();
	
		map[i][j].state = good ? 1 : -1;
	}
	
	static long analyze() {
		long ans = addend();
		
		while (good && cur != '\0') 
			if (cur == '+') {
				cur = buffer[++pos];
				ans += addend();
			} else 
			if (cur == '-') {
				cur = buffer[++pos];
				ans -= addend();
			} else
				break;
		
		return ans;
	}
	
	static long addend() {
		long ans = 1;
		if (cur == '-') {
			ans = -1;
			cur = buffer[++pos];
		}
		
		ans *= term();
		while (good && cur != '\0') 
			if (cur == '*') {
				cur = buffer[++pos];
				ans *= term();
			} else 
			if (cur == '/') {
				cur = buffer[++pos];
				long temp = term();
				if (temp == 0)
					ans = 0;
				else
					ans /= temp;
			} else
				break;
		
		return ans;
	}
	
	static long term() {
		if (cur == '(') {
			cur = buffer[++pos];
			long ans = analyze();
			
			//')'
			if (good) {//not important
				++pos;
				cur = pos == buflen ? '\0' : buffer[pos];
			}
			
			return ans;
		} else 
			if ('0' <= cur && cur <= '9') {
				long ans = 0;
				while ('0' <= cur && cur <= '9') {
					ans = ans * 10 + cur - 48;
					++pos;
					cur = pos == buflen ? '\0' : buffer[pos];
				}
				return ans;
			} else {
				int j, i;
				j = cur - 'A';
				cur = buffer[++pos];
				i = cur - '0' - 1;
				++pos;
				cur = pos == buflen ? '\0' : buffer[pos];

				switch(map[i][j].state) {
				case -1:
					int position = pos;
					char[] current = buffer;
					
					calc(i, j);
					
					//!good
					if (map[i][j].state == -1)
						return 0; // anything
						
					pos = position;
					buffer = current; //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
					
					buflen = buffer.length;
					cur = pos == buflen ? '\0' : buffer[pos];
					
					return map[i][j].val;
				case 0:
					good = false;
					return 0;//return anything
				case 1:
					return map[i][j].val;
				default:
					return Long.MIN_VALUE;//absurd
				}
			}
	}
	
	static int pos;
	static char cur;
	static int buflen;
	static char[] buffer;
}

class elem {
	char[] expr;
	long val;
	int state;
	
	elem(char[] v, int len) {
		if (len == 0) {
			state = 1;
			val = 0;
			//expr = null;
		} else {
			expr = new char[len];
			for (int i = 0; i < len; i++) 
				expr[i] = v[i];
			state = -1; //-1 not calculated; 0 - in process; 1 - calculated
			//val = Nan
		}
	}
}