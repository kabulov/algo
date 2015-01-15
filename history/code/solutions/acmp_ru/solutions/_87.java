import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		char[][] vect = new char[240][101];
		String str;
		int len = 0;
		
		while (!(str = in.next()).equals("ENDOFINPUT"))
			vect[len++] = str.toCharArray();
		
		int ans = 0;
		
		for (int i = 0; i < len; ++i) 
outer:		for (int j = 0; j < len; ++j) {
				if (j == i || vect[j].length >= vect[i].length) continue;
				if (!offers(vect[i], vect[j])) continue;
				for (int k = 0; k < len; ++k) {
					if (k == i || vect[k].length + vect[j].length != vect[i].length) continue;
					if (offere(vect[i], vect[k], vect[j].length)) {
						++ans;
						break outer;
					}
				}
			}
		
		out.println(ans);
		
		in.close();
		out.close();
	}
	
	static boolean offere(char[] main, char[] sub, int from) {
		for (int i = 0; i < sub.length; ++i)
			if (main[from+i] != sub[i])
				return false;
		return true;		
	}
	
	static boolean offers(char[] main, char[] sub) {
		for (int i = 0; i < sub.length; ++i)
			if (main[i] != sub[i])
				return false;
		return true;
	}
}