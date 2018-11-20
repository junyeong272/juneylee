TEMP	EQU		R0			;1�� ������ �����ϴ� ����
COUNT	EQU		R1			;�ݺ�Ƚ���� �����ϴ� ����
FND		EQU		P1			;FND(7-SEGMENT)
SW		EQU		P2			;����ġ
		
		ORG		0000H
		MOV		A,#0FFH
		MOV 	SW,A		;SW(P2)�� ��� �Է���Ʈ�� ���
		MOV		DPTR,#200H
		
MAIN:	MOV		TEMP,#0		;TEMP �ʱ�ȭ
		MOV		COUNT,#8	;�ݺ�Ƚ�� �ʱ�ȭ
		CLR		C
		SETB	P0.7		;DISP0�� ����ϱ� ���� ���Ǽ���
		CLR		P3.4
		CLR		P3.3
		MOV		A,SW		;���� SW���� A�� ����

LOOP1:	RRC		A
		JNC		LOOP2		;C�� 0�̸� LOOP2�� ����
		INC		TEMP		;C�� 1�̸� TEMP 1����
			
LOOP2:	DJNZ	COUNT,LOOP1
		MOV		A,TEMP		;8�� �ݺ� �� TEMP����(1�� ����) A�� ����
		MOVC	A,@A+DPTR	;1�� ������ FND�� ǥ���ϱ� ���� ���� ��ȯ��Ű�� �۾�(look up table �̿�)
		MOV		FND,A		;FND ON
		SJMP	MAIN

		ORG		200H
TABLE:	DB		11000000B	;0
		DB		11111001B	;1
		DB		10100100B	;2
		DB		10110000B	;3
		DB		10011001B	;4
		DB		10010010B	;5
		DB		10000010B	;6
		DB		11011000B	;7
		DB		10000000B	;8
		
		END