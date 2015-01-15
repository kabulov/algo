import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("input.txt"));
		out = new PrintWriter("output.txt");
		
		n = Integer.parseInt(in.readLine());
		
		int size = 0;
		fraction[] list = new fraction[n * n];
		for (int den = 2; den <= n; ++den)
			for (int num = 1; num < den; ++num)
				if (gcd(num, den) == 1)
					list[size++] = new fraction(num, den);
		
		fraction[] sortedlist = new fraction[size];
		for (int i = 0; i < size; ++i)
			sortedlist[i] = list[i];
		
		Arrays.sort(sortedlist);
		
		for (int i = 0; i < size; ++i)
			out.println(sortedlist[i].num + "/" + sortedlist[i].den);
		
		out.close();
	}
	
	static int gcd(int a, int b) {
		return b == 0 ? a : gcd(b, a % b);
	}
	
	static PrintWriter out;
	static int n;
}

class fraction implements Comparable<fraction> {
	int num, den;
	fraction(int n, int d) {
		num = n;
		den = d;
	}

	public int compareTo(fraction f) {
		return num * f.den - den * f.num;
	}
}