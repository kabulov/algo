import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		int n = Integer.parseInt(in.readLine());
		v = new elem[n];
		m = new elem[n / 2 + 1];
		
		for (int i = 0; i < n; ++i)
			v[i] = new elem(in.readLine());
		
		msort(0, n - 1);
		
		for (int i = 0; i < n; ++i)
			out.println(v[i].self);
		
		out.close();
	}
	
	static int len;
	static elem[] m;
	static elem[] v;
	
	static void msort(int l, int r) {
		if (l >= r)
			return;
		
		int mid = (l + r) / 2;
		msort(l, mid);
		msort(mid + 1, r);
		
		len = mid - l + 1;
		for (int i = l; i <= mid; ++i)
			m[i - l] = v[i];
		
		int k = l;
		for (int i = 0, j = mid + 1; i < len || j <= r;) 
			if (i == len)
				v[k++] = v[j++];
			else
			if (j > r)
				v[k++] = m[i++];
			else
			if (m[i].compare(v[j]) <= 0) 
				v[k++] = m[i++];
			else
				v[k++] = v[j++];
	}
}

class elem {
	long fst, scd;
	String self;
	
	elem(String arg) {
		fst = 0;
		self = arg;

		String[] mid = self.split(" ");
		scd = Long.parseLong(mid[0]);
		arg = mid[1];
		
		switch(arg.charAt(arg.length() - 1)) {
		case 'g':
			break;
		case 'p':
			scd *= 16380;
			break;
		case 't':
			scd *= 1000000; //1e6
			break;
		}
		
		if (!(arg.length() == 2 && arg.charAt(0) == 'm')) {
			scd *= 1000;
		}
		fst = scd / 1000000000; //1e9
		scd %= 1000000000; //1e9
		
		long tmp = 1;
		if (arg.length() == 2) {
			switch (arg.charAt(0)) {
			case 'G':
				tmp = 1000000000L; //1e9
				break;
			case 'M':
				tmp = 1000000L; // 1e6
				break;
			case 'k':
				tmp = 1000L; // 1e3
				break;
			case 'm':
				break;
			}
		}
		
		fst = tmp * fst + (tmp * scd) / 1000000000; //1e9
		scd = scd * tmp % 1000000000; //1e9
	}
	
	long compare(elem obj) {
		if (fst != obj.fst)
			return fst - obj.fst;
		
		return scd - obj.scd;
	}
}