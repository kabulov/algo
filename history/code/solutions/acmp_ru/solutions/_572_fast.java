

import static java.lang.Math.min;
import static java.lang.Math.max;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.regex.Pattern;


public class Main implements Runnable {
	public static void main(String[] args) {
		new Thread(new Main()).start();
	}

//	BufferedReader in;
//	Scanner in;
	StreamTokenizer in;
	PrintWriter out;
	
	public void run() {
		try {
			in = new StreamTokenizer(new FileReader("input.txt"));
//			in = new Scanner(new File("input.txt"));
//			in = new BufferedReader(new FileReader("input.txt"));
			out = new PrintWriter("output.txt");
			solve();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		} finally {
			out.close();
		}
	}
	
	int next() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
	
	int n, au;
	trio[] vect;
	int velen;
	
	void solve() throws IOException {
		n = next();
		au = next();
		
		vect = new trio[n + 5];
		velen = 0;
		
		for (int i = 1; i <= n; ++i) {
			vect[velen++] = new trio(next(), next(), i);
		}
		
		ans = new int[n + 5];
		len = 0;
		
		int sz = 0;
		PriorityQueue<trio> que = new PriorityQueue<trio>();
		
		for (int i = 0; i < velen; ++i) 
			if (vect[i].s >= 0) {
				que.add(new trio(vect[i].f, vect[i].s, vect[i].p));
			} else {
				vect[sz++] = vect[i];
			}
		
		velen = sz;
		
		while (!que.isEmpty()) {
			trio tmp = que.poll();
			if (tmp.f > au) break;
			au += tmp.s;
			ans[len++] = tmp.p;
		}
		
		for (int i = velen - 1; i >= 0; --i) 
			if (vect[i].f > au) {
				--velen;
				vect[i] = vect[velen];
			}
		
		if (velen > 0) {
			offer();
		}
		
		out.println(len);
		for (int i = 0; i < len; ++i) out.print(ans[i] + " ");
	}
	
	void offer() {
		for (int i = 0; i < velen; ++i) {
			vect[i].s = -vect[i].s;
			vect[i].f = au - vect[i].f + vect[i].s;
		}
		
		Arrays.sort(vect, 0, velen);
		
		PriorityQueue<trio> que = new PriorityQueue<trio>();
		int time = 0;		
		
		for (int i = 0; i < velen; ++i) {
			time += vect[i].s;
			que.add(new trio(-vect[i].s, vect[i].f, vect[i].p));
			if (time > vect[i].f) {
				time += que.poll().f;
			}
		}
		
		PriorityQueue<trio> q = new PriorityQueue<trio>();
		while (!que.isEmpty()) {
			trio tmp = que.poll();
			q.add(new trio(tmp.s, -1, tmp.p));
		}
		
		while (!q.isEmpty())
			ans[len++] = q.poll().p;
	}
	
	int len;
	int[] ans;
}

class trio implements Comparable<trio>{
	int f, s, p;
	trio(int a, int b, int c) {
		f = a;
		s = b;
		p = c;
	}
	@Override
	public int compareTo(trio arg) {
		return f - arg.f;
	}
}