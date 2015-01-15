import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		int n = in.nextInt();
		
		if (n < 3) {
			out.println(-1);
			out.close();
			return;
		}
		
		int[] len = new int[n];
		int[] pos = new int[n];
		
		for (int i = 0; i < n; i++) {
			pos[i] = i + 1;
			len[i] = in.nextInt();
		}
			
		int tmp, min;
		for (int i = 0; i < n - 1; i++) {
			min = i;
			for (int j = i + 1; j < n; j++)
				if (len[j] < len[min])
					min = j;
			
			tmp = len[min];
			len[min] = len[i];
			len[i] = tmp;
			
			tmp = pos[min];
			pos[min] = pos[i];
			pos[i] = tmp;
		}
		
		boolean can = false;
		tmp = n;
		while (tmp > 2) {
			if (len[tmp - 3] + len[tmp - 2] > len[tmp - 1]) {
				can = true;
				double a = len[tmp - 3];
				double b = len[tmp - 2];
				double c = len[tmp - 1];
				double p = a + b + c;
				double area = p * (p - 2 * a) * (p - 2 * b) * (p - 2 * c) / 16;
				out.println(Math.sqrt(area));
				out.println(pos[tmp - 3] + " " + pos[tmp - 2] + " " + pos[tmp - 1]);
				break;
			}
			--tmp;
		}
		
		if (!can) 
			out.println(-1);
		
		out.close();
	}
}