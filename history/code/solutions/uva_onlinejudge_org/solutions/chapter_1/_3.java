import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();

		double[] v = new double [1000];
		
		double s, neg, pos, m, diff;
		while (n > 0) {
			s = 0;
			for (int i = 0; i < n; ++i) 
				s += v[i] = in.nextDouble();
			
			m = s / n;
			
			neg = pos = 0;
			for (int i = 0; i < n; i++) {
				diff = (double)((long)((v[i] - m) * 100)) / 100.0;//depends on odd or even the number of travellers is (n is)
				if (diff < 0)
					neg += -diff;
				else
					pos += diff;
			}
			
			System.out.printf("$%.2f\n", Math.max(neg, pos));
			n = in.nextInt();
		}
	}
}