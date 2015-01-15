import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		PrintWriter out = new PrintWriter("output.txt");
		in = new StreamTokenizer(new FileReader("input.txt"));
		
		int n = nextInt();
		int[] vect = new int[n + 1];
		vect[0] = 0;
		for (int i = 1; i <= n; i++) {
			vect[i] = nextInt();
		}
	
		Arrays.sort(vect);
		
		int[] ans = new int[n + 1];
		ans[2] = vect[2] - vect[1];
		if (n > 2) {
			ans[3] = vect[3] - vect[1];
		}
		
		int j = 3, tmp;
		for (int i = 4; i <= n; i++) {
			ans[i] = Math.max(ans[j - 1], vect[i] - vect[j]);//Math.min(vect[i] - vect[1], Math.max(ans[j - 1], vect[i] - vect[j]));
			if ((tmp = Math.max(ans[i - 2], vect[i] - vect[i - 1])) < ans[i]) { //<= || < -> doesn`t matter
				ans[i] = tmp;
				j = i - 1;
			}
		}
		
		out.println(ans[n]);
		out.close();
	}
	
	static StreamTokenizer in;
	
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}