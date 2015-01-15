import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		out = new PrintWriter("output.txt");
		in = new StreamTokenizer(new FileReader("input.txt"));
		
		init();
		
		int l = nextInt();
		int r = nextInt();
		
		boolean empty = true;
		for (int i = 0; i < 40; i++) 
			if (l <= vect[i][0] && vect[i][0] <= r && l <= vect[i][1] && vect[i][1] <= r) {
				out.println(vect[i][0] + " " + vect[i][1]);
				empty = false;
			}
		
		if (empty)
			out.println("Absent");
		
		out.close();
	}

	static int[][] vect;
	
	static void init() {
		vect = new int[40][2];
		vect[0][0] = 220; vect[0][1] = 284;
		vect[1][0] = 1184; vect[1][1] = 1210;
		vect[2][0] = 2620; vect[2][1] = 2924;
		vect[3][0] = 5020; vect[3][1] = 5564;
		vect[4][0] = 6232; vect[4][1] = 6368;
		vect[5][0] = 10744; vect[5][1] = 10856;
		vect[6][0] = 12285; vect[6][1] = 14595;
		vect[7][0] = 17296; vect[7][1] = 18416;
		vect[8][0] = 63020; vect[8][1] = 76084;
		vect[9][0] = 66928; vect[9][1] = 66992;
		vect[10][0] = 67095; vect[10][1] = 71145;
		vect[11][0] = 69615; vect[11][1] = 87633;
		vect[12][0] = 79750; vect[12][1] = 88730;
		vect[13][0] = 100485; vect[13][1] = 124155;
		vect[14][0] = 122265; vect[14][1] = 139815;
		vect[15][0] = 122368; vect[15][1] = 123152;
		vect[16][0] = 141664; vect[16][1] = 153176;
		vect[17][0] = 142310; vect[17][1] = 168730;
		vect[18][0] = 171856; vect[18][1] = 176336;
		vect[19][0] = 176272; vect[19][1] = 180848;
		vect[20][0] = 185368; vect[20][1] = 203432;
		vect[21][0] = 196724; vect[21][1] = 202444;
		vect[22][0] = 280540; vect[22][1] = 365084;
		vect[23][0] = 308620; vect[23][1] = 389924;
		vect[24][0] = 319550; vect[24][1] = 430402;
		vect[25][0] = 356408; vect[25][1] = 399592;
		vect[26][0] = 437456; vect[26][1] = 455344;
		vect[27][0] = 469028; vect[27][1] = 486178;
		vect[28][0] = 503056; vect[28][1] = 514736;
		vect[29][0] = 522405; vect[29][1] = 525915;
		vect[30][0] = 600392; vect[30][1] = 669688;
		vect[31][0] = 609928; vect[31][1] = 686072;
		vect[32][0] = 624184; vect[32][1] = 691256;
		vect[33][0] = 635624; vect[33][1] = 712216;
		vect[34][0] = 643336; vect[34][1] = 652664;
		vect[35][0] = 667964; vect[35][1] = 783556;
		vect[36][0] = 726104; vect[36][1] = 796696;
		vect[37][0] = 802725; vect[37][1] = 863835;
		vect[38][0] = 879712; vect[38][1] = 901424;
		vect[39][0] = 898216; vect[39][1] = 980984;
	}
	
	static PrintWriter out;
	static StreamTokenizer in;
	static int nextInt() throws IOException {
		in.nextToken();
		return (int)in.nval;
	}
}

/*
int[] vect = new int[1000001];
for (int i = 2; i < 1000001; i++) {
	int j = 2;
	vect[i] = 1;
	while (j * j < i) {
		if (i % j == 0) {
			vect[i] += j + i / j;
		}
		++j;
	}
	if (j * j == i)
		vect[i] += j;
}

int len = 0;
for (int i = 2; i < 1000001; i++)
	if (i < vect[i] && vect[i] < 1000001 && vect[vect[i]] == i) { 
		out.println("vect[" + len + "][0] = " + i + "; vect[" + len + "][1] = " + vect[i] + ";");
		len++;
	}
*/