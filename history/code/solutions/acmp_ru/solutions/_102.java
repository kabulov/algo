import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		in = new StreamTokenizer(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		int[][] v = new int[3][2];
		for (int i = 0; i < 3; i++) {
			v[i][0] = next();
			v[i][1] = next();
		}
		
		int x = next();
		int y = next();
		
		int s1 = side(v[0][0], v[0][1], v[1][0], v[1][1], x, y);
		int s2 = side(v[1][0], v[1][1], v[2][0], v[2][1], x, y);
		int s3 = side(v[2][0], v[2][1], v[0][0], v[0][1], x, y);
		
		int buf = 0;
		if (s1 == 0)
			buf++;
		if (s2 == 0)
			buf++;
		if (s3 == 0)
			buf++;
		
		boolean in = false;

		switch(buf) {
		case 0:
			if (Math.abs(s1 + s2 + s3) == 3)
				in = true;
			break;
		case 1:
			if (Math.abs(s1 + s2 + s3) == 2)
				in = true;
			break;
		case 2:
			in = true;
			break;
		}
		
		if (in)
			out.println("In");
		else
			out.println("Out");
		
		out.close();
	}
	
	static int side(int x1, int y1, int x2, int y2, int x, int y) {
		int a = y2 - y1;
		int b = x1 - x2;
		int c = x2 * y1 - x1 * y2;
		int tmp = a * x + b * y + c;
		
		if (tmp > 0)
			return 1;
		else
		if (tmp < 0)
			return -1;
		
		return 0;
	}
	
	static StreamTokenizer in;
	static int next() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}

