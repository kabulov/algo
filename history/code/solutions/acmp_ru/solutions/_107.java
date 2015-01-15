
import static java.lang.Math.max;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;


public class Main implements Runnable {
	public static void main(String[] args) {
		new Thread(new Main()).start();
	}

	BufferedReader in;
	PrintWriter out;
	
	public void run() {
		try {
			in = new BufferedReader(new FileReader("input.txt"));
			out = new PrintWriter("output.txt");
			solve();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		} finally {
			out.close();
		}
	}
	
	int answer;
	char[] v;
	
	int[] size;
	int len;
	
	StringBuilder str;
	int cnt;
	
	char[][] template = {
			{'a','a'},
			{'a','b','a'},
			{'a','a','b'},
			{'a','b','b'},
			{'a','a','a'},
			{'a','b','a','c'},
			{'b','a','c','a'},
			{'a','b','a','b'},
			{'a','a','b','b'},
			{'a','b','b','a'},
			{'b','a','a','a'},
			{'a','b','a','a'},
			{'a','a','b','a'},
			{'a','a','a','b'},
			{'a','a','a','a'}
	};
	
	int[] pnt = {2, 2, 2, 2, 3, 2, 2, 3, 3, 4, 3, 3, 3, 3, 5};
	
	boolean match(int t, int sz, int pos) {
		for (int i = 1; i < sz; ++i) 
			for (int j = 0; j < i; ++j) { 
				if (template[t][j] == template[t][i] && v[pos + j] != v[pos + i]) 
					return false;				
				if (template[t][j] != template[t][i] && v[pos + j] == v[pos + i]) 
					return false;
			}
		return true;
	}
	
	int find(int lt, int rt, int sz, int pos) {
		int mx = 0;
		for (int i = lt; i <= rt; ++i) {
			if (match(i, sz, pos)) 
				mx = Math.max(mx, pnt[i]);
		}
//		if (mx == 0)return -1000;
		return mx;
	}
	
	void count() {
		for (int i = 0, p = 0; i < len; ++i) {
			//if (bad) cnt += -1000;
			if (size[i] == 2) {
				cnt += find(0, 0, 2, p);
			} else
			if (size[i] == 3) {
				cnt += find(1, 4, 3, p);
			} else
			if (size[i] == 4) {
				cnt += find(5, 14, 4, p);
			}
			p += size[i];
		}
	}
	
	void check() {
		cnt = 0;
		count();
		if (cnt > answer) {
			answer = cnt;
			StringBuilder cur = new StringBuilder();
			for (int i = 0, p = 0; i < len; ++i) {
				for (int j = 0; j < size[i]; ++j) {
					cur.append(v[p++]);
				}
				if (i + 1 < len) cur.append("-");
			}
			str = cur;
		}
	}
	
	void offer(int pos, int amt) {
		if (pos == 7) {
			size[len++] = amt; 
			check();
			--len;
			return;
		}
		if (amt >= 4) return;//==
		++amt;
		offer(pos + 1, amt);
		if (2 <= amt && 6 - pos >= 2) {
			size[len++] = amt;
			offer(pos + 1, 0);
			--len;
		}
	}
	
	void gen(int p) {
		if (p == 7) {
			len = 0;
			offer(0, 0);
			return;
		}
		gen(p + 1);
		for (int i = p + 1; i < 7; ++i) {
			swap(p, i);
			gen(p + 1);
			swap(p, i);
		}
	}
	
	void swap(int i, int j) {
		char c = v[i];
		v[i] = v[j];
		v[j] = c;
	}
	
	void solve() throws IOException {		
		v = in.readLine().toCharArray();
		str = new StringBuilder();	//
		for (int i = 0; i < v.length; ++i) str.append(v[i]);
		size = new int[10];
		answer = 0;
		len = 0;
		offer(0, 0);
		//gen(0);
		out.println(str);
		out.println(answer);
	}
	
}
