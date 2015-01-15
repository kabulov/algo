import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class Main {
	public static void main(String[] args) throws IOException {
		int[] ram = new int[1000];
		int[] cpu = new int[10];
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); 
		int n = Integer.parseInt(in.readLine());
		in.readLine();
		
		int ctr;
		String buf;
		
		int len;
		int pos;
		
		for (int lp = 0; lp < n; ++lp) {
			len = 0;
			Arrays.fill(ram, 0);
			Arrays.fill(cpu, 0);
			
			while ((buf = in.readLine()) != null && buf.length() > 0)
				ram[len++] = Integer.parseInt(buf);
				
			pos = 0;
			ctr = 1;
			while (ram[pos] != 100) {
				++ctr;
				switch(ram[pos] / 100) {
				case 2:
					cpu[scd(ram[pos])] = ram[pos] % 10;
					break;
				case 3:
					cpu[scd(ram[pos])] = (cpu[scd(ram[pos])] + ram[pos] % 10) % 1000;
					break;
				case 4:
					cpu[scd(ram[pos])] = (cpu[scd(ram[pos])] * (ram[pos] % 10)) % 1000;
					break;
				case 5:
					cpu[scd(ram[pos])] = cpu[ram[pos] % 10];
					break;
				case 6:
					cpu[scd(ram[pos])] = (cpu[scd(ram[pos])] + cpu[ram[pos] % 10]) % 1000; 
					break;
				case 7:
					cpu[scd(ram[pos])] = (cpu[scd(ram[pos])] * cpu[ram[pos] % 10]) % 1000; 
					break;
				case 8:
					cpu[scd(ram[pos])] = ram[cpu[ram[pos] % 10]];
					break;
				case 9:
					ram[cpu[ram[pos] % 10]] = cpu[scd(ram[pos])];
					break;
				case 0:
					if (cpu[ram[pos] % 10] != 0) {
						pos = cpu[scd(ram[pos])];
						continue;
					}
					break;
				}
				++pos;
			}			
		
			prn(ctr);
			if (lp < n - 1) prn("");
		}
	}
	
	static int scd(int n) {
		return (n / 10) % 10;
	}
	
	static void prn(Object o) {
		System.out.println(o);
	}
}