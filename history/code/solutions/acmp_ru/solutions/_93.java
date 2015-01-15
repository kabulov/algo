import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		int n = Integer.parseInt(in.readLine());
		
		ord = new int[n];
		Arrays.fill(ord, 0);
		
		BOR b = new BOR('\0');
		for (int i = 0; i < n; i++) {
			b.add(in.readLine(), 0, i);
		}
		
		int m = Integer.parseInt(in.readLine());
		for (int i = 0; i < m; i++) {
			b.check(in.readLine(), 0, true);
		}
		
		for (int i = 0; i < n; i++)
			out.print(ord[i] + " ");
		
		out.close();
	}
	
	static int[] ord;
}

class BOR{
	char ch;
	ArrayList<Integer> ord;
	HashMap<Character, BOR> bor;

	BOR(char c) {
		ch = c;
		ord = new ArrayList<Integer>();
		bor = new HashMap<Character, BOR>();
	}
	
	void add(String v, int pos, int ord) {
		if (pos == v.length()) {
			this.ord.add(ord);
			return;
		}
		
		Character c = v.charAt(pos);
		
		BOR b;
		if ((b = bor.get(c)) == null) {
			bor.put(c, b = new BOR(c));
		}

		b.add(v, pos + 1, ord);		
	}
	
	
	void check(String str, int pos, boolean flag) {
		if (pos == str.length()) {
			if (!ord.isEmpty() && !flag)
				for (int i = 0; i < ord.size(); i++)
					Main.ord[ord.get(i)]++;  
			return;
		}
		
		BOR b;
		Character c = str.charAt(pos);
		
		if (flag) {
			Iterator<BOR> iter = bor.values().iterator();
			while (iter.hasNext()) {
				b = iter.next();
				if (c == b.ch)
					b.check(str, pos + 1, true);
				else
					b.check(str, pos + 1, false);
			}
		} else {
			if ((b = bor.get(c)) != null)
				b.check(str, pos + 1, false); 
		}
	}

//	b.prn(new Stack<Character>(), b);
	
//	void prn(Stack<Character> s, BOR b) {
//		if (b.ord != -1) {
//			for (int i = 0; i < s.size(); i++)
//				System.out.print(s.get(i));
//			System.out.println(" " + b.ord);
//		}

//		char c;
//		BOR bb;
//		for (int i = 0; i < 26; i++) {
//			c = (char)(i + 65);
//			if ((bb = b.bor.get(c)) != null) {
//				s.push(c);
//				prn(s, bb);
//				s.pop();
//			}
//		}
//	}
}

