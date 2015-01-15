import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int fh, fm, sh, sm;
		fh = in.nextInt();
		fm = in.nextInt();
		sh = in.nextInt();
		sm = in.nextInt();
		
		if (sh < fh || (sh == fh && sm < fm))
			sh += 24;
		
		int answer = 0;
		
		if (fh == sh) {
			if (fm < 30 && sm > 30) 
				++answer;
			if (fm == 0)
				answer += fh > 13 ? fh - 12 : fh; 	//!
		} else {
			if (fm < 30) ++answer;
			if (sm > 30) ++answer;
			if (fm > 0) ++fh;						//!
			answer += sh - fh;
			for (int i = fh; i <= sh; ++i)
				if (i > 36)							//!
					answer += i - 36;				
				else
				if (i > 24)
					answer += i - 24;
				else
				if (i > 12)
					answer += i - 12;
				else
					answer += i;
		}
		
		out.println(answer);
		out.close();
	}
}