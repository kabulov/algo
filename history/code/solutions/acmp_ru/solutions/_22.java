import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("input.txt"));
        PrintWriter out = new PrintWriter("output.txt");
        
        int a = in.nextInt(), sol = 0;
        
        while (a > 0) {
        	++sol;
        	a &= a - 1;
        }
        
        out.println(sol);
        out.close();
    }
}