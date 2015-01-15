import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		String a = in.next();
		String b = in.next();
		
		if (a.length() != b.length()) {
			out.println("No");
			out.close();
			return;
		}
		
		if (a.equals(b)) {
			out.println("Yes");
			out.println(0);
			out.close();
			return;
		}
		
		if (a.length() == 1) {
			out.println("No");
			out.close();
			return;
		}
		
		StringBuilder ra = new StringBuilder(a).reverse();
		
		char[] av = a.toCharArray();
		char[] rav = ra.toString().toCharArray();
		char[] bv = b.toCharArray();
		
		int[] pb = new int[b.length()]; 
		pb[0] = 0;
		
		for (int i = 1, j = 0; i < pb.length; ++i) {
			while (j > 0 && bv[j] != bv[i]) 
				j = pb[j - 1];
			if (bv[j] == bv[i]) ++j;
			pb[i] = j;
		}
		
		int[] pa = new int[a.length()];
		
		for (int i = 0, j = 0; i < pa.length; ++i) {
			while (j > 0 && bv[j] != av[i])
				j = pb[j - 1];
			if (bv[j] == av[i]) ++j;
			pa[i] = j;
		}
		
		int len = 0;
		for (int i = rav.length - 1; i >= 0 && rav[i] == bv[i]; --i, ++len); 
			
		if (pa[pa.length - 1] + len >= pa.length) {
			out.println("Yes");
			out.println(pa.length - pa[pa.length - 1]);
		} else {
			out.println("No");
		}
		
		out.close();
	}	
}
