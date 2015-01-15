import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		boolean[] vect = new boolean[20002];
		Arrays.fill(vect, false);
		
		int base = 10000;
		int n = in.nextInt();
		
		for (int i = 0; i < n; ++i)
			vect[base + in.nextInt()] = true;
		
		boolean flag = false;
		for (int i = -base; i <= base;) { 
		
			if (vect[base + i]) {
				if (flag) out.print(", ");
				flag = true;
				
				if (vect[base + i + 1] == false) {
					out.print(i);
				} else 
				if (vect[base + i + 2] == false) {
					out.print(i + ", " + (i + 1));
					
					i += 2;
					continue;
				} else
				if (vect[base + i + 3] == false) {
					out.print(i + ", ");
					out.print((len(i + 1) > 3 ? "..." : (i + 1)));
					out.print(", " + (i + 2));
					
					i += 3;
					continue;
				} else {
					int j = i + 3;
					while (vect[base + j + 1]) ++j;
					out.print(i + ", ..., " + j);
					
					i = j + 1;
					continue;
				}
			}
			
			++i;
		}
		
		out.println();
		
		in.close();
		out.close();
	}
	
	static int len(int i) {
		int ans = i < 0 ? 1 : 0;
		while (i != 0) {
			ans++;
			i /= 10;
		}
		return ans;
	}
}