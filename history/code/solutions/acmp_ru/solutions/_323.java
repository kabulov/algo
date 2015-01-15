import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
//		in = new StreamTokenizer(new FileReader("input.txt"));
		Scanner in = new Scanner(new File("input.txt"));
		out = new PrintWriter("output.txt");

		int n = in.nextInt();
		
		boolean[] prime = new boolean[1000];
		for (int i = 2; i < 1000; i++)
			prime[i] = true;
		
		for (int i = 2; i < 1000; i++)
			if (prime[i]) {
				for (int j = i; j + i < 1000; j += i)
					prime[j + i] = false;
			}
		
		int prm = 0;
		for (int i = 2; i < 1000; i++)
			if (prime[i])
				prm++;
		
		int[] prmvect = new int[prm];
		int tmp = 0;
		for (int i = 2; i < 1000; i++)
			if (prime[i])
				prmvect[tmp++] = i;
		
outer:	for (int i = 0; i < prm; i++)
			for (int j = i; j < prm; j++)
				if (prmvect[i] + prmvect[j] == n) {
					out.println(prmvect[i] + " " + prmvect[j]);
					break outer;
				}
			
		out.close();
	}

	static PrintWriter out;
	
//	static int nextInt() throws IOException {
//		in.nextToken();
//		return (int)in.nval;
//	}
	
//	static StreamTokenizer in;
}