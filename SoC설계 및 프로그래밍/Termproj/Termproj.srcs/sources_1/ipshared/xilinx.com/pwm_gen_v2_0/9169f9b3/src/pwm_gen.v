`timescale 1ns / 1ns

module pwm_gen(
input   wire    clk,
input   wire    resetn,
input   wire    [31:0]  duty,
input   wire    [31:0]  cycle,
output  wire    pwm_out
    );
reg [31:0] pwm_cnt;
reg pwm;

assign pwm_out = pwm;
always @(posedge clk or negedge resetn) begin
    if(!resetn)
        pwm_cnt <= 32'd0;
    else begin
        if(pwm_cnt == (cycle -1))
            pwm_cnt <= 32'd0;
        else
            pwm_cnt <= pwm_cnt + 32'd1;
    end
end

always @(posedge clk or negedge resetn) begin
    if(!resetn)
        pwm <= 1'd1;
    else begin
        if(pwm_cnt == 32'd0)
            pwm <= 1'd1;
        else if(pwm_cnt == duty)
            pwm <= 1'd0;
        else
            pwm <= pwm;
    end
end

endmodule
