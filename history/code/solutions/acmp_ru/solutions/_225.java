import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		PrintWriter out = new PrintWriter("output.txt");
		in = new StreamTokenizer(new FileReader("input.txt"));
		
		int n = nextInt();
		int[][] vect = new int[3][3];
		vect[0][0] = nextInt();
		vect[0][1] = nextInt();
		vect[0][2] = nextInt();
		
		if (n == 1) {
			out.println(vect[0][0]);
			out.close();
			return;
		}
		
		vect[1][0] = nextInt();
		vect[1][1] = nextInt();
		vect[1][2] = nextInt();
		
		if (n == 2) {
			out.println(Math.min(vect[0][0] + vect[1][0], vect[0][1]));
			out.close();
			return;
		}
		
		int[] map = new int[n + 1];
		map[0] = 0;
		map[1] = vect[0][0];
		map[2] = Math.min(vect[0][0] + vect[1][0], vect[0][1]);
	
		vect[2][0] = nextInt();
		vect[2][1] = nextInt();
		vect[2][2] = nextInt();
		
		map[3] = Math.min(Math.min(vect[2][0] + map[2], vect[1][1] + map[1]), vect[0][2]);
		
		for (int i = 3; i < n; i++) {
			vect[0][0] = vect[1][0];
			vect[0][1] = vect[1][1];
			vect[0][2] = vect[1][2];
			
			vect[1][0] = vect[2][0];
			vect[1][1] = vect[2][1];
			vect[1][2] = vect[2][2];
			
			vect[2][0] = nextInt();
			vect[2][1] = nextInt();
			vect[2][2] = nextInt();
			
			map[i + 1] = Math.min(Math.min(vect[2][0] + map[i], vect[1][1] + map[i - 1]), vect[0][2] + map[i - 2]);
		}
		
		out.println(map[n]);
		out.close();
	}
	
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
	
	static StreamTokenizer in;
}