import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		
		inp = in.next().toCharArray();
		n = inp.length;
		
		init();
		
		solve();
		
		out = new PrintWriter("output.txt");
		print(0, n - 1);
		out.close();
	}
	
	static void print(int lt, int rt) {
		if (lt > rt)
			return;
		
		if (!open(inp[lt])) {
			out.print(sym(inp[lt]));
			out.print(inp[lt]);
			print(lt + 1, rt);
			return;
		}
		
		if (open(inp[rt])) {
			print(lt, rt - 1);
			out.print(inp[rt]);
			out.print(sym(inp[rt]));
			return;
		}
		
		if (ltrt[lt][rt]) {
			out.print(inp[lt]);
			if (stay[lt][rt]) {
				print(lt + 1, pos[lt][rt] - 1);
				out.print(inp[pos[lt][rt]]);
			} else {
				print(lt + 1, pos[lt][rt]);
				out.print(sym(inp[lt]));
			}
			print(pos[lt][rt] + 1, rt);
		} else {
			print(lt, pos[lt][rt] - 1);
			if (stay[lt][rt]) {
				out.print(inp[pos[lt][rt]]);
				print(pos[lt][rt] + 1, rt - 1);
			} else {
				out.print(sym(inp[rt]));
				print(pos[lt][rt], rt - 1);
			}
			out.print(inp[rt]);
		}
	}
	
	static PrintWriter out;
	
	static void solve() {
		for (int sz = 2; sz <= n; sz++) 
			for (int lt = 0, rt = lt + sz - 1; rt < n; lt++, rt++) {
				if (!open(inp[lt])) {
					map[lt][rt] = map[lt][lt] + map[lt + 1][rt];
//					pos[lt][rt] = lt;
//					ltrt[lt][rt] = false;
//					stay[lt][rt] = false;
					continue;
				}
				
				if (open(inp[rt])) {
					map[lt][rt] = map[lt][rt - 1] + map[rt][rt];
//					pos[lt][rt] = rt;
//					ltrt[lt][rt] = true;
//					stay[lt][rt] = false;
					continue;
				}
				
				map[lt][rt] = lt + 1 == rt ? 0 : map[lt + 2][rt];
				if (inp[lt + 1] != sym(inp[lt])) {
					map[lt][rt] += map[lt + 1][lt + 1] + 1;
					pos[lt][rt] = lt + 1;
					ltrt[lt][rt] = true;
					stay[lt][rt] = false;
				} else {
					pos[lt][rt] = lt + 1;
					ltrt[lt][rt] = true;
					stay[lt][rt] = true;
				}
				
				int tmp;
				for (int i = lt + 2; i <= rt; i++) { 
					tmp = i == rt ? 0 : map[i + 1][rt];
					if (inp[lt] == sym(inp[i])) {
						tmp += map[lt + 1][i - 1];				///principal
						if (tmp < map[lt][rt]) {
							map[lt][rt] = tmp;
							stay[lt][rt] = true;
							ltrt[lt][rt] = true;
							pos[lt][rt] = i;
						}
					} else {
						tmp += 1 + map[lt + 1][i];
						if (tmp < map[lt][rt]) {
							map[lt][rt] += tmp;
							pos[lt][rt] = i;
							ltrt[lt][rt] = true;
							stay[lt][rt] = false;
						}
					}
				}
				
				tmp = rt - 1 == lt ? 0 : map[lt][rt - 2];
				if (inp[rt - 1] != sym(inp[rt])) {
					tmp += map[rt - 1][rt - 1] + 1;
					if (tmp < map[lt][rt]) {
						map[lt][rt] = tmp;
						pos[lt][rt] = rt - 1;
						ltrt[lt][rt] = false;
						stay[lt][rt] = false;
					}
				} else {
					if (tmp < map[lt][rt]) {
						map[lt][rt] = tmp;
						pos[lt][rt] = rt - 1;
						stay[lt][rt] = true;
						ltrt[lt][rt] = false;
					}
				}
				
				for (int i = lt; i < rt - 1; i++) {
					tmp = i == lt ? 0 : map[lt][i - 1];
					if (inp[i] == sym(inp[rt])) {
						tmp += map[i + 1][rt - 1];
						if (tmp < map[lt][rt]) {
							map[lt][rt] = tmp;
							pos[lt][rt] = i;
							ltrt[lt][rt] = false;
							stay[lt][rt] = true;
						}
					} else {
						tmp += 1 + map[i][rt - 1];
						if (tmp < map[lt][rt]) {
							map[lt][rt] = tmp;
							pos[lt][rt] = i;
							ltrt[lt][rt] = false;
							stay[lt][rt] = false;
						}
					}
				}
			}
	}
	
	static char sym(char c) {
		if (c == '[')
			return ']';
		
		if (c == ']')
			return '[';
		
		if (c == '(')
			return ')';
		
		return '(';
	}
	
	static void init() {
		map = new int[n][n];
		pos = new int[n][n];
		ltrt = new boolean[n][n];
		stay = new boolean[n][n];
		
		for (int i = 0; i < n; i++) {
			map[i][i] = 1;
			stay[i][i] = false;
			pos[i][i] = i;
			ltrt[i][i] = open(inp[i]);
		}
	}
	
	static boolean open(char c) {
		return c == '(' || c == '[';
	}
	
	static int[][] map, pos;
	static boolean[][] ltrt, stay;
	
	static char[] inp;
	static int n;
}