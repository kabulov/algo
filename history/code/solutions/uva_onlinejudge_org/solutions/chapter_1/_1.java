import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		int[] len = new int[1000001];
		Arrays.fill(len, 0);
		
		int ln;
		long tmp;
		for (int i = 1; i <= 1000000; ++i) {
			tmp = i;
			ln = 1;
			while (tmp > 1) {
				++ln;
				if ((tmp & 1) == 1) 
					tmp = 3 * tmp + 1;
				else
					tmp >>= 1;
			}
			len[i] = ln;
		}
		
		Scanner in = new Scanner(System.in);
		
		int lt, rt, mx;
		while (in.hasNext()) {
			lt = in.nextInt();
			rt = in.nextInt();
		
			System.out.print(lt);
			System.out.print(" ");
			System.out.print(rt);
			System.out.print(" ");

			if (lt > rt) {
				mx = lt;
				lt = rt;
				rt = mx;
			}
			
			mx = len[lt];
			for (int i = lt + 1; i <= rt; ++i)
				if (len[i] > mx)
					mx = len[i];
			
			System.out.println(mx);
		}
	}
}
