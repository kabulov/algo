import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		Stack<Integer> s = new Stack<Integer>();
		int n = in.nextInt();
		while (n > 0) {
			if (n % 3 != 0) { 
				s.push(n % 3);
			} else {
				s.push(3);
				n -= 3;
			}
			n /= 3;
		}
		
		while (!s.isEmpty())
			out.print(s.pop());
		
		out.close();
	}
}
