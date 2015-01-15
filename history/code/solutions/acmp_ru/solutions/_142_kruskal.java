import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.Random;


public class Main {
	public static void main(String[] args) throws IOException {
		in = new StreamTokenizer(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		int n = nextInt();
		int m = nextInt();
		
		fst = new int[m];
		scd = new int[m];
		cst = new int[m];
		
		for (int i = 0; i < m; ++i) {
			fst[i] = nextInt() - 1;
			scd[i] = nextInt() - 1;
			cst[i] = nextInt();
		}
		
		rand = new Random();
		sort(0, m - 1);
		
		Set s = new Set(n);
		int amt = n - 1;
		int cost = 0;
		
		int p = 0;
		while (amt > 0) {
			if (s.get(fst[p]) != s.get(scd[p])) {
				--amt;
				cost += cst[p];
				s.unite(fst[p], scd[p]);
			}
			++p;
		}
		
		out.println(cost);
		out.close();
	}
	
	static int[] fst, scd, cst;

	static Random rand;
	static int mid, tmp;
	static void sort(int lt, int rt) {
		int i = lt, j = rt;
		mid = cst[lt + rand.nextInt(rt - lt + 1)];
		for (; i <= j;) {
			for (; cst[i] < mid; ++i);
			for (; mid < cst[j]; --j);
			if (i <= j) {
				tmp = cst[i];
				cst[i] = cst[j];
				cst[j] = tmp;
				
				tmp = fst[i];
				fst[i] = fst[j];
				fst[j] = tmp;
				
				tmp = scd[i];
				scd[i] = scd[j];
				scd[j] = tmp;
				
				++i;
				--j;
			}
		}
		if (lt < j) sort(lt, j);
		if (i < rt) sort(i, rt);
	}
	
	
	static StreamTokenizer in;
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}

class Set {
	int[] rep;
	int[] rank;
	
	Set(int size) {
		rep = new int[size];
		rank = new int[size];
		Arrays.fill(rank, 0);
		for (int i = 0; i < size; ++i)
			rep[i] = i;
	}
	
	int get(int a) {
		return (rep[a] == a) ? a : (rep[a] = get(rep[a]));
	}
	
	void unite(int a, int b) {
		a = get(a);
		b = get(b);
		if (rank[a] < rank[b])
			rep[a] = rep[b];
		else {
			if (rank[a] == rank[b]) ++rank[a];
			rep[b] = rep[a];
		}
	}
}