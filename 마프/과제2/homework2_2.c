#include <reg51.h>

void Delay(unsigned int);
void main(void)
{
	unsigned char LED = 0xFE; //LED�� ����� ���� �����ϴ� ����
	unsigned char SW;	//����ġ�� ���� Ȯ���ϴ� ����
	unsigned char i,j;
	
	//STACK_LEFT���� LED�� ���ʿ� �������� ���̵��� �����ִ� ����
	unsigned char stack_left[]={0xFF,0x7F,0x3F,0x1F,0x0F,0x07,0x03,0x01};
	
	//STACK_RIGHT���� LED�� �����ʿ� �������� ���̵��� �����ִ� ����
	unsigned char stack_right[]={0xFF,0xFE,0xFC,0xF8,0xF0,0xE0,0xC0,0x80};
	
	//STACK���� LED�� �̵��� �� �ִ� ������ �°� masking�Ͽ� carry���� ��� ���� (??)
	unsigned char carry[]={0x80,0x40,0x20,0x10,0x08,0x04,0x02,0x01};
	
	P2=0x03; //����ġ0,1���� �Է����� ���
	while(1) //���� �ݺ�
	{
		SW=P2;		//���� ����ġ ���� SW�� ����
		SW=SW&0x03; //masking
		switch(SW)
		{
			case(0): //STACK_RIGHT, 00(on,on)
			{
				for(i=0;i<8;i++){				//�� 8�� �ݺ�
					LED = 0x7F;					//�⺻ B'01111111'
					for(j=i;j<8;j++){			//8-i�� �ݺ�
						P1 = LED&stack_right[i];//�̵��ϴ� LED+���� LED�� ���ļ� LED ON
						Delay(1);				
						if(LED&carry[8-i]){		//carry[]�� �ش��ϴ� ��Ʈ�� 1�̸� (0�� ��Ʈ����~)
							LED = LED>>1;		//LED�� ������ ����Ʈ�ϰ� 
							LED = LED|0x80;		//7�� ��Ʈ�� 1�� �������
						}else					//carry[]�� �ش��ϴ� ��Ʈ�� 0�̸� 
							LED = LED>>1;		//LED�� ������ ����Ʈ�� ��
					}
					if(P2 != SW){				//���� ���߿� �ٸ� ����ġ�� ������ ��������
							LED = 0xFE;
							break;
					}
				}
				break;
			}
			case(1): //STACK_LEFT, 01(on,off)
			{
				for(i=0;i<8;i++){				//�� 8�� �ݺ�
					LED = 0xFE;					//�⺻ B'11111110'
					for(j=i;j<8;j++){			//8-i�� �ݺ�
						P1 = LED&stack_left[i];	//�̵��ϴ� LED+���� LED�� ���ļ� LED ON
						Delay(1);
						if(LED&carry[i]){		//carry[]�� �ش��ϴ� ��Ʈ�� 1�̸� (7�� ��Ʈ����~)
							LED = LED<<1;		//LED�� ���� ����Ʈ�ϰ� 
							LED = LED|0x01;		//0�� ��Ʈ�� 1�� �������
						}else					//carry[]�� �ش��ϴ� ��Ʈ�� 0�̸� 
							LED = LED<<1;		//LED�� ���� ����Ʈ�� ��
					}
					if(P2 != SW){				//���� ���߿� �ٸ� ����ġ�� ������ ��������
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
				if(LED&0x01){			//LED 0�� ��Ʈ�� 1�̸�
					LED = LED>>1;		//LED�� ������ ����Ʈ�ϰ�
					LED = LED|0x80;		//7�� ��Ʈ�� 1�� �������
				}else					//LED 0�� ��Ʈ�� 0�̸�
					LED = LED>>1;		//LED�� ������ ����Ʈ�� ��
				break;
			}
			case(3): //ROTATE_LEFT, 11(off,off)
			{
				P1 = LED;				//LED ON
				Delay(1);
				if(LED&0x80){			//LED 7�� ��Ʈ�� 1�̸�
					LED = LED<<1;		//LED�� ���� ����Ʈ�ϰ�
					LED = LED|0x01;		//0�� ��Ʈ�� 1�� �������
				}else					//LED 7�� ��Ʈ�� 0�̸�
					LED = LED<<1;		//LED�� ���� ����Ʈ�� ��
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
		