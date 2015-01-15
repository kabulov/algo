import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		String q = in.next();
		String n = in.next();
		
		if (q.indexOf("(") < 0) {
			if (new BigDecimal(q).equals(BigDecimal.ZERO)) 
				out.println(0);
			else
				out.println(new BigDecimal(q).multiply(new BigDecimal(n)).stripTrailingZeros().toPlainString());
		} else {
			String[] buf = q.split("[.]");
			BigInteger whole = new BigInteger(buf[0]);
			
			buf = buf[1].split("[(]");
			buf[1] = buf[1].substring(0, buf[1].length() - 1);
			
			BigInteger nmtr = new BigInteger(buf[0].concat(buf[1]));
			if (buf[0].length() > 0) nmtr = nmtr.subtract(new BigInteger(buf[0]));
			
			whole = whole.multiply(new BigInteger(n));
			
			if (nmtr.equals(BigInteger.ZERO)) {
				out.println(whole);
			} else {
				nmtr = nmtr.multiply(new BigInteger(n));

				int prelen = buf[0].length();
				if (prelen > 0) {
					String tmp = nmtr.toString();
					int len = tmp.length();
					while (prelen > 0 && tmp.charAt(len - 1) == '0') {
						--prelen;
						--len;
					}
					nmtr = new BigInteger(tmp.substring(0, len));
				}
				
				int len = buf[1].length();				
				{
					StringBuilder tmp = new StringBuilder();
					for (int i = 0; i < len; ++i) tmp.append("9");
					for (int i = 0; i < prelen; ++i) tmp.append("0");
					BigInteger mid = new BigInteger(tmp.toString());					
					
					whole = whole.add(nmtr.divide(mid));
					nmtr = nmtr.mod(mid);
				}
				
				out.print(whole);
				if (!nmtr.equals(BigInteger.ZERO)) {
					out.print(".");
					if (prelen == 0) {
						out.print("(");
						len -= nmtr.toString().length();
						for (int i = 0; i < len; ++i) out.print("0");
						out.print(nmtr);
						out.print(")");
					} else {
						StringBuilder tmp = new StringBuilder();
						for (int i = 0; i < len; ++i) tmp.append("9");
						BigInteger nines = new BigInteger(tmp.toString());
						
						BigInteger pre = nmtr.divide(nines);
						nmtr = nmtr.mod(nines);
						
						prelen -= pre.toString().length();
						for (int i = 0; i < prelen; ++i) out.print("0");
						out.print(pre);
						
						if (!nmtr.equals(BigInteger.ZERO)) {
							len -= nmtr.toString().length();
							out.print("(");
							for (int i = 0; i < len; ++i) out.print("0");
							out.print(nmtr);
							out.print(")");
						}
					}
				}
			}
		}
		
		out.close();
	}
}

/*
String[] buf = q.split("[(]");
int scale = buf[0].length() - buf[0].indexOf(".") - 1;
BigDecimal a = new BigDecimal(buf[0]).movePointRight(scale);
BigInteger c = new BigInteger(buf[1].substring(0, buf[1].length() - 1));

StringBuilder Snines = new StringBuilder();
int nlen = c.toString().length();
for (int i = 0; i < nlen; ++i) Snines.append('9');
BigInteger nines = new BigInteger(Snines.toString());

a = a.multiply(new BigDecimal(n)).add(new BigDecimal(c.multiply(new BigInteger(n)).divide(nines).toString()));

int len = c.toString().length();
c = c.multiply(new BigInteger(n)).mod(nines);
len -= c.toString().length();

if (a.equals(BigDecimal.ZERO) && c.equals(BigInteger.ZERO)) {
	out.println(0);
} else			
if (c.equals(BigInteger.ZERO)) {
	out.println(a.movePointLeft(scale).stripTrailingZeros().toPlainString());
} else {
	out.print(a.movePointLeft(scale).toPlainString());
	if (scale == 0) out.print(".");
	out.print("(");
	for (int i = 0; i < len; ++i) out.print("0");				
	out.print(c);
	out.print(")");
}
	
/*			out.println(a.movePointLeft(scale).toPlainString());
out.println(c);
/*			if (a.equals(BigDecimal.ZERO) && c.equals(BigInteger.ZERO)) {
	out.println(0);
} else			
if (!c.equals(BigInteger.ZERO)) {
	out.print(a.toPlainString());
	if (scale == 0) out.print(".");
	out.print("(");				
	len -= c.toString().length();
	for (int i = 0; i < len; ++i) out.print("0");				
	out.print(c);
	out.print(")");
} else {
	out.print(a.stripTrailingZeros().toPlainString());
}*/
