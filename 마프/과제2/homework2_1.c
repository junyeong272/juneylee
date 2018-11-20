#include <reg51.h>

#define	FND	P1 //7-segment
#define SW	P2 //switch
sbit	set0=P0^7;
sbit	set1=P3^3;
sbit	set2=P3^4;

void Delay(unsigned int);
void main(void){
	SW=0xFF;	//sw를 입력으로 사용
	
	set0=1;		//DISP0을 사용하기 위한 조건설정
	set1=0;
	set2=0;
	
	while(1){
		unsigned char count; 	//count 변수
		unsigned char sum=0; 	//1의 개수 합을 저장하는 변수
		unsigned char data1=0; 	//sw의 값을 저장하는 변수
		
		//look up table 기능 (0~9를 FND에 표시하기 위한 값)
		unsigned char table[]={0xC0,0xF9,0xA4,0xB0,0x99,0x92,0x82,0xD8,0x80};
		
		data1=SW;
		for(count=0;count<8;count++){
			if(data1&0x01) //data의 0번 비트의 값을 확인해서 1이면 sum 증가
				sum++;
			data1=data1>>1; //data의 값을 shift right
		}
		FND = table[sum]; //FND에 1의 개수를 출력
		Delay(1);
	}
}		
	
void Delay(unsigned int itime)
{
	unsigned int i,j;
	for(i=0;i<itime;i++)
		for(j=0;j<1275;j++);
}
		