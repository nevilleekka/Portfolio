/*
 * Rover_Navigator
 * * Coded By: Neville Ekka
 * 
 * Description:  Class for each rover signifying locomotion module installed in them. Always gets the first instruction object from Instruction Object List
 * then removes it to ensure serial locomotion for all rovers. Methodology for calculating direction and movement is done by mathematical expressions:
 * x= r * cos(angle)
 * y= r * sin(angle)
 * (r=1 in this case)
 * Each direction is converted to bounded angles of 0, 90,180 and 270. Upon performing above functions it outputs:
 * angle    X   Y
 *  0       1   0
 *  90		0	1
 *  180	   -1	0
 *  270	    0  -1
 *  
 *  Through this process navigation becomes simple.
 *  
 */

package Parser;

import java.util.Vector;

import org.jbox2d.common.MathUtils;;


public class Rover_Navigator {

	private int Location_X;
	private int Location_Y;
	private String Direction;
	private int Direction_Value;
	private int Grid_X;
	private int Grid_Y;
	public boolean display_all_messages=false;
	
	private String Name;
	
	/*
	 *public enum Compass
	 * 
	 *  Description: Defines angle values for compass directions. Getting values is also possible through this implementation
	 */
	public enum Compass { 
		North(90), South(270),East(0), West(180);

	    private final int _value;

	    Compass(int value) {
	        _value = value;
	    }

	    public int point() {
	        return _value;
	    }
	}
	
	public Rover_Navigator(String Name, boolean display_all_messages){
		this.Name=Name;
		this.display_all_messages=display_all_messages;
		
	}
	
	/*
	 * public void process_instruction(Vector<Rover_Instruction_Objects> instruction_object_List)
	 * 
	 * Description: Initializes by reading first Instruction Object from Rover Instruction Object list. Performs the position and rotational changes and deletes
	 * the first Instruction Object from Rover Instruction Object list.
	 * 
	 */
	
	public void process_instruction(Vector<Rover_Instruction_Objects> instruction_object_List){
		// Initializing....
		Rover_Instruction_Objects current_instruction = instruction_object_List.firstElement();
		Grid_X=current_instruction.grid_size[0];
		Grid_Y=current_instruction.grid_size[1];
		
		Location_X=(int) MathUtils.clamp(current_instruction.rover_deployed_position[0], 0, Grid_X);
		Location_Y=(int) MathUtils.clamp(current_instruction.rover_deployed_position[1], 0, Grid_Y);;
		Direction=current_instruction.rover_deployed_direction[0];
			
		switch(Direction){
		case "N":Direction_Value=Compass.North.point() ;  break;
		case "S":Direction_Value=Compass.South.point();  break;
		case "E":Direction_Value=Compass.East.point();  break;
		case "W":Direction_Value=Compass.West.point() ;  break;
		}
		if(display_all_messages){System.out.println("Rover: "+Name+" => "+"Navigate_Instructions SIZE = "+current_instruction.Navigate_Instructions.size());}
		
		// Performs all position and rotational changes for each instruction token existing in the list
		for(int instruction_index=0;instruction_index<=current_instruction.Navigate_Instructions.size()-1;instruction_index++){
			String instruction = current_instruction.Navigate_Instructions.get(instruction_index);
			if(display_all_messages){System.out.println("Rover: "+Name+" => "+"instruction"+"("+instruction_index+") = "+instruction);}
			switch(instruction){
					case "L":turn_left() ;  break;
					case "R":turn_right() ;  break;
					case "M":move() ;  break;
			}
  		 }
		instruction_object_List.remove(0); // Removes it from list for serial execution
	}
	
	/*
	 * public void process_instruction(Vector<Rover_Instruction_Objects> instruction_object_List)
	 * 
	 *Description: Adds the Direction_Value positive integer and assigns converted Direction after bounding angle so that angle doesn't
	 * reach positive and negative infinity
	 * 
	 */
	private void turn_left(){Direction_Value=Direction_Value+90; 
	if(Direction_Value==360){Direction_Value=0;} if(Direction_Value==-90){Direction_Value=270;}
	convert_direction(Direction_Value);
	//System.out.println("Rover: "+Name+" => "+"-> Turned Left angle = "+Direction_Value); 
	current_Point();
	}
	/*
	 * private void turn_right()
	 * 
	 * Description: Adds the Direction_Value negative integer and assigns converted Direction after bounding angle so that angle doesn't
	 *  reach positive and negative infinity
	 * 
	 */
	
	private void turn_right(){Direction_Value=Direction_Value-90;
	if(Direction_Value==360){Direction_Value=0;} if(Direction_Value==-90){Direction_Value=270;} 
	convert_direction(Direction_Value); 
	//System.out.println("Rover: "+Name+" => "+"-> Turned Right= "+Direction_Value);
	current_Point();
	}
	
	/*
	 * private void move()
	 * 
	 * Description: Performs math operation in accordance to :
	 *  angle   X   Y
	 *  0       1   0
	 *  90		0	1
	 *  180	   -1	0
	 *  270	    0  -1
	 * 
	 */
	private void move(){
	Location_X=Location_X+ (int)((Math.cos(Math.toRadians(Direction_Value))));
	Location_Y=Location_Y+(int)((Math.sin(Math.toRadians(Direction_Value))));
	current_Point();
	}
	
	
	/*
	 * private void convert_direction(int Direction_Value)
	 * 
	 * Description: Converts Direction_Value to  N E W S
	 */
	private void convert_direction(int Direction_Value){
		//North(90), South(270),East(0), West(180);
		switch(Direction_Value){
		case 0: Direction="E";  break;
		case 90:Direction="N" ;  break;
		case 180:Direction="W" ;  break;
		case 270:Direction="S";  break;
		}
	}
	
	
	public void current_position(){
		System.out.println("Rover: "+Name+" => "+"Location_X = "+Location_X+"Location_Y = "+Location_Y);
	}
	
	public void current_direction(){
		System.out.println("Rover: "+Name+" => "+"Direction = "+Direction);
	}
	
	public void Final_Point(){
		System.out.println("Rover: "+Name+" => FINAL POINT = "+Location_X+" "+Location_Y+" "+Direction);
	}
	
	public void current_Point(){
		if(display_all_messages){System.out.println("Rover: "+Name+" => "+Location_X+" "+Location_Y+" "+Direction);}
	}
	

}
