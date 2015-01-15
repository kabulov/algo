import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		f = in.next().split("[:]");
		s = in.next().split("[:]");
		
		fh = Integer.parseInt(f[0]);
		fm = Integer.parseInt(f[1]);
		fs = Integer.parseInt(f[2]);
		sh = Integer.parseInt(s[0]);
		sm = Integer.parseInt(s[1]);
		ss = Integer.parseInt(s[2]);
		
		v = new int[10];
		for (int i = 0; i < 10; ++i) v[i] = 0;
		
		if (fh == sh && fm == sm) {
			v[fh % 10] += ss - fs + 1;
			v[fh / 10] += ss - fs + 1;
			v[fm % 10] += ss - fs + 1;
			v[fm / 10] += ss - fs + 1;					   
			
			for (int i = fs; i <= ss; ++i) {
				++v[i % 10];
				++v[i / 10];
			}
			
			for (int i = 0; i < 10; ++i) out.println(v[i]);
			out.close();
			return;
		}
		
		v[fh % 10] += 60 - fs;
		v[fh / 10] += 60 - fs;
		v[fm % 10] += 60 - fs;
		v[fm / 10] += 60 - fs;					   
		for (int i = fs; i < 60; ++i) {
			++v[i % 10];
			++v[i / 10];
		}
		
		if (fh == sh) {
			v[fh % 10] += (sm - (fm + 1)) * 60;
			v[fh / 10] += (sm - (fm + 1)) * 60;
			for (int i = fm + 1; i < sm; ++i) {
				v[i % 10] += 60;
				v[i / 10] += 60;
			}
			for (int i = 0; i < 10; ++i)
				v[i] += (sm - (fm + 1)) * 16;
			
			v[sh % 10] += ss + 1;
			v[sh / 10] += ss + 1;
			v[sm % 10] += ss + 1;
			v[sm / 10] += ss + 1;
			for (int i = 0; i <= ss; ++i) {
				++v[i % 10];
				++v[i / 10];
			}			
			
			for (int i = 0; i < 10; ++i) out.println(v[i]);
			out.close();
		
			return;
		}
		
		v[fh % 10] += (60 - (fm + 1)) * 60;
		v[fh / 10] += (60 - (fm + 1)) * 60;
		for (int i = fm + 1; i < 60; ++i) {
			v[i % 10] += 60;
			v[i / 10] += 60;
		}
		for (int i = 0; i < 10; ++i) 
			v[i] += (60 - (fm + 1)) * (i < 6 ? 16 : 6);
		
		for (int i = fh + 1; i < sh; ++i) {
			v[i % 10] += 3600;
			v[i / 10] += 3600;
		}
		
		for (int i = 0; i < 10; ++i)
			v[i] += (sh - (fh + 1)) * 60 * (i < 6 ? 16 : 6);
		
		for (int i = 0; i < 10; ++i)
			v[i] += (sh - (fh + 1)) * 60 * (i < 6 ? 16 : 6);
		
		v[sh % 10] += sm * 60;
		v[sh / 10] += sm * 60;
		for (int i = 0; i < sm; ++i) {
			v[i % 10] += 60;
			v[i / 10] += 60;
		}
		for (int i = 0; i < 10; ++i) 
			v[i] += sm * (i < 6 ? 16 : 6);
		
		v[sh % 10] += ss + 1;
		v[sh / 10] += ss + 1;
		v[sm % 10] += ss + 1;
		v[sm / 10] += ss + 1;
		
		for (int i = 0; i <= ss; ++i) {
			++v[i % 10];
			++v[i / 10];
		}
	
		/*
		if (fh == sh) {
			if (fm == sm) {
				v[fh % 10] += ss - fs + 1;
				v[fh / 10] += ss - fs + 1;
				v[fm % 10] += ss - fs + 1;
				v[fm / 10] += ss - fs + 1;					   
				
				for (int i = fs; i <= ss; ++i) {
					++v[i % 10];
					++v[i / 10];
				}
			} else {
				v[fh % 10] += (60 - fs) + (ss + 1) + 60 * (sm - (fm + 1));
				v[fh / 10] += (60 - fs) + (ss + 1) + 60 * (sm - (fm + 1));
				
				v[fm % 10] += 60 - fs; 
				v[fm / 10] += 60 - fs;
				v[sm % 10] += ss + 1;
				v[sm / 10] += ss + 1;
				for (int i = fm + 1; i < sm; ++i) {
					v[i % 10] += 60;
					v[i / 10] += 60;
				}
				
				for (int i = fs; i < 60; ++i) {
					++v[i % 10];
					++v[i / 10];
				}
				for (int i = 0; i <= ss; ++i) {
					++v[i % 10];
					++v[i / 10];
				}
				for (int i = 0; i < 10; ++i) 
					v[i] += (sm - (fm + 1)) * 16;
			}
		} else {
			
		}
		*/
		for (int i = 0; i < 10; ++i) out.println(v[i]);
/*		
		for (int i = 0; i < 10; ++i) out.print(v[i] + " ");
		out.println();
		for (int i = 0; i < 10; ++i) v[i] = 0;
		while (true) {
			++v[fh % 10];
			++v[fh / 10];
			++v[fm % 10];
			++v[fm / 10];
			++v[fs % 10];
			++v[fs / 10];
			
			if (fh == sh && fm == sm && fs == ss) break;
			
			++fs;
			if (fs == 60) {
				fs = 0;
				++fm;
				if (fm == 60) {
					fm = 0;
					++fh;
				}
			}
		}
		
		for (int i = 0; i < 10; ++i) out.print(v[i]+ " "); 
*/		out.close();
	}
	
	static int[] v;
	static int fh, fm, fs, sh, sm, ss;
	static String[] f, s;
}