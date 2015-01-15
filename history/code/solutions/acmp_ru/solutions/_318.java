import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int n = in.nextInt();
		
		int len = 0;
		int[] vect = new int[32]; 
		
		while (n > 0) {
			vect[len++] = n % 2;
			n /= 2;
		}
		
		int pos = 0;
		while (vect[pos] == 0)
			pos++;
		
		int amt = 0;
		while (vect[pos] == 1) {
			vect[pos] = 0;
			amt++;
			pos++;
		}
		
		vect[pos] = 1;
		amt--;
		for (int i = 0; i < amt; i++)
			vect[i] = 1;
		
		if (pos == len)
			len++;
		
		long ans = 0;
		for (int i = len - 1; i >= 0; --i)
			ans = ans * 2 + vect[i];
		
		out.println(ans);
		out.close();
	}
}
