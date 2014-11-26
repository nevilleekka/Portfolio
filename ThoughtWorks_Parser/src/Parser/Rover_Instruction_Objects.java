
/*
 * Rover_Instruction_Objects
 * * Coded By: Neville Ekka
 * 
 * Description:  Class containing schematic for Instruction Object that is generated for each rover. 
 * 
 */

package Parser;

import java.util.Vector;

public final class Rover_Instruction_Objects {
	
	public int[] grid_size = new int[2];
	public int[] rover_deployed_position = new int[2];
	public String[] rover_deployed_direction = new String[1];
	public Vector<String> Navigate_Instructions = new Vector<String>();
	
	// Flag for checking if each container is filled with valid data
	public boolean Acquired_grid_size=false;
	public boolean Acquired_rover_deployed_position_and_direction=false;
	public boolean Acquired_navigate_instructions=false;
	
	public Rover_Instruction_Objects(){
	 // assignment of MIN_VALUE is done in assumption that grid size values are never lowest integer value. 
	grid_size[0]=Integer.MIN_VALUE;
	grid_size[1]=Integer.MIN_VALUE;
	rover_deployed_position[0]=Integer.MIN_VALUE;
	rover_deployed_position[1]=Integer.MIN_VALUE;
	rover_deployed_direction[0]=null;
	}

}



