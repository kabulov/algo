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
    
    String FileName = "cycle2";
    
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
        }
        
        yes = false;
        col = new int[n];
        Arrays.fill(col, 0);
        prev = new int[n];
        
        ans = new int[n];
        for (int i = 0; i < n && !yes; ++i)
            if (col[i] == 0) {
                prev[i] = -1;
                bfs(i);
            }
        
        if (!yes) out.println("NO");
    }
    
    void bfs(int v) {
        col[v] = 1;
        for (int i = 0, sz = g[v].size(), t; i < sz && !yes; ++i) {
            t = g[v].get(i);
            if (col[t] == 0) {
                prev[t] = v;
                bfs(t);
            } else
            if (col[t] == 1) {
                out.println("YES");
                
                int len = 0;
                while (v != t) {
                    ans[len++] = v + 1;
                    v = prev[v];
                }
                ans[len++] = t + 1;
                for (int j = len - 1; j >= 0; --j) 
                    out.print(ans[j] + " ");
                
                yes = true;
                return;
            }
        }
        col[v] = 2;
    }
    
    int[] ans;
    
    ArrayList<Integer> g[];
    int n, m;
    
    boolean yes;
    int[] col;
    int[] prev;
}