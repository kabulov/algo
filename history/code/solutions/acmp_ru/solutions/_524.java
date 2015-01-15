import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("input.txt"));
        PrintWriter out = new PrintWriter("output.txt");

        int m = in.nextInt();
        int n = in.nextInt();
        
        int x = in.nextInt();
        int y = in.nextInt();
        
        if ((m + n) % 2 != (x + y) % 2) {
            out.println(0);
        } else {
            if (can(m, n, x, y)) {
                if (m == x && n == y)
                    out.println(0);
                else
                    out.println(1);
            } else {
                out.println(2);
outer:            for (int i = 1; i < 9; i++) 
                    for (int j = 1; j < 9; j++)
                        if ((m + n) % 2 == (i + j) % 2 && can(m, n, i, j) && can(x, y, i, j)) {
                            out.println(i + " " + j);
                            break outer;
                        }
            }
        }
        
        out.close();
    }
    
    static boolean can(int m, int n, int x, int y) {
        return Math.abs(m - x) == Math.abs(n - y);
    }
} 