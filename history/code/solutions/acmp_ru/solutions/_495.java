import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("input.txt"));
        PrintWriter out = new PrintWriter("output.txt");

        int n = in.nextInt();
        point[] fst = new point[n + 1];
        point[] scd = new point[n + 1];
        point[] buf;
        
        for (int i = 0; i < n; i++) { 
        	fst[i] = new point(in.nextInt(), in.nextInt());
        	scd[i] = new point(0, 0);
        }
        	
        fst[n] = fst[0];
        scd[n] = scd[0];
        
        int k = in.nextInt();
        for (int j = 0; j < k; j++) {
        	buf = fst;
        	fst = scd;
        	scd = buf;
        	
        	for (int i = 0; i < n; i++) {
        		fst[i].x = (scd[i].x + scd[i + 1].x) / 2;
        		fst[i].y = (scd[i].y + scd[i + 1].y) / 2;
        	}   
        	
        	fst[n] = fst[0];
        }
        
        double s = 0;
        for (int i = 0; i < n; i++)
        	s += Math.hypot(fst[i].x - fst[i + 1].x, fst[i].y - fst[i + 1].y);
        
        out.println(s);
        out.close();
    }
} 

class point {
	double x, y;
	point(double x, double y) {
		this.x = x;
		this.y = y;
	}
}