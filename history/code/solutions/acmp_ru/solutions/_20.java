import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		in = new StreamTokenizer(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int n = nextInt(), maxlen = 1, curlen = 0, prev, cur = nextInt(), flag = 0;
		
		for (int i = 1; i < n; ++i) {
			prev = cur;
			cur = nextInt();
			
			if (prev < cur) {
				if (flag == 1) {
					++curlen;
				} else {
					if (curlen > maxlen) maxlen = curlen;
					curlen = 2;
				}
				flag = -1;
			} else
			if (prev > cur) {
				if (flag == -1) {
					++curlen;
				} else {
					if (curlen > maxlen) maxlen = curlen;
					curlen = 2;
				}
				flag = 1;
			} else {
				if (curlen > maxlen) maxlen = curlen;
				flag = 0;
				curlen = 1;
			}
		}
		
		if (curlen > maxlen) maxlen = curlen;
		
		out.println(maxlen);
		out.close();
	}
	
	static StreamTokenizer in;
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}