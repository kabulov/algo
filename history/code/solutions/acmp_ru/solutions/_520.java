import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		long n = in.nextLong();
		
		long a, b, c;
		a = b = c = 0;
		
		{	//solution
			a = n / 144;
			n %= 144;

			long tmp = n / 12 * 10250 + Math.min(10250, (n % 12) * 1050);
			
			if (114000 <= tmp) 
				++a;
			else {
				b = n / 12;
				n %= 12;
				
				tmp = n * 1050;
				if (10250 <= tmp) 
					++b;
				else {
					c = n;
				}
			}
		}		
		
		out.println(a + " " + b + " " + c);
		out.close();
	}
}