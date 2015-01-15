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

		String[] mid = in.readLine().split(" ");
		char[] v1 = mid[0].toCharArray();
		char[] v2 = mid[1].toCharArray();
		
		int a = 0;
		for (int i = 0; i < 4; i++)
			if (v1[i] == v2[i])
				a++;
		
		int b = 0;
		if (v2[0] != v1[0] && (v1[1] == v2[0] || v1[2] == v2[0] || v1[3] == v2[0]))
			b++;
		
		if (v2[1] != v1[1] && (v1[0] == v2[1] || v1[2] == v2[1] || v1[3] == v2[1]))
			b++;
		
		if (v2[2] != v1[2] && (v1[0] == v2[2] || v1[1] == v2[2] || v1[3] == v2[2]))
			b++;
		
		if (v2[3] != v1[3] && (v1[0] == v2[3] || v1[1] == v2[3] || v1[2] == v2[3]))
			b++;

		out.println(a + " " + b);
		out.close();
	}
}
