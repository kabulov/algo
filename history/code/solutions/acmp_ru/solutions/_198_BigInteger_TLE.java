import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.math.BigInteger;


public class Main {
	public static void main(String[] args) throws IOException {
		in = new StreamTokenizer(new FileReader("input.txt"));
		
		int n = nextInt();
		BigInteger[][] matrix = new BigInteger[n][n + 1];
		for (int i = 0; i < n; i++)
			for (int j = 0; j <= n; j++)
				matrix[i][j] = new BigInteger(new Integer(nextInt()).toString());
		
		int pos;
		BigInteger tmp;
		for (int i = 0; i < n - 1; i++) {
			if (matrix[i][i].equals(BigInteger.ZERO)) {
				pos = i;
				while (matrix[pos][i].equals(BigInteger.ZERO))
					pos++;
				for (int j = i; j <= n; j++) {
					tmp = matrix[pos][j];
					matrix[pos][j] = matrix[i][j];
					matrix[i][j] = tmp;
				}
			}
			
			if (matrix[i][i].compareTo(BigInteger.ZERO) < 0)
				for (int j = i; j <= n; j++)
					matrix[i][j] = matrix[i][j].negate();
			
			for (int p = i + 1; p < n; p++) {
//				if (matrix[p][i] == 0)
//					continue;
				
				if (matrix[p][i].compareTo(BigInteger.ZERO) < 0)
					for (int j = i; j <= n; j++)
						matrix[p][j] = matrix[p][j].negate();
				
				while (matrix[p][i].compareTo(BigInteger.ZERO) != 0) {
					if (matrix[p][i].compareTo(matrix[i][i]) < 0) {
						tmp = matrix[i][i].divide(matrix[p][i]);
						if  (matrix[i][i].mod(matrix[p][i]).compareTo(BigInteger.ZERO)  == 0)
							tmp = tmp.subtract(BigInteger.ONE);
						for (int j = i; j <= n; j++)
							matrix[i][j] = matrix[i][j].subtract(tmp.multiply(matrix[p][j]));
					} else {
						tmp = matrix[p][i].divide(matrix[i][i]);
						for (int j = i; j <= n; j++)
							matrix[p][j] = matrix[p][j].subtract(tmp.multiply(matrix[i][j]));						
					}
				}
			}
		}
		
		BigInteger root;
		BigInteger[] answer = new BigInteger[n];
		for (int i = n - 1; i >= 0; i--) {
			root = answer[i] = matrix[i][n].divide(matrix[i][i]);
			for (int p = i - 1; p >= 0; p--)
				matrix[p][n] = matrix[p][n].subtract(root.multiply(matrix[p][i]));
		}
		
		PrintWriter out = new PrintWriter("output.txt");
		for (int i = 0; i < n; i++)
			out.print(answer[i] + " ");
			
		out.close();
	}
	
	static StreamTokenizer in;
	
	public static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}