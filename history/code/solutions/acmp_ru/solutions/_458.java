import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt"), "ISO-8859-1"));
		PrintWriter out = new PrintWriter("output.txt", "ISO-8859-1");

		int n = Integer.parseInt(in.readLine());
		Locale.setDefault(Locale.US);
		
		int[] v = new int[n];
		StringTokenizer st = new StringTokenizer(in.readLine());
		for (int i = 0; i < n; i++)
			v[i] = Integer.parseInt(st.nextToken()) - 1;
		
		String str = in.readLine();
		int len = str.length() / n, mid = n;
		if (str.length() % n != 0) {
			mid = str.length() % n;
			++len;
		}
		
		char[][] map = new char[n][len];
		for (int i = 0; i < n; i++)
			map[i][len - 1] = '#';
			
		int pos = 0;
		for (int i = 0; i < n; i++) {
			int tmp;
			if (v[i] < mid)
				tmp = len;
			else
				tmp = len - 1;
			for (int j = 0; j < tmp; j++)
				map[v[i]][j] = str.charAt(pos + j);			
			pos += tmp;
		}
		
		for (int j = 0; j < len; j++)
			for (int i = 0; i < n; i++)
				if (map[i][j] != '#')
					out.print(map[i][j]);
		
		out.close();
	}
}
