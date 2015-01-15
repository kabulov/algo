import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;


public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("input.txt"), 4096);
		PrintWriter out = new PrintWriter("output.txt");
		
		String inp = in.readLine();
		int len = (inp.length() + 1) / 2;
		BigInteger sqr = new BigInteger(inp); //answer * answer <= sqr
		
		BigInteger aa = BigInteger.ZERO; //answer * answer
		BigInteger a2 = BigInteger.ZERO; //2 * answer		

		BigInteger tmpaa;		
		BigInteger bb;
		BigInteger b2;
		BigInteger b;
		
		StringBuilder bstr = new StringBuilder();
		bstr.setLength(len + 1);
		for (int i = 0; i <= len; i++)
			bstr.setCharAt(i, '0');

		for (int i = len; i > 0; i--) {
			bstr.setCharAt(len - i, '0');
			bstr.setCharAt(1 + len - i, '1');
			
			b = new BigInteger(new String(bstr));
			b2 = b.add(b);
			bb = b.multiply((b));
			
			for (int j = 0; j < 9; j++) {
				tmpaa = aa.add(a2.multiply(b)).add(bb);
				
				System.out.println(tmpaa);
				
				if (tmpaa.compareTo(sqr) > 0)
					break;
				else {
					aa = tmpaa;
					a2 = a2.add(b2);
				}
			}
		}
		
		out.println(a2.divide(BigInteger.ONE.add(BigInteger.ONE)));
		out.close();
	}
}