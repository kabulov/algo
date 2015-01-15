import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		out = new PrintWriter("output.txt");
		in = new StreamTokenizer(new FileReader("input.txt"));

		int day = nextInt();
		int mon = nextInt();
		
		int d = 31;
		int m = 12;
		
		int[] month = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		
		boolean[][] map = new boolean[13][32];
		//true - proigrish
		for (int i = 1; i < 13; i++)
			for (int j = 1; j < 32; j++)
				map[i][j] = true;
		
		while (!(d == day && m == mon)) {
			if (--d == 0) {
				d = month[--m];
			}
			
			if (map[m][d]) {
				if (d > 1)
					map[m][d - 1] = false;
				if (d > 2)
					map[m][d - 2] = false;
				if (m > 1 && d <= month[m - 1])
					map[m - 1][d] = false;
				if (m > 2 && d <= month[m - 2])
					map[m - 2][d] = false;
			}
		}
		
		if (map[m][d])
			out.println(2);
		else
			out.println(1);
		
		out.close();
	}
	
	static PrintWriter out;
	static StreamTokenizer in;
	
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}