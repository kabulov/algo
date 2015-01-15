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
		
		if (fst.compareTo(scd) == 0) {
			out.println(fst);
			out.close();
			return;
		}
		
		fst = fst.toLowerCase();
		scd = scd.toLowerCase();
		
		String a = shorten(fst.toCharArray(), scd.toCharArray());
		String b = shorten(scd.toCharArray(), fst.toCharArray());

		if (a.length() < b.length())
			out.println(a);
		else
		if (a.length() > b.length())
			out.println(b);
		else
			out.println(a.compareTo(b) < 0 ? a : b);
		
		out.close();
	}
	
	static String shorten(char[] a, char[] b) {
		int[] pref = new int[b.length];
		pref[0] = 0;
		
		for (int i = 1, j = 0; i < b.length; ++i) {
			while (j > 0 && b[i] != b[j]) j = pref[j - 1];
			if (b[i] == b[j]) ++j;
			pref[i] = j;
		}
		
		int j = 0;
		for (int i = 0; i < a.length; ++i) {
			while (j > 0 && a[i] != b[j]) j = pref[j - 1];
			if (a[i] == b[j]) ++j;
			
			if (j == b.length) {
				String tmp = new String();
				tmp += Character.toUpperCase(a[0]);
				for (int k = 1; k < i - b.length + 1; ++k) tmp += a[k];
				if (i - b.length + 1 > 0) tmp += Character.toUpperCase(b[0]);
				for (int k = i - b.length + 2; k < a.length; ++k) tmp += a[k];
				return tmp;
				//accepted
				//but doljno bit tak :
				//esli odin raz vstrechaetsa kak podstroka, to fiksiruem eto vxojdenie
				//esli dva raza vstrechaetsa kak podstroka, to
				// esli pervoe vxojdenie v samom nachale, to vtoroe vxojdenie fiksiruem
				// inache pervoe vxojdenie fiksiruem
			}
		}
		
		StringBuilder tmp = new StringBuilder(Character.toString(Character.toUpperCase(a[0])));
		for (int i = 1; i < a.length - j; ++i) tmp.append(a[i]);
		if (j > 0) {
			tmp.append(Character.toUpperCase(a[a.length - j]));
			for (int i = a.length - j + 1; i < a.length; ++i) tmp.append(a[i]);
			for (int i = j; i < b.length; ++i) tmp.append(b[i]);
		} else {
			tmp.append(Character.toUpperCase(b[0]));
			for (int i = 1; i < b.length; ++i) tmp.append(b[i]);
		}
		
		return tmp.toString();
	}
}