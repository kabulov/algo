import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int k = in.nextInt();
		int day = (1 + k) % 7;
		
		int p = 0;
		if (k >= 366) {
			k-= 366;
			if (k >= 365) k-= 365;
			p = 1;
		}
		
		int m = 1;
		for (int i = 0; mon[p][i] <= k; ++i ) {
			k -= mon[p][i];
			++m;
		}
		
		out.print(DAY[day]);
		out.print(", ");
		out.print(1 + k > 9 ? "" : "0");
		out.print(1 + k);
		out.print(".");
		out.print(m > 9 ? "" : "0");
		out.print(m);
		
		out.close();
	}
	
	static int[][] mon = {
		{31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31},
		{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}
	};
	
	static String[] DAY = {
		"Monday",
		"Tuesday",
		"Wednesday",
		"Thursday",
		"Friday",
		"Saturday",
		"Sunday"
	};
}