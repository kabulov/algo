import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;


public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("input.txt"), 4096);
		PrintWriter out = new PrintWriter("output.txt");
		
		String inp = in.readLine();
		if (inp.equals("0")) {
			out.println(0);
			out.close();
			return;
		}
		
		int len = (inp.length() + 1) / 2;

		byte[] a, aa, a2, sqr;
		int alen, aalen, a2len, sqrlen;
		
		sqrlen = inp.length();
		sqr = new byte[sqrlen + 1];
		for (int i = 0; i < sqrlen; i++)
			sqr[i + 1] = (byte)(inp.charAt(sqrlen - i - 1) - 48);
		
		alen = len;
		a = new byte[alen + 1];
		Arrays.fill(a, (byte)0);
		a[alen] = 1;
		
		a2len = len;
		a2 = new byte[a2len + 2]; // or + 3//2 enough
		Arrays.fill(a2, (byte)0);
		a2[a2len] = 2;
		
		aalen = 2 * len - 1;
		aa = new byte[sqrlen * 2 + 1];
		Arrays.fill(aa, (byte)0);
		aa[aalen] = 1;
		
		int tmplen;
		byte[] tmp = new byte[sqrlen * 2 + 1];
		Arrays.fill(tmp, (byte)0);
		
		int j;
		boolean smaller;
outer1:	for (int i = 2; i <= 9; i++) {
			tmplen = aalen;
			for (j = 2 * len - 1; j <= aalen; j++)
				tmp[j] = aa[j];
			
			for (j = len; j <= a2len; j++) {
				tmp[j + len - 1] += a2[j];
				tmp[j + len] += (byte)(tmp[j + len - 1] / 10);
				tmp[j + len - 1] %= 10;
			}

			j += len - 1;
			while (tmp[j] > 9) {
				tmp[j + 1] += (byte)(tmp[j] / 10);
				tmp[j] %= 10;
				j++;
			}
			if (tmp[j] == 0)
				j--;
			if (j > tmplen) {
				tmplen = j;
			}
			
			j = 2 * len - 1;
			tmp[j]++;
			while (tmp[j] > 9) {
				tmp[j + 1]++;
				tmp[j] = 0;
				j++;
			}
			if (tmp[j] == 0)
				j--;
			if (j > tmplen) {
				tmplen = j;
			}
			
			if (tmplen < sqrlen)
				smaller = true;
			else
			if (tmplen > sqrlen) {
				smaller = false;
			} else {
				smaller = true;
				for (j = tmplen; j >= len; j--)
					if (sqr[j] > tmp[j])
						break;
					else
					if (sqr[j] < tmp[j]) {
						smaller = false;
						break;
					}
			}
			
			if (smaller) {
				aalen = tmplen;
				for (j = 2 * len - 1; j <= aalen; j++)
					aa[j] = tmp[j];
				
				a2[len] += 2;
				j = len;
				while (a2[j] > 9) {
					a2[j + 1]++;
					a2[j] = 0;
					j++;
				}
				if (a2[j] == 0)
					j--;
				if (j > a2len)
					a2len = j;
				
				a[len] = (byte)i;
			} else
				break outer1;
		}
		
		Arrays.fill(aa, (byte)0);
		if (a[len] <= 3) {
			aalen = 2 * len - 1;
			aa[aalen] = (byte)(a[len] * a[len]);
		} else {
			aalen = 2 * len;
			j = a[len] * a[len];
			aa[aalen - 1] = (byte)(j % 10);
			aa[aalen] = (byte)(j / 10);
		}

		int p;
		for (int i = len - 1; i > 0; i--) {
			Arrays.fill(tmp, (byte)0);
outer2: 	for (j = 1; j <= 9; j++) {
				tmplen = aalen;
				for (p = 1; p <= tmplen; p++)
					tmp[p] = aa[p];

				for (p = 1; p <= a2len; p++) {
					tmp[p + i - 1] += a2[p];
					tmp[p + i] += (byte)(tmp[p + i - 1] / 10);
					tmp[p + i - 1] %= 10;
				}
					
				p += i - 1;
				while (tmp[p] > 9) {
					tmp[p + 1] += (byte)(tmp[p] / 10);
					tmp[p] %= 10;
					p++;
				}
				if (tmp[p] == 0)
					p--;
				if (p > tmplen)
					tmplen = p;
	
				p = 2 * i - 1;
				tmp[p]++;
				while (tmp[p] > 9) {
					tmp[p + 1]++;
					tmp[p] = 0;
					p++;
				}
				if (tmp[p] == 0)
					p--;
				if (p > tmplen)
					tmplen = p;
				
				if (tmplen < sqrlen)
					smaller = true;
				else
				if (tmplen > sqrlen)
					smaller = false;
				else {
					smaller = true;
					for (p = tmplen; p > 0; p--)
						if (sqr[p] > tmp[p])
							break;
						else
						if (sqr[p] < tmp[p]) {
							smaller = false;
							break;
						}
				}

				if (smaller) {
					aalen = tmplen;
					for (p = 1; p <= aalen; p++)
						aa[p] = tmp[p];
					
					a2[i] += 2;
					p = i;
					while (a2[p] > 9) {
						a2[p + 1] += (byte)(a2[p] / 10);
						a2[p] %= 10;
						p++;
					}
					if (a2[p] == 0)
						p--;
					if (p > a2len)
						a2len = p;
					
					a[i] = (byte)j;
				} else
					break outer2;
			}
		}
		
		for (int i = alen; i > 0; i--)
			out.print(a[i]);
		
		out.close();
	}
}