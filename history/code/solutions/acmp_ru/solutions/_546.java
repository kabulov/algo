import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int n = in.nextInt();
		if (n == 1) {
			out.println("1 1 0 1");
			out.close();
			return;
		}
		
		int sheet = n / 4;
		if (n % 4 != 0)
			sheet++;
		
		int sleft = sheet * 4, sright = 1;
		for (int i = 1; i <= sheet; i++) {
			if (sleft > n)
				out.print(i + " 1 " + "0" + " ");
			else
				out.print(i + " 1 " + sleft + " ");
			
			out.println(sright);
			sleft--;
			sright++;
			
			out.print(i + " 2 " + sright + " ");
			if (sleft > n)
				out.println(0);
			else
				out.println(sleft);
			
			sleft--;
			sright++;
		}
		
		out.close();
	}
}