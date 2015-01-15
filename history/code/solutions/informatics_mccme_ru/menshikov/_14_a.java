import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		HashMap<String, Integer> map = new HashMap<String, Integer>();
		String cur = in.readLine();
		
		map.put(cur, 0);
		
		int len = 0;
		String[] v = new String[50];
		
		for (; (v[len++] = in.readLine()) != null;);
		--len;
		
		String[] tmp;
		String[][] lr = new String[len][2];
	
		for (int i = 0; i < len; i++) {
			tmp = v[i].replace(" ", "").split("[-][>]");
			lr[i][0] = tmp[0];
			lr[i][1] = tmp[1];
		}
		
		int[] leng = new int[len];
		for (int i = 0; i < len; i++)
			leng[i] = lr[i][1].length();
			
		int p = -1, lnt = 1, i;
		
outer:	while (true) {
			for (i = 0; i < len; i++)
				if ((p = cur.indexOf(lr[i][0])) >= 0) 
					break;
			
			if (p < 0) {
				out.println(--lnt + " " + 0);
				break outer;
			} else {
				cur = replace(cur, p, leng[i], lr[i][1]);
				
				if (map.get(cur) == null) {
					map.put(cur, lnt);
				} else {
					int prev = map.get(cur);
					
					out.println(prev + " " + (lnt - prev));
					
					break outer;
				}
				
				lnt++;
			}
		}
		
		out.close();
	}
	
	static String replace(String dst, int p, int len, String src) {
		StringBuilder str = new StringBuilder(dst);
		for (int i = 0; i < len; i++)
			str.setCharAt(p + i, src.charAt(i));
		
		return new String(str);
	}
}