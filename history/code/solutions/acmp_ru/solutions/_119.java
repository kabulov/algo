import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		in = new StreamTokenizer(new FileReader("input.txt"));	
		PrintWriter out = new PrintWriter("output.txt");

		int n = nextInt();
		time[] v = new time[n];
		for (int i = 0; i < n; i++) 
			v[i] = new time(nextInt(), nextInt(), nextInt());
		
		Arrays.sort(v);
		
		for (int i = 0; i < n; i++)
			out.println(v[i].h + " " + v[i].m + " " + v[i].s);
		
		out.close();
	}
	
	static StreamTokenizer in;
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}

class time implements Comparable<time> {
	int h, m, s, t;
	time(int h, int m, int s) {
		this.h = h;
		this.m = m;
		this.s = s;
		t = 3600 * h + 60 * m + s;
	}
	
	public int compareTo(time obj) {
		return t - obj.t;
	}
}
