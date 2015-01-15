import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		String str = in.next();
		int wki = str.charAt(1) - 48;
		int wkj = str.charAt(0) - 'A' + 1;
		
		str = in.next();
		int wfi = str.charAt(1) - 48;
		int wfj = str.charAt(0) - 'A' + 1;

		str = in.next();
		int bki = str.charAt(1) - 48;
		int bkj = str.charAt(0) - 'A' + 1;
		
		boolean yes = false;
		
		if (wfi == bki)
			if (wki != wfi)
				yes = true;
			else 
			if (((wfj < bkj) && (wkj < wfj || wkj > bkj)) || ((wfj > bkj) && (wkj < bkj || wkj > wfj)))
				yes = true;
			
		if (wfj == bkj)
			if (wkj != wfj)
				yes = true;
			else
			if (((wfi < bki) && ((wki < wfi) || (wki > bki))) || ((wfi > bki) && ((wki > wfi) || (wki < bki))))
				yes = true;
		
		if (Math.abs(wfi - bki) == Math.abs(wfj - bkj))
			if (bki - wfi == bkj - wfj) {
				if (wki - wfi != wkj - wfj)
					yes = true;
				else
				if (wfi < bki) {
					if (!(wfi < wki && wki < bki))
						yes = true;
				} else {
					if (!(bki < wki && wki < wfi))
						yes = true;
				}
			} else {
				if (wki - wfi != -(wkj - wfj))
					yes = true;
				else
				if (wfi < bki) {
					if (!(wfi < wki && wki < bki))
						yes = true;
				} else {
					if (!(bki < wki && wki < wfi))
						yes = true;
				}
			}
		
		if (yes)
			out.println("YES");
		else
			out.println("NO");
		
		out.close();
	}
}