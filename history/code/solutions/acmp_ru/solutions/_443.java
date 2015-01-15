import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		String str = in.readLine();
		String[] spt = str.split(" ");
		int n = Integer.parseInt(spt[0]);
		m = Integer.parseInt(spt[1]);
		x = Integer.parseInt(spt[2]);
		
		int[] map = new int[m];
		Arrays.fill(map, 0);
		
		for (int i = 0; i < n; i++) 
			++map[hash(in.readLine())];
			
		int answer = 0;
		for (int i = 0; i < m; i++)
			answer += map[i] * (map[i] - 1) / 2;
		
		out.println(answer);
		out.close();
	}
	
	static int m, x;
	
	static int hash(final String str) {
		int xi = 1;
		int ans = 0;
		int len = str.length();
		
		for (int i = 0; i < len; ++i) {
			ans = (ans + (str.charAt(i) - 48) * xi) % m;
			xi = (xi * x) % m;
		}
		
		return ans;
	}
}
