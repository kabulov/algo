import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		in = new StreamTokenizer(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
	
		int n = nextInt();
		double rouble = 100, usd = 0, eur = 0;
		
		double us, er;
		for (int i = 0; i < n; i++) {
			us = next();
			er = next();
			if (usd * us > rouble)
				rouble = usd * us;
			if (eur * er > rouble)
				rouble = eur * er;
			if (rouble / us > usd)
				usd = rouble / us;
			if (rouble / er > eur)
				eur = rouble / er;
		}
		
		out.printf("%.2f", rouble);
		out.close();
	}
	
	static StreamTokenizer in;
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
	
	static double next() throws IOException {
		in.nextToken();
		return in.nval;
	}
}
