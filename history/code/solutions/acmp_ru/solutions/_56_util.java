import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		int n = Integer.parseInt(in.readLine());
		String[] fnd = new String[n];
		for (int i = 0; i < n; i++)
			fnd[i] = in.readLine();
		
		int m = Integer.parseInt(in.readLine());
		String[] fof = new String[m];
		for (int i = 0; i < m; i++)
			fof[i] = in.readLine();
		
		boolean[] mutfnd = new boolean[n];
		Arrays.fill(mutfnd, false);
		
		boolean[] mutfof = new boolean[m];
		Arrays.fill(mutfof, false);
		
		Arrays.sort(fnd);
		Arrays.sort(fof);
		
		for (int i = 0; i < n; i++)
			if (Arrays.binarySearch(fof, fnd[i]) >= 0)
				mutfnd[i] = true;
		
		for (int i = 0; i < m; i++) 
			if (Arrays.binarySearch(fnd, fof[i]) >= 0)
				mutfof[i] = true;
		
		out.print("Friends: ");
		for (int i = 0; i < n - 1; i++)
			out.print(fnd[i] + ", ");
		if (n > 0)
			out.print(fnd[n - 1]);
		out.println();
		
		int len = 0;
		String[] mid = new String[200];
		for (int i = 0; i < n; i++)
			if (mutfnd[i])
				mid[len++] = fnd[i];
		
		out.print("Mutual Friends: ");
		for (int i = 0; i < len - 1; i++)
			out.print(mid[i] + ", ");
		if (len > 0)
			out.print(mid[len - 1]);
		out.println();
		
		len = 0;
		for (int i = 0; i < m; i++)
			if (!mutfof[i])
				mid[len++] = fof[i];
		
		out.print("Also Friend of: ");
		for (int i = 0; i < len - 1; i++)
			out.print(mid[i] + ", ");
		if (len > 0)
			out.print(mid[len - 1]);
		out.println();
		
		out.close();
	}

}
