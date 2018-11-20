#include <reg51.h>

void Delay(unsigned int);
void main(void)
{
	unsigned char LED = 0xFE; //LED에 출력할 값을 저장하는 변수
	unsigned char SW;	//스위치의 값을 확인하는 변수
	unsigned char i,j;
	
	//STACK_LEFT에서 LED가 왼쪽에 차곡차곡 쌓이도록 도와주는 역할
	unsigned char stack_left[]={0xFF,0x7F,0x3F,0x1F,0x0F,0x07,0x03,0x01};
	
	//STACK_RIGHT에서 LED가 오른쪽에 차곡차곡 쌓이도록 도와주는 역할
	unsigned char stack_right[]={0xFF,0xFE,0xFC,0xF8,0xF0,0xE0,0xC0,0x80};
	
	//STACK에서 LED가 이동할 수 있는 개수에 맞게 masking하여 carry값을 얻는 역할 (??)
	unsigned char carry[]={0x80,0x40,0x20,0x10,0x08,0x04,0x02,0x01};
	
	P2=0x03; //스위치0,1번을 입력으로 사용
	while(1) //무한 반복
	{
		SW=P2;		//현재 스위치 값을 SW에 저장
		SW=SW&0x03; //masking
		switch(SW)
		{
			case(0): //STACK_RIGHT, 00(on,on)
			{
				for(i=0;i<8;i++){				//총 8번 반복
					LED = 0x7F;					//기본 B'01111111'
					for(j=i;j<8;j++){			//8-i번 반복
						P1 = LED&stack_right[i];//이동하는 LED+쌓인 LED를 합쳐서 LED ON
						Delay(1);				
						if(LED&carry[8-i]){		//carry[]에 해당하는 비트가 1이면 (0번 비트부터~)
							LED = LED>>1;		//LED를 오른쪽 쉬프트하고 
							LED = LED|0x80;		//7번 비트를 1로 만들어줌
						}else					//carry[]에 해당하는 비트가 0이면 
							LED = LED>>1;		//LED를 오른쪽 쉬프트만 함
					}
					if(P2 != SW){				//스택 도중에 다른 스위치를 누르면 빠져나감
							LED = 0xFE;
							break;
					}
				}
				break;
			}
			case(1): //STACK_LEFT, 01(on,off)
			{
				for(i=0;i<8;i++){				//총 8번 반복
					LED = 0xFE;					//기본 B'11111110'
					for(j=i;j<8;j++){			//8-i번 반복
						P1 = LED&stack_left[i];	//이동하는 LED+쌓인 LED를 합쳐서 LED ON
						Delay(1);
						if(LED&carry[i]){		//carry[]에 해당하는 비트가 1이면 (7번 비트부터~)
							LED = LED<<1;		//LED를 왼쪽 쉬프트하고 
							LED = LED|0x01;		//0번 비트를 1로 만들어줌
						}else					//carry[]에 해당하는 비트가 0이면 
							LED = LED<<1;		//LED를 왼쪽 쉬프트만 함
					}
					if(P2 != SW){				//스택 도중에 다른 스위치를 누르면 빠져나감
							LED = 0xFE;
							break;
					}
				}
				break;
			}
			case(2): //ROTATE_RIGHT, 10(off,on)
			{
				P1 = LED;				//LED ON
				Delay(1);
				if(LED&0x01){			//LED 0번 비트가 1이면
					LED = LED>>1;		//LED를 오른쪽 쉬프트하고
					LED = LED|0x80;		//7번 비트를 1로 만들어줌
				}else					//LED 0번 비트가 0이면
					LED = LED>>1;		//LED를 오른쪽 쉬프트만 함
				break;
			}
			case(3): //ROTATE_LEFT, 11(off,off)
			{
				P1 = LED;				//LED ON
				Delay(1);
				if(LED&0x80){			//LED 7번 비트가 1이면
					LED = LED<<1;		//LED를 왼쪽 쉬프트하고
					LED = LED|0x01;		//0번 비트를 1로 만들어줌
				}else					//LED 7번 비트가 0이면
					LED = LED<<1;		//LED를 왼쪽 쉬프트만 함
				break;
			}
		}
	}
}

void Delay(unsigned int itime)
{
	unsigned int i,j;
	for(i=0;i<itime;i++)
		for(j=0;j<1275;j++);
}
		