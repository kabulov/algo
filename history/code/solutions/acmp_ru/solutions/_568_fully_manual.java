import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		in = new StreamTokenizer(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		int n = nextInt();
		List[] v = new List[n];
		for (int i = 0; i < n; ++i)
			v[i] = null;
		
		int m = nextInt();
		short ti, tj;
		List tmp;
		for (int i = 0; i < m; ++i) {
			ti = (short)(nextInt() - 1);
			tj = (short)(nextInt() - 1);

			tmp = new List(tj);
			tmp.next = v[ti];
			v[ti] = tmp;
			
			tmp = new List(ti);
			tmp.next = v[tj];
			v[tj] = tmp;
		}
		
		List st = new List((short)0);
		
		int sz = 0;
		List ans = null;
		while (st != null) {
			ti = st.v;
			if (v[ti] != null) {
				tmp = new List(v[ti].v);
				tmp.next = st;
				st = tmp;
				v[ti] = v[ti].next;
			} else {
				++sz;
				tmp = new List(st.v);
				st = st.next;
				tmp.next = ans;
				ans = tmp;
			}
		}
		
		out.println(sz - 1);
		for (int i = 0; i < sz; ++i) {
			out.print((ans.v + 1) + " ");
			ans = ans.next;
		}
		
		out.close();
	}
	
	static StreamTokenizer in;
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}

class List {
	short v;
	List next;
	
	List(short node) {
		v = node;
		next = null;
	}
}