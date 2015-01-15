
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		StringTokenizer tok = new StringTokenizer(in.nextLine(), DelimSet, true); 
		StringBuilder str = new StringBuilder();		
		String buf;
				
		boolean space = false;
		while (tok.hasMoreTokens()) {
			buf = tok.nextToken();
			if (buf.toLowerCase().equals("a") || buf.toLowerCase().equals("an") || buf.toLowerCase().equals("the")) continue;
			if (buf.equals(" ")) {
				if (!space) str.append(buf);
				space = true;
			} else {
				str.append(buf);
				space = false;
			}
		}
		
		tok = new StringTokenizer(str.toString().trim(), DelimSet, true);
		str = new StringBuilder();
		StringBuilder mid;
		
		while (tok.hasMoreTokens()) {
			buf = tok.nextToken();
			if (!isDelim(buf)) {
				mid = new StringBuilder(buf);
				space = mid.charAt(0) != Character.toLowerCase(mid.charAt(0));
				mid.setCharAt(0, Character.toLowerCase(mid.charAt(0)));
				int p = mid.indexOf("c", 0);
				while (p != -1) {
					if (p == mid.length() - 1) {
						mid.setCharAt(p, 'k');
					} else {
						if (mid.charAt(p + 1) == 'i' || mid.charAt(p + 1) == 'e') {
							mid.setCharAt(p, 's');
						} else
						if (mid.charAt(p + 1) == 'k') {
							mid.deleteCharAt(p);
							//if (p == 0) space = false; 	!!
						} else {
							mid.setCharAt(p, 'k');
						}
					}
					p = mid.indexOf("c", p);
				}
				if (space) mid.setCharAt(0, Character.toUpperCase(mid.charAt(0)));
				buf = mid.toString();
			}
			str.append(buf);
		}
		
		tok = new StringTokenizer(str.toString(), DelimSet, true);
		str = new StringBuilder();
		
		while (tok.hasMoreTokens()) {
			mid = new StringBuilder(tok.nextToken());
			space = mid.charAt(0) != Character.toLowerCase(mid.charAt(0));
			mid.setCharAt(0, Character.toLowerCase(mid.charAt(0)));
			for (int i = 0; i + 1 < mid.length();) {
				if (mid.charAt(i) != mid.charAt(i + 1)) {
					++i;
					continue;
				}
				
				switch(mid.charAt(i)) {
				case 'e':
					mid.deleteCharAt(i);
					mid.setCharAt(i, 'i');
					if (i > 0) --i;
					break;
				case 'o':
					mid.deleteCharAt(i);
					mid.setCharAt(i, 'u');
					if (i > 0) --i;
					break;
				default:
					mid.deleteCharAt(i);
					if (i > 0) --i;
				}
			}
			if (space) mid.setCharAt(0, Character.toUpperCase(mid.charAt(0)));
			str.append(mid.toString());
		}
		
		tok = new StringTokenizer(str.toString(), DelimSet, true);
		str = new StringBuilder();
		
		while (tok.hasMoreTokens()) {
			mid = new StringBuilder(tok.nextToken());
			if (mid.length() > 1 && mid.charAt(mid.length() - 1) == 'e') mid.deleteCharAt(mid.length() - 1);
			str.append(mid.toString());
		}
		
		out.print(str);
		out.close();
	}

	static boolean isDelim(String c) {
		return DelimSet.indexOf(c) >= 0;
	}
	
	static String DelimSet = " .,?!:-;()\'\"";
	
}

//while (st.hasMoreTokens()) prn(st.nextToken());
//static void prn(Object o) {
//	System.out.print(o);
//}	
