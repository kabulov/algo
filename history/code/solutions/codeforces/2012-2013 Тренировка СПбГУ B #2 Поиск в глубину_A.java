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
    
    String FileName = "ancestor";
    
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
        g = new ArrayList[n];
        for (int i = 0; i < n; ++i) g[i] = new ArrayList<Integer>();
        
        int node = 0;
        for (int i = 0, v; i < n; ++i) {
            v = next() - 1;
            if (v == -1)  {
                node = i;
                continue;
            }
            g[v].add(i);
        }
        
        tin = new int[n];
        tout = new int[n];
        Arrays.fill(tin, 0);
        Arrays.fill(tout, 0);
        ctr = 0;
        
        bfs(node);
        
        m = next();
        for (int i = 0, f, s; i < m; ++i) {
            f = next() - 1;
            s = next() - 1;
            out.println((tin[f] <= tin[s] && tout[s] <= tout[f]) ? 1 : 0);
        }
    }
    
    ArrayList<Integer> g[];
    int n, m, ctr;
    
    int[] tin, tout;
    
    void bfs(int v) {
        tin[v] = ++ctr;
        
        for (int i = 0, sz = g[v].size(); i < sz; ++i)
            bfs(g[v].get(i));
        
        tout[v] = ++ctr;
    }
}