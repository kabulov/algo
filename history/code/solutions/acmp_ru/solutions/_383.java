import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int n = in.nextInt();		
		int ans = 0, cnt = 0;
		
		int l, s, m;
		while (cnt < n) {
			++ans;
			l = s = 0;
			m = ans;
			while (m > 0) {
				s += m % 10;
				m /= 10;
				++l;
			}
			if (s % l == 0) ++cnt;
		}
		
		out.println(ans);
		out.close();
	}	
}
