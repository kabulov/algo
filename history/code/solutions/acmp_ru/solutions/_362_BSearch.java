import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		int ow, oh, kw, kh;
		
		ow = in.nextInt();
		oh = in.nextInt();
		kw = in.nextInt();
		kh = in.nextInt();
		
		int tmp;
		if (ow > oh) {
			tmp = ow;
			ow = oh;
			oh = tmp;
		}
		
		if (kw > kh) {
			tmp = kw;
			kw = kh;
			kh = tmp;
		}
		
		if (can(ow, oh, kw, kh)) 
			out.println("Possible");
		else
			out.println("Impossible");
		
		out.close();
	}

	static boolean can(int ow, int oh, int kw, int kh) {
		if (ow > kw)
			return false;
		
		if (oh <= kh)
			return true;
		
		if (oh <= kw) {
			if (ow <= kh)
				return true;

			return false;
		}
		
		double mid;
		double lt = 0;
		double rt = Math.PI / 2;
		
		while (Math.abs(rt - lt) >= eps) {
			mid = (lt + rt) / 2;
			if (oh * Math.cos(mid) + ow * Math.sin(mid) > kw)
				lt = mid;
			else
				rt = mid;
		}
		
		mid = (lt + rt) / 2;
		if (oh * Math.sin(mid) + ow * Math.cos(mid) > kh)
			return false;
		
		return true;
	}
	
	static double eps = 1e-14;
}
