module stopwatch(
input	wire	clk,
input	wire	resetn,
input	wire 	[1:0]	set,
output	wire 	[23:0]	timer
);

reg [31:0]	clk_cnt;
reg [7:0]	msec_10;
reg [7:0]	sec;
reg [7:0]	min;
wire [1:0]	flag;

assign  flag = set;
assign	timer = {min, sec, msec_10};
always @(posedge clk or negedge resetn) begin
	if(!resetn) begin
		clk_cnt <= 32'd0;
		msec_10 <= 8'd0;
		sec <= 8'd0;
		min <= 8'd0;
	end
	else begin
		if(flag == 2'd0) begin
			clk_cnt <= clk_cnt;
			msec_10 <= msec_10;
			sec <= sec;
			min <= min;
		end
		else if(flag == 2'd1) begin
			if(clk_cnt == (32'd250000-1)) begin
				clk_cnt <= 32'd0;
				msec_10 <= msec_10 + 8'd1;
				if(msec_10 == 8'd100) begin
					msec_10 <= 8'd0;
					sec <= sec + 8'd1;
					if(sec == 8'd60) begin
						sec <= 8'd0;
						min <= min + 1;
						if(min == 8'd60) begin
							min <= 8'd0;
						end
						else begin
							min <= min;
						end
					end
					else begin
						sec <=sec;
					end
				end
				else begin
					msec_10 <= msec_10;
				end
			end
			else begin
				clk_cnt <= clk_cnt + 32'd1;
			end
		end
		else if(flag == 2'd2) begin
			clk_cnt <= 32'd0;
			msec_10 <= 8'd0;
			sec <= 8'd0;
			min <= 8'd0;
		end
		else begin
			clk_cnt <= clk_cnt;
			msec_10 <= msec_10;
			sec <= sec;
			min <= min;
		end
	end
end
endmodule