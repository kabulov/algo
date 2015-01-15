import static java.lang.Math.sin;
import static java.lang.Math.cos;
import static java.lang.Math.abs;
import static java.lang.Math.cbrt;
import static java.lang.Math.min;
import static java.lang.Math.max;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Pattern;
 
 
public class Main {
//  static int next() throws IOException {
//      in.nextToken();
//      return (int)in.nval;
//  }
//  static StreamTokenizer in;
//  static Scanner in;
    static BufferedReader in;
    static PrintWriter out;
     
    public static void main(String[] args) throws IOException {
        //in = new Scanner(new File(""));
        //in = new StreamTokenizer(new FileReader(""));
         
        in = new BufferedReader(new FileReader("input.txt"));
        out = new PrintWriter("output.txt");
         
        String buf = in.readLine().trim();
        
        if (badprobels(buf)) {
        	out.println("Error");
        	out.close();
        	return;
        }
        
        buf = buf.replaceAll(" ", "");
        if (buf.length() == 0) {//Error ? 
            out.close();
            return;
        }
//        if (buf.equals("--1")) {
 //       	out.println("1");
  //      	out.close();
   //     	return;
    //    }
        
        par = new parser(buf);
         
        stvar = new Stack<Double>();
        stop = new Stack<Character>();
         
        state = 0;
         
outer:  while (true) {
            int t = par.nextToken();
             
            if (t == -1) {
                state = -1;
                break;
            }   
             
            if (t == 1) {
                switch(par.token) {
                case '+': 
                case '-':
                    if (state == 0) {//maybe while if --- allowed; erroneous
                        stop.add(par.token == '+' ? 'p' : 'm');
                        state = 3;
                    } else
                    if (state == 1 || state == 4) {
                        while (!stop.isEmpty() && stop.peek() != '(' && priority(stop.peek()) >= priority(par.token)) 
                            if (!perform()) {
                                state = -1;
                                break outer;
                            }                               
                        stop.add(par.token);
                        state = 2;
                    } else {
                        state = -1;
                        break outer;
                    }
                    break;
                case '*':
                case '/':
                    if (state == 1 || state == 4) {
                        while (!stop.isEmpty() && stop.peek() != '(' && priority(stop.peek()) >= priority(par.token)) 
                            if (!perform()) {
                                state = -1;
                                break outer;
                            }                               
                        stop.add(par.token);
                        state = 2;
                    } else {
                        state = -1;
                        break outer;
                    }
                    break;
                case '(':
                    if (state == 1 || state == 4) {
                        state = -1;
                        break outer;
                    }
                    stop.push('(');
                    state = 0;
                    break;
                case ')':
                    if (!(state == 1 || state == 4)) {
                        state = -1;
                        break outer;
                    }
                    if (stop.isEmpty()) {
                        state = -1;
                        break outer;
                    }
                    while (true) {
                        if (stop.isEmpty()) {
                            state = -1;
                            break outer;
                        }
                        if (stop.peek() == '(') {
                            stop.pop();
                            break;
                        } else {
                            if (!perform()) {
                                state = -1;
                                break outer;
                            }
                        }
                    }
                    state = 1; //erroneous
                    break;
                case 's':case 'c':
                    if (state == 0 || state == 2 || state == 3) {
                        //while needed ? erroneous
                        stop.push(par.token);
                        state = 5;
                    } else {
                        state = -1;
                        break outer;
                    }
                    break;
                case 'n':
                    if (state == 0 || state == 2 || state == 3) {
                        //parsedouble problems : erroneous
                        stvar.push(Double.parseDouble(par.number));
                        state = 4;
                    } else {
                        state = -1;
                        break outer;
                    }
                    break;
                default:
                    //never falls here
                }
                continue;
            }
             
            //t == 0
            state = 0;
            while (!stop.isEmpty()) {
                if (stop.peek() == '(' || !perform()) {
                    state = -1;
                    break;
                }
            }
             
            break;
        }
         
        if (state == -1)
            out.println("Error");
        else
            out.println(stvar.pop()); //maybe needs precision
         
        out.close();
    }   
     
    static boolean badprobels(String str) {
    	for (int i = 0; i < str.length(); ++i) {
    		if (i > 0 && str.charAt(i) == ' ' && '0' <= str.charAt(i - 1) && str.charAt(i - 1) <= '9') {
    			int j = i + 1;
    			while (j < str.length() && str.charAt(j) == ' ') ++j;
    			if (j < str.length() && '0' <= str.charAt(j) && str.charAt(j) <= '9') {
    				return true;
    			}
    		}
    	}
    	return false;
    }
    
    static int state;
     
    static boolean perform() {
        char c = stop.pop();
        switch(c) {
        case '+':case '-':case '*':case '/':
            if (stvar.size() < 2) return false;     //erroneous
            double s = stvar.pop(), f = stvar.pop();
            //maybe stvar isempty needed !!
            if (c == '+') f += s;
            if (c == '-') f -= s;
            if (c == '*') f *= s;
            if (c == '/') f /= s;
            stvar.push(f);
            break;
        case 'p':
            if (stvar.isEmpty()) return false;      //erroneous
            //stvar.push(+stvar.pop());
            break;
        case 'm':
            if (stvar.isEmpty()) return false;      //erroneous
            stvar.push(-stvar.pop()); //negate
            break;
        case 's':
            if (stvar.isEmpty()) return false;      //erroneous
            stvar.push(sin(stvar.pop()));
            break;
        case 'c':
            if (stvar.isEmpty()) return false;      //erroneous
            stvar.push(cos(stvar.pop()));
            break;
        }
        return true;
    }
     
    static int priority(char c) {
        switch(c) {
        case '+': return 0; //binary
        case '-': return 0; //binary
        case '*': return 1; //binary
        case '/': return 1; //binary
        case 'p': return 2; //unary +
        case 'm': return 2; //unary -
        case 's': return 2; //sin
        case 'c': return 2; //cos
        default: return -1;
        }
    }
     
    static parser par;
    static Stack<Double> stvar;
    static Stack<Character> stop;
}
 
class parser {
    char[] src;
    int pos, len;
    char cur, token;
    String number;
    String set = "0123456789+-*/().sinco";
     
    parser(String from) {
        src = from.toCharArray();
        len = src.length;
        pos = -1;   //!!
    }
     
    int nextChar() {//getchar
        ++pos;
        if (pos >= len) return 0;   //eof
        cur = src[pos];
        return 1;   //good
    }
     
    boolean bad(char c) {
        return set.indexOf(c) < 0;
    }
     
    boolean isDigit(char c) {
        return '0' <= c && c <= '9';
    }
     
    int nextToken() {
        if (nextChar() == 0) return 0;
        if (bad(cur)) return -1;
        if (cur == '-' || cur == '+' || cur == '*' || cur == '/' || cur == '(' || cur == ')') {
            token = cur;
            return 1;
        }
        if (cur == 's') {
            if (len - pos - 1 < 2) return -1; //sin must be, can be 3 or 4
            nextChar();
            if (cur != 'i') return -1;
            nextChar();
            if (cur != 'n') return -1;
            token = 's';
            return 1;
        }
        if (cur == 'c') {
            if (len - pos - 1 < 2) return -1;
            nextChar();
            if (cur != 'o') return -1;
            nextChar();
            if (cur != 's') return -1;
            token = 'c';
            return 1;
        }
        if (!isDigit(cur)) return -1;   //!! erroneous, maybe add || '.'
        token = 'n';
        StringBuilder num = new StringBuilder();
        num.append(cur);
        boolean dot = false;
        while (true) { 
            //21. -> normal schitaet
            if (nextChar() == 0) {
                number = num.toString();
                return 1;           
            }
            if (bad(cur)) return -1;
            if (cur != '.' && !isDigit(cur)) {
                --pos;  //erroneous
                number = num.toString();
                return 1;
            }
            if (cur == '.') {
                if (dot) return -1;
                dot = true;
            }
            num.append(cur);
        }
        //parsedouble needed???? !!! erroneous
        //number
        //is . allowed
    }
}