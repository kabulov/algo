import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int n = in.nextInt();
		int ans = 0;
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < i; j++)
				in.nextInt();
			
			for (int j = i; j < n; j++)
				if (in.nextInt() == 1)
					++ans;
		}
		
		out.println(ans);
		out.close();
	}
}