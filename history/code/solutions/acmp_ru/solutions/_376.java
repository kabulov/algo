import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		out = new PrintWriter("output.txt");
		in = new StreamTokenizer(new FileReader("input.txt"));
		
		int bday = nextInt();
		int bmon = nextInt();
		
		int d = nextInt();
		int m = nextInt();
		int y = nextInt();
		
		int answer = 0;
		while (!(d == bday && m == bmon)) {
			++d;
			if (d > 31) {
				d = 1;
				++m;
				if (m > 12) {
					m = 1;
					++y;
				}
			} else
			if (d > 30) {
				if (m == 4 || m == 6 || m == 9 || m == 11) {
					d = 1;
					++m;
				}
			} else
			if (d > 29 && m == 2) {
				d = 1;
				m = 3;
			} else
			if (d > 28 && m == 2 && !isLeap(y)) {
				d = 1;
				m = 3;
			}
			++answer;
		}
		
		out.println(answer);
		out.close();
	}
	
	static boolean isLeap(int y) {
		return (y % 400 == 0) || (y % 4 == 0 && y % 100 != 0);
	}
	
	static PrintWriter out;
	static StreamTokenizer in;
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}
