import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		in = new StreamTokenizer(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		int len = solve(nextInt());
		out.println(answer + "\n" + len);		
		out.close();
	}
	
	static int tmp, answer;
	static int solve(int n) {
		int d1, d2, d3,d4, buf = n;
		d4 = n % 10;
		n /= 10;
		d3 = n % 10;
		n /= 10;
		d2 = n % 10;
		n /= 10;
		d1 = n;
		
		if (d2 > d1) {
			tmp = d1;
			d1 = d2;
			d2 = tmp;
		}
		
		if (d3 > d1) {
			tmp = d1;
			d1 = d3;
			d3 = tmp;
		}
		
		if (d4 > d1) {
			tmp = d1;
			d1 = d4;
			d4 = tmp;
		}
		
		if (d3 > d2) {
			tmp = d2;
			d2 = d3;
			d3 = tmp;
		}
		
		if (d4 > d2) {
			tmp = d2;
			d2 = d4;
			d4 = tmp;
		}
		
		if (d4 > d3) {
			tmp = d3;
			d3 = d4;
			d4 = tmp;
		}
		//d1 >= d2 >= d3 >= d4
		
		int a = ((d1 * 10 + d2) * 10 + d3) * 10 + d4;
		int b = ((d4 * 10 + d3) * 10 + d2) * 10 + d1;

		if (a - b == buf) {
			answer = buf;
			return 0;
		} else
			return 1 + solve(a - b);
	}
	
	static StreamTokenizer in;
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}