import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
//		in = new StreamTokenizer(new FileReader("input.txt"));
		Scanner in = new Scanner(new File("input.txt"));
		out = new PrintWriter("output.txt");
		
		int n = in.nextInt();
		int k = in.nextInt();
		
		String[] vect = new String[n];
		for (int i = 1; i <= n; i++)
			vect[i - 1] = new Integer(i).toString();
		
		ran = new Random();
		sort(vect, 0, vect.length - 1);
		for (int i = 0; i < n; i++)
			if (Integer.parseInt(vect[i]) == k) {
				out.println(i + 1);
				break;
			}
		
		out.close();
	}
	
	static String tmp;
	static String mid;
	static Random ran;
	
	static void sort(String[] vect, int l, int r) {
		int i = l, j = r;
		mid = vect[l + ran.nextInt(r - l + 1)];
		while (i <= j) {
			while (vect[i].compareTo(mid) < 0) i++;
			while (mid.compareTo(vect[j]) < 0) j--;
			if (i <= j) {
				tmp = vect[i];
				vect[i] = vect[j];
				vect[j] = tmp;
				i++;
				j--;
			}
		}
		if (l < j) sort(vect, l, j);
		if (i < r) sort(vect, i, r);
	}
	
	static PrintWriter out;
	
//	static int nextInt() throws IOException {
//		in.nextToken();
//		return (int)in.nval;
//	}
	
//	static StreamTokenizer in;
}