import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		PrintWriter out = new PrintWriter("output.txt");
		BufferedReader in = new BufferedReader(new FileReader("input.txt"));
		
		String str = in.readLine();
		int n = Integer.parseInt(str.split(" ")[0]);
		int k = Integer.parseInt(str.split(" ")[1]);
		
		str = in.readLine();
		
		int[] amt = new int[26];
		int[] min = new int[26];
		for (int i = 0; i < 26; i++) {
			amt[i] = min[i] = 0;
		}
		
		amt[str.charAt(0) - 'A'] = 1;
		
		int c, tmp;
		int left = -k, right = 0;
		while (++right < n) {
			if (left >= 0) {
				if (amt[c = str.charAt(left) - 'A'] > 0)
					--amt[c];
			}
			++left;
			
			++amt[c = str.charAt(right) - 'A'];
			if (amt[c] == 1) {
				tmp = Integer.MAX_VALUE;
				for (int i = 0; i < 26; i++)
					if (i != c && amt[i] > 0 && min[i] < tmp)
						tmp = min[i];
				min[c] = tmp + 1;
			}
		}
		
		out.println(min[str.charAt(n - 1) - 'A']);
		out.close();
	}
}
