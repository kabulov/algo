import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		int[] t, v;
		t = new int[4];
		v = new int[4];
		
		for (int i = 0; i < 4; ++i) {
			t[i] = in.nextInt();
			v[i] = in.nextInt();
		}
		
		int tpov = in.nextInt();
		int d = in.nextInt();
		
		int[][] pos = {
			{1, 1},
			{1, -1},
			{-1, -1},
			{-1, 1}
		};
		
		for (int i = 0; i < 4; ++i) 
			t[i] = t[i] * v[i] + d;
		
		for (int i = 0; i < 3; ++i) {
			int m = i;
			for (int j = i + 1; j < 4; ++j)
				if (t[m] * v[j] > t[j] * v[m])
					m = j;
			
			if (m != i) {
				int tmp = t[i];
				t[i] = t[m];
				t[m] = tmp;
				
				tmp = v[i];
				v[i] = v[m];
				v[m] = tmp;
				
				int[] buf = pos[i];
				pos[i] = pos[m];
				pos[m] = buf;
			}
		}
		
		int s = 1;
		for (int i = 1; i < 4; ++i) {
			int time = tpov;
			if (pos[i][0] * pos[i - 1][0] < 0 && pos[i][1] * pos[i - 1][1] < 0) time += tpov;			
			if (t[i] * v[i - 1] - v[i] * t[i - 1] < time * v[i] * v[i - 1]) break;
			++s;
		}
		
		out.println(s == 4 ? "ALIVE" : s);
		out.close();
	}
}