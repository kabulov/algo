import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
//		2^(p-1) * (2^p - 1);
//		System.out.println((1L << 31) * ((1L << 32) - 1) + "\n" + Long.MAX_VALUE);

		int len = 0;
		long[] perfect = new long[8];
//		for (long i = 2; i <= 32; i++) {
//			//if (isPrime(2^p - 1))
//			//vect[len++] = 2^(p-1) * (2^p - 1);
//			if (isPrime((1L << i) - 1))
//				perfect[len++] = (1L << (i - 1)) * ((1L << i) - 1);
//		}
		
//		out.println(len);
//		for (int i = 0; i < len; i++)
//			out.println("perfect[" + i + "] = " + perfect[i] + ";");
		
		perfect[0] = 6;
		perfect[1] = 28;
		perfect[2] = 496;
		perfect[3] = 8128;
		perfect[4] = 33550336;
		perfect[5] = 8589869056L;
		perfect[6] = 137438691328L;
		perfect[7] = 2305843008139952128L;

		long left = in.nextLong();
		long right = in.nextLong();
		
		boolean have = false;
		for (int i = 0; i < 8; i++)
			if (left <= perfect[i] && perfect[i] <= right) 
				have = true;
		
		if (!have)
			out.println("Absent");
		else
			for (int i = 0; i < 8; i++)
				if (left <= perfect[i] && perfect[i] <= right) 
					out.println(perfect[i]);
					
		out.close();
	}
	
//	static boolean isPrime(long arg) {
//		for (long div = 2; div * div < arg; ++div)
//			if (arg % div == 0)
//				return false;
		
//		return true;
//	}
}