import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt"); 

		int k1, m, k2, p2, n2;
		k1 = in.nextInt();
		m = in.nextInt();
		k2 = in.nextInt();
		p2 = in.nextInt();
		n2 = in.nextInt();
		
		if (contradiction(k1, m, k2, p2, n2)) {
			out.println("-1 -1");
		} else {
			int t = m * (p2 - 1) + (n2 - 1);
			
			if (t == 0) {
				if (k1 <= k2) 
					out.println("1 1");
				else
					out.println((k1 <= k2 * m ? "1" : "0") + " " + (m == 1 ? "1" : "0"));			///!!!!!! k1 <= k2...
			} else {
				int r = (k2 - 1) / t;
				int l = (k2 - 1) / (t + 1) + 1;
				
				if ((k1 - 1) / (l * m) != (k1 - 1) / (r * m))
					out.print("0 ");
				else
					out.print(((k1 - 1) / (l * m) + 1) + " ");
				
				if (m == 1) 
					out.println(1);
				else {
					int n1 = ((k1 - 1) % (m * l) + 1 - 1) / l + 1;
					for (int i = l + 1; i <= r; ++i)
						if (((k1 - 1) % (m * i) + 1 - 1) / i + 1 != n1) {
							n1 = 0;
							break;
						}
					
					out.println(n1);
				}
			}
		}
		
		out.close();
	}
	
	static boolean contradiction(int k1, int m, int k2, int p2, int n2) {
		if (n2 > m) return true;
		int t = m * (p2 - 1) + (n2 - 1);
		if (t > 0) {
			if (t >= k2) return true;
			if (k2 % t != 0 && k2 / t * (t + 1) < k2) return true;		//(k2 - 1) / t
			if (k2 % t == 0 && (k2 / t - 1) * (t + 1) < k2) return true;
		}
		return false;
	}
}
