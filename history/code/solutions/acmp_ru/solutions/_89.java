import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		int n = Integer.parseInt(in.readLine());
		double speed = Integer.MIN_VALUE;
		String fastest = "";
		
		String name;
		String[] buf;

		String str;
		int start, end;
		double sp;
		for (int i = 0; i < n; i++) {
			str = in.readLine();
			name = "\"" + str.substring(1, str.lastIndexOf("\"")) + "\"";
			buf = str.substring(str.lastIndexOf("\"") + 2, str.length()).replace(':', ' ').split(" ");
			start = Integer.parseInt(buf[0]) * 60 + Integer.parseInt(buf[1]);
			end = Integer.parseInt(buf[2]) * 60 + Integer.parseInt(buf[3]);
			if (end <= start) {
				end += 24 * 60;
			} 
			
			sp = 650.0 * 60 / (end - start);
			if (sp > speed) {
				speed = sp;
				fastest = name;
			}
		}
		
		out.println("The fastest train is " + fastest + ".");
		out.println("It's speed is " + (Math.round(speed)) + " km/h, approximately.");
		out.close();
	}
}
