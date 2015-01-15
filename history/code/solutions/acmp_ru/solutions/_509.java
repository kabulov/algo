import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		src = new char[2][];
		dst = new char[2][];
		
		src[0] = in.readLine().trim().toCharArray();
		src[1] = in.readLine().trim().toCharArray();
		dst[0] = in.readLine().trim().toCharArray();
		dst[1] = in.readLine().trim().toCharArray();
		
		dstcode = encode(dst);
		if (encode(src) == dstcode) {
			out.println(0);
			out.close();
			return;
		}
		
		set = new HashSet<Long>();
		set.add(encode(src));
		
		que = new long[40320]; //8!
		len = new int[40320]; //8!
		
		que[0] = encode(src);
		len[0] = 0;
		
		add = 1;
		rem = 0;
		
		flag = false;
		map = new char[2][4];
algo:	while (rem < add) {
			decode(que[rem], map);
			int i = 0, j = 0; //may optimize here
outer:		for (i = 0; i < 2; i++)
				for (j = 0; j < 4; j++)
					if (map[i][j] == '#')
						break outer;
			
			for (int k = 0; k < 4; k++) {
				check(i, j, i + cst[k][0], j + cst[k][1]);
				if (flag) break algo;
			}
			
			++rem;
		}
		
		if (rem == add) 
			out.println(-1);
		else
			out.println(len[add]);
		
		out.close();
	}
	
	static int[][] cst = {
		{0, 1},
		{0, -1},
		{1, 0},
		{-1, 0}
	};
	
	static boolean flag;
	static long dstcode;
	static void check(int i, int j, int itry, int jtry) {
		if (!good(itry, jtry)) return;
		swap(i, j, itry, jtry);
		encode(map);
		if (!set.contains(code)) {
			set.add(code);
			que[add] = code;
			len[add] = len[rem] + 1;
			if (code == dstcode) {
				flag = true;
				return;
			}
			++add;
		}
		swap(i, j, itry, jtry);
	}
	
	static char buf;
	static void swap(int i1, int j1, int i2, int j2) {
		buf = map[i1][j1];
		map[i1][j1] = map[i2][j2];
		map[i2][j2] = buf;
	}
	
	static boolean good(int i, int j) {
		return 0 <= i && i < 2 && 0 <= j && j < 4;
	}
	
	static HashSet<Long> set;
	static long[] que;
	static int[] len;
	static int add, rem;
	
	static char[][] map, src, dst;
	
	static long base = 63, code, digit;
	static long encode(char[][] map) {
		code = 0;
		for (int i = 0; i < 2; ++i)
			for (int j = 0; j < 4; ++j) {
				if (Character.isDigit(map[i][j]))
					digit = map[i][j] - '0';
				else 
				if (Character.isLetter(map[i][j])) {
					if (Character.isLowerCase(map[i][j]))
						digit = map[i][j] - 'a' + 10;
					else
						digit = map[i][j] - 'A' + 36;
				} else
					digit = 62;
				code = code * base + digit;
			}
		return code;
	}
	
	static void decode(long hash, char[][] map) {
		for (int i = 1; i >= 0; --i)
			for (int j = 3; j >= 0; --j) {
				digit = hash % base;
				hash /= base;
				if (digit == 62)
					map[i][j] = '#';
				else
				if (36 <= digit)
					map[i][j] = (char)('A' + digit - 36);
				else
				if (10 <= digit)
					map[i][j] = (char)('a' + digit - 10);
				else
					map[i][j] = (char)('0' + digit);
			}
	}
}