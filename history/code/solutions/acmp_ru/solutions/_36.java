import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("input.txt"));
        PrintWriter out = new PrintWriter("output.txt");
        
        int n = in.nextInt();
        
        boolean[] prime = new boolean[2 * n];
        Arrays.fill(prime, true);
        for (int i = 2; i < n; i++)
        	if (prime[i])
        		for (int j = i; j + i < 2 * n; j += i) 
        			prime[j + i] = false;
        
        int sol = 0;
        for (int i = n + 1; i < 2 * n; i++)
        	if (prime[i])
        		sol++;
        
        out.println(sol);
        out.close();
    }
}