
import static java.lang.Math.max;
import static java.lang.Math.min;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.regex.Pattern;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		String str = in.readLine();		
		String[] buf = str.split(" ");
		
		int n = Integer.parseInt(buf[0]), k = Integer.parseInt(buf[1]), cst = 24 * 60 * 60;
		int[] st = new int[k], et = new int[k];		
		LinkedList<pair> que = new LinkedList<pair>();
		
		Pattern pat = Pattern.compile("[: ]");
		
		Calendar calS = Calendar.getInstance();  
		Calendar calE = Calendar.getInstance();  
				
		pair cur;
		for (int loop = 0, time = 0, intime = 0, delta, mid, tmp; loop < n; loop += k) {
			for (int i = 0; i < k; ++i) {
				buf = pat.split(in.readLine());
				mid = Integer.parseInt(buf[0]) * 3600 + Integer.parseInt(buf[1]) * 60 + Integer.parseInt(buf[2]);
				tmp = intime % cst;
				if (tmp < mid) {
					delta = mid - tmp;
				} else {
					delta = cst - tmp + mid;
				}
				intime += delta;
				que.add(new pair(i, Integer.parseInt(buf[3])));
			}			
			
			time = max(time, intime);
			Arrays.fill(st, -1);
			
			while (que.size() > 0) {
				cur = que.poll();
				if (st[cur.p] == -1) st[cur.p] = time % cst;
				delta = min(cur.d, 10);
				time += delta;
				cur.d -= delta;
				if (cur.d == 0) {
					et[cur.p] = time % cst;
				} else {
					que.add(cur);
				}
			}
			
			for (int i = 0; i < k; ++i) {
				calS.set(0, 0, 0, st[i] / 3600, (st[i] / 60) % 60, st[i] % 60);
				calE.set(0, 0, 0, et[i] / 3600, (et[i] / 60) % 60, et[i] % 60);
				out.printf("%tH:%tM:%tS %tH:%tM:%tS\n", calS, calS, calS, calE, calE, calE);
			}
		}
		
		out.close();
	}	
}

class pair {
	int p, d;
	pair(int pos, int dur) {
		p = pos;
		d = dur;
	}
}