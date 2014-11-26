/*
 * ThoughtWorks_Parser
 * Coded By: Neville Ekka
 * For: Kai Y Zhang 
 * For Agency: AgencyThoughtWorks
 * 
 */

/*
 * Rover_Parser
 * Description:  Main class for setting up rover and defining file path for text file 
 * containing instructions
 */

package Parser;
import java.io.*;
import java.util.Scanner;



public final class Rover_Parser {

	
    public static void main(String[] args) throws IOException {
    	String file_path="src/Parser/Rover_Instructions.txt";
    	
    	Rover_Instruction_Parser rip = new Rover_Instruction_Parser(file_path,false); // Change to true for detailed parsing messages

    	
    	Rover_Navigator rover_1= new Rover_Navigator("Neville Ekka",false);// Change to true for detailed rover navigation messages
    	rover_1.process_instruction(rip.rover_instruction);
    	rover_1.Final_Point();
    	
    	Rover_Navigator rover_2= new Rover_Navigator("Kai Y Zhang",false);// Change to true for detailed rover navigation messages
    	rover_2.process_instruction(rip.rover_instruction);
    	rover_2.Final_Point();
    	
    	Rover_Navigator rover_3= new Rover_Navigator("ThoughtWorks",false);// Change to true for detailed rover navigation messages
    	rover_3.process_instruction(rip.rover_instruction);
    	rover_3.Final_Point();

        }
    
    
    
}


