import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("input.txt"));
        PrintWriter out = new PrintWriter("output.txt");
        
        a = in.next().toCharArray();

        amap = new int[10];
        len = a.length;
        
        int b = in.nextInt();
        
        bmap = new int[10];
        for (int i = 0; i < 10; i++)
        	bmap[i] = 0;
        
        while (b > 0) {
        	bmap[b % 10]++;
        	b /= 10;
        }
        
        c = in.nextInt();
        
        solution = Integer.MAX_VALUE;
        solve(0);
        
        if (solution == Integer.MAX_VALUE)
        	out.println("NO");
        else {
        	out.println("YES");
        	out.println(solution + " " + (c - solution));
        }
        
        out.close();
    }
    
    static char tmp;
    static int newb, buf, newa;
    static boolean equal;
    static void solve(int pos) {
    	if (pos == len) {
    		newa = 0;
    		for (int i = 0; i < len; i++)
    			newa = newa * 10 + a[i] - 48;
    		newb = c - newa;
    		buf = newb;
    		if (buf > 0) {
    			for (int i = 0; i < 10; i++)
    				amap[i] = 0;
    			while (buf > 0) {
    				amap[buf % 10]++;
    				buf /= 10;
    			}
    			equal = true;
    			for (int i = 0; i < 10; i++)
    				if (amap[i] != bmap[i]) {
    					equal = false;
    					break;
    				}
    			if (equal && newa < solution)
    				solution = newa;    				
    		}
    		return;
    	}
    	
    	solve(pos + 1);
    	for (int i = pos + 1; i < len; i++) {
    		tmp = a[pos];
    		a[pos] = a[i];
    		a[i] = tmp;
    		solve(pos + 1);
    		tmp = a[pos];
    		a[pos] = a[i];
    		a[i] = tmp;
    	}
    }
    
    static int c;
    static int solution;
    
    static int len;
    static char[] a;

    static int[] bmap;
    static int[] amap;
}