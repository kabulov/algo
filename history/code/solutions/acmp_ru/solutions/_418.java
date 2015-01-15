import static java.lang.Math.max;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.ListIterator;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("input.txt")) ;
		PrintWriter out = new PrintWriter("output.txt");
	
		LinkedList<Integer> list = new LinkedList<Integer>();
		list.add(0);
		ListIterator<Integer> it = list.listIterator();
		it.next();
		
		int maxlen = 0, curlen = 0;//is '\': +=1
		String str;
		if ((str = in.readLine()) == null) {
			out.println(0);
			out.close();
			return;
		}
		
		char[] inp = str.toCharArray();
		
		for (int i = 0; i < inp.length; ++i) {
			switch(inp[i]) {
			case '\\':
				it.add(0);
				curlen = 0;
				break;
			case '<':
				if (curlen > 0) {
					--curlen;
					it.set(curlen);
				} else {
					it.previous();
					if (it.hasPrevious()) {
						it.remove();
						curlen = it.previous();
					}
					it.next();
				}
				break;
			case '^':
				it.previous();
				if (it.hasPrevious()) {
					curlen = it.previous();
				}
				it.next();
				break;
			case '|':
				if (it.hasNext()) {
					curlen = it.next();
				}
				break;
			default:
				it.previous();
				it.next();
				++curlen;
				it.set(curlen);
				maxlen = max(maxlen, curlen);
			}
		}
		
		out.println(maxlen);
		out.close();
	}	
}