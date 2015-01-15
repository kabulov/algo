import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		String str = in.next();
		int i = str.charAt(1) - 48;
		int j = str.charAt(0) - 'A' + 1;
		
		str = "BLACK";
		if (((i % 2 == 1) && (j % 2 == 0)) || ((i % 2 == 0) && (j % 2 == 1))) 
			str = "WHITE";
		
		out.println(str);
		out.close();
	}
}