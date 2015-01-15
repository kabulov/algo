import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int n = Integer.parseInt(in.readLine());
		String sub = in.readLine();
		
		if (n == 1) {
			if (sub.equals("A"))
				out.println(1);
			else
				out.println(0);
		} else
		if (n == 2) {
			if (sub.equals("B"))
				out.println(1);
			else
				out.println(0);
		} else {
			fib f, f1, f2;
			
			f = new fib();
			f1 = new fib();
			
			f1.start = f1.end = "A";
			if (sub.equals("A"))
				f1.amt = 1;
			else
				f1.amt = 0;
			
			f.start = f.end = "B";
			if (sub.equals("B"))
				f.amt = 1;
			else
				f.amt = 0;
			
			int len = sub.length();			
			for (int i = 3; i <= n; i++) {
				f2 = f1;
				f1 = f;
				f= new fib();
				
				f.amt = f1.amt + f2.amt;
				String tmp = f1.end + f2.start;
				
				if (len > 1 && tmp.length() >= len) {				
					for (int j = 0; j <= tmp.length() - len; j++)
						if (tmp.substring(j, j + len).equals(sub))
							f.amt++;
				}
				
				if (f1.start.length() < len - 1) {
					if (tmp.length() < len)
						f.start = new String(tmp);
					else
						f.start = tmp.substring(0, len - 1);
				} else
					f.start = f1.start;
				
				if (f2.end.length() < len - 1) {
					if (tmp.length() < len)
						f.end = tmp;
					else
						f.end = tmp.substring(tmp.length() - (len - 1), tmp.length());
				} else
					f.end = f2.end;
			}
			
			out.println(f.amt);
		}
		
		out.close();
	}
}

class fib {
	String start, end;
	int amt;
	fib() {
		amt = 0;
		start = end = "";
	}
}