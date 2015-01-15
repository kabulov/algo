import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		in = new StreamTokenizer(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
		int[] rank = new int[3050];
		int cstep = 0, lstep;
		Arrays.fill(rank, 0);
		
		while (true) {
			in.nextToken();
			if (in.ttype != StreamTokenizer.TT_NUMBER)
				break;
			
			int n = (int)in.nval;
			
			int cur = nextInt();
			int prev;
			
			if (n == 1) {
				out.println("Jolly");
				continue;
			}
			
			lstep = ++cstep;
			boolean jolly = true;

			for (int i = 1; i < n; ++i) {
				prev = cur;
				cur = nextInt();
				
				if (!jolly) continue;
				
				int dif = Math.abs(cur - prev);
				if (dif == 0 || dif >= n) {
					jolly = false;
					continue;
				}
					
				boolean cell = rank[dif] < lstep ? false : true;
				if (cell) {
					jolly = false;
				} else {
					rank[dif] = ++cstep;
				}
			}
			
			out.println(jolly ? "Jolly" : "Not jolly");
		}
		
		out.close();
	}
	
	static StreamTokenizer in;
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;		
	}
}

