import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		out = new PrintWriter("output.txt");
		
		int n = in.nextInt();
		int m = in.nextInt();
		
		int tw, th, f, twf, twth, thf, twthf;
		
		tw = amt1(n, m, 2); 
		th = amt1(n, m, 3); 
		f = amt1(n, m, 5); 
		twf = amt2(n, m, 2, 5); 
		twth = amt2(n, m, 2, 3); 
		thf = amt2(n, m, 3, 5);  
		twthf = amt3(n, m, 2, 3, 5); 
		
		int red, green, blue, black;
		
		red = tw - twf - twth + twthf;
		green = th - thf;
		blue = f;
		black = n * m - red - blue - green;
		
		out.println("RED : " + red);
		out.println("GREEN : " + green);
		out.println("BLUE : " + blue);
		out.println("BLACK : " + black);
		
		out.close();
	}
	
	static int amt3(int a, int b, int c, int d, int e) {
		return (a / c - a / (c * d) - a / (c * e) + a / (c * d * e)) * (b / (d * e)) +
			   (a / d - a / (d * c) - a / (d * e) + a / (c * d * e)) * (b / (c * e)) +
			   (a / e - a / (e * c) - a / (e * d) + a / (c * d * e)) * (b / (c * d)) +
			   (a / (c * d) - a / (c * d * e)) * (b / e) + 
			   (a / (c * e) - a / (c * d * e)) * (b / d) +
			   (a / (d * e) - a / (c * d * e)) * (b / c) +
			   (a / (c * d * e)) * b +
			   (b / (c * d * e)) * (a - (a / c + a / d + a / e - a / (c * d) - a / (c * e) - a / (d * e) + a / (c * d * e)));
	}
	
	static int amt1(int a, int b, int c) {
		return (a / c) * b + (a - a / c) * (b / c);
	}
	
	static int amt2(int a, int b, int c, int d) {
		return (a / c - a / (c * d)) * (b / d) + (a / d - a / (c * d)) * (b / c) + (a / (c * d)) * b + (b / (c * d)) * (a - a / c - a / d + a / (c * d));
	}
	
	static PrintWriter out;
}
