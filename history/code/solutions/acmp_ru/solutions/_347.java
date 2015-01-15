import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		PrintWriter out = new PrintWriter("output.txt");
		in = new StreamTokenizer(new FileReader("input.txt"));
		
		int[] vect = new int[14];
		for (int i = 0; i < 14; i++)
			vect[i] = 0;
		for (int i = 0; i < 5; i++)
			vect[nextInt()]++;
		
		int five = 0, four = 0, three = 0, two = 0, one = -1;
		for (int i = 0; i < 14; i++) {
			switch(vect[i]) {
			case 5:
				five++;
				one = -2;
				break;
			case 4:
				four++;
				one = -2;
				break;
			case 3:
				three++;
				one = -2;
				break;
			case 2:
				two++;
				one = -2;
				break;
			case 1:
				if (one == -1)
					one = 1;
				else
				if (one != -2)
					one++;
				break;
			case 0:
				if (one != -1 && one < 5)
					one = -2;
			}
		}
		
		if (five > 0) {
			out.println("Impossible");
		} else
		if (four > 0) {
			out.println("Four of a Kind");
		} else
		if (three > 0 && two > 0) {
			out.println("Full House");
		} else
		if (one == 5) {
			out.println("Straight");
		} else
		if (three > 0) {
			out.println("Three of a Kind");
		} else
		if (two > 1) {
			out.println("Two Pairs");
		} else
		if (two > 0) {
			out.println("One Pair");
		} else {
			out.println("Nothing");
		}
		
		out.close();
	}
	
	static StreamTokenizer in;
	
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}