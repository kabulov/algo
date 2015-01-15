import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		in = new StreamTokenizer(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int n = nextInt();
		int k = nextInt();
		int t = nextInt();
		
		int[] time = new int[n];
		int[] price = new int[n];
		int[] width = new int[n];
		
		for (int i = 0; i < n; i++)
			time[i] = nextInt();
		
		for (int i = 0; i < n; i++)
			price[i] = nextInt();
		
		for (int i = 0; i < n; i++) 
			width[i] = nextInt();
		
		int min, tmp;
		for (int i = 0; i < n - 1; i++) {
			min = i;
			for (int j = i + 1; j < n; j++)
				if (time[min] > time[j]) {
					min = j;
				}
			
			tmp = time[min];
			time[min] = time[i];
			time[i] = tmp;
			
			tmp = price[min];
			price[min] = price[i];
			price[i] = tmp;
			
			tmp = width[min];
			width[min] = width[i];
			width[i] = tmp;
		}
		
		int p = 0;
		for (int i = 0; i < n; i++)
			if (time[i] < width[i])
				++p;
			else {
				time[i - p] = time[i];
				price[i - p] = price[i];
				width[i - p] = width[i];
			}
		n -= p;		
				
		int[] fst = new int[k + 1];
		int[] scd = new int[k + 1];
		int[] vect;
		
		Arrays.fill(fst, 0);
		Arrays.fill(scd, 0);
		int gang = 0, tm = 1;
		
		while (tm <= t) {
			fst[0] = Math.max(scd[0], scd[1]);
			fst[k] = Math.max(scd[k], scd[k - 1]);
			
			for (int i = 1; i < k; i++) 
				fst[i] = Math.max(scd[i], Math.max(scd[i - 1], scd[i + 1])); 

			while (gang < n && time[gang] == tm) {
				fst[width[gang]] += price[gang];
				gang++;
			}
			
			vect = fst;
			fst = scd;			
			scd = vect;
			
			tm++;
		}

		int answer = 0;
		for (int i = 0; i <= k; i++) {
			if (scd[i] > answer)
				answer = scd[i];
		}
		
		out.println(answer);
		out.close();
	}
	
	static StreamTokenizer in;
	
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}
