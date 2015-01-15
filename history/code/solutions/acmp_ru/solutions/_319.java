import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("input.txt"));
        PrintWriter out = new PrintWriter("output.txt");

        long x1 = in.nextLong();
        long y1 = in.nextLong();
        long x2 = in.nextLong();
        long y2 = in.nextLong();
        
        out.println(gcd(Math.abs(x1 - x2), Math.abs(y1 - y2)) + 1);
        out.close();
    }
    
    static long gcd(long a, long b) {
    	return b == 0 ? a : gcd(b, a % b);
    }
} 