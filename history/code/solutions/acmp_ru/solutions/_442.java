import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws IOException {
		char[] inp = new char[(int)(new File("input.txt").length())]; 
		new FileReader(new File("input.txt")).read(inp);
		PrintWriter out = new PrintWriter("output.txt"); 

		int[] v = new int[51];
		Arrays.fill(v, 0);
		
		Stack<Integer> st = new Stack<Integer>(); 
		
		int pos = 0;
		int cur = 10;

		char[] tag = new char[5000];
		int len;
		
		while (pos < inp.length) {
			if (inp[pos] == '<') {
				len = 0;
				while (++pos < inp.length && inp[pos] != '>') tag[len++] = inp[pos];
				if (pos == inp.length) break;
				
				if (equals(tag, len, "/font")) 
					cur = st.pop();
				else {
					int font = getFont(tag, len);
					if (font != Integer.MAX_VALUE) {
						st.push(cur);
						if (flag) 
							cur += font;
						else
							cur = font;
					}
				}
				
				++pos;
			} else {
				if (!isTab(inp[pos])) ++v[cur];						
				++pos;
			}
		}
		
		for (int i = 1; i < 51; ++i) 
			if (v[i] > 0) 
				out.println(i + " " + v[i]);
		
		out.close();
	}	

	static boolean flag;
	
	static int getFont(char[] v, int len) {
		int bad = Integer.MAX_VALUE;
		if (len > 10 && equals(v, 10, "font size=")) {
			int l = 11;
			int r = 11;
			while (r < len && v[r] != '\"') ++r;
			if (r == len || r == l) return bad;
			if (r + 1 != len) return bad;
			int n = 0;
			boolean neg = false;			
			if (v[l] == '-' || v[l] == '+') {
				flag = true;
				if (v[l] == '-') neg = true;
				++l;
				if (l == r) return bad;
			} else
				flag = false;
			for (int i = l; i < r; ++i) {
				if (!('0' <= v[i] && v[i] <= '9')) return bad;
				n = n * 10 + v[i] - '0';
			}			
			if (neg) n = -n;
			return n;
		}
		
		return bad;
	}
	
	static boolean equals(char[] v, int len, String str) {
		if (len != str.length()) return false;
		for (int i = 0; i < len; ++i)
			if (v[i] != str.charAt(i))
				return false;
		
		return true;
	}
	
	static boolean isTab(char c) {
		return c == 9 || c == 10 || c == 13 || c == 32; 
	}
}
