
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int n = in.nextInt(), k = in.nextInt();
		long[] v = new long[k];
		v[0] = 1;
		for (int i = 1; i < k; ++i)
			v[i] = v[i - 1] * (n - k + i);//!
		
		LinkedList<Integer> list = new LinkedList<Integer>();
		for (int i = 1; i <= n; ++i) list.add(i);
		
		long ans = 1;
		
		ListIterator<Integer> it;
		it = list.listIterator();
		
		for (int i = 0, cur, p = k - 1; i < k; ++i, --p) {
			cur = in.nextInt();
			it = list.listIterator();
			while (it.next() < cur) ans += v[p];
			it.remove();
		}
		
		out.println(ans);
		out.close();
	}
}
