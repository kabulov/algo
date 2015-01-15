import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		int x1, y1, x2, y2, k;
		int[] l, c;
		
		x1 = in.nextInt();
		y1 = in.nextInt();
		x2 = in.nextInt();
		y2 = in.nextInt();
		k = in.nextInt();
		
		l = new int[4];
		c = new int[4];
		
		for (int i = 0; i < 4; i++)
			c[i] = l[i] = 0;
		
		int[] len = new int[1001];
		Arrays.fill(len, 0);
		
		for (int i = 0; i < k; i++)
			l[i] = in.nextInt();
		
		for (int i = 0; i < k; i++)
			len[l[i]] += in.nextInt();
		
		k = 4;
		for (int i = 1000; i > 0; i--)
			if (len[i] > 0) {
				--k;
				l[k] = i;
				c[k] = len[i];				
			}
		
		int l0, l1, l2, l3;
		int c0, c1, c2, c3;
		
		l0 = l[0];
		l1 = l[1];
		l2 = l[2];
		l3 = l[3];
		c0 = c[0];
		c1 = c[1];
		c2 = c[2];
		c3 = c[3];
		
		int ans = Integer.MAX_VALUE;
		
		int amt0, vx0, vy0, tmp0;
		int amt1, vx1, vy1, tmp1;
		int amt2, vx2, vy2, tmp2;
		
		int xa, ya, xb, yb;
		int x, y, tmpx, tmpy, tmp;
    	for (amt0 = 0; amt0 <= c0; ++amt0) 
		 for (vx0 = -amt0; vx0 <= amt0; ++vx0) {
		  tmp0 = amt0 - (vx0 < 0 ? -vx0 : vx0);
		  for (vy0 = -tmp0; vy0 <= tmp0; vy0 += 2 * tmp0) {
		   xa = x1 + vx0 * l0;
		   ya = y1 + vy0 * l0;
		   for (amt1 = 0; amt1 <= c1; ++amt1) 
			for (vx1 = -amt1; vx1 <= amt1; ++vx1) {
			 tmp1 = amt1 - (vx1 < 0 ? -vx1 : vx1);
			 for (vy1 = -tmp1; vy1 <= tmp1; vy1 += 2 * tmp1) {
			  xb = xa + vx1 * l1;
			  yb = ya + vy1 * l1;
			  for (amt2 = 0; amt2 <= c2; ++amt2) 
			   for (vx2 = -amt2; vx2 <= amt2; ++vx2) {
				tmp2 = amt2 - (vx2 < 0 ? -vx2 : vx2);
				for (vy2 = -tmp2; vy2 <= tmp2; vy2 += 2 * tmp2) {
					x = xb + vx2 * l2;
					y = yb + vy2 * l2;
					tmpx = x2 - x;
					tmpy = y2 - y;
					if (tmpx % l3 == 0 && tmpy % l3 == 0) {
						if (tmpx < 0)
							tmpx = -tmpx;
						if (tmpy < 0)
							tmpy = -tmpy;
						tmp = (tmpx + tmpy) / l3;
						if (tmp <= c3) {
							tmp += amt0 + amt1 + amt2;
							if (tmp < ans)
								ans = tmp;
						}
					}
					if (tmp2 == 0)
						break;
				}
			   }
			  if (tmp1 == 0)
				  break;
			}
			}
		   if (tmp0 == 0)
			   break;
		 }
		 }
		
		if (ans == Integer.MAX_VALUE)
			ans = -1;
		out.println(ans);
		out.close();
	}
}