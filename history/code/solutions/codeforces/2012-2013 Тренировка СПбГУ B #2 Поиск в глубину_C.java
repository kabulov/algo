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
    
    String FileName = "bridges";
    
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
        
        ev = new edge[m];
        for (int i = 0, f, s; i < m; ++i) {
            f = next() - 1;
            s = next() - 1;
            ev[i] = new edge(f, s, i + 1);
        }
    
        g = new ArrayList[n];
        for (int i = 0; i < n; ++i) g[i] = new ArrayList<Integer>();
        
        for (int i = 0, f, s; i < m; ++i) {
            f = ev[i].fst;
            s = ev[i].scd;
            g[f].add(i);
            g[s].add(i);
        }
        
        tin = new int[n];
        fup = new int[n];
    
        timer = 0;
        anslen = 0;
        ans = new int[m];

        Arrays.fill(tin, -1);
        dfs(0, -1);
                
        out.println(anslen);
        Arrays.sort(ans, 0, anslen);
        for (int i = 0; i < anslen; ++i)
            out.print(ans[i] + " ");
    }
    
    void dfs(int v, int p) { 
        tin[v] = fup[v] = ++timer;
        edge tmp;
        for (int i = 0, sz = g[v].size(), to; i < sz; ++i) {
            tmp = ev[g[v].get(i)];
            to = tmp.fst == v ? tmp.scd : tmp.fst;
            if (to == p) continue;
            if (tin[to] != -1) {
                fup[v] = min(fup[v], tin[to]);
            } else {
                dfs(to, v);
                fup[v] = min(fup[v], fup[to]);
                if (tin[v] < fup[to]) {
                    ans[anslen++] = tmp.pos;
                }
            }
        }
    }
    
    int anslen;
    int[] ans;
    
    int timer;
    int[] tin, fup;
    ArrayList<Integer> g[];
    edge[] ev;
    int n, m;
}

class edge {
    int fst, scd, pos;
    edge(int f, int s, int p) {
        fst = f;
        scd = s;
        pos = p;
    }
}