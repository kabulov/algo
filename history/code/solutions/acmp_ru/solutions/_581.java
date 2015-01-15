import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        in = new StreamTokenizer(new FileReader("input.txt"));
    	PrintWriter out = new PrintWriter("output.txt");

    	int n = next();
    	long x1, y1, x2, y2;
    	
    	x1 = next();
    	y1 = next();
    	x2 = next();
    	y2 = next();
    	
    	long a = y2 - y1;
    	long b = x1 - x2;
    	long c = x2 * y1 - x1 * y2;
    	long den = a * a + b * b;
    	
    	int[] pos = new int[n];
    	int len = 0;
    	
    	long x, y, r;
    	for (int i = 0; i < n; ++i) {
    		x = next();
    		y = next();
    		r = next();
    		
    		if ((a * x + b * y + c) * (a * x + b * y + c) <= r * r * den) 
    			pos[len++] = i + 1;
    	}
    	
    	out.println(len);
    	for (int i = 0; i < len; ++i) {
    		out.print(pos[i]);
    		out.print(" ");
    	}
    
        out.close();
    }
    
    static StreamTokenizer in;
    static int next() throws IOException {
    	in.nextToken();
    	return (int)in.nval;
    }
} 
