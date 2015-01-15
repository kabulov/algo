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
		int best = 0, sumbest = 0;
		
		int tmp, buf;
		for (int i = 1; i * i <= n; i++) 
			if (n % i == 0) {
				tmp = sum(i);
				if (tmp > sumbest || (tmp == sumbest && i < best)) {
					best = i;
					sumbest = tmp;
				}			
				buf = n / i;
				tmp = sum(buf);
				if (tmp > sumbest || (tmp == sumbest && buf < best)) {
					best = buf;
					sumbest = tmp;
				}
			}
		
		out.println(best);
		out.close();
	}
	
	static int sum(int num) {
		int result = 0;
		
		while (num > 0) {
			result += num % 10;
			num /= 10;
		}
		
		return result;
	}
	
	static StreamTokenizer in;
	
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}
