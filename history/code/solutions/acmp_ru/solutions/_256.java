import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new  PrintWriter("output.txt");
			
		int n = in.nextInt();
		int y = 0, z = 0, v;
		
		for (int i = 0; i < n; ++i) {
			char c = in.next().charAt(0);
			v = in.nextInt();
			
			if (c == 'Y') {
				y += v;
			}else
			if (c == 'Z') {
				z += v;
			}else {//c=='X'
				y += v;
				z -= v;
			}				
		}
		
		if ((z >= 0 && y >= 0) || (z <= 0 && y <= 0)) {
			out.println(Math.abs(z) + Math.abs(y));
		} else {
			out.println(Math.max(Math.abs(z), Math.abs(y)));
		}
		
		out.close();
	}	
}

