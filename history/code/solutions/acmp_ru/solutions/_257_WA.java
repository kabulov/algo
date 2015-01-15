

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
import java.util.regex.Pattern;


public class Main implements Runnable {
	public static void main(String[] args) {
		new Thread(new Main()).start();
	}

//	BufferedReader in;
	Scanner in;
//	StreamTokenizer in;
	PrintWriter out;
	
	public void run() {
		try {
			Locale.setDefault(Locale.US);
//			in = new StreamTokenizer(new FileReader("input.txt"));
			in = new Scanner(new File("input.txt"));
//			in = new BufferedReader(new FileReader("input.txt"));
			out = new PrintWriter("output.txt");
			solve();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		} finally {
			out.close();
		}
	}
	
//	int next() throws IOException {
//		in.nextToken();
//		return (int)in.nval;
//	}
//	

	void offer(long a, long b, long c, long[] v) {
		BigInteger A, B, C, D;
		A = new BigInteger(new Long(a).toString());
		B = new BigInteger(new Long(b).toString());
		C = new BigInteger(new Long(c).toString());
		D = B.multiply(B).subtract(A.multiply(C).multiply(BigInteger.ONE.add(BigInteger.ONE).add(BigInteger.ONE).add(BigInteger.ONE)));
		if (D.equals(BigInteger.ZERO)) {
			if (B.mod(A.add(A)).equals(BigInteger.ZERO)) {
				v[0] = 1; 
				v[1] = -b / (2 * a);
			} else {
				v[0] = 0;
			}
			return;
		} else
		if (D.compareTo(BigInteger.ZERO) < 0) {
			v[0] = 0;
			return;
		} else {
			v[0] = 0;
			BigInteger sq = sqrt(D);
			if (!sq.multiply(sq).equals(D)) return;
			long d = Long.parseLong(sq.toString());
			long fst = 0, scd = 0;
			boolean Fs = false, Sc = false;
			if ((-b + d) % (2 * a) == 0) {
				fst = (-b + d) / (2 * a);
				++v[0];
				Fs = true;
			}
			if ((-b - d) % (2 * a) == 0) {
				scd = (-b - d) / (2 * a);
				++v[0];
				Sc = true;
			}
			if (Fs && Sc && fst > scd) {
				long tmp = fst;
				fst = scd;
				scd = tmp;
			}
			if (Fs) {
				v[1] = fst;
				if (Sc) v[2] = scd;
			} else {
				if (Sc) v[1] = scd;			
			}
			return;
		}
	}

	BigInteger sqrt(BigInteger num) {		//!!
		if (num.compareTo(BigInteger.ONE) <= 0) return num;
		
		BigInteger lt = BigInteger.ZERO, mid, two = BigInteger.ONE.add(BigInteger.ONE), rt = new BigInteger(num.toString());
		while (lt.add(BigInteger.ONE).compareTo(rt) < 0) {
			mid = lt.add(rt).divide(two);
			if (mid.multiply(mid).compareTo(num) > 0)
				rt = mid;
			else
				lt = mid;
		}
		return lt;
	}
	
	long a, b, c, d;

	void solve() {
		a = in.nextLong();
		b = in.nextLong();
		c = in.nextLong();
		d = in.nextLong();
		
		if (a == 0 && b == 0 && c == 0) {
			out.println(d == 0 ? "-1" : "0");
			return;
		}

		if (a == 0 && b == 0) {
			if (d % c == 0) {
				out.println("1 " + (-d / c));
			} else {
				out.println("0");
			}
			return;
		}

		if (a == 0) {
			long[] v = new long[10];
			offer(b, c, d, v);
			out.print(v[0]);
			for (int i = 1; i <= v[0]; ++i) out.print(" " + v[i]);
		
			return;
		}

		
		//last case : x^3;
		if (rootamt(3 * a, 2 * b, c) <= 1) {
			 BigInteger x = findwide(a, b, c, d, (long)-1e15, (long)1e15);
			 if (x == null) {
				 out.println("0");
			 } else {
				 out.print("1 ");
				 out.println(x);
			 }				 
		} else {
			//hard!!
			//here!!!!!!
			int anslen = 0;
			long[] answer = new long[10];
			//2 roots
			double[] root = getroot(3 * a, 2 * b, c);
			
			double x = -b / (6.0 * a);
			//long X = (long)(x + 0.5) + 100;		//!!
			x = Math.round(x) + 1e1;
			
			double eps = 1e-8; //!!
			if (6.0 * a * x + b > 0) {
				//now its ascending : up, down, up
				x = root[0];
				if (abs(a * x * x * x + b * x * x + c * x + d) < eps) {
					//if (f(a, b, c, d, mid).compareTo(BigInteger.ZERO) == 0) {
					//long mid = (long)(root[0] + 0.5); //round
					x = Math.round(root[0]);
					if (abs(a * x * x * x + b * x * x + c * x + d) < eps) answer[anslen++] = (long)x;
//					if (f(a, b, c, d, mid).compareTo(BigInteger.ZERO) == 0) answer[anslen++] = mid;
					long tmp = (long)Math.ceil(root[1]);
					BigInteger myroot = findwide(a, b, c, d, tmp, (long)1e15);					
					if (myroot != null) answer[anslen++] = new Long(myroot.toString());
//					double lt = root[1];
//					double rt = 
				} else {
					x = root[1];
					if (abs(a * x * x * x + b * x * x + c * x + d) < eps) {
//						long mid = (long)(root[1] + 0.5);
//					    if (f(a, b, c, d, mid).compareTo(BigInteger.ZERO) == 0) answer[anslen++] = mid;
						x = Math.round(root[1]);
						if (abs(a * x * x * x + b * x * x + c * x + d) < eps) answer[anslen++] = (long)x;
						long tmp = (long)Math.floor(root[0]);
						BigInteger myroot = findwide(a, b, c, d, (long)-1e15, tmp);
						if (myroot != null) answer[anslen++] = new Long(myroot.toString());
					} else {
						//mid = (long)(root[0] + 0.5);	//too bad
						//if (f(a, b, c, d, mid).compareTo(BigInteger.ZERO) < 0) {
						x = root[0];
						if (a * x * x * x + b * x * x + c * x + d < 0) {							
							long tmp = (long)Math.ceil(root[1]);
							BigInteger myroot = findwide(a, b, c, d, tmp, (long)1e15);
							if (myroot == null) {
								//no roots
							} else {
								anslen = 1;
								answer[0] = new Long(myroot.toString());
							}
						} else {
							x = root[1];
							//mid = (long)(root[1] + 0.5); //too bad
							//if (f(a, b, c, d, mid).compareTo(BigInteger.ZERO) > 0) {
							if (a * x * x * x + b * x * x + c * x + d > 0) {
								long tmp = (long)Math.floor(root[0]);
								BigInteger myroot = findwide(a, b, c, d, (long)-1e15, tmp);
								if (myroot == null) {
									//no roots
								} else {
									anslen = 1;
									answer[0] = new Long(myroot.toString());
								}
							} else {
								long mid = (long)Math.floor(root[0]);
								BigInteger myroot = findwide(a, b, c, d, (long)-1e15, mid);
								if (myroot != null) {
									answer[anslen++] = new Long(myroot.toString());	
								}
								
								mid = (long)Math.ceil(root[0]);
								long tmp = (long)Math.floor(root[1]);
								myroot = findwide(a, b, c, d, mid, tmp);
								if (myroot != null) {
									answer[anslen++] = new Long(myroot.toString());
								}
								
								mid = (long)Math.ceil(root[1]);
								myroot = findwide(a, b, c, d, mid, (long)1e15);
								if (myroot != null) {
									answer[anslen++] = new Long(myroot.toString());
								}
							}
						}
					}
				}
			} else {
				//now its : down, up, down
				x = root[0];
				if (abs(a * x * x * x + b * x * x + c * x + d) < eps) {
//					long mid = (long)(root[0] + 0.5);
//					if (f(a, b, c, d, mid).compareTo(BigInteger.ZERO) == 0) answer[anslen++] = mid;
					x = Math.round(root[0]);
					if (abs(a * x * x * x + b * x * x + c * x + d) < eps) answer[anslen++] = (long)x;
					long tmp = (long)Math.ceil(root[1]);
					BigInteger myroot = findwide(a, b, c, d, tmp, (long)1e15);
					if (myroot != null) answer[anslen++] = new Long(myroot.toString());
				} else {
					x = root[1];
					if (abs(a * x * x * x + b * x * x + c * x + d) < eps) {
//						long mid = (long)(root[1] + 0.5);
//						if (f(a, b, c, d, mid).compareTo(BigInteger.ZERO) == 0) answer[anslen++] = mid;
						x = Math.round(root[1]);
						if (abs(a * x * x * x + b * x * x + c * x + d) < eps) answer[anslen++] = (long)x;
						long tmp = (long)Math.floor(root[0]);
						BigInteger myroot = findwide(a, b, c, d, (long)-1e15, tmp);
						if (myroot != null) answer[anslen++] = new Long(myroot.toString());
					} else {
						//mid = (long)(root[0] + 0.5);
						x = root[0];
						if (a * x * x * x + b * x * x + c * x + d > 0) {
						//if (f(a, b, c, d, mid).compareTo(BigInteger.ZERO) > 0) {
							long tmp = (long)Math.ceil(root[1]);
							BigInteger myroot = findwide(a, b, c, d, tmp, (long)1e15);
							if (myroot == null) {
								//no roots
							} else {
								anslen = 1;
								answer[0] = new Long(myroot.toString());
							}
						} else {
							x = root[1];
							if (a * x * x * x + b * x * x + c * x + d < 0) {
							//mid = (long)(root[1] + 0.5);
							//if (f(a, b, c, d, mid).compareTo(BigInteger.ZERO) < 0) {
								long tmp = (long)Math.floor(root[0]);
								BigInteger myroot = findwide(a, b, c, d, (long)-1e15, tmp);
								if (myroot == null) {
									//no roots
								} else {
									anslen = 1;
									answer[0] = new Long(myroot.toString());
								}
							} else {
								long mid = (long)Math.floor(root[0]);
								BigInteger myroot = findwide(a, b, c, d, (long)-1e15, mid);
								if (myroot != null) {
									answer[anslen++] = new Long(myroot.toString());
								}
								
								mid = (long)Math.ceil(root[0]);
								long tmp = (long)Math.floor(root[1]);
								myroot = findwide(a, b, c, d, mid, tmp);
								if (myroot != null) {
									answer[anslen++] = new Long(myroot.toString());
								}
								
								mid = (long)Math.ceil(root[1]);
								myroot = findwide(a, b, c, d, mid, (long)1e15);
								if (myroot != null) {
									answer[anslen++] = new Long(myroot.toString());
								}
							}							
						}
					}
				}
			}
			
			out.print(anslen);
			Arrays.sort(answer, 0, anslen);
			for (int i = 0; i < anslen; ++i) out.print(" " + answer[i]);
		}

		return;
		
	}
	
	double[] getroot(long a, long b, long c) {
		//must BigDecimal here -> binary search of root
		//bad precision now
		double[] ans = new double[2];
		double d = Math.sqrt((double)b * b - 4 * (double)a * c);
		ans[0] = (-b + d) / (2.0 * a);
		ans[1] = (-b - d) / (2.0 * a);
		if (ans[0] > ans[1]) {
			double tmp = ans[0];
			ans[0] = ans[1];
			ans[1] = tmp;
		}
		return ans;
	}
	
	int rootamt(long a, long b, long c) {
		BigInteger A = new BigInteger(new Long(a).toString()), B = new BigInteger(new Long(b).toString()), C = new BigInteger(new Long(c).toString());
		BigInteger D = B.multiply(B).subtract(A.multiply(C).multiply(BigInteger.ONE.add(BigInteger.ONE).add(BigInteger.ONE).add(BigInteger.ONE)));
		int t = D.compareTo(BigInteger.ZERO);
		if (t < 0) return 0;
		if (t == 0) return 1;
		return 2;
	}
	
	BigInteger f(long a, long b, long c, long d, long x) {
		BigInteger A = new BigInteger(Long.valueOf(a).toString());
		BigInteger B = new BigInteger(Long.valueOf(b).toString());
		BigInteger C = new BigInteger(Long.valueOf(c).toString());
		BigInteger D = new BigInteger(Long.valueOf(d).toString());
		BigInteger X = new BigInteger(Long.valueOf(x).toString());		
		return D.add(X.multiply(C.add(X.multiply(B.add(X.multiply(A))))));
	}
	
	BigInteger findwide(long a, long b, long c, long d, long lt, long rt) { //rt - lt very big
		if (f(a, b, c, d, lt).compareTo(BigInteger.ZERO) == 0) return new BigInteger(new Long(lt).toString());
		if (f(a, b, c, d, rt).compareTo(BigInteger.ZERO) == 0) return new BigInteger(new Long(rt).toString());
		
		BigInteger temp = f(0, 3 * a, 2 * b, c, rt);
		if (temp.compareTo(BigInteger.ZERO) > 0) {
			double eps = 1e-5;
			double l = lt, r = rt;
			while (abs(r - l) > eps) {
				double m = (l + r) / 2;
				if (a * m * m * m + b * m * m + c * m + d < 0) {//eps
					l = m;
				} else {
					r = m;
				}
			}
			
			l = (l + r) / 2;
			l = Math.round(l);
			if (abs(a * l * l * l + b * l * l + c * l + d) < eps) return new BigInteger(new Long((long)l).toString());
			return null;
			//vozrastaet
//			while (lt + 1 < rt) {
//				long mid = (lt + rt) / 2;
//				if (f(a, b, c, d, mid).compareTo(BigInteger.ZERO) > 0) {
//					rt = mid;
//				} else {
//					lt = mid;
//				}
//			}
//			if (f(a, b, c, d, lt).compareTo(BigInteger.ZERO) == 0) return new BigInteger(new Long(lt).toString());		//!!
//			return null;
		} else { //<
			//ubivaet
			double eps = 1e-5;
			double l = lt, r = rt;
			while (abs(r - l) > eps) {
				double m = (l + r) / 2;
				if (a * m * m * m + b * m * m + c * m + d < 0) {//eps
					r = m;
				} else {
					l = m;
				}
			}
			
			l = (l + r) / 2;
			l = Math.round(l);
			if (abs(a * l * l * l + b * l * l + c * l + d) < eps) return new BigInteger(new Long((long)l).toString());
			return null;
			
//			while (lt + 1 < rt) {
//				long mid = (lt + rt) / 2;
//				if (f(a, b, c, d, mid).compareTo(BigInteger.ZERO) < 0) {
//					rt = mid;
//				} else {
//					lt = mid;
//				}
//			}
//			if (f(a, b, c, d, lt).compareTo(BigInteger.ZERO) == 0) return new BigInteger(new Long(lt).toString());		//!!
//			if (f(a, b, c, d, rt).compareTo(BigInteger.ZERO) == 0) return new BigInteger(new Long(rt).toString());		//!!
//			return null;
		}
	}
}