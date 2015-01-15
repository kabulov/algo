import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("input.txt"));
        PrintWriter out = new PrintWriter("output.txt");

        long x, y;
        x = in.nextLong();
        y = in.nextLong();
        
        int n = in.nextInt();
        int[] pos = new int[n];
        int len = 0;
        
        long sum;
        long x1, y1, x2, y2, x3, y3;
        for (int i = 0; i < n; i++) {
        	x1 = in.nextLong();
        	y1 = in.nextLong();
        	x2 = in.nextLong();
        	y2 = in.nextLong();
        	x3 = in.nextLong();
        	y3 = in.nextLong();
        	sum = vect(x1, y1, x2, y2, x, y) + vect(x2, y2, x3, y3, x, y) + vect(x3, y3, x1, y1, x, y);
        	if (Math.abs(sum) == 3) {
        		pos[len++] = i + 1;
        	}
        }
        
        out.println(len);
        for (int i = 0; i < len; i++)
        	out.print(pos[i] + " ");
        
        out.close();
    }
    
    static long vect(long x1, long y1, long x2, long y2, long x, long y) {
    	long mid = (x2 - x1) * (y - y1) - (x - x1) * (y2 - y1);
    	
    	if (mid > 0)
    		return 1;

    	if (mid < 0)
    		return -1;
    	
    	return 0;
    }
} 