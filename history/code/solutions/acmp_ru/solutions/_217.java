import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		in = new StreamTokenizer(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
	
		int m = next();
		int[] left = new int[m];
		int[] right = new int[m];
		
		for (int i = 0; i < m; i++) {
			left[i] = next();
			if (left[i] == 0)
				left[i] = 1;
			
			right[i] = next();
			if (right[i] == 0)
				right[i] = 1;
		}
			
		int min, buf;
		for (int i = 0; i < m - 1; i++) {
			min = i;
			for (int j = i + 1; j < m; j++)
				if (right[j] < right[min])
					min = j;
			
			buf = right[min];
			right[min] = right[i];
			right[i] = buf;
			
			buf = left[min];
			left[min] = left[i];
			left[i] = buf;
		}
		
		int n = next();
		int[] crd = new int[n + 1];
		crd[0] = Integer.MIN_VALUE;
		for  (int i = 1; i <= n; i++)
			crd[i] = next();
		
		int sol = 0;
		
		int[][] map = new int[m][n + 1];
		for (int i = 0; i < m; i++)
			map[i][0] = 0;
		
		int k, p, lft;
		for (int i = 1; i <= n; i++) 
			for (int j = 0; j < m; j++) {
				map[j][i] = Integer.MIN_VALUE;
				
				lft = crd[i] - left[j] + 1;
				k = 0;
				p = i - 1;
				
				while (k < m) {
					while (crd[p] + right[k] - 1 >= crd[i] || lft <= crd[p])
						--p;
					
					if (map[k][p] + 1 > map[j][i])
						map[j][i] = map[k][p] + 1;
					
					++k;
				}
				
				if (map[j][i] > sol)
					sol = map[j][i];
				if (map[j][i - 1] > map[j][i])
					map[j][i] = map[j][i - 1];
			}
				
		
		out.println(sol);
		out.close();
	}
	
	static StreamTokenizer in;
	static int next() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}
