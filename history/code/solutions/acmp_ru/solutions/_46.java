import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int n = in.nextInt();
		
		String eps = "7182818284590452353602875";
		
		if (n == 0) {
			out.println(3);
		} else {
			out.print("2.");
			
			if (n == 25) {
				out.println(eps);
			} else
			//if succeding digit is < 5 then the digit itself else(>=5) digit + 1(and switch case operator usage unimportant)
			if (n == 1) {
				out.println("7");
			} else {	
				char c;
				out.print(eps.substring(0, n - 1));
				
				switch(n) {
				case 2: case 6:
					c = '2';
					break;
				case 4: case 8: case 22:
					c = '3';
					break;
				case 11:
					c = '6';
					break;
				case 19: case 17:
					c = '4';
					break;
				case 23:
					c = '9';
					break;
				case 10: case 14:
					c = '5';
					break;		
				case 24:
					c = '8';
					break;
				default:
					c = eps.charAt(n - 1);
				}
				
				out.println(c);
			}			
		}
		out.close();
	}
}
