import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int fa = in.nextInt();
		int fb = in.nextInt();
		int fc = in.nextInt();
		
		int sa = in.nextInt();
		int sb = in.nextInt();
		int sc = in.nextInt();
		
		int tmp;
		
		if (fa > fb) {
			tmp = fa;
			fa = fb;
			fb = tmp;
		}
		
		if (fa > fc) {
			tmp = fa;
			fa = fc;
			fc = tmp;
		}
		
		if (fb > fc) {
			tmp = fb;
			fb = fc;
			fc = tmp;
		}
		
		if (sa > sb) {
			tmp = sa;
			sa = sb;
			sb = tmp;
		}
		
		if (sa > sc) {
			tmp = sa;
			sa = sc;
			sc = tmp;
		}
		
		if (sb > sc) {
			tmp = sb;
			sb = sc;
			sc = tmp;
		}
		
		boolean fbig = false;
		boolean sbig = false;
		
		if (fa >= sa && fb >= sb && fc >= sc)
			fbig = true;
		
		if (sa >= fa && sb >= fb && sc >= fc)
			sbig = true;
		
		String answer = "";
		if (!fbig && !sbig)
			answer = "Boxes are incomparable";
		else
		if (fbig && sbig)
			answer = "Boxes are equal";
		else
		if (sbig)
			answer = "The first box is smaller than the second one";
		else	
		//if (fbig)
			answer = "The first box is larger than the second one";
		
		out.println(answer);
		out.close();
	}
}