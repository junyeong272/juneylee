
################################################################
# This is a generated script based on design: system
#
# Though there are limitations about the generated script,
# the main purpose of this utility is to make learning
# IP Integrator Tcl commands easier.
################################################################

################################################################
# Check if script is running in correct Vivado version.
################################################################
set scripts_vivado_version 2014.4
set current_vivado_version [version -short]

if { [string first $scripts_vivado_version $current_vivado_version] == -1 } {
   puts ""
   puts "ERROR: This script was generated using Vivado <$scripts_vivado_version> and is being run in <$current_vivado_version> of Vivado. Please run the script in Vivado <$scripts_vivado_version> then open the design in Vivado <$current_vivado_version>. Upgrade the design by running \"Tools => Report => Report IP Status...\", then run write_bd_tcl to create an updated script."

   return 1
}

################################################################
# START
################################################################

# To test this script, run the following commands from Vivado Tcl console:
# source system_script.tcl

# If you do not already have a project created,
# you can create a project using the following command:
#    create_project project_1 myproj -part xc7z020clg484-1


# CHANGE DESIGN NAME HERE
set design_name system

# If you do not already have an existing IP Integrator design open,
# you can create a design using the following command:
#    create_bd_design $design_name

# CHECKING IF PROJECT EXISTS
if { [get_projects -quiet] eq "" } {
   puts "ERROR: Please open or create a project!"
   return 1
}


# Creating design if needed
set errMsg ""
set nRet 0

set cur_design [current_bd_design -quiet]
set list_cells [get_bd_cells -quiet]

if { ${design_name} eq "" } {
   # USE CASES:
   #    1) Design_name not set

   set errMsg "ERROR: Please set the variable <design_name> to a non-empty value."
   set nRet 1

} elseif { ${cur_design} ne "" && ${list_cells} eq "" } {
   # USE CASES:
   #    2): Current design opened AND is empty AND names same.
   #    3): Current design opened AND is empty AND names diff; design_name NOT in project.
   #    4): Current design opened AND is empty AND names diff; design_name exists in project.

   if { $cur_design ne $design_name } {
      puts "INFO: Changing value of <design_name> from <$design_name> to <$cur_design> since current design is empty."
      set design_name [get_property NAME $cur_design]
   }
   puts "INFO: Constructing design in IPI design <$cur_design>..."

} elseif { ${cur_design} ne "" && $list_cells ne "" && $cur_design eq $design_name } {
   # USE CASES:
   #    5) Current design opened AND has components AND same names.

   set errMsg "ERROR: Design <$design_name> already exists in your project, please set the variable <design_name> to another value."
   set nRet 1
} elseif { [get_files -quiet ${design_name}.bd] ne "" } {
   # USE CASES: 
   #    6) Current opened design, has components, but diff names, design_name exists in project.
   #    7) No opened design, design_name exists in project.

   set errMsg "ERROR: Design <$design_name> already exists in your project, please set the variable <design_name> to another value."
   set nRet 2

} else {
   # USE CASES:
   #    8) No opened design, design_name not in project.
   #    9) Current opened design, has components, but diff names, design_name not in project.

   puts "INFO: Currently there is no design <$design_name> in project, so creating one..."

   create_bd_design $design_name

   puts "INFO: Making design <$design_name> as current_bd_design."
   current_bd_design $design_name

}

puts "INFO: Currently the variable <design_name> is equal to \"$design_name\"."

if { $nRet != 0 } {
   puts $errMsg
   return $nRet
}

##################################################################
# DESIGN PROCs
##################################################################



# Procedure to create entire design; Provide argument to make
# procedure reusable. If parentCell is "", will use root.
proc create_root_design { parentCell } {

  if { $parentCell eq "" } {
     set parentCell [get_bd_cells /]
  }

  # Get object for parentCell
  set parentObj [get_bd_cells $parentCell]
  if { $parentObj == "" } {
     puts "ERROR: Unable to find parent cell <$parentCell>!"
     return
  }

  # Make sure parentObj is hier blk
  set parentType [get_property TYPE $parentObj]
  if { $parentType ne "hier" } {
     puts "ERROR: Parent <$parentObj> has TYPE = <$parentType>. Expected to be <hier>."
     return
  }

  # Save current instance; Restore later
  set oldCurInst [current_bd_instance .]

  # Set parent object as current
  current_bd_instance $parentObj


  # Create interface ports
  set FIXED_IO [ create_bd_intf_port -mode Master -vlnv xilinx.com:display_processing_system7:fixedio_rtl:1.0 FIXED_IO ]

  # Create ports
  set PB [ create_bd_port -dir I -from 2 -to 0 PB ]
  set clk [ create_bd_port -dir I clk ]
  set lcd_data [ create_bd_port -dir O -from 7 -to 0 lcd_data ]
  set lcd_en [ create_bd_port -dir O lcd_en ]
  set lcd_rs [ create_bd_port -dir O lcd_rs ]
  set lcd_rw [ create_bd_port -dir O lcd_rw ]
  set pwm_out [ create_bd_port -dir O pwm_out ]
  set resetn [ create_bd_port -dir I resetn ]
  set seg_data [ create_bd_port -dir O -from 7 -to 0 seg_data ]
  set seg_en [ create_bd_port -dir O -from 7 -to 0 seg_en ]

  # Create instance: processing_system7_0, and set properties
  set processing_system7_0 [ create_bd_cell -type ip -vlnv xilinx.com:ip:processing_system7:5.5 processing_system7_0 ]
  set_property -dict [ list CONFIG.PCW_I2C0_I2C0_IO {MIO 50 .. 51} CONFIG.PCW_I2C0_PERIPHERAL_ENABLE {1} CONFIG.PCW_IRQ_F2P_INTR {1} CONFIG.PCW_PRESET_BANK1_VOLTAGE {LVCMOS 1.8V} CONFIG.PCW_UART1_PERIPHERAL_ENABLE {1} CONFIG.PCW_UIPARAM_DDR_ENABLE {0} CONFIG.PCW_USE_FABRIC_INTERRUPT {1}  ] $processing_system7_0

  # Create instance: processing_system7_0_axi_periph, and set properties
  set processing_system7_0_axi_periph [ create_bd_cell -type ip -vlnv xilinx.com:ip:axi_interconnect:2.1 processing_system7_0_axi_periph ]
  set_property -dict [ list CONFIG.NUM_MI {4}  ] $processing_system7_0_axi_periph

  # Create instance: pushbutton_0, and set properties
  set pushbutton_0 [ create_bd_cell -type ip -vlnv xilinx.com:user:pushbutton:1.0 pushbutton_0 ]

  # Create instance: pwm_gen_0, and set properties
  set pwm_gen_0 [ create_bd_cell -type ip -vlnv xilinx.com:user:pwm_gen:2.0 pwm_gen_0 ]

  # Create instance: rst_processing_system7_0_50M, and set properties
  set rst_processing_system7_0_50M [ create_bd_cell -type ip -vlnv xilinx.com:ip:proc_sys_reset:5.0 rst_processing_system7_0_50M ]

  # Create instance: seven_seg_0, and set properties
  set seven_seg_0 [ create_bd_cell -type ip -vlnv xilinx.com:user:seven_seg:1.0 seven_seg_0 ]

  # Create instance: textlcd_0, and set properties
  set textlcd_0 [ create_bd_cell -type ip -vlnv xilinx.com:user:textlcd:1.0 textlcd_0 ]

  # Create instance: xlconcat_0, and set properties
  set xlconcat_0 [ create_bd_cell -type ip -vlnv xilinx.com:ip:xlconcat:2.1 xlconcat_0 ]
  set_property -dict [ list CONFIG.NUM_PORTS {1}  ] $xlconcat_0

  # Create interface connections
  connect_bd_intf_net -intf_net processing_system7_0_FIXED_IO [get_bd_intf_ports FIXED_IO] [get_bd_intf_pins processing_system7_0/FIXED_IO]
  connect_bd_intf_net -intf_net processing_system7_0_M_AXI_GP0 [get_bd_intf_pins processing_system7_0/M_AXI_GP0] [get_bd_intf_pins processing_system7_0_axi_periph/S00_AXI]
  connect_bd_intf_net -intf_net processing_system7_0_axi_periph_M00_AXI [get_bd_intf_pins processing_system7_0_axi_periph/M00_AXI] [get_bd_intf_pins seven_seg_0/S00_AXI]
  connect_bd_intf_net -intf_net processing_system7_0_axi_periph_M01_AXI [get_bd_intf_pins processing_system7_0_axi_periph/M01_AXI] [get_bd_intf_pins textlcd_0/S00_AXI]
  connect_bd_intf_net -intf_net processing_system7_0_axi_periph_M02_AXI [get_bd_intf_pins processing_system7_0_axi_periph/M02_AXI] [get_bd_intf_pins pushbutton_0/S00_AXI]
  connect_bd_intf_net -intf_net processing_system7_0_axi_periph_M03_AXI [get_bd_intf_pins processing_system7_0_axi_periph/M03_AXI] [get_bd_intf_pins pwm_gen_0/S00_AXI]

  # Create port connections
  connect_bd_net -net PB_1 [get_bd_ports PB] [get_bd_pins pushbutton_0/PB]
  connect_bd_net -net clk_1 [get_bd_ports clk] [get_bd_pins pwm_gen_0/clk] [get_bd_pins seven_seg_0/clk] [get_bd_pins textlcd_0/lcdclk]
  connect_bd_net -net processing_system7_0_FCLK_CLK0 [get_bd_pins processing_system7_0/FCLK_CLK0] [get_bd_pins processing_system7_0/M_AXI_GP0_ACLK] [get_bd_pins processing_system7_0_axi_periph/ACLK] [get_bd_pins processing_system7_0_axi_periph/M00_ACLK] [get_bd_pins processing_system7_0_axi_periph/M01_ACLK] [get_bd_pins processing_system7_0_axi_periph/M02_ACLK] [get_bd_pins processing_system7_0_axi_periph/M03_ACLK] [get_bd_pins processing_system7_0_axi_periph/S00_ACLK] [get_bd_pins pushbutton_0/s00_axi_aclk] [get_bd_pins pwm_gen_0/s00_axi_aclk] [get_bd_pins rst_processing_system7_0_50M/slowest_sync_clk] [get_bd_pins seven_seg_0/s00_axi_aclk] [get_bd_pins textlcd_0/s00_axi_aclk]
  connect_bd_net -net processing_system7_0_FCLK_RESET0_N [get_bd_pins processing_system7_0/FCLK_RESET0_N] [get_bd_pins rst_processing_system7_0_50M/ext_reset_in]
  connect_bd_net -net pushbutton_0_INTR [get_bd_pins pushbutton_0/INTR] [get_bd_pins xlconcat_0/In0]
  connect_bd_net -net pwm_gen_0_pwm_out [get_bd_ports pwm_out] [get_bd_pins pwm_gen_0/pwm_out]
  connect_bd_net -net resetn_1 [get_bd_ports resetn] [get_bd_pins pwm_gen_0/resetn] [get_bd_pins seven_seg_0/resetn] [get_bd_pins textlcd_0/resetn]
  connect_bd_net -net rst_processing_system7_0_50M_interconnect_aresetn [get_bd_pins processing_system7_0_axi_periph/ARESETN] [get_bd_pins rst_processing_system7_0_50M/interconnect_aresetn]
  connect_bd_net -net rst_processing_system7_0_50M_peripheral_aresetn [get_bd_pins processing_system7_0_axi_periph/M00_ARESETN] [get_bd_pins processing_system7_0_axi_periph/M01_ARESETN] [get_bd_pins processing_system7_0_axi_periph/M02_ARESETN] [get_bd_pins processing_system7_0_axi_periph/M03_ARESETN] [get_bd_pins processing_system7_0_axi_periph/S00_ARESETN] [get_bd_pins pushbutton_0/s00_axi_aresetn] [get_bd_pins pwm_gen_0/s00_axi_aresetn] [get_bd_pins rst_processing_system7_0_50M/peripheral_aresetn] [get_bd_pins seven_seg_0/s00_axi_aresetn] [get_bd_pins textlcd_0/s00_axi_aresetn]
  connect_bd_net -net seven_seg_0_seg_data [get_bd_ports seg_data] [get_bd_pins seven_seg_0/seg_data]
  connect_bd_net -net seven_seg_0_seg_en [get_bd_ports seg_en] [get_bd_pins seven_seg_0/seg_en]
  connect_bd_net -net textlcd_0_lcd_data [get_bd_ports lcd_data] [get_bd_pins textlcd_0/lcd_data]
  connect_bd_net -net textlcd_0_lcd_en [get_bd_ports lcd_en] [get_bd_pins textlcd_0/lcd_en]
  connect_bd_net -net textlcd_0_lcd_rs [get_bd_ports lcd_rs] [get_bd_pins textlcd_0/lcd_rs]
  connect_bd_net -net textlcd_0_lcd_rw [get_bd_ports lcd_rw] [get_bd_pins textlcd_0/lcd_rw]
  connect_bd_net -net xlconcat_0_dout [get_bd_pins processing_system7_0/IRQ_F2P] [get_bd_pins xlconcat_0/dout]

  # Create address segments
  create_bd_addr_seg -range 0x10000 -offset 0x43C20000 [get_bd_addr_spaces processing_system7_0/Data] [get_bd_addr_segs pushbutton_0/S00_AXI/S00_AXI_reg] SEG_pushbutton_0_S00_AXI_reg
  create_bd_addr_seg -range 0x10000 -offset 0x43C30000 [get_bd_addr_spaces processing_system7_0/Data] [get_bd_addr_segs pwm_gen_0/S00_AXI/S00_AXI_reg] SEG_pwm_gen_0_S00_AXI_reg
  create_bd_addr_seg -range 0x10000 -offset 0x43C00000 [get_bd_addr_spaces processing_system7_0/Data] [get_bd_addr_segs seven_seg_0/S00_AXI/S00_AXI_reg] SEG_seven_seg_0_S00_AXI_reg
  create_bd_addr_seg -range 0x10000 -offset 0x43C10000 [get_bd_addr_spaces processing_system7_0/Data] [get_bd_addr_segs textlcd_0/S00_AXI/S00_AXI_reg] SEG_textlcd_0_S00_AXI_reg
  

  # Restore current instance
  current_bd_instance $oldCurInst

  save_bd_design
}
# End of create_root_design()


##################################################################
# MAIN FLOW
##################################################################

create_root_design ""


