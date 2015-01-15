import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");

		init();
		
		int j = in.nextInt() - 1;
		int i = in.nextInt() - 1;
		
		if (vect[j] == i)
			out.println(2);
		else
			out.println(1);
		
		out.close();
	}
	
	static int[] vect;
	static void init() {
		vect = new int[250];
		Arrays.fill(vect, 0);
		vect[1] = 2;
		vect[2] = 1;
		vect[3] = 5;
		vect[5] = 3;
		vect[4] = 7;
		vect[7] = 4;
		vect[6] = 10;
		vect[10] = 6;
		vect[8] = 13;
		vect[13] = 8;
		vect[9] = 15;
		vect[15] = 9;
		vect[11] = 18;
		vect[18] = 11;
		vect[12] = 20;
		vect[20] = 12;
		vect[14] = 23;
		vect[23] = 14;
		vect[16] = 26;
		vect[26] = 16;
		vect[17] = 28;
		vect[28] = 17;
		vect[19] = 31;
		vect[31] = 19;
		vect[21] = 34;
		vect[34] = 21;
		vect[22] = 36;
		vect[36] = 22;
		vect[24] = 39;
		vect[39] = 24;
		vect[25] = 41;
		vect[41] = 25;
		vect[27] = 44;
		vect[44] = 27;
		vect[29] = 47;
		vect[47] = 29;
		vect[30] = 49;
		vect[49] = 30;
		vect[32] = 52;
		vect[52] = 32;
		vect[33] = 54;
		vect[54] = 33;
		vect[35] = 57;
		vect[57] = 35;
		vect[37] = 60;
		vect[60] = 37;
		vect[38] = 62;
		vect[62] = 38;
		vect[40] = 65;
		vect[65] = 40;
		vect[42] = 68;
		vect[68] = 42;
		vect[43] = 70;
		vect[70] = 43;
		vect[45] = 73;
		vect[73] = 45;
		vect[46] = 75;
		vect[75] = 46;
		vect[48] = 78;
		vect[78] = 48;
		vect[50] = 81;
		vect[81] = 50;
		vect[51] = 83;
		vect[83] = 51;
		vect[53] = 86;
		vect[86] = 53;
		vect[55] = 89;
		vect[89] = 55;
		vect[56] = 91;
		vect[91] = 56;
		vect[58] = 94;
		vect[94] = 58;
		vect[59] = 96;
		vect[96] = 59;
		vect[61] = 99;
		vect[99] = 61;
		vect[63] = 102;
		vect[102] = 63;
		vect[64] = 104;
		vect[104] = 64;
		vect[66] = 107;
		vect[107] = 66;
		vect[67] = 109;
		vect[109] = 67;
		vect[69] = 112;
		vect[112] = 69;
		vect[71] = 115;
		vect[115] = 71;
		vect[72] = 117;
		vect[117] = 72;
		vect[74] = 120;
		vect[120] = 74;
		vect[76] = 123;
		vect[123] = 76;
		vect[77] = 125;
		vect[125] = 77;
		vect[79] = 128;
		vect[128] = 79;
		vect[80] = 130;
		vect[130] = 80;
		vect[82] = 133;
		vect[133] = 82;
		vect[84] = 136;
		vect[136] = 84;
		vect[85] = 138;
		vect[138] = 85;
		vect[87] = 141;
		vect[141] = 87;
		vect[88] = 143;
		vect[143] = 88;
		vect[90] = 146;
		vect[146] = 90;
		vect[92] = 149;
		vect[149] = 92;
		vect[93] = 151;
		vect[151] = 93;
		vect[95] = 154;
		vect[154] = 95;
		vect[97] = 157;
		vect[157] = 97;
		vect[98] = 159;
		vect[159] = 98;
		vect[100] = 162;
		vect[162] = 100;
		vect[101] = 164;
		vect[164] = 101;
		vect[103] = 167;
		vect[167] = 103;
		vect[105] = 170;
		vect[170] = 105;
		vect[106] = 172;
		vect[172] = 106;
		vect[108] = 175;
		vect[175] = 108;
		vect[110] = 178;
		vect[178] = 110;
		vect[111] = 180;
		vect[180] = 111;
		vect[113] = 183;
		vect[183] = 113;
		vect[114] = 185;
		vect[185] = 114;
		vect[116] = 188;
		vect[188] = 116;
		vect[118] = 191;
		vect[191] = 118;
		vect[119] = 193;
		vect[193] = 119;
		vect[121] = 196;
		vect[196] = 121;
		vect[122] = 198;
		vect[198] = 122;
		vect[124] = 201;
		vect[201] = 124;
		vect[126] = 204;
		vect[204] = 126;
		vect[127] = 206;
		vect[206] = 127;
		vect[129] = 209;
		vect[209] = 129;
		vect[131] = 212;
		vect[212] = 131;
		vect[132] = 214;
		vect[214] = 132;
		vect[134] = 217;
		vect[217] = 134;
		vect[135] = 219;
		vect[219] = 135;
		vect[137] = 222;
		vect[222] = 137;
		vect[139] = 225;
		vect[225] = 139;
		vect[140] = 227;
		vect[227] = 140;
		vect[142] = 230;
		vect[230] = 142;
		vect[144] = 233;
		vect[233] = 144;
		vect[145] = 235;
		vect[235] = 145;
		vect[147] = 238;
		vect[238] = 147;
		vect[148] = 240;
		vect[240] = 148;
		vect[150] = 243;
		vect[243] = 150;
		vect[152] = 246;
		vect[246] = 152;
		vect[153] = 248;
		vect[248] = 153;
	}
}

/*
boolean[][] map = new boolean[250][250];
for (int i = 0; i < 250; i++)
	for (int j = 0; j < 250; j++)
		map[i][j] = true;

for (int i = 0; i < 250; ++i)
	map[i][0] = map[0][i] = map[i][i] = false;

int i, j, p;
for (int mid = 2; mid < 250; ++mid) {
	for (i = 1; i < mid; ++i)
		if (map[mid][i]) {
			for (p = 0; p < 250; ++p) {
				map[mid][p] = false;
				map[p][i] = false;						
			}
			
			for (p = mid, j = i; p < 250; p++, j++)
				map[p][j] = false;
				
			out.println("vect[" + i + "] = " + mid + ";");
			break;
		}
	
	for (i = 1; i < mid; ++i)
		if (map[i][mid]) {
			for (p = 0; p < 250; p++) {
				map[p][mid] = false;
				map[i][p] = false;
			}
			
			for (p = i, j = mid; j < 250; p++, j++)
				map[p][j] = false;
			
			out.println("vect[" + mid + "] = " + i + ";");
			break;
		}
}
*/