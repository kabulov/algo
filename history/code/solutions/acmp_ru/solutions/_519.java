import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		String str = in.next();
		
		out.println(Min(str) + " " + Max(str));
		
		out.close();
	}
	
	static int Min(String s) {
		int[] v = new int[10];
		int len = s.length();
		for (int i = 0; i < len; i++)
			v[i] = s.charAt(i) - '0';
		
		int min, tmp;
		for (int i = 0; i < len - 1; i++) {
			min = i;
			for (int j = i + 1; j < len; j++)
				if (v[j] < v[min])
					min = j;
			
			tmp = v[min];
			v[min] = v[i];
			v[i] = tmp;
		}
		
		int i = 0;
		while (v[i] == 0)
			i++;
		
		tmp = v[i];
		v[i] = v[0];
		v[0] = tmp;
		
		int ans = 0;
		for (i = 0 ; i < len; i++)
			ans = ans * 10 + v[i];
		
		return ans;
	}
	
	static int Max(String s) {
		int[] v = new int[10];
		int len = s.length();
		for (int i = 0; i < len; i++)
			v[i] = s.charAt(i) - '0';
		
		int max, tmp;
		for (int i = 0 ; i < len - 1; i++) {
			max = i;
			for (int j = i + 1; j < len; j++)
				if (v[j] > v[max])
					max = j;
			
			tmp = v[max];
			v[max] = v[i];
			v[i] = tmp;
		}
		
		int ans = 0;
		for (int i = 0; i < len; i++)
			ans = ans * 10 + v[i];
		
		return ans;
	}
}
