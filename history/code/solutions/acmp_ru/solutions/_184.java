import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		int n = Integer.parseInt(in.readLine());
		
		note[] v = new note[n];
		
		String[] buf, tmp;
		for (int i = 0; i < n; i++) {
			buf = in.readLine().split(" ");
			tmp = buf[1].split("[:]");
			buf = buf[0].split("[.]");
			v[i] = new note(Integer.parseInt(buf[0]), Integer.parseInt(buf[1]), Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1]));
		}
		
		rand = new Random();
		sort(v, 0, n - 1);
		
		int[] mon = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		int[] sum = new int[12];
		sum[0] = mon[0];
		for (int i = 1; i < 12; i++)
			sum[i] += sum[i - 1] + mon[i];
		
		note f, s;
		int ans = 0;
		for (int i = 0; i < n; i += 2) {
			f = v[i];
			s = v[i + 1];
			
			if (f.mon == s.mon) {
//				if (f.d == s.d) {
//					ans += s.h * 60 + s.min - (f.h * 60 + f.min);
//				} else {
//					ans += (s.d - f.d) * 8 * 60 + (s.h - 10) * 60 + s.min - ((f.h - 10) * 60 + f.min);
//				}
				ans += (s.d - f.d) * 8 * 60 + (s.h - 10) * 60 + s.min - ((f.h - 10) * 60 + f.min);
			} else {
				ans += (sum[s.mon - 1] - sum[f.mon - 1] - mon[s.mon - 1]) * 8 * 60;
				ans += (s.d - 1) * 8 * 60 + (s.h - 10) * 60 + s.min;
				ans += (mon[f.mon - 1] - f.d + 1) * 8 * 60 - ((f.h - 10) * 60 + f.min);
			}
			
//			++ans; //or ->
		}
		
		ans += n / 2; //-> or
		
		out.println(ans / 60 + ":" + (ans % 60 < 10 ? "0" : "") + ans % 60);
		out.close();
	}
	
	static Random rand;
	static note mid, tmp;
	static void sort(note[] v, int l, int r) {
		int i = l, j = r;
		mid = v[l + rand.nextInt(r - l + 1)];
		while (i <= j) {
			while (v[i].compare(mid) < 0) ++i;
			while (mid.compare(v[j]) < 0) --j;
			if (i <= j) {
				tmp = v[i];
				v[i] = v[j];
				v[j] = tmp;
				++i;
				--j;
			}
		}
		if (l < j) sort(v, l, j);
		if (i < r) sort(v, i, r);
	}
}

class note {
	int d, mon, h, min;
	note(int day, int month, int hour, int minute) {
		d = day;
		mon = month;
		h = hour;
		min = minute;
	}
	
	int compare(note obj) {
		if (mon != obj.mon)
			return mon - obj.mon;
		
		if (d != obj.d)
			return d - obj.d;
		
		if (h != obj.h)
			return h - obj.h;
		
		return min - obj.min;		
	}
}