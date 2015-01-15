import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.LinkedList;

public class Main {
	public static void main(String[] args) throws IOException {
		in = new StreamTokenizer(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		n = nextInt();
		inmap = new elem[n + 2][n + 2];
		
		elem sentinel = new elem(Integer.MIN_VALUE);
		for (int i = 0; i < n + 2; i++)
			inmap[i][0] = inmap[i][n + 1] = inmap[0][i] = inmap[n + 1][i] = sentinel;
		
		boolean doit = true;
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				inmap[i][j] = new elem(nextInt());
				if (inmap[i][j].inp != 0)
					doit = false;
			}
		}
		
		if (doit) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++)
					out.print("0 ");
				out.println();
			}
			out.close();
			return;
		}
		
		q = new LinkedList<que>();
		p = new LinkedList<pos>();
		
		pos pstn;
		int ic, jc;
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (inmap[i][j].inp > 0) {
					inmap[i][j].inp *= -1;
					p.add(new pos(i, j));
					while (!p.isEmpty()) {
						pstn = p.poll();
						ic = pstn.i;
						jc = pstn.j;
						
						check(ic, jc, 1, 0);
						check(ic, jc, -1, 0);
						check(ic, jc, 0, 1);
						check(ic, jc, 0, -1);
					}
				}
			}
		}
		
		que mid;
		while (!q.isEmpty()) {
			mid = q.poll();
			
			if (mid.dist > 0 && inmap[mid.i][mid.j].dist < 0)
				continue;
			
			offer(mid, 1, 0);
			offer(mid, -1, 0);
			offer(mid, 0, 1);
			offer(mid, 0, -1);
		}
		
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (inmap[i][j].inp < 0)
					out.print(-inmap[i][j].inp + " ");
				else
				if (inmap[i][j].dist < 0)
					out.print(0 + " ");
				else
					out.print(Math.abs(inmap[inmap[i][j].p.i][inmap[i][j].p.j].inp) + " ");
			}
			out.println();
		}
		
		out.close();
	}
	
	static void offer(que mid, int iinc, int jinc) {
		int i = mid.i + iinc;
		int j = mid.j + jinc;
		int d = mid.dist + (mid.dist >= 0 ? 1 : -1);
		int pi = mid.src.i;
		int pj = mid.src.j;
		
		if (inmap[i][j].inp == 0) {
			if (inmap[i][j].dist == 0) {
				inmap[i][j].dist = d;
				inmap[i][j].p.i = pi;
				inmap[i][j].p.j = pj;
				q.add(new que(i, j, d, pi, pj));
			} else 
			if (inmap[i][j].dist > 0) {
				if (d < 0) {
					if (-d == inmap[i][j].dist) {
						inmap[i][j].dist *= -1;
						q.add(new que(i, j, d, pi, pj));
					}
				} else
				if (inmap[i][j].dist == d) {
					if (!(inmap[i][j].p.i == pi && inmap[i][j].p.j == pj)) {
						inmap[i][j].dist *= -1;
						q.add(new que(i, j, -d, pi, pj));
					}
				}
			}
			
		}
	}
	
	static LinkedList<que> q;
	static LinkedList<pos> p;
	
	static void check(int i, int j, int iinc, int jinc) {
		if (inmap[i + iinc][j + jinc].inp > 0) {
			inmap[i + iinc][j + jinc].inp *= -1;
			p.add(new pos(i + iinc, j + jinc));
		} else
		if (inmap[i + iinc][j + jinc].inp == 0 && inmap[i][j].dist == 0) {
			inmap[i][j].dist = Integer.MIN_VALUE;
			q.add(new que(i, j, 0, i, j));
		}
	}
	
	static int n;
	static elem[][] inmap;
	
	static StreamTokenizer in;
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}

class que {
	int i, j, dist;
	pos src;
	que(int ic, int jc, int d, int si, int sj) {
		i = ic;
		j = jc;
		dist = d;
		src = new pos(si, sj);
	}
}

class pos {
	int i, j;
	pos(int ic, int jc) {
		i = ic;
		j = jc;
	}
}

class elem {
	int dist, inp;
	pos p;
	elem(int n) {
		dist = 0;
		p = new pos(0, 0);
		inp = n;
	}
}