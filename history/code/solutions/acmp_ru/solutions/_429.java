import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		int n = Integer.parseInt(in.readLine());
		vect = new LinkedList[n];
		
		for (int i = 0; i < n; i++)
			vect[i] = new LinkedList<node>();
		
		String str;
		String[] buf;
		int par, val;

		for (int i = 1; i < n; i++) {
			str = in.readLine();
			buf = str.split(" ");
			par = Integer.parseInt(buf[1]) - 1;

			if (str.charAt(0) == 'N') {
				vect[par].add(new node(i));
			} else {
				if (buf[2].charAt(0) == '0')
					val = 0;
				else {
					val = 1;
					if (buf[2].charAt(0) == '-')
						val = -1;
				}
				vect[par].add(new node(i, val));
			}
		}
		
		if ((val = solve(0, 1)) == 1)
			out.print("+");
		
		out.println(val);
		out.close();
	}

	static int solve(int node, int player) {
		int best, val;
		if (player == 1) {
			best = -1;
			for (node x : vect[node]) {
				if (!x.leaf) {
					val = solve(x.num, 2);
				} else
					val = x.val;
				
				if (val > best)
					best = val;
				
				if (best == 1) 
					return 1;
			}
			return best;
		} else {
			best = 1;
			for (node x : vect[node]) {
				if (!x.leaf)
					val = solve(x.num, 1);
				else
					val = x.val;
				
				if (val < best)
					best = val;
				
				if (best == -1)
					return -1;
			}
			return best;
		}
	}
	
	static LinkedList<node>[] vect;
}

class node {
	boolean leaf;
	int num, val;
	
	node(int num) {
		leaf = false;
		this.num = num;
	}
	
	node (int num, int val) {
		leaf = true;
		this.num = num;
		this.val = val;
	}
}