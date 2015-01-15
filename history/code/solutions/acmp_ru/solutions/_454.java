import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt"); 

		int l = in.nextInt();
		int r = in.nextInt();
		
		int amt = r - l + 1;
		int fst = l;
		int ofs = 1;
		
		while (amt > 1) {
			fst += ofs;
			amt /= 2;
			ofs *= 2;
			amt -= amt / 2;
			ofs *= 2;
		}
		
		out.println(fst);		
		out.close();
	}
}