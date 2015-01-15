import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
	
		String str = in.readLine();
		int n = Integer.parseInt(str.split(" ")[0]);
		int s = Integer.parseInt(str.split(" ")[1]);
		
		if (s < n || s > 6 * n) {
			out.println(0);
		} else		
		if (n == 1) {
			out.println(1.0 / 6.0);
		} else {
			double[][] map = new double[n][s + 6];		
			
			for (int i = 0; i < n; i++)
				for (int j = 0; j < s + 6; j++)
					map[i][j] = 0;
		
			int min = Math.min(s + 5, 6 + 5);
			for (int i = 6; i <= min; i++)
				map[0][i] = 1.0 / 6.0;
			
			double sum;
			for (int i = 1; i < n; i++) {
				if (5 + (i + 1) * 6 < 5 + s)
					min = 5 + (i + 1) * 6;
				else
					min = 5 + s;
				
				sum = 0;
				for (int j = 6 + i; j <= min; j++) {
					sum = sum - map[i - 1][j - 7] + map[i - 1][j - 1];
					map[i][j] = sum / 6;
				}
			}
			
			out.println(map[n - 1][5 + s]);
		}
		
		out.close();
	}
}