module pushbutton(
input	wire 	[2:0]	PB,
output	wire	[2:0]	PBAXI,
output	wire	INTR
);

assign PBAXI[0] = ~PB[0];
assign PBAXI[1] = ~PB[1];
assign PBAXI[2] = ~PB[2];
assign INTR = ~(PB[0] & PB[1] & PB[2]);

endmodule


		


	

		
	  