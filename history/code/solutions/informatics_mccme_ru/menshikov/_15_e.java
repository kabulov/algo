import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int n = in.nextInt();

		prlt = new int[n];
		for (int i = 0; i < n; i++)
			prlt[i] = in.nextInt();
		
		mnlt = new int[n][n];
		for (int i = 0; i < n; i++) {
			mnlt[i][i] = prlt[i];
			for (int j = i + 1; j < n; j++)
				mnlt[i][j] = Math.min(prlt[j], mnlt[i][j - 1]);
		}
		
		prrt = new int[n];
		for (int i = 0; i < n; i++)
			prrt[i] = prlt[n - 1 - i];
		
		mnrt = new int[n][n];
		for (int i = 0; i < n; i++) {
			mnrt[i][i] = prrt[i];
			for (int j = i + 1; j < n; j++)
				mnrt[i][j] = Math.min(prrt[j], mnrt[i][j - 1]);
		}
		
		pcnt = new int[n];
		for (int i = 0; i < n; i++)
			pcnt[i] = in.nextInt();
		
		int minpos = -1;
		int minprc = -1;
		int maxsum = -1;
		
		for (int i = 1; i < n; i++) {//!! < n but not < n - 1
			int prc = 1, sum = 0;
			int klt = i, krt = n - i; //!! == n - i but not n - i - 1
			
			sum += pcnt[i];
			for (int j = i + 1; j < n && prlt[j - 1] > 1; j++) {
				sum += pcnt[j];
				klt = j;
			}
			
			int scdi = n - i; //!! == n - i but not n - i - 1
			
			sum += pcnt[scdi];
			for (int j = scdi + 1; j < n && prrt[j - 1] > 1; j++) {
				sum += pcnt[j];
				krt = j;
			}
			
			if (sum > maxsum) {
				maxsum = sum;
				minpos = i;
				minprc = 1;
			}
			
			while (prc < MAXIMUM) {
				if (alter(prc + 1, i, scdi, klt, krt)) {
					if (sum * prc > maxsum) {
						maxsum = sum * prc;
						minpos = i;
						minprc = prc;
					}
					
					sum = 0;
					klt = i;
					if (mnlt[0][klt - 1] >= prc + 1) {
						sum += pcnt[klt];
						for (int j = i + 1; j < n && prc + 1 < mnlt[i][j - 1]; j++) {
							klt = j;
							sum += pcnt[j];
						}
					}
					
					krt = scdi;
					if (mnrt[0][krt - 1] >= prc + 1) {
						sum += pcnt[krt];
						for (int j = scdi + 1; j < n && prc + 1 < mnrt[scdi][j - 1]; j++) {
							krt = j;
							sum += pcnt[j];
						}
					}
				}
				++prc;
			}
		}
		
		out.println(minpos + " " + minprc); //!!minpos but not (minpos + 1)
		out.close();
	}
	
	static boolean alter(int prc, int lt, int rt, int klt, int krt) {
		if (prc == MAXIMUM)
			return true;
		
		if (mnlt[0][lt - 1] < prc && prc - 1 == mnlt[0][lt - 1])//<=
			return true;
		
		if (mnrt[0][rt - 1] < prc && prc - 1 == mnrt[0][rt - 1])//<=
			return true;
		
		if (klt > lt) {
			if (mnlt[lt][klt - 1] == prc && prc - 1 < mnlt[lt][klt - 1])//<=
				return true;
		}
		
		if (krt > rt) {
			if (mnrt[rt][krt - 1] == prc && prc - 1 < mnrt[rt][krt - 1])//<=
				return true;
		}
		
		return false;
	}
	
	static int[] pcnt; //procent
	static int[] prlt, prrt;//price
	static int[][] mnlt, mnrt;//min
	
	static final int MAXIMUM = (int)1e4;
}