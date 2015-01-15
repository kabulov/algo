import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("input.txt")); 
		PrintWriter out = new PrintWriter("output.txt");
		
		int[] map = new int[33];
		for (int i = 0; i < 33; ++i) map[i] = 0;
		
		int n = Integer.parseInt(in.readLine());
		
		int tmp, mid;
		String[] buf;
		
		for (int i = 0; i < n; ++i) {
			mid = 0;
			buf = in.readLine().split("[.]");
			
			for (int j = 0; j < 4; ++j) {
				tmp = Integer.parseInt(buf[j]);
		
				if (tmp == 255) 
					mid += 8;
				else {
					mid += amt(tmp);
					break;
				}
			}
			
			map[mid] = 1;
		}
		
		for (int i = 1; i < 33; ++i) map[i] += map[i - 1];
		
		int m = Integer.parseInt(in.readLine());
		
		int fst, scd, len;
		for (int i = 0; i < m; ++i) {
			buf = in.readLine().split(" ");
			fst = str2num(buf[0].split("[.]"));
			scd = str2num(buf[1].split("[.]"));
						
			len = 0;
			for (int j = 31; j >= 0; --j) 
				if ((((fst ^ scd) >>> j) & 1) == 0) 
					++len;
				else
					break;
			
			out.println(map[len]);
		}
		
		out.close();
	}

	static int str2num(String[] src) {
		int num = 0;
		for (int i = 0; i < 4; ++i) {
			int tmp = Integer.parseInt(src[3 - i]);
			for (int j = 0; j < 8; ++j) {
				num |= (tmp & 1) << (i * 8 + j);
				tmp >>>= 1;
			}
		}
		return num;
	}
	
	static int amt(int n) {
		switch(n) {
		case 0:
			return 0;
		case 128:
			return 1;
		case 192:
			return 2;
		case 224:
			return 3;
		case 240:
			return 4;
		case 248:
			return 5;
		case 252:
			return 6;
		case 254:
			return 7;
		case 255:
			return 8;
		default://
			return -1;
		}
	}
}
