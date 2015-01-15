import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		String inp = in.readLine();
		String[] buf;
		buf = inp.split(" ");
		int n = Integer.parseInt(buf[0]);
		int m = Integer.parseInt(buf[1]);
		
		vect = new name[n];
		
		boolean dot;
		for (int i = 0; i < n; i++) {
			inp = in.readLine();
			dot = inp.indexOf('.') >= 0;
			buf = inp.split("[.]");
			if (buf.length == 1)
				vect[i] = new name(buf[0], "", dot, i);
			else
				vect[i] = new name(buf[0], buf[1], dot, i);
		}
		
		rand = new Random();
		
		String fst, scd;
		int len = vect.length, tmp, k;
		for (int i = 0; i < m; i++) {
			if (len <= 1) {
				len = 0;
				break;
			}

			tmp = 1;
			k = 0;
			
			if (i % 2 == 0) {
				sortext(0, len - 1);
				
				for (int j = 1; j < len; j++) {
					fst = vect[j].ext;
					scd = vect[j - 1].ext;
					if (fst.length() == scd.length() && equal(fst, scd)) {
						tmp++;
					} else {
						if (tmp == 1)
							++k;
						tmp = 1;
					}
					vect[j - k] = vect[j];
				}
				if (tmp == 1)
					k++;
				
				len -= k;
			} else {
				sortname(0, len - 1);
				
				for (int j = 1; j < len; j++) {
					fst = vect[j].name;
					scd = vect[j - 1].name;
					if (fst.length() == scd.length() && equal(fst, scd))
						tmp++;
					else {
						if (tmp == 1)
							k++;
						tmp = 1;
					}
					vect[j - k] = vect[j];	
				}
				if (tmp == 1)
					k++;
				
				len -=k;
			}
		}
		
		Arrays.sort(vect, 0, len, new byorder());
		
		out.println(len);
		for (int i = 0; i < len; i++)
			out.println(vect[i].name + (vect[i].dot ? "." : "") + vect[i].ext);
		
		out.close();
	}
	
	static boolean equal(String a, String b) {
		int len = a.length();
		for (int i = 0; i < len; ++i)
			if (a.charAt(i) != b.charAt(i))
				return false;
		
		return true;
	}
	
	static name tmp;
	static void sortext(int l, int r) {
		int i = l, j = r;
		String mid = vect[l + rand.nextInt(r - l + 1)].ext;
		while (i <= j) {
			while (vect[i].ext.compareTo(mid) < 0) ++i;
			while (mid.compareTo(vect[j].ext) < 0) --j;
			if (i <= j) {
				tmp = vect[i];
				vect[i] = vect[j];
				vect[j] = tmp;
				++i;
				--j;
			}
		}
		if (l < j) sortext(l, j);
		if (i < r) sortext(i, r);
	}
	
	static void sortname(int l, int r) {
		int i = l, j = r;
		String mid = vect[l + rand.nextInt(r - l + 1)].name;
		while (i <= j) {
			while (vect[i].name.compareTo(mid) < 0) ++i;
			while (mid.compareTo(vect[j].name) < 0) --j;
			if (i <= j) {
				tmp = vect[i];
				vect[i] = vect[j];
				vect[j] = tmp;
				++i;
				--j;
			}
		}
		if (l < j) sortname(l, j);
		if (i < r) sortname(i, r);
	}
	
	static Random rand;
	static name[] vect;
}

class name {
	String name, ext;
	boolean dot;
	int order;

	name(String n, String e, boolean d, int ord) {
		name = n;
		ext = e;
		order = ord;
		dot = d;
	}
}

class byorder implements Comparator<name> {
	public int compare(name f, name s) {
		return f.order - s.order;
	}
}
