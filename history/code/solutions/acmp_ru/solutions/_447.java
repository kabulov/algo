import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int n = Integer.parseInt(in.readLine());
		int five = 0, two = 0;
		
		int tmp = 5;
		while (n / tmp > 0) {
			five += n / tmp;
			tmp *= 5;
		}
		
		tmp = 2;
		while (n / tmp > 0) {
			two += n / tmp;
			tmp *= 2;
		}
		
		int ans = 1;
		for (int i = 2; i <= n; i++) {
			tmp = i;
			while (tmp % 2 == 0)
				tmp /= 2;
			
			while (tmp % 5 == 0)
				tmp /= 5;
			
			ans = (ans * tmp) % 10;
		}
		
		for (int i = 0; i < two - five; i++)
			ans = (ans * 2) % 10;
		
		out.println(ans);
		out.close();
	}
}
