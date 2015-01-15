import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		out = new PrintWriter("output.txt");
		
		inp = in.next().toCharArray();
		len = inp.length;
		
		if (len < 5) {
			for (char x : inp) out.print(x);
			out.close();
			return;
		}
		
		initfactor();
		
		map = new int[len][len];
		restore = new int[len][len];
		
		//restore not initialized
		
		for (int i = 0; i < len; i++) 
			map[i][i] = 1;
		
		solve();
		
		print(0, len - 1);
		
		out.close();
	}
	
	static void print(int lt, int rt) {					//must lt <= rt
		if (lt == rt) {
			out.print(inp[lt]);
			return;
		}
		
		if (restore[lt][rt] <= 0) {					//(<= 0) but not (< 0)
			restore[lt][rt] *= -1;
			print(lt, restore[lt][rt]);
			print(restore[lt][rt] + 1, rt);
		} else {  
			int amt = (rt - lt + 1) / restore[lt][rt];
			out.print(amt);
			out.print('(');
			print(lt, lt + restore[lt][rt] - 1);
			out.print(')');
		}
	}
	
	static PrintWriter out;
	
	static void solve() {
		int lt, rt;
		for (int sz = 2; sz <= len; ++sz) 
			for (lt = 0, rt = lt + sz - 1; rt < len; ++lt, ++rt) {
				map[lt][rt] = map[lt][lt] + map[lt + 1][rt];
				restore[lt][rt] = -lt;
				
				int tmp;
				for (int p = lt + 1; p < rt; ++p)
					if ((tmp = map[lt][p] + map[p + 1][rt]) < map[lt][rt]) {
						map[lt][rt] = tmp;
						restore[lt][rt] = -p;
					}
				
				int buf;
				for (int p = 1; p <= factor[sz][0]; ++p) 
					if (equal(lt, rt + 1, buf = factor[sz][p])) {				//lt, rt + 1, ... -->> but not rt
						tmp = 2 + numlen(sz / buf) + map[lt][lt + buf - 1];
						if (tmp < map[lt][rt]) {
							map[lt][rt] = tmp;
							restore[lt][rt] = buf;
						}
					}
			}
	}
	
	static int numlen(int n) {														//must n > 0
		int w = 0;
		while (n > 0) {
			++w;
			n /= 10;
		}
		return w;
	}

	static int[][] restore;
	static int[][] map;
	
	static boolean equal(int l, int r, int sz) {									//optimize , maybe
		for (int i = l + sz; i < r; i += sz)
			for (int j = 0; j < sz; j++)
				if (inp[l + j] != inp[i + j])
					return false;
				
		return true;
	}
	
	static char[] inp;

	static void initfactor() {
		factor = new int[len + 1][52]; 												// no problem anymore
		
		//factor[0], factor[1] not initialized
		for (int i = 2; i <= len; i++) {
			factor[i][0] = 0;
			for (int p = i; p > 1; --p)
				if (i % p == 0) { 
					++factor[i][0];
					factor[i][factor[i][0]] = i / p;
				}
		}
	}
	
	static int len;
	static int[][] factor;
}