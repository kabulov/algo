

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
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;
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
	
	void solve() throws IOException {
		int n = in.nextInt();
		if (n == 23 || n == 239) {
			out.println("IMPOSSIBLE");
			return;
		}
		vect = new int[10];
		if (n <= 10000000) {//1e7
			calc8(n); //maybe dont use cbrt inside
			Arrays.sort(vect, 0, len);
			for (int i = len - 1; i >= 0; --i) out.print(vect[i] + " ");
		} else {
			//O(1) <- one
			int one = 2;
//			initHash(n);
//			calcfast(n - one); //variations here 
//			calccbrt(n - one);
			calclog(n - one);
			//calcupdown
			Arrays.sort(vect, 0, len);
			for (int i = len - 1; i >= 0; --i) out.print(vect[i] + " ");
			for (int i = 0; i < one; ++i) out.print("1 ");
		}
//		vect = new int[10];
//		int lower = in.nextInt(), upper = in.nextInt();
//		for (int iter = lower; iter <= upper; ++iter) {
//			out.print(iter);
//			out.print(" -> ");
//			if (calc8(iter)) {
//				out.print("len = ");
//				out.print(len);
//				out.print(" :::: ");
//				for (int inner = 0; inner < len; ++inner) {
//					out.print(" ");
//					out.print(vect[inner]);
//				}
//				out.println();
//			} else {
//				out.println("#############");
//			}
//		}
	}
	
	void initlog(int n) {
		arr = new int[1000000];
		arrlen = 0;
		for (int i = 1, t; (t = i * i * i) <= n; ++i) arr[arrlen++] = t;
		for (int i = 1, ti; (ti = i * i * i) <= n; ++i)
			for (int j = i, tj; ti + (tj = j * j * j) <= n; ++j)
				arr[arrlen++] = ti + tj;
		Arrays.sort(arr, 0, arrlen);
		int sz = 1;
		for (int i = 1; i < arrlen; ++i)
			if (arr[i] != arr[i - 1]) {
				arr[sz++] = arr[i];
			}
		arrlen = sz;
	}
	
	int[] arr;
	int arrlen;
	
	boolean find(int key, int lt, int rt) {
		//if`s here
		int mid;
		while (lt + 1 < rt) {
			mid = (lt + rt) >>> 1;
			if (key < arr[mid])
				rt = mid;
			else
				lt = mid;
		}
		return arr[lt] == key;
	}
	
	boolean calclog(int n) {
		initlog(n);
		
		for (i1 = 1; (t1 = i1 * i1 * i1) <= n; ++i1) {
			n1 = n - t1;
			if (n1 == 0) {
				len = 1;
				vect[0] = i1;
				return true;
			}
			for (i2 = i1; (t2 = i2 * i2 * i2) <= n1; ++i2) {
				n2 = n1 - t2;
				if (n2 == 0) {
					len = 2;
					vect[0] = i1;
					vect[1] = i2;
					return true;
				}
				//const part up to here
				for (i3 = i2; (t3 = i3 * i3 * i3) <= n2; ++i3) {
					n3 = n2 - t3;
					if (n3 == 0) {
						len = 3;
						vect[0] = i1;
						vect[1] = i2;
						vect[2] = i3;
						return true;
					}
					for (i4 = i3; (t4 = i4 * i4 * i4) <= n3; ++i4) {
						n4 = n3 - t4;
						if (n4 == 0) {
							len = 4;
							vect[0] = i1;
							vect[1] = i2;
							vect[2] = i3;
							vect[3] = i4;
							return true;
						}
						//n^2 part up to here
						if (find(n4, 0, arrlen)) {
							for (int a = 0, ta; (ta = a * a * a) <= n4; ++a)
								//must cbrt here;
								for (int b = 0, tb; ta + (tb = b * b * b) <= n4; ++b)
									if (ta + tb == n4) {
										if (a == 0) {
											len = 5;
											vect[0] = i1;
											vect[1] = i2;
											vect[2] = i3;
											vect[3] = i4;
											vect[4] = b;
										} else {
											len = 6;
											vect[0] = i1;
											vect[1] = i2;
											vect[2] = i3;
											vect[3] = i4;
											vect[4] = a;
											vect[5] = b;	
										}
										return true;
									}
						}
					}	
				}	
			}
		}
		return false;
	}
	
	boolean calccbrt(int n) {
		for (i1 = 1; (t1 = i1 * i1 * i1) <= n; ++i1) {
			n1 = n - t1;
			if (n1 == 0) {
				len = 1;
				vect[0] = i1;
				return true;
			}
			for (i2 = i1; (t2 = i2 * i2 * i2) <= n1; ++i2) {
				n2 = n1 - t2;
				if (n2 == 0) {
					len = 2;
					vect[0] = i1;
					vect[1] = i2;
					return true;
				}
				//const part up to here
				for (i3 = i2; (t3 = i3 * i3 * i3) <= n2; ++i3) {
					n3 = n2 - t3;
					if (n3 == 0) {
						len = 3;
						vect[0] = i1;
						vect[1] = i2;
						vect[2] = i3;
						return true;
					}
					for (i4 = i3; (t4 = i4 * i4 * i4) <= n3; ++i4) {
						n4 = n3 - t4;
						if (n4 == 0) {
							len = 4;
							vect[0] = i1;
							vect[1] = i2;
							vect[2] = i3;
							vect[3] = i4;
							return true;
						}
						for (i5 = i4; (t5 = i5 * i5 * i5) <= n4; ++i5) {
							n5 = n4 - t5;
							if (n5 == 0) {
								len = 5;
								vect[0] = i1;
								vect[1] = i2;
								vect[2] = i3;
								vect[3] = i4;
								vect[4] = i5;
								return true;
							}
							//n^3 part up to here
							i6 = (int)cbrt(n5);
							if (i6 * i6 * i6 != n5) ++i6;
							if ((t6 = i6 * i6 * i6) == n5) {
								len = 6;
								vect[0] = i1;
								vect[1] = i2;
								vect[2] = i3;
								vect[3] = i4;
								vect[4] = i5;
								vect[5] = i6;
								return true;
							}
						}	
					}	
				}	
			}
		}
		return false;
	}
	
	void initHash(int n) {
		hs = new HashSet<Integer>();
		//must faster
		for (int i = 1, t; (t = i * i * i) <= n; ++i) hs.add(t);
		for (int i = 1, ti; (ti = i * i * i) <= n; ++i)
			for (int j = i, tj; ti + (tj = j * j * j) <= n; ++j)
			hs.add(ti + tj);		
	}
	
	HashSet<Integer> hs;
	
	boolean calcfast(int n) {
		initHash(n);
		for (i1 = 1; (t1 = i1 * i1 * i1) <= n; ++i1) {
			n1 = n - t1;
			if (n1 == 0) {
				len = 1;
				vect[0] = i1;
				return true;
			}
			for (i2 = i1; (t2 = i2 * i2 * i2) <= n1; ++i2) {
				n2 = n1 - t2;
				if (n2 == 0) {
					len = 2;
					vect[0] = i1;
					vect[1] = i2;
					return true;
				}
				//const part up to here
				for (i3 = i2; (t3 = i3 * i3 * i3) <= n2; ++i3) {
					n3 = n2 - t3;
					if (n3 == 0) {
						len = 3;
						vect[0] = i1;
						vect[1] = i2;
						vect[2] = i3;
						return true;
					}
					for (i4 = i3; (t4 = i4 * i4 * i4) <= n3; ++i4) {
						n4 = n3 - t4;
						if (n4 == 0) {
							len = 4;
							vect[0] = i1;
							vect[1] = i2;
							vect[2] = i3;
							vect[3] = i4;
							return true;
						}
						//n^2 part up to here
						if (hs.contains(n4)) {
							for (int a = 0, ta; (ta = a * a * a) <= n4; ++a)
								//must cbrt here;
								for (int b = 0, tb; ta + (tb = b * b * b) <= n4; ++b)
									if (ta + tb == n4) {
										if (a == 0) {
											len = 5;
											vect[0] = i1;
											vect[1] = i2;
											vect[2] = i3;
											vect[3] = i4;
											vect[4] = b;
										} else {
											len = 6;
											vect[0] = i1;
											vect[1] = i2;
											vect[2] = i3;
											vect[3] = i4;
											vect[4] = a;
											vect[5] = b;	
										}
										return true;
									}
						}
//						i5 = (int)cbrt(n4);
//						if (i5 * i5 * i5 != n4) ++i5;
//						if (i5 * i5 * i5 == n4) {
//							len = 5;
//							vect[0] = i1;
//							vect[1] = i2;
//							vect[2] = i3;
//							vect[3] = i4;
//							vect[4] = i5;
//							return true;
//						}
						//must cbrt here
//						for (i8 = i7; (t8 = i8 * i8 * i8) <= n7; ++i8) {
//							n8 = n7 - t8;
//							if (n8 == 0) {
//								len = 8;
//								vect[0] = i1;
//								vect[1] = i2;
//								vect[2] = i3;
//								vect[3] = i4;
//								vect[4] = i5;
//								vect[5] = i6;
//								vect[6] = i7;
//								vect[7] = i8;
//								return true;
//							}
//						}	
					}	
				}	
			}
		}
		return false;
	}
	
	boolean calc8(int n) {
		for (i1 = 1; (t1 = i1 * i1 * i1) <= n; ++i1) {
			n1 = n - t1;
			if (n1 == 0) {
				len = 1;
				vect[0] = i1;
				return true;
			}
			for (i2 = i1; (t2 = i2 * i2 * i2) <= n1; ++i2) {
				n2 = n1 - t2;
				if (n2 == 0) {
					len = 2;
					vect[0] = i1;
					vect[1] = i2;
					return true;
				}
				for (i3 = i2; (t3 = i3 * i3 * i3) <= n2; ++i3) {
					n3 = n2 - t3;
					if (n3 == 0) {
						len = 3;
						vect[0] = i1;
						vect[1] = i2;
						vect[2] = i3;
						return true;
					}
					for (i4 = i3; (t4 = i4 * i4 * i4) <= n3; ++i4) {
						n4 = n3 - t4;
						if (n4 == 0) {
							len = 4;
							vect[0] = i1;
							vect[1] = i2;
							vect[2] = i3;
							vect[3] = i4;
							return true;
						}
						for (i5 = i4; (t5 = i5 * i5 * i5) <= n4; ++i5) {
							n5 = n4 - t5;
							if (n5 == 0) {
								len = 5;
								vect[0] = i1;
								vect[1] = i2;
								vect[2] = i3;
								vect[3] = i4;
								vect[4] = i5;
								return true;
							}
							for (i6 = i5; (t6 = i6 * i6 * i6) <= n5; ++i6) {
								n6 = n5 - t6;
								if (n6 == 0) {
									len = 6;
									vect[0] = i1;
									vect[1] = i2;
									vect[2] = i3;
									vect[3] = i4;
									vect[4] = i5;
									vect[5] = i6;
									return true;
								}
								for (i7 = i6; (t7 = i7 * i7 * i7) <= n6; ++i7) {
									n7 = n6 - t7;
									if (n7 == 0) {
										len = 7;
										vect[0] = i1;
										vect[1] = i2;
										vect[2] = i3;
										vect[3] = i4;
										vect[4] = i5;
										vect[5] = i6;
										vect[6] = i7;
										return true;
									}
									i8 = (int)cbrt(n7);
									if (i8 * i8 * i8 != n7) ++i8;
									if (i8 * i8 * i8 == n7) {
										len = 8;
										vect[0] = i1;
										vect[1] = i2;
										vect[2] = i3;
										vect[3] = i4;
										vect[4] = i5;
										vect[5] = i6;
										vect[6] = i7;
										vect[7] = i8;
										return true;
									}
									//must cbrt here
//									for (i8 = i7; (t8 = i8 * i8 * i8) <= n7; ++i8) {
//										n8 = n7 - t8;
//										if (n8 == 0) {
//											len = 8;
//											vect[0] = i1;
//											vect[1] = i2;
//											vect[2] = i3;
//											vect[3] = i4;
//											vect[4] = i5;
//											vect[5] = i6;
//											vect[6] = i7;
//											vect[7] = i8;
//											return true;
//										}
//									}	
								}	
							}	
						}	
					}	
				}	
			}
		}
		return false;
	}
	
	int i1, i2, i3, i4, i5, i6, i7, i8;
	int t1, t2, t3, t4, t5, t6, t7, t8;
	int n1, n2, n3, n4, n5, n6, n7, n8;
	
	int len;
	int[] vect;
	
//	void prn(Object o) {
//		System.out.println(o);
//	}
	
}