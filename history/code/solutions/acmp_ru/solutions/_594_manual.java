import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int n = Integer.parseInt(in.readLine());
		v = new int[n];
		p = new int[n];
		
		int lgt;
		String str;
		
		char c;
		int pos;
		for (int i = 0; i < n; ++i) {
			str = in.readLine();
			
			lgt = pos = 0;
			while ('0' <= (c = str.charAt(pos++)) && c <= '9') 
				lgt = lgt * 10 + c - 48;
			
			--pos;
			switch(str.charAt(pos)) {
			case 'm':
				if (pos + 1 == str.length())
					pos = 1;
				else
					pos = 1609;
				break;
			case 'k':
				if (str.charAt(pos + 1) == 'm')
					pos = 1000;
				else
					pos = 1852;
				break;
			case 'u':
				pos = 33;
				break;
			case 'z':
				pos = 3;
				break;
			case 's':
				pos = 38;
				break;
			}
			
			v[i] = lgt * pos;
			p[i] = i + 1;
		}
		
		rand = new Random();
		sort(0, n - 1);
		
		pos = n;
		while (v[pos - 3] + v[pos - 2] <= v[pos - 1]) 
			--pos; // existence is guaranteed
		
		out.println(area(v[pos - 3], v[pos - 2], v[pos - 1]) / 4);
		out.println(p[pos - 3] + " " + p[pos - 2] + " " + p[pos - 1]);
		
		out.close();
	}

	static double area(double a, double b, double c) {
		double p = a + b + c;
		return Math.sqrt(p * (p - 2 * a) * (p - 2 * b) * (p - 2 * c) / 16);
	}
	
	static int[] v, p;
	
	static Random rand;
	static int mid, buf;
	
	static void sort(int l, int r) {
		int i = l, j = r;
		mid = v[l + rand.nextInt(r - l + 1)];
		for (; i <= j;) {
			for (; v[i] < mid; ++i);
			for (; mid < v[j]; --j);
			if (i <= j) {
				buf = v[i];
				v[i] = v[j];
				v[j] = buf;
				
				buf = p[i];
				p[i] = p[j];
				p[j] = buf;
				
				++i;
				--j;
			}
		}
		if (l < j) sort(l, j);
		if (i < r) sort(i, r);
	}
}
