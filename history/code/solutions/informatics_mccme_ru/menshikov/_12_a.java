import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		in = new StreamTokenizer(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int a = nextInt();
		int b = nextInt();
		int c = nextInt();
		
		in.nextToken();
		long n = (long)in.nval;
		
		if (n <= 3) {
			switch((int)n){
			case 1:
				out.println(a);
				break;
			case 2:
				out.println(b);
				break;
			case 3:
				out.println(c);
				break;
			}
			out.close();
			return;
		}
		
		int tmp;
		if (n <= 1000) {
			for (int i = 4; i <= n; i++) {
				tmp = (a + b + c) % 10;
				a = b;
				b = c;
				c = tmp;
			}
			out.println(c);
		} else {
			int[][][] map = new int[10][10][10];
			for (int i = 0; i < 10; i++)
				for (int j = 0; j < 10; j++)
					for (int k = 0; k < 10; k++)
						map[i][j][k] = -1;
			
			int ctr = 0;
			while (map[a][b][c] == -1) {
				map[a][b][c] = ++ctr;
				tmp = (a + b + c) % 10;
				a = b;
				b = c;
				c = tmp;
			}
			++ctr;
			
			n -= 2;
			n = (n - map[a][b][c]) % (ctr - map[a][b][c]);
			for (int i = 1; i <= n; i++) {
				tmp = (a + b + c) % 10;
				a = b;
				b = c;
				c = tmp;
			}
			
			out.println(c);
		}
		
		out.close();
	}
	
	static StreamTokenizer in;
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}