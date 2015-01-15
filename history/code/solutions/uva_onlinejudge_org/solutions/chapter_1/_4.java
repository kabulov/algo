import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		out = new PrintWriter(System.out);

		int len, bound;
		int[] v = new int[8];
		int s = in.nextInt(), n = in.nextInt();
		
		while (s > 0) {
			len = 0;
			
			if (n == 0) {
				v[len++] = 0;
			} 
			
			while (n > 0) {
				v[len++] = n % 10;
				n /= 10;
			}
			
			bound = 2 * s + 3;
			for (int i = 1; i <= bound; ++i) {
				prn(v[len - 1], s, i);
				for (int j = len - 2; j >= 0; --j) {
					out.print(" ");
					prn(v[j], s, i);
				}
				out.println();
			}
			
			s = in.nextInt();
			n = in.nextInt();
			out.println();
		}
		
		out.close();
	}
	
	static PrintWriter out;
	
	static void prn(int d, int s, int i) {
		switch(d) {
		case 0:
			if (i == 1 || i == 2 * s + 3) {
				space();
				line(s, '-');
				space();
			} else 
			if (i == s + 2)
				line(s + 2, ' ');
			else {
				slash();
				line(s, ' ');
				slash();
			}
			break;
		case 1:
			if (i == 1 || i == s + 2 || i == 2 * s + 3)
				line(s + 2, ' ');
			else {
				line(s + 1, ' ');
				slash();	
			}
			break;
		case 2:
			if (i == 1 || i == s + 2 || i == 2 * s + 3) {
				space();
				line(s, '-');
				space();
			} else 
			if (i < s + 2) {
				line(s + 1, ' ');
				slash();
			} else {
				slash();
				line(s + 1, ' ');
			}
			break;
		case 3:
			if (i == 1 || i == s + 2 || i == 2 * s + 3) {
				space();
				line(s, '-');
				space();
			} else {
				line(s + 1, ' ');
				slash();
			}
			break;
		case 4:
			if (i == 1 || i == 2 * s + 3) {
				line(s + 2, ' ');
			} else
			if (i == s + 2) {
				space();
				line(s, '-');
				space();
			} else 
			if (i < s + 2) {
				slash();
				line(s, ' ');
				slash();
			} else {
				line(s + 1, ' ');
				slash();
			}
			break;
		case 5:
			if (i == 1 || i == s + 2 || i == 2 * s + 3) {
				space();
				line(s, '-');
				space();
			} else
			if (i < s + 2) {
				slash();
				line(s + 1, ' ');
			} else {
				line(s + 1, ' ');
				slash();
			}
			break;
		case 6:
			if (i == 1 || i == s + 2 || i == 2 * s + 3) {
				space();
				line(s, '-');
				space();
			} else
			if (i < s + 2) {
				slash();
				line(s + 1, ' ');
			} else {
				slash();
				line(s, ' ');
				slash();
			}
			break;
		case 7:
			if (i == 1) {
				space();
				line(s, '-');
				space();
			} else
			if (i == s + 2 || i == 2 * s + 3) 
				line(s + 2, ' ');
			else {
				line(s + 1, ' ');
				slash();
			}
			break;
		case 8:
			if (i == 1 || i == s + 2 || i == 2 * s + 3) {
				space();
				line(s, '-');
				space();
			} else {
				slash();
				line(s, ' ');
				slash();
			}
			break;
		case 9:
			if (i == 1 || i == s + 2 || i == 2 * s + 3) {
				space();
				line(s, '-');
				space();
			} else
			if (i < s + 2) {
				slash();
				line(s, ' ');
				slash();
			} else {
				line(s + 1, ' ');
				slash();
			}
			break;
		}
	}
	
	static void line(int len, char c) {
		for (int i = 0; i < len; ++i)
			out.print(c);
	}
	
	static void slash() {
		out.print('|');
	}
	
	static void space() {
		out.print(' ');
	}
}