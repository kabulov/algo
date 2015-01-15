import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		expr = in.next().toCharArray();
		error = expr.length == 0 ? true : false;
		
		if (!error) {
			init();
			error = isError();
		}
		
		if (error)
			out.println("ERROR");
		else {
			init();
			out.println(Correct() ? "YES" : "NO");
		}
			
		out.close();
	}
	
	static boolean Correct() {
		char op;
		
		int fst, scd, thd;
		fst = scd = thd = 0;
		
		boolean fstneg, thdneg, scdneg;
		fstneg = thdneg = scdneg = false;
		
		if (cur == '-') {
			fstneg = true;
			next();
		}
		
		while (('0' <= cur && cur <= '9')) {
			fst = fst * 10 + cur - 48;
			next();
		}
		
		op = cur;
		next();
		
		while (('0' <= cur && cur <= '9') || cur == '-') {
			if (cur == '-')
				scdneg = true;
			else
				scd = scd * 10 + cur - 48;
			next();
		}
		
		next();
		if (cur == '-') {
			thdneg = true;
			next();
		}
		
		while ('0' <= cur && cur <= '9') {
			thd = thd * 10 + cur - 48;
			next();
		}
		
		if (fstneg) fst = -fst;
		if (scdneg) scd = -scd;
		if (thdneg) thd = -thd;
		
		switch(op) {
		case '+':
			if (fst + scd == thd) return true;
			break;
		case '-':
			if (fst - scd == thd) return true;
			break;
		case '*':
			if (fst * scd == thd) return true;
			break;
		case '/':
			if (scd == 0) return false;
			if (fst == scd * thd) return true;
			break;
		}
		
		return false;
	}
	
	static boolean isError() {
		if (cur != '-' && !('0' <= cur && cur <= '9')) return true; //pos == 0
		if (expr.length == 1) return true;
		if (cur == '-') {
			next();
			if (!('0' <= cur && cur <= '9')) return true;
		}
		
		boolean fst = true, scd = false, thd = false;

		while (cur != '\0') {
			if (!('0' <= cur && cur <= '9') && cur != '-' && cur != '+' && cur != '*' && cur != '/' && cur != '=')
				return true;

			if ('0' <= cur && cur <= '9') {
				next();			
				continue;
			}
		
			if (thd) return true;
			
			if (cur == '=') {
				if (!scd) return true;
				
				next();
				if (cur != '-' && !('0' <= cur && cur <= '9')) return true;
				if (cur == '-') {
					next();
					if (!('0' <= cur && cur <= '9')) return true;
				}
				scd = false;
				thd = true;
				continue;
			}
			
			if (!fst) return true;
			
			next();
			if (!('0' <= cur && cur <= '9') && cur != '-') return true;
			if (cur == '-') {
				next();
				if (!('0' <= cur && cur <= '9')) return true;
			}
			fst = false;
			scd = true;
		}
		
		return !thd;
	}
	
	static void init() {
		pos = 0;
		cur = expr[0];
	}
	
	static void next() {
		if (pos + 1 >= expr.length) 
			cur = '\0';
		else {
			++pos;
			cur = expr[pos];
		}
	}
	
	static char[] expr;
	static boolean error;	
	static char cur;
	static int pos;
}