import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		Pattern len = Pattern.compile("^[0-9]+");
		Pattern unit = Pattern.compile("[a-z]+$");
		
		int n = Integer.parseInt(in.readLine());
		v = new int[n];
		p = new int[n];
		
		int lgt;
		String str;
		
		Matcher mat;
		for (int i = 0; i < n; ++i) {
			str = in.readLine();
			
			mat = len.matcher(str);
			mat.find();
			lgt = Integer.parseInt(mat.group());
			
			mat = unit.matcher(str);
			mat.find();
			lgt *= convert(mat.group());
			
			v[i] = lgt;
			p[i] = i + 1;
		}
		
		rand = new Random();
		sort(0, n - 1);
		
		int pos = n;
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
	
	static int convert(String str) {
		if (str.charAt(0) == 'k') {
			if (str.charAt(1) == 'm')
				return 1000;
			return 1852;
		}
		
		if (str.charAt(0) == 'u')
			return 33;
		
		if (str.charAt(0) == 'z')
			return 3;
		
		if (str.charAt(0) == 's')
			return 38;
		
		if (str.charAt(0) == 'm' && str.length() > 1)
			return 1609;
		
		return 1;
	}
}
