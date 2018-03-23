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
const int maxn= 1000;
char s[maxn][maxn];
int n,m;
int  coins,hpPotion,wand,jumpBoots,speedBoots;
int wrongMap;
bool isBlock(char c){
	return
		c=='A'||
		c=='B'||
		c=='C'||
		c=='D'||
		c=='E'||
		c=='G'||
		c=='I'||
		c=='K'||
		c=='L'||
		c=='R'||
		c=='T'||
		c=='U'||
		c=='W'||
		c=='Y';
}
int main()
{
#ifndef ONLINE_JUDGE	
	freopen("map1_5.txt","r",stdin);
	freopen("mapCheck.out","w",stdout);
#endif
	scanf("%d%d\n",&n,&m);
	for (int i=1;i<=n;i++){
		gets(s[i]+1);
		int len= strlen(s[i]+1);
		if (len!=m){
			wrongMap=1;
			break;
		}
		for (int j=1;j<=len;j++){
			if (s[i][j]=='o'||s[i][j]=='C')
				coins++;
			if (s[i][j]=='a'||s[i][j]=='A')
				hpPotion++;
			if (s[i][j]=='W')
				wand++;
			if (s[i][j]=='J')
				jumpBoots++;
			if (s[i][j]=='S')
				speedBoots++;
			if (isBlock(s[i][j])){
				if (i>2){
					if (!isBlock(s[i-1][j])&&
							(isBlock(s[i-2][j])||
							 (isBlock(s[i-2][j-1])&&!isBlock(s[i-1][j-1]))||
							 (isBlock(s[i-2][j+1])&&!isBlock(s[i-1][j+1]))
							 )
							){
						printf("%d %d\n",i, j);
						wrongMap=1;
					}
				}
				if (isBlock(s[i-1][j-1])&&
						!isBlock(s[i-1][j])&&
						isBlock(s[i-1][j+1])){
					printf("%d %d\n",i, j);
					wrongMap=1;						
				}
			}
		}
	}
	if (wrongMap){
		printf("Wrong map!");
		return 0;
	}
	printf("coins:%d\n",coins);
	printf("hpPotion:%d\n",hpPotion);
	printf("wand:%d\n",wand);
	printf("jumpBoots:%d\n",jumpBoots);
	printf("speedBoots:%d\n",speedBoots);
	return 0;
}
