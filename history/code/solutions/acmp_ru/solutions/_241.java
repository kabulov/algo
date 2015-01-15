import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int k = in.nextInt();
		
		int max = solve(0, k);
	
		for (int i = 1; i < 7; ++i) {
			int m = solve(i, k);
			if (m > max) max = m;
		}
		
		out.println(max);
		out.close();
	}
	
	static int solve(int d, int k) {
		date dat = new date(d);
		
		int s = 0;
		
		for (int i = 1, j = 0; ; dat.next(), ++i)  
			if (i <= k) {
				if (dat.name == 0 || dat.name == 6) ++j;
			} else  
			if ((dat.mon == 2 && dat.day == 23) || (dat.mon == 3 && dat.day == 8)) {	
				++s;
				if (dat.name == 0 || dat.name == 6) ++j;
			} else
			if (dat.name == 0 || dat.name == 6) {
				++s;
			} else 
			if (j > 0) { 
				++s;
				--j;
			} else		
				break;		
		
		return s + k;
	}
}

class date {
	int mon, day, name;
	int[] v = {31, 28, 31};
	
	date(int c) {
		mon = 1;
		day = 1;
		name = c;
	}
	
	void next() {
		++day;
		if (day > v[mon - 1]) {
			day = 1;
			++mon;
		}
		name = (name + 1) % 7;
	}
}

/*		for (int i = 1; i <= ans; ++i) {
if (((dat.mon == 2 && dat.day == 23) || (dat.mon == 3 && dat.day == 8) || (i <= k)) && (dat.name == 0 || dat.name == 6)) ++ans;
dat.next();
}

while ((dat.mon == 2 && dat.day == 23) || (dat.mon == 3 && dat.day == 8) || dat.name == 0 || dat.name == 6) {	
++ans;
if (((dat.mon == 2 && dat.day == 23) || (dat.mon == 3 && dat.day == 8)) && (dat.name == 0 || dat.name == 6)) {
	++ans;
	dat.next();
}
dat.next();
}
*/		
