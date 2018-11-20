TEMP	EQU		R0			;1의 개수를 저장하는 변수
COUNT	EQU		R1			;반복횟수를 저장하는 변수
FND		EQU		P1			;FND(7-SEGMENT)
SW		EQU		P2			;스위치
		
		ORG		0000H
		MOV		A,#0FFH
		MOV 	SW,A		;SW(P2)를 모두 입력포트로 사용
		MOV		DPTR,#200H
		
MAIN:	MOV		TEMP,#0		;TEMP 초기화
		MOV		COUNT,#8	;반복횟수 초기화
		CLR		C
		SETB	P0.7		;DISP0을 사용하기 위한 조건설정
		CLR		P3.4
		CLR		P3.3
		MOV		A,SW		;현재 SW값을 A에 저장

LOOP1:	RRC		A
		JNC		LOOP2		;C가 0이면 LOOP2로 점프
		INC		TEMP		;C가 1이면 TEMP 1증가
			
LOOP2:	DJNZ	COUNT,LOOP1
		MOV		A,TEMP		;8번 반복 후 TEMP값을(1의 개수) A에 저장
		MOVC	A,@A+DPTR	;1의 개수를 FND에 표현하기 위해 값을 변환시키는 작업(look up table 이용)
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