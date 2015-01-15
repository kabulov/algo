import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		in = new StreamTokenizer(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int n = nextInt();
		nextInt();
		nextInt();		

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
				
		int answer = 0;
		if (n > 0) {
			int[] sol = new int[n];
			sol[0] = answer = price[0];
			
			int max;
			for (int i = 1; i < n; i++) {
				max = 0;
				
				for (int j = 0; j < i; j++)
					if (time[i] - time[j] >= Math.abs(width[i] - width[j]) && sol[j] > max)
						max = sol[j];
				
				sol[i] = max + price[i];
				
				if (sol[i] > answer)
					answer = sol[i];
			}
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
