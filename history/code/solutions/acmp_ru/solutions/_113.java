import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
	public static void main(String[] args) throws IOException {
//		in = new StreamTokenizer(new FileReader("input.txt"));
//		Scanner in = new Scanner(new File("input.txt"));
		BufferedReader in = new BufferedReader(new FileReader("input.txt"));
		out = new PrintWriter("output.txt");

		int n  = Integer.parseInt(in.readLine());
		
		String line;
		int answer = 0;
		
		int[][] map = new int[n + 1][n + 1];
		for (int i = 0; i <= n; i++)
			map[i][0] = map[0][i] = 0;
		
		for (int i = 1; i <= n; i++) {
			line = in.readLine();
			for (int j = 1; j <= n; j++)
				if (line.charAt(j - 1) == '1') {
					map[i][j] = 1 + Math.min(map[i - 1][j - 1], Math.min(map[i - 1][j], map[i][j - 1]));
					if (map[i][j] > answer)
						answer = map[i][j];
				} else {
					map[i][j] = 0;
				}
		}
		
		out.println(answer * answer);
		out.close();
	}
	
	static PrintWriter out;
	
//	static int nextInt() throws IOException {
//		in.nextToken();
//		return (int)in.nval;
//	}
	
//	static StreamTokenizer in;
}