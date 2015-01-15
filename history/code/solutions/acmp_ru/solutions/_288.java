
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		String str;
		char[] v;
		
		char type = '\0';
		int ans = 0;
		boolean instr = false, open = false;
		
		while ((str = in.readLine()) != null) {
			v = str.toCharArray();
outer:		for (int i = 0; i < v.length; ++i) {
				switch(v[i]) {
				case '\'':
					if (open) continue;
					instr = !instr;
					break;
				case '/':
					if (open) continue;
					if (instr) continue;
					if (i + 1 < v.length && v[i + 1] == '/') {
						++ans;
						break outer;
					}
					break;
				case '{':
					if (open) continue;
					if (instr) continue;
					open = true;
					++ans;
					type = '}';
					break;
				case '}':
					if (open && type == '}') open = false;					
					break;
				case '(':
					if (open) continue;
					if (instr) continue;
					if (i + 1 < v.length && v[i + 1] == '*') {
						++ans;
						open = true;
						type = ')';
					}
					break;
				case ')':
					if (open && type == ')') 
						if (i > 0 && v[i - 1] == '*') open = false;						
					break;
				default:
					
				}
			}
		}
		
		out.println(ans);
		out.close();
	}

}
