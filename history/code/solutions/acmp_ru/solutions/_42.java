import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("input.txt"));
        PrintWriter out = new PrintWriter("output.txt");
        
        int n = in.nextInt();
        
        long sol = 1;

        if (n > 1) {
        	while (n > 0 && n % 3 != 0) {
        		sol *= 2;
        		n -= 2;
        	}
        
        	while (n > 0) {
       			sol *= 3;
       			n -= 3;
       		}
        }
        
        out.println(sol);
        out.close();
    }
}