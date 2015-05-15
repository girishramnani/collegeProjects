#include<stdio.h>
/* gauss elimination method */
double display(double coef[3][4]){
	for(int i=0;i<3;i++){
		for(int j=0;j<4;j++){
			printf("%f ",coef[i][j] );
		}
		printf("\n");
	}
	printf("\n");
}
double solve(double coef[3][4]){
	display(coef);
	double t =coef[1][0]/coef[0][0];
	double t2= coef[2][0]/coef[0][0];
	for(int i=0;i<4;i++){
		coef[1][i]= coef[1][i]-(t*coef[0][i]);
		coef[2][i]= coef[2][i]-(t2*coef[0][i]);
		display(coef);
	}
	t = coef[2][1]/coef[1][1];
	for(int i=0;i<4;i++){
		coef[2][i]-=(t*coef[1][i]);
		
		display(coef);
	}
	for(int j=2;j>=1;j--){
	for(int i=0;i<4;i++){
		coef[j][i]/=coef[2][j];
	}
	for(int i=0;i<4;i++){
		coef[1][i]= coef[1][i]-(coef[2][i]);
		display(coef);
	}	
	}
	
	
}


int main(int argc, char const *argv[])
{
	double coefficient[3][4]={{3,0.1,0.2,7.85},{0.1,7,0.3,-19.3},{0.3,0.2,10,71.4}};
	solve(coefficient);

	return 0;
}