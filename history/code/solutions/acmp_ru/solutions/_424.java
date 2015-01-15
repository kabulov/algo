import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		long n = Long.parseLong(in.readLine());
		int ans = 0;
		while (n > 1) {
			if (n % 9 == 0)
				n /= 9;
			else
				n = n / 9 + 1;
			ans++;
			if (n > 1) {
				if (n % 2 == 0)
					n /= 2;
				else
					n = n / 2 + 1;
				ans++;
			}
		}
		
		if (ans % 2 == 1)
			out.println("Stan wins.");
		else
			out.println("Ollie wins.");
		
		out.close();
	}
}
