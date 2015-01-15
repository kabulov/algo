import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("input.txt")); 
		PrintWriter out = new PrintWriter("output.txt");
		
		int n = Integer.parseInt(in.readLine());
		
		int[] mask = new int[n];
		for (int i = 0; i < n; ++i) 
			mask[i] = str2mask(in.readLine().split("[.]"));
		
		int fst, scd, amt;		
		int m = Integer.parseInt(in.readLine());
		String[] buf;
		
		for (int i = 0; i < m; ++i) {
			buf = in.readLine().split(" ");
			fst = str2mask(buf[0].split("[.]"));
			scd = str2mask(buf[1].split("[.]"));
			
			amt = 0;
			for (int j = 0; j < n; ++j)
				if ((fst & mask[j]) == (scd & mask[j])) ++amt;
			
			out.println(amt);
		}
		
		out.close();
	}
	
	static int str2mask(String[] src) {
		int mask = 0;
		for (int i = 0; i < 4; ++i) {
			int part = Integer.parseInt(src[3 - i]);
			for (int j = 0; j < 8; ++j) {
				mask |= (part & 1) << (8 * i + j);
				part >>>= 1;
			}				
		}
		return mask;
	}
}
