import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		String fst = in.next();
		String scd = in.next();
		String thd = in.next();
		
		int[] vect = new int[10];
		for (int i = 0; i < 10; i++)
			vect[i] = 0;
		
		int len = fst.length();
		for (int i = 0; i < len; i++)
			vect[fst.charAt(i) - 48] = 1;
		
		len = scd.length();
		for (int i = 0; i < len; i++)
			if (vect[scd.charAt(i) - 48] == 1)
				vect[scd.charAt(i) - 48] = 2;
		
		for (int i = 0; i < 10; i++)
			if (vect[i] == 1)
				vect[i] = 0;
		
		len = thd.length();
		for (int i = 0; i < len; i++)
			if (vect[thd.charAt(i) - 48] == 2)
				vect[thd.charAt(i) - 48] = 3;
		
		int answer = 0;
		for (int i = 0; i < 10; i++)
			if (vect[i] == 3)
				answer++;
		
		out.println(answer);
		for (int i = 0; i < 10; i++)
			if (vect[i] == 3)
				out.print(i + " ");
		
		out.close();
	}
}