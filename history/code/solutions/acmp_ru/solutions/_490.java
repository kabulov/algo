import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		String fst = in.next();
		String scd = in.next();
		
		String[] buf = fst.split("[.]");
		int d1 = Integer.parseInt(buf[0]);
		int m1 = Integer.parseInt(buf[1]);
		int y1 = Integer.parseInt(buf[2]);
		
		buf = scd.split("[.]");
		int d2 = Integer.parseInt(buf[0]);
		int m2 = Integer.parseInt(buf[1]);
		int y2 = Integer.parseInt(buf[2]);
		
		int[] mon = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		
		int ans = 0;
		while (!(d1 == d2 && m1 == m2 && y1 == y2)) {
			d1++;
			if (d1 > mon[m1 - 1]) {
				d1 = 1;
				m1++;
				if (m1 > 12) {
					m1 = 1;
					y1++;
				}
			}
			ans++;
		}
		
		out.println(ans);
		out.close();
	}
}
