import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int n = Integer.parseInt(in.readLine());
		
		HashMap<String, Integer> map = new HashMap<String, Integer>();	
		String[] inp = new String[n];
		int[] x, y, r;
		x = new int[n];
		y = new int[n];
		r = new int[n];
		
		String[] buf;
		for (int i = 0; i < n; ++i) {
			inp[i] = in.readLine();
			map.put(inp[i], 0);
			
			buf = in.readLine().split(" ");
			x[i] = Integer.parseInt(buf[0]);
			y[i] = Integer.parseInt(buf[1]);
			r[i] = Integer.parseInt(buf[2]);
		}
		
		int X, Y;
		buf = in.readLine().split(" ");
		X = Integer.parseInt(buf[0]);
		Y = Integer.parseInt(buf[1]);
				
		int size = map.size();
		String[] name = new String[size];
		int len = 0;

		int mid;
		for (int i = 0; i < n; ++i) {
			if ((mid = map.get(inp[i])) == 0) 
				name[len++] = inp[i];
			
			if ((X - x[i]) * (X - x[i]) + (Y - y[i]) * (Y - y[i]) <= r[i] * r[i]) {
				if (mid < 0) mid = 0;
				map.put(inp[i], mid + 1);
			} else
			if (mid == 0)
				map.put(inp[i], -1);
		}
		
		out.println(size);
		for (int i = 0; i < size; ++i) {
			out.print(name[i]);
			out.print(" ");
			out.println((mid = map.get(name[i])) < 0 ? 0 : mid);
		}
		
		out.close();
	}
}
