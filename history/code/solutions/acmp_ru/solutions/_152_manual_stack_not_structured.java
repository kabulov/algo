import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;


public class Main {
	public static void main(String[] args) throws IOException {
		in = new StreamTokenizer(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
	
		int n = nextInt();
		
		class List {
			int v;
			List next;
			List(int n) {
				v = n;
				next = null;
			}
		}
		
		List[] lst = new List[n];
		Arrays.fill(lst, null);
		
		int m = nextInt();
		for (int k = 0; k < m; ++k) {
			int i = nextInt() - 1;
			int j = nextInt() - 1;
			if (lst[i] == null) {
				lst[i] = new List(j);
			} else {
				List tmp = new List(j);
				tmp.next = lst[i];
				lst[i] = tmp;
			}
		}
			
		boolean good = true;
		
		int[] col = new int[n];
		Arrays.fill(col, 0);
		
		int[] st = new int[m + 1];
		int top;				//?
		
		int i, j;
outer:	for (int k = 0; k < n; ++k) 
			if (col[k] == 0) {
				top = 0;
				st[0] = k;
				col[k] = 1;
inner:			while (top >= 0) {
					i = st[top];
					while (lst[i] != null) {
						j = lst[i].v;
						lst[i] = lst[i].next;
						
						if (col[j] == 1) {
							good = false;
							break outer;
						}
						
						if (col[j] == 0) {
							col[j] = 1;
							++top;
							st[top] = j;
							continue inner;
						}
					}
					col[i] = -1;
					--top;
				}
				col[k] = -1;
			}
		
		out.println(good ? "Yes" : "No");
		out.close();
	}

	
	
	static StreamTokenizer in;
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}