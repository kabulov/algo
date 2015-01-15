import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int n = in.nextInt();
		int m = in.nextInt();
		
		int[] v = new int[26];
		Arrays.fill(v, 0);
		
		for (int i = 0; i < n; ++i) {
			String str = in.next();
			for (int j = 0; j < n; ++j)
				++v[str.charAt(j) - 'A'];
		}
		
		for (int i = 0; i < m; ++i) {
			String str = in.next();
			int len = str.length();
			for (int j = 0; j < len; ++j)
				--v[str.charAt(j) - 'A'];
		}
		
		for (int i = 0; i < 26; ++i) 
			if (v[i] != 0) {
				char c = (char)('A' + i);
				for (int j = 0; j < v[i]; ++j) out.print(c);
			}
		
		out.close();
	}
}

/*import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		n = in.nextInt();
		int m = in.nextInt();
		
		inp = new char[n][n];
		map = new int[n][n];
		
		for (int i = 0; i < n; ++i) {
			String tmp = in.next();
			for (int j = 0; j < n; ++j) {
				inp[i][j] = tmp.charAt(j);
				map[i][j] = 1;
			}
		}
		
		if (m > 0) {
			word = new String[m];
			for (int i = 0; i < m; ++i) word[i] = in.next();		
	
			len = new int[m];
			for (int i = 0; i < m; ++i) len[i] = word[i].length();
			
			amt = new int[m];
			for (int i = 0; i < m; ++i) amt[i] = 0;
			
			for (int k = 0; k < m; ++k) {
				for (int i = 0; i < n; ++i) {
					for (int j = 0; j < n; ++j) 
						if (offer(i, j, 0, k)) 
							++amt[k];
				}
				if (len[k] > 1 && word[k].charAt(0) == word[k].charAt(len[k] - 1)) amt[k] /= 2;
			}
			
			if (m > 1) {
				rand = new Random();
				sort(0, m - 1);
			}
			
			for (int k = 0; k < m; ++k) {
outer:			for (int i = 0; i < n; ++i) {
					for (int j = 0; j < n; ++j) 
						if (map[i][j] == 1 && erase(i, j, 0, k)) 
							break outer;
				}

//				for (int i = 0; i < n; ++i) {
//					System.out.println();
//					for (int j = 0; j < n; ++j) {
//						System.out.print(map[i][j]);
//					}
//				}			
			}
		}

		int s = 0;
		for (int i = 0; i < n; ++i)
			for (int j = 0; j < n; ++j)
				s += map[i][j];
		
		if (s > 0) {
			char[] v = new char[s];
			for (int i = 0, p = 0; i < n; ++i)
				for (int j = 0; j < n; ++j)
					if (map[i][j] == 1)
						v[p++] = inp[i][j];
		
			Arrays.sort(v);
			for (int i = 0; i < s; ++i) out.print(v[i]);
		}
		
		out.close();
	}
	
	static boolean erase(int i, int j, int p, int k) {
		if (inp[i][j] != word[k].charAt(p)) return false;
		
		map[i][j] = 0;
		++p;
		
		if (p < len[k]) {
			if (i > 0 && map[i - 1][j] == 1 && erase(i - 1, j, p, k)) return true;
			if (j > 0 && map[i][j - 1] == 1 && erase(i, j - 1, p, k)) return true;
			if (i + 1 < n && map[i + 1][j] == 1 && erase(i + 1, j, p, k)) return true;
			if (j + 1 < n && map[i][j + 1] == 1 && erase(i, j + 1, p, k)) return true;
			
			map[i][j] = 1;
			return false;
		}
		
		return true;		
	}
	
	static boolean offer(int i, int j, int p, int k) {
		if (inp[i][j] != word[k].charAt(p)) return false;
		
		map[i][j] = 0;
		++p;
		
		if (p < len[k]) {
			if (i > 0 && map[i - 1][j] == 1 && offer(i - 1, j, p, k)) {
				map[i][j] = 1;
				return true;
			}
			if (j > 0 && map[i][j - 1] == 1 && offer(i, j - 1, p, k)) {
				map[i][j] = 1;
				return true;
			}
			if (i + 1 < n && map[i + 1][j] == 1 && offer(i + 1, j, p, k)) {
				map[i][j] = 1;
				return true;
			}
			if (j + 1 < n && map[i][j + 1] == 1 && offer(i, j + 1, p, k)) {
				map[i][j] = 1;
				return true;
			}
			
			map[i][j] = 1;
			return false;
		}
		
		map[i][j] = 1;		
		return true;
	}
	
	static int n;
	static int[] len, amt;
	static String[] word;
	static char[][] inp;
	static int[][] map;

	static void sort(int l, int r) {
		int i = l, j = r;
		int mid = amt[l + rand.nextInt(r - l + 1)];
		while (i <= j) {
			for (;amt[i] < mid; ++i);
			for (;mid < amt[j]; --j);
			if (i <= j) {
				tmp = amt[i];
				amt[i] = amt[j];
				amt[j] = tmp;
				
				tmp = len[i];
				len[i] = len[j];
				len[j] = tmp;
				
				buf = word[i];
				word[i] = word[j];
				word[j] = buf;
				
				++i;
				--j;
			}
		}
		if (l < j) sort(l, j);
		if (i < r) sort(i, r);
	}
	
	static int tmp;
	static String buf;
	static Random rand;	
}
*/