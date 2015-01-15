import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		in = new StreamTokenizer(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt"); 

		src = new int[6];
		for (int i = 0; i < 6; ++i) src[i] = nextInt();
		
		dst = new int[6];
		for (int i = 0; i < 6; ++i) dst[i] = nextInt();
		
		mid = new int[6];
		boolean ans = false;
		
outer:	for (int i = 0; i < 4; ++i) {
			copy();
			for (int j = 0; j < i; ++j) showLeft();

			for (int j = 0; j < 4; ++j) {
				if (equal()) {
					ans = true;
					break outer;
				}
				if (j < 3) rotLeft(); //nimportant 'if'
			}
		}
		
		if (!ans) {
			copy();
			showUp();
			for (int j = 0; j < 4; ++j) {
				if (equal()) {
					ans = true;
					break;
				}
				if (j < 3) rotLeft(); //nimportant 'if'
			}
		}
		
		if (!ans) {
			copy();
			showUp();
			showUp();
			showUp();
		
			for (int j = 0; j < 4; ++j) {
				if (equal()) {
					ans = true;
					break;
				}
				if (j < 3) rotLeft(); //nimportant 'if'
			}
		}
		
		out.println(ans ? "YES" : "NO");
		out.close();
	}
	
	static void rotLeft() {
		int t3 = mid[2], t4 = mid[3];
		mid[2] = mid[5];
		mid[3] = mid[4];
		mid[4] = t3;
		mid[5] = t4;
	}
	
	static void showLeft() {
		int t1 = mid[0], t2 = mid[1];
		mid[0] = mid[4];
		mid[1] = mid[5];
		mid[4] = t2;
		mid[5] = t1;
	}
	
	static void showUp() {
		int t1 = mid[0], t2 = mid[1];
		mid[0] = mid[2];
		mid[1] = mid[3];
		mid[2] = t2;
		mid[3] = t1;
	}
	
	static void copy() {
		for (int i = 0; i < 6; ++i) mid[i] = src[i];
	}
	
	static int[] mid;
	
	static boolean equal() {
		for (int i = 0; i < 6; ++i)
			if (mid[i] != dst[i])
				return false;
		
		return true;
	}
	
	static int[] src, dst;
	
	static StreamTokenizer in;
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}
