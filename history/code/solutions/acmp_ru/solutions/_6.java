import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("input.txt"));
        PrintWriter out = new PrintWriter("output.txt");
        
        char[] inp = in.next().toCharArray();
        
        if (error(inp)) {
        	out.println("ERROR");
        	out.close();
        	return;
        }
        
        int i1 = inp[1] - 48;
        int i2 = inp[4] - 48;
        int j1 = inp[0] - 54;
        int j2 = inp[3] - 54;
        
        if (Math.abs((i1 - i2) * (j1 - j2)) == 2)
        	out.println("YES");
        else
        	out.println("NO");
        
        out.close();
    }
    
    static boolean error(char[] v) {
    	if (v.length != 5)
    		return true;
    	
    	if (!('A' <= v[0] && v[0] <= 'H'))
    		return true;
    	
    	if (!('1' <= v[1] && v[1] <= '8'))
    		return true;
    	
    	if (v[2] != '-')
    		return true;
    	
    	if (!('A' <= v[3] && v[3] <= 'H'))
    		return true;
    	
    	if (!('1' <= v[4] && v[4] <= '8'))
    		return true;
    	
    	return false;
    }
}