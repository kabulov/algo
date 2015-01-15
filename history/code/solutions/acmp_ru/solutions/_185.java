import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int n = in.nextInt();
		int k = in.nextInt();
		
		v = new ArrayList[n]; 
		for (int i = 0; i < n; i++) 
			v[i] = new ArrayList<Integer>();
		
		int p;
		while ((p = in.nextInt()) != 0) 
			v[p - 1].add(in.nextInt() - 1);
		
		bool = new boolean[n];
		Arrays.fill(bool, false);
		
		dfs(k - 1);
		
		for (int i = 1; i < n; i++)
			bool[0] &= bool[i];
		
		out.println(bool[0] ? "Yes" : "No");
		out.close();
	}

	static void dfs(int p) {
		bool[p] = true;
		int siz = v[p].size();
		for (int i = 0; i < siz; i++)
			if (!bool[pos = v[p].get(i)]) 
				dfs(pos);
	}
	
	static int pos;
	static ArrayList<Integer>[] v;
	static boolean[] bool;
}