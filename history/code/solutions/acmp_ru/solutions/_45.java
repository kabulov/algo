import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("input.txt"));
        PrintWriter out = new PrintWriter("output.txt");
        
        int n = in.nextInt(), sol;
        
        if (n <= 1) {
        	out.println(n == 0 ? 10 : 1);
        	out.close();
        	return;
        }
        
        int two, three, five, seven;
        two = three = five = seven = 0;
        
        while (n % 2 == 0) {
        	two++;
        	n /= 2;
        }
        
        while (n % 3 == 0) {
        	three++;
        	n /= 3;
        }
        
        while (n % 5 == 0) {
        	five++;
        	n /= 5;
        }

        while (n % 7 == 0) {
        	seven++;
        	n /= 7;
        }
        
        if (n > 1) {
        	out.println(-1);
        } else {
        	int nine = three / 2;
        	three %= 2;
        	int eight = two / 3;
        	two %= 3;
        	int four = 0, six = 0;
        	if (three == 0) {
        		if (two == 2) {
        			two = 0;
        			four = 1;
        		}
        	} else {
        		if (two == 1) {
        			three = 0;
        			two = 0;
        			six = 1;
        		} else
        		if (two == 2) {
        			two = 1;
        			three = 0;
        			six = 1;
        		}
        	}
        	for (int i = 0; i < two; i++)
        		out.print(2);
        	for (int i = 0; i < three; i++)
        		out.print(3);
        	for (int i = 0; i < four; i++)
        		out.print(4);
        	for (int i = 0; i < five; i++)
        		out.print(5);
        	for (int i = 0; i < six; i++)
        		out.print(6);
        	for (int i = 0; i < seven; i++)
        		out.print(7);
        	for (int i = 0; i < eight; i++)
        		out.print(8);
        	for (int i = 0; i < nine; i++)
        		out.print(9);
        }
        
        out.close();
    }
}