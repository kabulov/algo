import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		char[] v = in.next().toCharArray();
		
		char[] elem = new char[2];
		int len; //v itoge ne nujen
		
		int s = 0; //0 - vse xorosho do pozicii na kotoroy stoyu
		for (int i = 0; i < v.length; ) {
			if (!('A' <= v[i] && v[i] <= 'Z')) {	//v uslovii skazano chto garantiruetsa -> nifiga ne garantiruetsa i 'if' nujen
				s = -1;
				break; //s == 0
			}

			len = 1;
			elem[0] = v[i];
			
			++i;
			if (i == v.length) break;
			
			if ('A' <= v[i] && v[i] <= 'Z') {
				if (v[i] != v[i-1]) continue;
				
				if (i + 1 == v.length || !('a' <= v[i + 1] && v[i + 1] <= 'z')) {
					s = -1;
					break;
				}
				
				continue;
			} 
			
			if ('0' <= v[i] && v[i] <= '9') {
				if (v[i] == '0') { 				//!!!! a nado?
					s = -1;
					break;
				}
				
				if (v[i] == '1' && (i + 1 == v.length || !('0' <= v[i+1] && v[i+1] <= '9'))) {
					s = -1;
					break;
				}
				
				while (i < v.length && '0' <= v[i] && v[i] <= '9') ++i;
				
				if (i < v.length && v[i] == elem[0]) {			//!!! a nado?
					if (i + 1 == v.length || !('a' <= v[i+1] && v[i+1] <= 'z')) {
						s = -1;
						break;
					}
				}
				
				continue;
			}
			
//			if ('a' <= v[i] && v[i] <= 'z') {
				len = 2;
				elem[1] = v[i];
				
				++i;
				if (i == v.length) break;
				
				if ('0' <= v[i] && v[i] <= '9') {
					if (v[i] == '0') { 				//!!!! a nado?
						s = -1;
						break;
					}
					
					if (v[i] == '1' && (i + 1 == v.length || !('0' <= v[i+1] && v[i+1] <= '9'))) {
						s = -1;
						break;
					}
					
					while (i < v.length && '0' <= v[i] && v[i] <= '9') ++i;
					
					if (i + 1 < v.length && v[i] == elem[0] && v[i+1] == elem[1]) { // !!! a nado?
						s = -1;
						break;
					}
					
					continue;
				}
				
				//if ('A' <= v[i] && v[i] <= 'Z')
				if (i + 1 < v.length && v[i] == elem[0] && v[i+1] == elem[1]) {//!!! a nado?
					s = -1;
					break;
				}
				
//				continue;
//			} 
		}
		
		out.println(s == 0 ? "YES" : "NO");
		out.close();
	}
}