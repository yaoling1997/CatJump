#include<cstdio>
#include<cstdlib>
#include<algorithm>
#include<cstring>
#include<vector>
#include<cmath>
#define ll long long
#define ld long double
#define pb push_back
#define mp make_pair
using namespace std;
const int maxn= 5e5;
char s[maxn];
int n,m,coins,hpPotion;
int main()
{
#ifndef ONLINE_JUDGE	
	freopen("map1_5.txt","r",stdin);
	freopen("mapCheck.out","w",stdout);
#endif
	scanf("%d%d\n",&n,&m);
	for (int i=1;i<=n;i++){
		gets(s+1);
		int len= strlen(s+1);
		if (len!=m){
			printf("wrong map!");
			return 0;
		}
		for (int j=1;j<=len;j++){
			if (s[j]=='o'||s[j]=='C')
				coins++;
			if (s[j]=='a'||s[j]=='A')
				hpPotion++;
		}
	}
	printf("coins:%d\n",coins);
	printf("hpPotion:%d\n",hpPotion);
	return 0;
}
