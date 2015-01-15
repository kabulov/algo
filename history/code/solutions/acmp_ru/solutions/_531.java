import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("input.txt"));
        PrintWriter out = new PrintWriter("output.txt");

        long x, y, r;
        
        x1 = in.nextInt();
        y1 = in.nextInt();
        x2 = in.nextInt();
        y2 = in.nextInt();
        x = in.nextInt();
        y = in.nextInt();
        r = in.nextInt();
        
        long tmp;
        if (x1 > x2) {
        	tmp = x1;
        	x1 = x2;
        	x2 = tmp;
        }
        
        if (y1 > y2) {
        	tmp = y1;
        	y1 = y2;
        	y2 = tmp;
        }
        
        long xd = 0;
        long ans = 0;

        for (long i = -r; i <= 0; ++i) {
        	while (i * i + (xd + 1) * (xd + 1) <= r * r)
        		++xd;
        	
        	ans += amt(y + i, x - xd, x + xd);
        }
        
        for (long i = 1; i <= r; ++i) {
        	while (i * i + xd * xd > r * r)
        		--xd;
        		
        	ans += amt(y + i, x - xd, x + xd);
        }
        
        out.println(ans);
        out.close();
    }
    
    static long x1, y1, x2, y2;
    static long amt(long y, long xl, long xr) {
    	if (y1 > y || y2 < y)
    		return 0;
    	
    	if (x1 > xr || x2 < xl)
    		return 0;
    	
    	return Math.min(xr, x2) - Math.max(xl, x1) + 1;
    }
} 