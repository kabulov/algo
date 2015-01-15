import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		String fdate = in.next();
		String sdate = in.next();
		
		String[] temp = fdate.split("[.]");
		int fdb = Integer.parseInt(temp[0]);
		int fmb = Integer.parseInt(temp[1]);
		int fyb = Integer.parseInt(temp[2]);
		
		temp = sdate.split("[.]");
		int sdb = Integer.parseInt(temp[0]);
		int smb = Integer.parseInt(temp[1]);
		int syb = Integer.parseInt(temp[2]);
		
		int fst = 0, scd = 0;
		int fd, fm, fy, sd, sm, sy;
		fd = fm = fy = sd = sm = sy = 0;
		
		while (!(fd == fdb && fm == fmb && fy == fyb)) {
			fd++;
			if (fd == 32) {
				fd = 1;
				fm++;
				if (fm > 12) {
					fm = 1;
					fy++;
				}
			} else
			if (fd == 31) {
				if (fm == 4 || fm == 6 || fm == 9 || fm == 11) {
					fd = 1;
					fm++;
				}
			} else
			if (fm == 2) {
				if (fd == 30) {
					fd = 1;
					fm++;
				} else
				if (fd == 29 && !isLeap(fy)) {
					fd = 1;
					fm++;
				}
			}
			fst++;
		}
		
		while (!(sd == sdb && sm == smb && sy == syb)) {
			sd++;
			if (sd == 32) {
				sd = 1;
				sm++;
				if (sm > 12) {
					sm = 1;
					sy++;
				}
			} else
			if (sd == 31) {
				if (sm == 4 || sm == 6 || sm == 9 || sm == 11) {
					sd = 1;
					sm++;
				}
			} else 
			if (sm == 2) {
				if (sd == 30) {
					sd = 1;
					sm++;
				} else
				if (sd == 29 && !isLeap(sy)) {
					sd = 1;
					sm++;
				}
			}
			scd++;
		}
		
		out.println(scd - fst + 1);
		out.close();
	}
	
	static boolean isLeap(int date) {
		return (date % 400 == 0 || (date % 4 == 0 && (date % 100 != 0)));
	}
}