import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		PrintWriter out = new PrintWriter("output.txt");
		Scanner in = new Scanner(new File("input.txt"));
		
		int n = in.nextInt();
		BigInteger[][] map = new BigInteger[3][3];
		
		map[1][1] = BigInteger.ONE;
		map[2][1] = BigInteger.ONE;
		map[1][2] = BigInteger.ONE;
		map[2][2] = BigInteger.ONE;
		
		for (int i = 2; i <= n; i++) {
			map[0][0] = map[1][1];
			map[1][0] = map[2][1];
			map[0][1] = map[1][2];
			map[1][1] = map[2][2];
			
			map[1][2] = map[1][1].add(map[1][0]);
			map[2][1] = map[1][1].add(map[0][1]);
			map[2][2] = map[0][0].add(map[1][1]).add(map[0][1]).add(map[1][0]);
		}
		
		out.println(map[2][2]);
		out.close();
	}
}
