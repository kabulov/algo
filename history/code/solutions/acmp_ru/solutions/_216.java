import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt"); 

		int n = in.nextInt();
		int[] v = new int[n];
		for (int i = 0; i < n; ++i) v[i] = in.nextInt();
		
		Arrays.sort(v);
		
		int ans = 0, l = 0, r = n - 1;
		
		while (l < r) {
			++ans;
			--v[l];
			--v[r];
			
			for (int i = r; v[i - 1] > v[i]; --i) { 
				int buf = v[i];
				v[i] = v[i - 1];
				v[i - 1] = buf;
			}

			while (l < r && v[l] == 0) ++l;
		}
		
		out.println(ans);
		out.close();
	}
}

/*int s = 0;
for (int i = 0; i < n; ++i) s += in.nextInt();
out.println(s / 2);*/


/*		int[] v = new int[n];
for (int i = 0; i < n; ++i) v[i] = in.nextInt();

Arrays.sort(v);

int ans = 0;		

int k = 0;
for (int i = 0; i < n;) 
	if (i == n - 1 || v[i] != v[i + 1]) 
		v[k++] = v[i++];
	else {
		ans += v[i];
		i += 2;
	}		

int l = 0, r = k - 1;
while (l < r) 
	if (v[l] < v[r]) {
		ans += v[l];
		v[r] -= v[l];
		++l;
	} else
	if (v[l] > v[r]) {
		ans += v[r];
		v[l] -= v[r];
		--r;
	} else {
		ans += v[l];
		++l;
		--r;
	}
		
out.println(ans);
*/
