import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("input.txt"));
        PrintWriter out = new PrintWriter("output.txt");

        double x0, y0, vx, vy;
        x0 = in.nextLong();
        y0 = in.nextLong();
        vx = in.nextLong();
        vy = in.nextLong();
        
        double v, t, d;
        v = in.nextLong();
        t = in.nextLong();
        d = in.nextLong();
        
        double p = Math.hypot(x0 + vx * t, y0 + vy * t);
        
        if (Math.abs(p - d) <= t * v)
        	out.println("YES");
        else
        	out.println("NO");
        
        out.close();
    }
} 