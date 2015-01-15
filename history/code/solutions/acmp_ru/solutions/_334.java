
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.regex.Pattern;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("input.txt")); 
		PrintWriter out = new PrintWriter("output.txt");

		int n = Integer.parseInt(in.readLine());
		
		String[] buf;
		Pattern pat = Pattern.compile("[:]");
		int[] v = new int[n];
		
		for (int i = 0, h, m, s; i < n; ++i) {
			buf = pat.split(in.readLine());
			h = Integer.parseInt(buf[0]) - 1;
			m = Integer.parseInt(buf[1]);
			s = Integer.parseInt(buf[2]);
			v[i] = h * 3600 + m * 60 + s;
		}
		
		Arrays.sort(v);
		
		long mn = 0, cur;
		int ans = 0, cst = 12 * 3600, i = 0;
		while (i < n && v[i] == 0) ++i;
		for (int k = i; k < n; ++k) mn += cst - v[k]; 
		cur = mn;
		
		int j = i;
		for (i = 1; i < cst; ++i) {
			cur += n;
			while (j < n && v[j] == i) {
				cur -= cst;
				++j;
			}
			if (cur < mn) {
				mn = cur;
				ans = i;
			}
		}
		
		Calendar cal = Calendar.getInstance();
		cal.set(0, 0, 0, 0, (ans / 60) % 60, ans % 60);		
		out.printf("%d:%tM:%tS", ans / 3600 + 1, cal, cal);
		
		out.close();
	}	
}