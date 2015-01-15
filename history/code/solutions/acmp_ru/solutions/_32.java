import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		int a = in.nextInt();
		int b = in.nextInt();
		
		v = new int[10]; //9 enough
		
		if (a >= 0 && b >= 0)
			out.println(getMax(a) - getMin(b));
		else
		if (a >= 0 && b <= 0)
			out.println(getMax(a) + getMax(-b));
		else
		if (a <= 0 && b >= 0)
			out.println(-getMin(-a) - getMin(b));
		else
		if (a <= 0 && b <= 0)
			out.println(-getMin(-a) + getMax(-b));

		out.close();
	}

	static int[] v;
	static int getMax(int n) {
		int len = 0;
		while (n > 0) {
			v[len++] = n % 10;
			n /= 10;
		}
		
		int max, tmp;
		for (int i = 0; i < len - 1; i++) {
			max = i;
			for (int j = i + 1; j < len; j++)
				if (v[j] > v[max])
					max = j;
			
			tmp = v[i];
			v[i] = v[max];
			v[max] = tmp;
		}
		
		max = 0;
		for (int i = 0; i < len; i++)
			max = max * 10 + v[i];
		
		return max;
	}
	
	static int getMin(int n) {
		int len = 0;
		while (n > 0) {
			v[len++] = n % 10;
			n /= 10;
		}
		
		int min, tmp;
		for (int i = 0; i < len - 1; i++) {
			min = i;
			for (int j = i + 1; j < len; j++)
				if (v[j] < v[min])
					min = j;
			
			tmp = v[i];
			v[i] = v[min];
			v[min] = tmp;
		}
		
		for (min = 0; min < len && v[min] == 0; min++);
		if (min < len) {
			tmp = v[min];
			v[min] = v[0];
			v[0] = tmp;
		}
		
		min = 0;
		for (int i = 0; i < len; i++)
			min = min * 10 + v[i];
		
		return min;
	}
}
