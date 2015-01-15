import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int l = in.nextInt();
		int r = in.nextInt();
		
		int ans = 0, tmp, mul;
		
		for (int i = l; i <= r; ++i) {
			tmp = i;
			mul = 1;
			
			while (tmp > 0) {
				mul *= tmp % 10;
				tmp /= 10;
			}
			
			if (mul != 0 && i % mul == 0) ++ans;
		}
		
		out.println(ans);
		out.close();
	}
}