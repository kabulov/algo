import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;


public class Main {
	public static void main(String[] args) throws IOException {
		in = new StreamTokenizer(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int n = nextInt();
		vect = new int[100];
		
		for (int i = 0; i < n; i++)
			vect[i] = nextInt();
		
		sum = new int[n];
		sum[0] = vect[0];
		for (int i = 1; i < n; i++)
			sum[i] = sum[i - 1] + vect[i];
		
		matrix = new int[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				matrix[i][j] = - 1;
		
		for (int i = 0; i < n; i++)
			matrix[i][i] = vect[i];
		
		solve(0, n - 1);
		
		switch (sign(2 * matrix[0][n - 1] - getSum(0, n - 1))) {
		case 1:
			out.println(1);
			break;
		case -1:
			out.println(2);
			break;
		case 0:
			out.println(0);
			break;
		}
		
		out.close();
	}
	
	static void solve(int left, int right) {
		if (left == right)
			return;			
		
		if (matrix[left][right] != -1)
			return;
		
		if (matrix[left + 1][right] == -1)
			solve(left + 1, right);
		
		if (matrix[left][right - 1] == -1)
			solve(left, right - 1);
		
		matrix[left][right] = Math.max(vect[left] + (getSum(left + 1, right) - matrix[left + 1][right]), 
									   vect[right] + (getSum(left, right - 1) - matrix[left][right - 1]));
	}

	static int sign(int n) {
		if (n > 0)
			return 1;
		else
		if (n < 0)
			return -1;
		return 0;
	}
	
	static int getSum(int l, int r) {
		if (l == 0)
			return sum[r];
		else
			return sum[r] - sum[l - 1];
	}
	
	static int[] vect;
	static int[] sum;
	static int[][] matrix;
	
	static StreamTokenizer in;
	
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}
