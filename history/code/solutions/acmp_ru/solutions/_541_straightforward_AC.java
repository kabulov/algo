import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;


public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		String SIn = in.readLine();
		String TIn = in.readLine();
		
		char[] max, min, s, t;
		s = SIn.toCharArray();
		t = TIn.toCharArray();
		
		int len = s.length;
		
		boolean sbool = false;
		boolean tbool = false;
		
		char tmp;
		boolean compare;
		max = new char[len];
		for (int i = 0; i < len; i++) {
			if (s[0] != '0') {
				if (sbool) {
					compare = false;
					for (int j = 0; j < len; j++)
						if (s[j] != max[j]) {
							if (s[j] > max[j])
								compare = true;
							break;
						}
						
					if (compare) {
						for (int j = 0; j < len; j++)
							max[j] = s[j];
					}
				} else {
					for (int j = 0; j < len; j++)
						max[j] = s[j];
					sbool = true;
				}
			}
			
			tmp = s[0];
			for (int j = 0; j < len - 1; j++)
				s[j] = s[j + 1];
			s[len - 1] = tmp;				
		}

		len = t.length;
		min = new char[len];
		for (int i = 0; i < len; i++) {
			if (t[0] != '0') {
				if (tbool) {
					compare = false;
					for (int j = 0; j < len; j++)
						if (t[j] != min[j]) {
							if (t[j] < min[j])
								compare = true;
							break;
						}
					
					if (compare) {
						for (int j = 0; j < len; j++)
							min[j] = t[j];
					}
				} else {
					for (int j = 0; j < len; j++)
						min[j] = t[j];
					tbool = true;
				}
			}
			
			tmp = t[0];
			for (int j = 0; j < len - 1; j++)
				t[j] = t[j + 1];
			t[len - 1] = tmp;				
		}
		
		StringBuilder mx = new StringBuilder();
		StringBuilder mn = new StringBuilder();
		
		mx.setLength(s.length);
		mn.setLength(t.length);
		
		len = s.length;
		for (int i = 0; i < len; i++)
			mx.setCharAt(i, max[i]);
		
		len = t.length;
		for (int i = 0; i < len; i++)
			mn.setCharAt(i, min[i]);
		
		out.println((new BigInteger(new String(mx))).subtract(new BigInteger(new String(mn))));
		
		out.close();		
	}
}