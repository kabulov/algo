import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int n = in.nextInt();
		int k = in.nextInt();
		
		String str = in.next();
		begin = str.charAt(1) - 'A';
		end = str.charAt(0) - 'A';
		
		int[][] amt = new int[2][2];
		amt[0][0] = amt[0][1] = amt[1][0] = amt[1][1] = 0;				///!!!
		
		for (int i = 0; i < n; i++) {
			str = in.next();
			++amt[str.charAt(0) - 'A'][str.charAt(1) - 'A'];
		}
		
		map = new long[amt[0][0] + 1][amt[0][1] + 1][amt[1][0] + 1][amt[1][1] + 1][2][2];
		
		//init
		for (int aa = 0; aa <= amt[0][0]; aa++)
			for (int ab = 0; ab <= amt[0][1]; ab++)
				for (int ba = 0; ba <= amt[1][0]; ba++)
					for (int bb = 0; bb <= amt[1][1]; bb++)
						for (int i = 0; i < 2; i++)
							for (int j = 0; j < 2; j++)
								map[aa][ab][ba][bb][i][j] = -1;
		
		if (amt[0][0] > 0) {
			map[1][0][0][0][begin][0] = begin == 1 ? 0 : sgn(amt[0][0]);
			map[1][0][0][0][begin][1] = 0;
		}
		
		if (amt[0][1] > 0) {
			map[0][1][0][0][begin][0] = 0;
			map[0][1][0][0][begin][1] = begin == 1 ? 0 : sgn(amt[0][1]);
		}
		
		if (amt[1][0] > 0) {
			map[0][0][1][0][begin][0] = begin == 0 ? 0 : sgn(amt[1][0]);
			map[0][0][1][0][begin][1] = 0;
		}
		
		if (amt[1][1] > 0) {
			map[0][0][0][1][begin][0] = 0;
			map[0][0][0][1][begin][1] = begin == 0 ? 0 : sgn(amt[1][1]);
		}
		
		//solve
		long ans = 0;

		for (int AA = 0; AA <= amt[0][0]; ++AA)
			for (int AB = 0; AB <= amt[0][1]; ++AB)
				for (int BA = 0; BA <= amt[1][0]; ++BA)
					for (int BB = 0; BB <= amt[1][1]; ++BB)
						if (AA + AB + BA + BB == k) {
							if (map[AA][AB][BA][BB][begin][end] == -1) {
								aa = AA;
								ab = AB;
								ba = BA;
								bb = BB;
								solve(end);
							}
							ans += map[AA][AB][BA][BB][begin][end];
						}
		
//		aa = amt[0][0];
//		ab = amt[0][1];
//		ba = amt[1][0];
//		bb = amt[1][1];

//		if (map[amt[0][0]][amt[0][1]][amt[1][0]][amt[1][1]][begin][end] == -1)
//			solve(end);
		
//		if (map[amt[0][0]][amt[0][1]][amt[1][0]][amt[1][1]][begin][end] == 0) 
//			out.println("NO");
//		else {
//			out.println("YES");
//			out.println(map[amt[0][0]][amt[0][1]][amt[1][0]][amt[1][1]][begin][end]);
//		}
		
		if (ans == 0)
			out.println("NO");
		else {
			out.println("YES");
			out.println(ans);
		}
		out.close();
	}
	
	static void solve(int last) {
		map[aa][ab][ba][bb][begin][last] = 0;
		if (last == 0) {
			if (aa > 0) {
				--aa;
				if (map[aa][ab][ba][bb][begin][0] == -1) solve(0);
				map[aa + 1][ab][ba][bb][begin][last] += map[aa][ab][ba][bb][begin][0];
				++aa;
			}
			if (ba > 0) {
				--ba;
				if (map[aa][ab][ba][bb][begin][1] == -1) solve(1);
				map[aa][ab][ba + 1][bb][begin][last] += map[aa][ab][ba][bb][begin][1];
				++ba;
			}
		} else {
			if (ab > 0) {
				--ab;
				if (map[aa][ab][ba][bb][begin][0] == -1) solve(0);
				map[aa][ab + 1][ba][bb][begin][last] += map[aa][ab][ba][bb][begin][0];
				++ab;
			}
			if (bb > 0) {
				--bb;
				if (map[aa][ab][ba][bb][begin][1] == -1) solve(1);
				map[aa][ab][ba][bb + 1][begin][last] += map[aa][ab][ba][bb][begin][1];
				++bb;
			}
		}
	}
	
	static int aa, ab, ba, bb;
	
	static int sgn(int arg) {
		return arg > 0 ? 1 : 0;
	}
	
	static int begin, end;	
	static long[][][][][][] map;
}