import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("input.txt"));
        PrintWriter out = new PrintWriter("output.txt");
        
        int n = in.nextInt();
        int[] map = new int[n + 1];
        map[0] = 1;
        
        for (int deg = 1; deg <= n; deg *= 2) {
            for (int j = 0; j + deg <= n; ++j)
                map[j + deg] += map[j];
        }
        
        out.println(map[n]);
        out.close();
    }
}