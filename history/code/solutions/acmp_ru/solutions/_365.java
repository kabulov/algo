import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		out = new PrintWriter("output.txt");
		Scanner in = new Scanner(new File("input.txt"));
		
		int n = in.nextInt();
		vect = new int[n];
		for (int i = 1; 2 * i <= n; i++) {
			len = 1;
			vect[0] = i;
			solve(i, n - i);
		}
		
		out.close();
	}
	
	private static void solve(int low, int sum) {
		while (low <= sum - low) {
			vect[len++] = low;
			solve(low, sum - low);
			len--;
			low++;
		}
		
		for (int i = 0; i < len; i++)
			out.print(vect[i] + "+");
		out.println(sum);
	}

	static int len;
	static int[] vect;
	static PrintWriter out;
}