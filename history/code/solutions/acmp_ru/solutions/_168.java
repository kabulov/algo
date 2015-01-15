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

		StringBuilder str = new StringBuilder();
		String key = in.readLine();
		for (int i = 1; i <= 10000; i++)
			str.append(Integer.toString(i));
		
		out.println(str.indexOf(key) + 1);
		out.close();
	}
}
