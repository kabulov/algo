import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;


public class Main {
	public static void main(String[] args) throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));  
		out = new PrintWriter(System.out);
	
		int loop = Integer.parseInt(in.readLine());
		in.readLine();
		
		name = new String[20];
		vote = new int[20];
		vect = new List[1000];
		
		for (int i = 0; i < 1000; i++)
			vect[i] = new List();

		String str;
		String[] buf;
		List cur, prev;
		
		int amt, pos;
		for (int lp = 0; lp < loop; lp++) {
			n = Integer.parseInt(in.readLine());
			for (int i = 0; i < n; ++i) {
				name[i] = in.readLine();
				vote[i] = 0;
			}
			
			m = 0;
			while ((str = in.readLine()) != null && str.length() > 0) {//can check something later buf - needed?
				buf = str.split(" ");
				cur = vect[m++];
				for (int i = 0; i < n; i++) {
					cur.next = new List(Integer.parseInt(buf[i]) - 1, 1);
					cur = cur.next;
				}
			}

			// m == 0 possible ?;
			for (int i = 0; i < m; i++)
				++vote[vect[i].next.p];
			
			amt = n;
			while (true) {
				maxcnt();
				if (maxpos != -1 && vote[maxpos] > m / 2) {
					System.out.println(name[maxpos]);
					break;
				}
				
				mincnt();
				if (minamt == amt) {
					for (int i = 0; i < n; i++)
						if (vote[i] >= 0)
							System.out.println(name[i]);
					break;
				} else { //<
					amt -= minamt;
					for (int i = 0; i < n; i++)
						if (vote[i] == min)
							vote[i] = -1;
					
					for (int i = 0; i < m; i++) {
						prev = vect[i];
						cur = prev.next;
						pos = cur.p;
						while (cur != null) {
							if (vote[cur.p] < 0) {
								prev.next = cur.next;
							} else {
								prev = cur;
							}
							cur = cur.next;
						}
						if (vote[pos] == -1)
							++vote[vect[i].next.p];
					}
				}
			}
			
			if (lp + 1 < loop) System.out.println();
		}
		
		out.close();
	}
	
	static int maxpos;
	
	static void maxcnt() {
		int max = Integer.MIN_VALUE;
		maxpos = -1;
		for (int i = 0; i < n; i++)
			if (vote[i] >= 0 && vote[i] >= max) {
				if (vote[i] > max) {
					maxpos = i;
					max = vote[i];
				} else {
					maxpos = -1;
				}
			}
	}
	
	static int minamt, min;
	
	static void mincnt() {
		min = Integer.MAX_VALUE;
		minamt = 0;
		for (int i = 0; i < n; i++)
			if (vote[i] >= 0 && vote[i] <= min) { 
				if (vote[i] == min) 
					++minamt;
				else {
					min = vote[i];
					minamt = 1;
				}
			}
		//if minamt == 0 throw something
	}
	
	static BufferedReader in;
	static PrintWriter out;
	
	static int n, m;
	static String[] name;
	static int[] vote;
	static List[] vect;
}

class List {
	int p, v;
	List next;
	
	List() {
		p = v = -1;
		next = null;
	}
	
	List(int p, int v) {
		this.p = p;
		this.v = v;
		next = null;
	}
}