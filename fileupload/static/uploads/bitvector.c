#include<stdio.h>

#include<stdlib.h>

#define WORDSIZE 32
#define WORD_S 5
#define MASK 0x1f


int initbv(int **bv, int val){
	*bv =(int *)calloc(val/WORDSIZE +1,sizeof(int));
	return *bv!=NULL;
}

int main(int argc, char const *argv[])
{
	int *bv;
	initbv(&bv,100);
	return 0;
}