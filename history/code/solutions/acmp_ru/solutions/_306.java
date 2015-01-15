import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt"); 

		int n = in.nextInt();
		char[] v = new char[n];
		
		int fst, ofs;	
		int amt, c;
		int nchg, flag;
		
		fst = 0;
		ofs = 1;
		
		amt = n;
		c = 1;	//0 - 'R', 1 - 'B';
		
		nchg = 1;	//1 - true, 0 - false
		flag = 0;
		
		while (amt > 0) {
			if (nchg == 0) fst += ofs / 2;
			nchg = 0;

			ofs *= 2;
			
			if (amt == 1) {
				v[fst] = c == 0 ? 'R' : 'B';
				break;
			}
			
			for (int i = fst + (flag == 1 ? ofs / 2 : 0); i < n; i += ofs) {
				v[i] = c == 0 ? 'R' : 'B';
				c = 1 - c;
			}
			
			if (flag == 1) nchg = 1;
			
			if (amt % 2 == 0) {
				amt /= 2;
			} else {
				amt = amt / 2 + flag;
				flag = 1 - flag;
			}
		}
		
		for (int i = 0; i < n; ++i) out.print(v[i]);
		
		out.close();
	}
}
/*
if (amt % 2 == 0) {
  if (flag == 0) {
		amt /= 2;
		flag = 0;
	} else {
		amt /= 2;
		flag = 1;
	}
} else {
	if (flag == 0) {
		flag = 1;
		amt /= 2;
	} else {
		amt -= amt / 2;
		flag = 0;
	}
}
*/