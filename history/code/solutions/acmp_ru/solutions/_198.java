import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;


public class Main {
	public static void main(String[] args) throws IOException {
		in = new StreamTokenizer(new FileReader("input.txt"));
		int n = nextInt();
		
		double[][] vect = new double[n][n + 1];
		for (int i = 0; i < n; i++)
			for (int j = 0; j <= n; j++)
				vect[i][j] = nextInt();
		
		int max;
		double tmp;
		for (int i = 0; i < n - 1; i++) {
			max = i;
			for (int p = i + 1; p < n; p++)
				if (vect[p][i] > vect[max][i])
					max = p;
					
			if (max > i) {
				for (int j = i; j <= n; j++) {
					tmp = vect[i][j];
					vect[i][j] = vect[max][j];
					vect[max][j] = tmp;
				}
			}

//			if (vect[i][i] < 0)					//no need to include this fragment
//				for (int j = i; j <= n; j++)	//but if included : AC remains
//					vect[i][j] = -vect[i][j];
			
			tmp = vect[i][i];
			for (int j = i; j <= n; j++)
				vect[i][j] /= tmp;
			
			for (int p = i + 1; p < n; p++) {
//				if (vect[p][i] < 0)					//no need to include this fragment
//					for (int j = i; j <= n; j++)	//buf if included : AC remains
//						vect[p][j] = -vect[p][j];
				
				tmp = vect[p][i];
				for (int j = i; j <= n; j++)
					vect[p][j] -= vect[i][j] * tmp;
			}
		}
		
		for (int i = n - 1; i >= 0; i--) {
			tmp = vect[i][n] /= vect[i][i];
			for (int p = i - 1; p >= 0; p--)
				vect[p][n] -= tmp * vect[p][i];
		}
		
		PrintWriter out = new PrintWriter("output.txt");
		for (int i = 0; i < n; i++)
			out.printf(Math.round(vect[i][n]) + " ");
		out.close();
	}
	
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
	
	static StreamTokenizer in;
}