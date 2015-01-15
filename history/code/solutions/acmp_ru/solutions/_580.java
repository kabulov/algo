import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("input.txt"));
        PrintWriter out = new PrintWriter("output.txt");

        double a, b, c, r;
        
        a = in.nextInt();
        b = in.nextInt();
        c = in.nextInt();
        r = in.nextInt();
        
        double p = a + b + c;
        double s = Math.sqrt(p * (p - 2 * a) * (p - 2 * b) * (p - 2 * c) / 16);
        
        if (2 * s >= r * p)
        	out.println("YES");
        else
        	out.println("NO");
        
        out.close();
    }
} 