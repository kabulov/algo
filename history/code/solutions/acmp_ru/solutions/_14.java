import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("input.txt"));
        PrintWriter out = new PrintWriter("output.txt");
        
        long a = in.nextLong();
        long b = in.nextLong();
        
        long aa = a, bb = b, tmp;
        while (bb > 0) {
        	tmp = aa;
        	aa = bb;
        	bb = tmp % bb;
        }
        //aa = gcd(a, b)
        
        out.println(a / aa * b);
        
        out.close();
    }
}