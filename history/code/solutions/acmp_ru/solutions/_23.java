import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("input.txt"));
        PrintWriter out = new PrintWriter("output.txt");
        
        int a = in.nextInt(), sol = 0;

        for (int i = 1; i <= a; i++)
        	if (a % i == 0)
        		sol += i;
        
        out.println(sol);
        out.close();
    }
}