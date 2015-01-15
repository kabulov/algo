import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Scanner;
import java.util.Stack;



public class Main {
	public static void main(String[] args) throws IOException {
		out = new PrintWriter("output.txt");
		in = new Scanner(new File("input.txt")); 
		
		BigInteger from = in.nextBigInteger();
		BigInteger to = in.nextBigInteger();
		String number = in.next();
		
		BigInteger ten = BigInteger.ZERO;
		int len = number.length();
		for (int i = 0; i < len; i++) {
			ten = ten.multiply(from).add(c2d(number.charAt(i)));
		}
		
		Stack<Character> stack = new Stack<Character>();
		BigInteger zero = BigInteger.ZERO;
		if (ten.compareTo(zero) == 0)
			stack.push('0');
		else
		while (ten.compareTo(zero) > 0) {
			stack.push(d2c(ten.mod(to)));
			ten = ten.divide(to);
		}
		
		while (!stack.isEmpty()) {
			out.print(stack.pop());
		}
		
		out.close();
	}
	
	static BigInteger c2d(final char c) {
		if ('0' <= c && c <= '9')
			return new BigInteger(new Integer(c - 48).toString());
		else
			return new BigInteger(new Integer(c - 55).toString());
	}
	
	static Character d2c(final BigInteger n) {
		int d = Integer.parseInt(n.toString());
		if (0 <= d && d <= 9) 
			return new Character((char)(d + 48));
	    else 
			return new Character((char)(d + 55));
	}

	static PrintWriter out;
	static Scanner in;
}