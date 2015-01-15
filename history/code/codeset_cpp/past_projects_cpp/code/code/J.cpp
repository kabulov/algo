//#include <stdio.h>
//#include <stdlib.h>
//#include <algorithm>
//#include <iostream>
//#include <queue>
//#include <vector>
//#include <set>
//#include <string>
//using namespace std;
//#define pb push_back
//#define mp make_pair
//
//unsigned vals1[200];
//unsigned vals2[2000];
//unsigned vals3[40000];
//unsigned vals4[550000];
//unsigned vals5[2200000];
//unsigned vals6[2300000];
//unsigned vals7[800000];
//unsigned vals8[70000];
//unsigned vals9[700];
//const unsigned maxn=301;
//const unsigned lim=2000000000;
//char pp[maxn+1];
//unsigned p[maxn], top, tp[10];
//
//unsigned calc(unsigned v)
//{
//	unsigned i, ans=0;
//	for(i=0; i<tp[1]; i++)
//	{
//		ans+=v/vals1[i];
//		if (v/vals1[i]>0)
//			ans--;
//	}
//	for(i=0; i<tp[2]; i++)
//		ans-=v/vals2[i];
//	for(i=0; i<tp[3]; i++)
//		ans+=v/vals3[i];
//	for(i=0; i<tp[4]; i++)
//		ans-=v/vals4[i];
//	for(i=0; i<tp[5]; i++)
//		ans+=v/vals5[i];
//	for(i=0; i<tp[6]; i++)
//		ans-=v/vals6[i];
//	for(i=0; i<tp[7]; i++)
//		ans+=v/vals7[i];
//	for(i=0; i<tp[8]; i++)
//		ans-=v/vals8[i];
//	for(i=0; i<tp[9]; i++)
//		ans+=v/vals9[i];	
//	return ans;
//}
//
//int main()
//{
//#ifndef ONLINE_JUDGE
//   freopen("in.txt", "rt", stdin);
//   freopen("out.txt", "wt", stdout);
//#endif
//	unsigned i, j, k, f, a, b, l, r;
//	unsigned tmp;
//	scanf("%u %u %u", &a, &b, &k);
//	for(i=2; i<=k+1; i++)
//	{
//		f=1;
//		for(j=2; j*j<=i; j++)
//			if (!(i%j))
//			{
//				f=0;
//				break;
//			}
//		pp[i]=f;
//	}
//	for(i=0; i<maxn; i++)
//		if (pp[i])
//			p[top++]=i;
//	for(k=0; k<top; k++)
//	{
//		for(i=0; i<tp[8]; i++)
//		{
//			if (vals8[i]>lim/p[k])
//				continue;
//			tmp=vals8[i]*p[k];
//			if (tmp<lim)
//				vals9[tp[9]++]=tmp;
//		}
//		for(i=0; i<tp[7]; i++)
//		{
//			if (vals7[i]>lim/p[k])
//				continue;
//			tmp=vals7[i]*p[k];
//			if (tmp<lim)
//				vals8[tp[8]++]=tmp;
//		}
//		for(i=0; i<tp[6]; i++)
//		{
//			if (vals6[i]>lim/p[k])
//				continue;
//			tmp=vals6[i]*p[k];
//			if (tmp<lim)
//				vals7[tp[7]++]=tmp;
//		}
//		for(i=0; i<tp[5]; i++)
//		{
//			if (vals5[i]>lim/p[k])
//				continue;
//			tmp=vals5[i]*p[k];
//			if (tmp<lim)
//				vals6[tp[6]++]=tmp;
//		}
//		for(i=0; i<tp[4]; i++)
//		{
//			if (vals4[i]>lim/p[k])
//				continue;
//			tmp=vals4[i]*p[k];
//			if (tmp<lim)
//				vals5[tp[5]++]=tmp;
//		}
//		for(i=0; i<tp[3]; i++)
//		{
//			if (vals3[i]>lim/p[k])
//				continue;
//			tmp=vals3[i]*p[k];
//			if (tmp<lim)
//				vals4[tp[4]++]=tmp;
//		}
//		for(i=0; i<tp[2]; i++)
//		{
//			if (vals2[i]>lim/p[k])
//				continue;
//			tmp=vals2[i]*p[k];
//			if (tmp<lim)
//				vals3[tp[3]++]=tmp;
//		}
//		for(i=0; i<tp[1]; i++)
//		{
//			if (vals1[i]>lim/p[k])
//				continue;
//			tmp=vals1[i]*p[k];
//			if (tmp<lim)
//				vals2[tp[2]++]=tmp;
//		}
//		vals1[tp[1]++]=p[k];
//	}
//	r=calc(a+b);
//	l=calc(a);
//	printf("%u", b+l-r);
//}