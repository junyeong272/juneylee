//Copyright 1986-2014 Xilinx, Inc. All Rights Reserved.
//--------------------------------------------------------------------------------
//Tool Version: Vivado v.2014.4_63036 (win64) Build 1071353 Tue Nov 18 18:29:27 MST 2014
//Date        : Thu Jun 20 18:21:48 2019
//Host        : PC-12 running 64-bit Service Pack 1  (build 7601)
//Command     : generate_target system_wrapper.bd
//Design      : system_wrapper
//Purpose     : IP block netlist
//--------------------------------------------------------------------------------
`timescale 1 ps / 1 ps

module system_wrapper
   (FIXED_IO_mio,
    FIXED_IO_ps_clk,
    FIXED_IO_ps_porb,
    FIXED_IO_ps_srstb,
    PB,
    clk,
    lcd_data,
    lcd_en,
    lcd_rs,
    lcd_rw,
    pwm_out,
    resetn,
    seg_data,
    seg_en);
  inout [53:0]FIXED_IO_mio;
  inout FIXED_IO_ps_clk;
  inout FIXED_IO_ps_porb;
  inout FIXED_IO_ps_srstb;
  input [2:0]PB;
  input clk;
  output [7:0]lcd_data;
  output lcd_en;
  output lcd_rs;
  output lcd_rw;
  output pwm_out;
  input resetn;
  output [7:0]seg_data;
  output [7:0]seg_en;

  wire [53:0]FIXED_IO_mio;
  wire FIXED_IO_ps_clk;
  wire FIXED_IO_ps_porb;
  wire FIXED_IO_ps_srstb;
  wire [2:0]PB;
  wire clk;
  wire [7:0]lcd_data;
  wire lcd_en;
  wire lcd_rs;
  wire lcd_rw;
  wire pwm_out;
  wire resetn;
  wire [7:0]seg_data;
  wire [7:0]seg_en;

system system_i
       (.FIXED_IO_mio(FIXED_IO_mio),
        .FIXED_IO_ps_clk(FIXED_IO_ps_clk),
        .FIXED_IO_ps_porb(FIXED_IO_ps_porb),
        .FIXED_IO_ps_srstb(FIXED_IO_ps_srstb),
        .PB(PB),
        .clk(clk),
        .lcd_data(lcd_data),
        .lcd_en(lcd_en),
        .lcd_rs(lcd_rs),
        .lcd_rw(lcd_rw),
        .pwm_out(pwm_out),
        .resetn(resetn),
        .seg_data(seg_data),
        .seg_en(seg_en));
endmodule
