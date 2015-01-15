import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("input.txt"));
        PrintWriter out = new PrintWriter("output.txt");
        
        int n = in.nextInt();
        int answer = 0;
        
        for (int i = 1; i <= n / 4; ++i)
            for (int j = i; j <= (n - i) / 3; ++j)
                answer += (n - i - j) / 2 - j + 1;
        
        out.println(answer);
        out.close();
    }
}