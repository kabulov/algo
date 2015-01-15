import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
	public static void main(String[] args) throws IOException {
//		in = new StreamTokenizer(new FileReader("input.txt"));
//		Scanner in = new Scanner(new File("input.txt"));
		BufferedReader in = new BufferedReader(new FileReader("input.txt"));
		out = new PrintWriter("output.txt");

		comlen = new int[6];
		commands = new int[6][];
		
		String str;
		for (int i = 0; i < 6; i++) {
			str = in.readLine();
			comlen[i] = str.length();
			commands[i] = new int[comlen[i]];
			for (int j = 0; j < comlen[i]; j++)
				commands[i][j] = getNum(str.charAt(j));
		}
		
		map = new int[6][100];
		for (int i = 0; i < 6; i++)
			map[i][0] = 1;
		
		for (int i = 0; i < 6; i++)
			for (int j = 1; j < 100; j++)
				map[i][j] = -1;
	
		str = in.readLine();
		int param = Integer.parseInt(str.split(" ")[1]);
		calc(getNum(str.charAt(0)), param - 1);
		

		out.println(map[getNum(str.charAt(0))][param - 1]);
		out.close();
	}
	
	static int[] comlen;
	static int[][] commands;
	static int[][] map;
	
	private static void calc(int com, int param) {
		if (map[com][param] != -1)
			return;
		
		map[com][param] = 1;
		param--;
		for (int i = 0; i < comlen[com]; i++) {
			if (map[commands[com][i]][param] == - 1)
				calc(commands[com][i], param);
			map[com][param + 1] += map[commands[com][i]][param];
		}
	}
	
	static int getNum(char c) {
		switch (c) {
		case 'N':
			return 0;
		case 'S':
			return 1;
		case 'W':
			return 2;
		case 'E':
			return 3;
		case 'U':
			return 4;
		case 'D':
			return 5;
		}
		return -1;
	}

	static PrintWriter out;
}