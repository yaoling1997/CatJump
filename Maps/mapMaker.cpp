#include<cstdio>
#include<cstdlib>
#include<algorithm>
#include<cstring>
#include<vector>
#include<sys/timeb.h>
using namespace std;
const int maxn= 2e5;
int n,m,i,j,x,y,z;
int random(int o){
	return rand()%o;
}
int main()
{
	freopen("map.txt","w",stdout);
	struct timeb t;ftime(&t);
	srand(t.time*1000+t.millitm);
	n= 20;
	m= 60;
	printf("%d\n%d\n",n,m);
	for (int i=1;i<=n;i++){
		for (int j=1;j<=m;j++)
			if (i==1||i==n||j==1||j==m)
				printf("B");
			else printf(" ");
		printf("\n");
	}
	return 0;
}
