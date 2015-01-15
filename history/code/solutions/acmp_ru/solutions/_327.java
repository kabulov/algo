import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		in = new StreamTokenizer(new FileReader("input.txt"));
//		Scanner in = new Scanner(new File("input.txt"));
		out = new PrintWriter("output.txt");

		int n = nextInt();
		boolean happy;
		
		int ticket;
		for (int i = 0; i < n; i++) {
			happy = false;
			
			ticket = nextInt();
			if (ticket != 999999 && isHappy(ticket + 1))
				happy = true;
			if (ticket > 0 && isHappy(ticket - 1))
				happy = true;
			
			if (happy)
				out.println("Yes");
			else
				out.println("No");
		}
		
		out.close();
	}
	
	static boolean isHappy(int n) {
		return ((n / 100000)) + ((n / 10000) % 10) + ((n / 1000) % 10) == (((n % 1000) / 100) + ((n % 100) / 10) + (n % 10));
	}
	
	static PrintWriter out;
	
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
	
	static StreamTokenizer in;
}