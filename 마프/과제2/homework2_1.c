#include <reg51.h>

#define	FND	P1 //7-segment
#define SW	P2 //switch
sbit	set0=P0^7;
sbit	set1=P3^3;
sbit	set2=P3^4;

void Delay(unsigned int);
void main(void){
	SW=0xFF;	//sw�� �Է����� ���
	
	set0=1;		//DISP0�� ����ϱ� ���� ���Ǽ���
	set1=0;
	set2=0;
	
	while(1){
		unsigned char count; 	//count ����
		unsigned char sum=0; 	//1�� ���� ���� �����ϴ� ����
		unsigned char data1=0; 	//sw�� ���� �����ϴ� ����
		
		//look up table ��� (0~9�� FND�� ǥ���ϱ� ���� ��)
		unsigned char table[]={0xC0,0xF9,0xA4,0xB0,0x99,0x92,0x82,0xD8,0x80};
		
		data1=SW;
		for(count=0;count<8;count++){
			if(data1&0x01) //data�� 0�� ��Ʈ�� ���� Ȯ���ؼ� 1�̸� sum ����
				sum++;
			data1=data1>>1; //data�� ���� shift right
		}
		FND = table[sum]; //FND�� 1�� ������ ���
		Delay(1);
	}
}		
	
void Delay(unsigned int itime)
{
	unsigned int i,j;
	for(i=0;i<itime;i++)
		for(j=0;j<1275;j++);
}
		