import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class Main {
	public static void main(String[] args) throws IOException {
		in = new StreamTokenizer(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		int n = nextInt();
		
		fVal = new int[n];
		fPos = new int[n];
		
		for (int i = 0; i < n; ++i) {
			fVal[i] = nextInt();
			fPos[i] = i;
		}
		
		sVal = new int[n];
		sPos = new int[n];

		for (int i = 0; i < n; ++i) {
			sVal[i] = nextInt();
			sPos[i] = i;
		}
		
		buf = new int[n / 2 + 1];
		tmp = new int[n / 2 + 1];
		
		sortsVal(0, n - 1);
		sortfVal(0, n - 1);
		
		for (int i = 0; i < n; ++i)
			if (fVal[i] != sVal[i]) {
				out.println(-1);
				out.close();
				return;
			}
		
		for (int i = 0; i < n; ++i)
			fVal[i] = sPos[i];
			
		sortfPos(0, n - 1);
		
		answer = 0;
		
		merge(fVal, 0, n - 1);
			
		out.println(answer);	
		
		out.close();
	}
	
	static void sortfVal(int l, int r) {
		if (l >= r) return;
		
		int m = (l + r) / 2;

		sortfVal(l, m);
		sortfVal(m + 1, r);
		
		for (int i = l; i <= m; ++i) {
			buf[i - l] = fVal[i];
			tmp[i - l] = fPos[i];
		}
		
		int p = l, i = 0, j = m + 1;
		
		while (i <= m - l || j <= r)
			if (i > m - l) {
				fVal[p] = fVal[j];
				fPos[p++] = fPos[j++];
			} else
			if (j > r) {
				fVal[p] = buf[i];
				fPos[p++] = tmp[i++];
			} else
			if (buf[i] <= fVal[j]) { 
				fVal[p] = buf[i];
				fPos[p++] = tmp[i++];
			} else {
				fVal[p] = fVal[j];
				fPos[p++] = fPos[j++];
			}
	}
	
	static void sortfPos(int l, int r) {
		if (l >= r) return;
		
		int m = (l + r) / 2;

		sortfPos(l, m);
		sortfPos(m + 1, r);
		
		for (int i = l; i <= m; ++i) {
			buf[i - l] = fPos[i];
			tmp[i - l] = fVal[i];
		}
		
		int p = l, i = 0, j = m + 1;
		
		while (i <= m - l || j <= r)
			if (i > m - l) {
				fPos[p] = fPos[j];
				fVal[p++] = fVal[j++];
			} else
			if (j > r) {
				fPos[p] = buf[i];
				fVal[p++] = tmp[i++];
			} else
			if (buf[i] <= fPos[j]) { 
				fPos[p] = buf[i];
				fVal[p++] = tmp[i++];
			} else {
				fPos[p] = fPos[j];
				fVal[p++] = fVal[j++];
			}
	}

	static void sortsVal(int l, int r) {
		if (l >= r) return;
		
		int m = (l + r) / 2;

		sortsVal(l, m);
		sortsVal(m + 1, r);
		
		for (int i = l; i <= m; ++i) {
			buf[i - l] = sVal[i];
			tmp[i - l] = sPos[i];
		}
		
		int p = l, i = 0, j = m + 1;
		
		while (i <= m - l || j <= r)
			if (i > m - l) {
				sVal[p] = sVal[j];
				sPos[p++] = sPos[j++];
			} else
			if (j > r) {
				sVal[p] = buf[i];
				sPos[p++] = tmp[i++];
			} else
			if (buf[i] <= sVal[j]) { 
				sVal[p] = buf[i];
				sPos[p++] = tmp[i++];
			} else {
				sVal[p] = sVal[j];
				sPos[p++] = sPos[j++];
			}
	}

	static int[] fVal, sVal, fPos, sPos;
	
	static void merge(int[] v, int l, int r) {
		if (l >= r) return;
		
		int m = (l + r) / 2;

		merge(v, l, m);
		merge(v, m + 1, r);
		
		for (int i = l; i <= m; ++i)
			buf[i - l] = v[i];
		
		int p = l, i = 0, j = m + 1;
		
		while (i <= m - l || j <= r)
			if (i > m - l)
				v[p++] = v[j++];
			else
			if (j > r)
				v[p++] = buf[i++];
			else
			if (buf[i] <= v[j]) 
				v[p++] = buf[i++];
			else {
				v[p++] = v[j++];
				answer += m - l - i + 1;
			}
	}
	
	static long answer;
	static int[] buf, tmp;
	
	static StreamTokenizer in;
	
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
	
}
