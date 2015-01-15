import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt"); 
		
		int n = in.nextInt();
		int m = in.nextInt();
		
		inp = new Inp[n];
		for (int i = 0; i < n; ++i) {
			in.next();
			inp[i] = new Inp();
			inp[i].p = i;
			inp[i].d = (int)(10000 * Double.parseDouble(in.next()));
		}
		
		for (int i = 0; i < n - 1; ++i) {
			int mp = i;
			for (int j = i + 1; j < n; ++j)
				if (inp[j].d < inp[mp].d)
					mp = j;
			
			if (mp != i) {
				Inp tmp = inp[i];
				inp[i] = inp[mp];
				inp[mp] = tmp;
			}
		}
		
		char[] v = in.next().toCharArray();	//!!! timelim
		int[] chars = new int[26];
		for (int i = 0; i < 26; ++i) chars[i] = 0;
		for (int i = 0; i < m; ++i) ++chars[v[i] - 'a'];	 
		
		mid = new Mid[n]; 
		int len = 0;
		for (int i = 0; i < 26; ++i) 
			if (chars[i] != 0) {
				mid[len] = new Mid();
				mid[len].c = (char)('a' + i);
				mid[len].d = chars[i];
				++len;
			}
			
		for (int i = 0; i < len - 1; ++i) {
			int mp = i;
			for (int j = i + 1; j < len; ++j) 
				if (mid[j].d < mid[mp].d)
					mp = j;
			
			if (mp != i) {
				Mid tmp = mid[i];
				mid[i] = mid[mp];
				mid[mp] = tmp;
			}
		}
		
		outp = new Outp[n];
		for (int i = 0; i < n; ++i) outp[i] = new Outp();
		
		for (int amt = 0, pos = 0; amt + len < n; ++amt) {
			while (chars[pos] != 0) ++pos;
			outp[amt].c = (char)('a' + pos);
			++pos;
		}			
			
		for (int i = 0; i < n; ++i) {
			outp[i].p = inp[i].p;
			if (i >= (n - len)) outp[i].c = mid[i - (n - len)].c;
		}
		
		for (int i = 0; i < n - 1; ++i) {
			int mp = i;
			for (int j = i + 1; j < n; ++j)
				if (outp[j].p < outp[mp].p)
					mp = j;
			
			if (mp != i) {
				Outp tmp = outp[i];
				outp[i] = outp[mp];
				outp[mp] = tmp;
			}
		}
		
		for (int i = 0; i < n; ++i) 
			out.println(outp[i].c);
		
		out.close();
	}
	
	static Inp[] inp;
	static Mid[] mid;
	static Outp[] outp;
}

class Inp {
	int p;
	int d;
}

class Mid {
	char c;
	int d;
}

class Outp {
	int p;
	char c;
}
