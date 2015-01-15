import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		in = new StreamTokenizer(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
	
		int x1 = nextInt();
		int y1 = nextInt();
		
		int x2 = nextInt();
		int y2 = nextInt();
		
		int xa = nextInt();
		int ya = nextInt();
		
		if (x1 == x2) {
//			if (xa == x1)
//				out.println(xa + " " + ya);
//			else {
				out.println((x1 - (xa - x1)) + " " + ya);
//			}
		} else {//y1 == y2
//			if (ya == y1) 
//				out.println(xa + " " + ya);
//			else {
				out.println(xa + " " + (y1 - (ya - y1)));
//			}
		}
		
		out.close();
	}
	
	static StreamTokenizer in;
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}
