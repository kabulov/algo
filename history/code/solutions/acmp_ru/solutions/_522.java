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
		int m = in.nextInt();
		
		boolean[] fst = new boolean[64001];
		Arrays.fill(fst, false);
		
		for (int i = 0; i < n; i++)
			fst[32000 + in.nextInt()] = true;
		
		boolean[] scd = new boolean[64001];
		Arrays.fill(scd, false);
		
		for (int i = 0; i < m; i++)
			scd[32000 + in.nextInt()] = true;
		
		int answer = 1;
		for (int i = 0; i <= 64000; i++)
			if (fst[i] ^ scd[i]) {
				answer = 0;
				break;
			}
			
		out.println(answer);
		out.close();
	}
}