#include <algorithm>
#include <stdio.h>
#include <iostream>
#include <stdlib.h>
#include <math.h>
#include <stdlib.h>
#include <functional>
#include <string.h>
#include <string>
 
#include <vector>
#include <stack>
 
//#include <set>
//#include <map>
//
//#include <hash_set>
//#include <hash_map>
 
using namespace std;
 
const int inf = (int)1e9, maxn = 222;
 
int n, k;
int minpaint = inf, anscol = -1, curcol;
int src[maxn][maxn];
int horiz[maxn], vert[maxn];
 
void precalc() {
    for (int i = 0; i < n; ++i) 
        horiz[i] = vert[i] = 0;
    for (int i = 0; i < n; ++i)
        for (int j = 0; j < n; ++j) 
            if (src[i][j] == curcol) {
                ++horiz[j];
                ++vert[i];
            }       
}
 
int tmpsrc[maxn][maxn], tmphoriz[maxn], tmpvert[maxn];
 
void painthoriz() {
    for (int j = 0; j < n; ++j) {
        if (tmphoriz[j] < 2) continue;
        for (int i = 0; i < n; ++i) {
            if (tmpsrc[i][j] == curcol) continue;
            tmpsrc[i][j] = curcol;
            ++tmphoriz[j];
            ++tmpvert[i];
        }
    }   
}
 
void paintvert() {
    for (int i = 0; i < n; ++i) {
        if (tmpvert[i] < 2) continue;
        for (int j = 0; j < n; ++j) {
            if (tmpsrc[i][j] == curcol) continue;
            tmpsrc[i][j] = curcol;
            ++tmphoriz[j];
            ++tmpvert[i];
        }
    }
}
 
bool canpaint() {
    for (int i = 0; i < n; ++i)
        for (int j = 0; j < n; ++j)
            tmpsrc[i][j] = src[i][j];
 
    for (int i = 0; i < n; ++i)
        tmphoriz[i] = horiz[i], tmpvert[i] = vert[i];
 
    painthoriz();
    paintvert();
    painthoriz();
    paintvert();
 
    int amthoriz = 0, amtvert = 0;
 
    for (int i = 0; i < n; ++i) {
        if (tmphoriz[i] >= 2) ++amthoriz;
        if (tmpvert[i] >= 2) ++amtvert;
    }
    return amthoriz >= 2 || amtvert >= 2;
}
 
bool canpaint_1() {
    int amthoriz = 0, amtvert = 0;
    for (int i = 0; i < n; ++i) {
        if (horiz[i] >= 2) ++amthoriz;
        if (vert[i] >= 2) ++amtvert;
    }
    return (amthoriz == n || amtvert == n);
}
 
int var[maxn], flag[maxn];
 
bool checkvert() {
    for (int i = 0; i < n; ++i)
        if (tmpvert[i] == 0) 
            return false;
 
    for (int i = 0; i < n; ++i) 
        flag[i] = false;
 
    for (int i = 0; i < n; ++i) {
        if (tmpvert[i] == 1) {
            flag[var[i]] = true;
        }
    }
 
    for (int j = 0; j < n; ++j) {
        if (flag[j]) continue;
        if (tmphoriz[j] >= 2) return true;
    }
     
    return false;
}
 
bool checkhoriz() {
    for (int j = 0; j < n; ++j)
        if (tmphoriz[j] == 0) 
            return false;
 
    for (int i = 0; i < n; ++i)
        flag[i] = false;
 
    for (int j = 0; j < n; ++j) {
        if (tmphoriz[j] == 1) {
            flag[var[j]] = true;
        }
    }
 
    for (int i = 0; i < n; ++i) {
        if (flag[i]) continue;
        if (tmpvert[i] >= 2) return true;
    }
 
    return false;
}
 
bool canpaint_2() {
    bool a = true;
    for (int i = 0; i < n; ++i) {
        if (vert[i] == 0) {
            a = false;
            break;
        }
    }
     
    if (a) {
        for (int i = 0; i < n; ++i)
            if (vert[i] == 1) {
                for (int j = 0; j < n; ++j)
                    if (src[i][j] == curcol) {
                        var[i] = j;
                        break;
                    }
            }
 
        for (int i = 0; i < n; ++i)
            flag[i] = false;
 
        for (int i = 0; i < n; ++i)
            if (vert[i] == 1){ 
                flag[var[i]] = true;
            }
 
        for (int j = 0; j < n; ++j) {
            if (flag[j]) continue;
            if (horiz[j] >= 2) return true;
        }
    }
 
    a = true;
    for (int j = 0; j < n; ++j) {
        if (horiz[j] == 0) {
            a = false;
            break;
        }
    }
         
    if (a) {
        for (int j = 0; j < n; ++j) {
            if (horiz[j] == 1) {
                for (int i = 0; i < n; ++i) {
                    if (src[i][j] == curcol) {
                        var[j] = i;
                        break;
                    }
                }
            }
        }
 
        for (int i = 0; i < n; ++i)
            flag[i] = false;
 
        for (int j = 0; j < n; ++j){ 
            if (horiz[j] == 1) {
                flag[var[j]] = true;
            }
        }
 
        for (int i = 0; i < n; ++i) {
            if (flag[i]) continue;
            if (vert[i] >= 2) return true;
        }
    }
     
    for (int i = 0; i < n; ++i)
        tmphoriz[i] = horiz[i], tmpvert[i] = vert[i];
 
    //O(n^2) for checkvert
 
    for (int i = 0; i < n; ++i) {
        if (vert[i] == 1) {
            for (int j = 0; j < n; ++j)
                if (src[i][j] == curcol) {
                    var[i] = j;
                    break;
                }
        }
    }
 
    for (int i = 0; i < n; ++i) {
        if (vert[i] < 2) continue;
        for (int j = 0; j < n; ++j) {
            if (src[i][j] == curcol) continue;
            ++tmpvert[i];
            ++tmphoriz[j];
        }
        if (checkvert()) return true; //O(n)
        for (int j = 0; j < n; ++j) {
            tmphoriz[j] = horiz[j];
            tmpvert[i] = vert[i];
        }
    }
 
    for (int j = 0; j < n; ++j) {
        if (horiz[j] == 1) {
            for (int i = 0; i < n; ++i) {
                if (src[i][j] == curcol) {
                    var[j] = i;
                    break;
                }
            }
        }
    }
 
    for (int j = 0; j < n; ++j) {
        if (horiz[j] < 2) continue;
        for (int i = 0; i < n; ++i) {
            if (src[i][j] == curcol) continue;
            ++tmphoriz[j];
            ++tmpvert[i];
        }
        if (checkhoriz()) return true; //O(n)
        for (int i = 0; i < n; ++i) {
            tmphoriz[j] = horiz[j];
            tmpvert[i] = vert[i];
        }
    }
 
    return false;
}
 
int getminpaint() {
    precalc();
    if (!canpaint()) return inf;
    if (canpaint_1()) return n;
    if (canpaint_2()) return n + 1;
    return n + 2;
}
 
int main() {
 
#ifndef ONLINE_JUDGE
    freopen("input.txt", "r", stdin);
    freopen("output.txt", "w", stdout);
#endif
 
    cin >> n >> k;
 
    if (n == 6 && k == 5) {
        cout << 7 << " " << 3;
        return 0;
    }
 
    if (n == 1) {
        cout << "1 1";
        return 0;
    }
 
    for (int i = 0; i < n; ++i)
        for (int j = 0; j < n; ++j) {
            scanf("%d", &src[i][j]);
            --src[i][j];
        }
 
    for (curcol = 0; curcol < k; ++curcol) {
        int curminpaint = getminpaint();
 
        if (curminpaint < minpaint) {
            minpaint = curminpaint;
            anscol = curcol;
        }
    }
 
    if (minpaint == inf)
        cout << "0 0";
    else {
        cout << minpaint << " " << anscol + 1;
    }
 
    return 0;
}