import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		String[] buf = in.next().split("[:]");
		
		int h, m, s;
		h = Integer.parseInt(buf[0]);
		m = Integer.parseInt(buf[1]);
		s = Integer.parseInt(buf[2]);
		
//		if (s == 60) ++m;
//		if (m >= 60) ++h;
//		h %= 24;
		
		int a, b, c, d;
		d = a = b = c = 0;
		buf = in.next().split("[:]");
		
		switch(buf.length) {
		case 1:
			c = Integer.parseInt(buf[0]);
			break;
		case 2:
			b = Integer.parseInt(buf[0]);
			c = Integer.parseInt(buf[1]);
			break;
		case 3:
			a = Integer.parseInt(buf[0]);
			b = Integer.parseInt(buf[1]);
			c = Integer.parseInt(buf[2]);
			break;
		}
		
//		b += c / 60;
//		c %= 60;
		
//		a += b / 60;
//		b %= 60;
		
//		d = a / 24;
//		a %= 24;
		
		s += c;
		m += b;
		h += a;
		
		m += s / 60;
		s %= 60;
		
		h += m / 60;
		m %= 60;
		
		d += h / 24; //=
		h %= 24;
		
		out.print(h < 10 ? "0" + h : h);
		out.print(":");
		out.print(m < 10 ? "0" + m : m);
		out.print(":");
		out.print(s < 10 ? "0" + s : s);
		out.println(d > 0 ? "+" + d + " days" : "");

		out.close();
	}
}