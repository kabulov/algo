import static java.lang.Math.min;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Arrays;

public class Solution implements Runnable {
    public static void main(String[] argv) {
        new Thread(new Solution()).start();
    }
    
    StreamTokenizer in;
    PrintWriter out;
    
    String FileName = "points";
    
    public void run() {
        try {
            in = new StreamTokenizer(new FileReader(FileName + ".in"));
            out = new PrintWriter(FileName + ".out");
//          in = new StreamTokenizer(new FileReader("in.txt"));
//          out = new PrintWriter("out.txt");
            solve();            
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        } finally {
            out.close();
        }
    }
    
    int next() throws IOException {
        in.nextToken();
        return (int)in.nval;
    }
    
    void solve() throws IOException {
        n = next();
        m = next();
        
        g = new ArrayList[n];
        for (int i = 0; i < n; ++i) g[i] = new ArrayList<Integer>();
        
        for (int i = 0, f, s; i < m; ++i) {
            f = next() - 1;
            s = next() - 1;
            g[f].add(s);
            g[s].add(f);
        }
        
        timer = 0;
        tin = new int[n];
        fup = new int[n];
        Arrays.fill(tin, -1);
        
        map = new boolean[n];
        Arrays.fill(map, false);
        
        bfs(0, -1);
        
        int[] ans = new int[n];
        int anslen = 0;
        for (int i = 0; i < n; ++i)
            if (map[i]) {
                ans[anslen++] = i + 1;
            }
        
        out.println(anslen);
        Arrays.sort(ans, 0, anslen);
        for (int i = 0; i < anslen; ++i)
            out.print(ans[i] + " ");
    }   
    
    void bfs(int v, int p) {
        int children = 0;
        boolean flag = false;
        tin[v] = fup[v] = ++timer;
        for (int i = 0, sz = g[v].size(), to; i < sz; ++i) {
            to = g[v].get(i);
            if (to == p) continue;
            if (tin[to] != -1) {
                fup[v] = min(fup[v], tin[to]);
            } else {
                bfs(to, v);
                fup[v] = min(fup[v], fup[to]);
                if (p != -1 && tin[v] <= fup[to] && !flag) {
                    map[v] = true;
                    flag = true;
                }
                ++children;
            }
        }
        if (p == -1 && children > 1 && !flag) {
            map[v] = true;
        }
    }
    
    boolean[] map;
    
    int timer;
    int[] tin, fup;
    
    ArrayList<Integer> g[];
    int n, m;
}