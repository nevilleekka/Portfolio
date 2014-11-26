/*
 * Rover_Instruction_Parser
 * Coded By: Neville Ekka
 * 
 * Description:  This class pares give text files for token and categorizes to 
 * arrays after creating new Rover Instruction objects for each rover. A new
 * rover instruction is defined by first line and performing (Line mod 2) of every
 * line. This parses can generate instructions objects for infinite Rovers  
 * 
 * Supported Warnings and Messages:
 * 
 * 
 * |Parse Warning| = 1st for Deployed X position is MORE than Grid Size X @ Line:
 * |Parse Warning| = 2st for Deployed Y position is MORE than Grid Size Y @ Line:
 * 
 * 
 * |Parse Error| = Blank Chracter NOT Allowed or Line Cannot Be Empty @ Line:
 * |Parse Error| = White Space or Blank Chracter  NOT Allowed  or Line Cannot Be Empty @ Line:
 * 
 * |Parse Error| = 1st token Should be int for Grid Size X position @ Line:
 * |Parse Error| = 2nd token Should be int for Grid Size Y position @ Line:
 * 
 * |Parse Error| = 1st token Should be int for Deployed X position @ Line:
 * |Parse Error| = 2nd token Should be int for Deployed Y position at @ Line:
 * |Parse Error| = 3rd token Should be single character String as 'N' 'E' 'W' 'S' for Deployed direction @ Line:
 * 
 * |Parse Error| = Only Subtokens of 'L' 'R' 'M' Allowed at Line: "+" token: "+" subToken:
 * |Parse Error| = Only 1st token (with no space) Allowed for Navigate Instructions @ Line:
 * 
 */


package Parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.EmptyStackException;
import java.util.Scanner;
import java.util.Vector;

public final class Rover_Instruction_Parser {
	
	private String File_Path="";
	private int Line_Number=0;
	public boolean display_all_messages=false;
	
	public Vector<Rover_Instruction_Objects> rover_instruction=new Vector<Rover_Instruction_Objects>();
	public boolean Instructions_Parsed=false; 
	
	Rover_Instruction_Parser(String File_Path,boolean display_all_messages){

		this.File_Path=File_Path;
		this.display_all_messages=display_all_messages;
		parse_text_file(this.File_Path);
		
	}
	
	  /*
	    *  public  void parse_text_file(String file_path)
	    * 
	    * Description: Begins parsing by first scanning for lines in the text file then scanning for individual token in a line
	    */	
	
    public  void parse_text_file(String file_path){
    	//String text_File ="C:/Users/User3/workspace/ThoughtWorks_Parser/src/Parser/Rover_Instructions.txt";
  
    	Scanner Line_Scanner = null;

    	try {
    		Line_Scanner = new Scanner(new BufferedReader(new FileReader(this.File_Path)));

    while (Line_Scanner.hasNextLine()) {
    	String current_line =Line_Scanner.nextLine();
    	//System.out.println("Current Line ="+current_line);
    	Line_Number++;
    	char[] Char_line_array= current_line.toCharArray();
    	
    	try{
    	if(Char_line_array[0]==' '){throw new Exception("|Parse Error| = Blank Chracter NOT Allowed or Line Cannot Be Empty @ Line: "+Line_Number);}
    	} catch(Exception ex){ex =new Exception("|Parse Error| = White Space or Blank Chracter  NOT Allowed  or Line Cannot Be Empty @ Line: "+Line_Number); System.out.print(ex); System.exit(0);}
    	
    	if( (Line_Number==1) || ( Line_Number>=4 && (Line_Number%2)==0)  ){ 	// Important condition to create new objects on first line and every first line of 2 lines grouped as one after 3rd line	
    	rover_instruction.add(new Rover_Instruction_Objects());
    	if(display_all_messages){System.out.println("");}
    	if(display_all_messages){System.out.println("|Object Instruction|-----> Objects Created "+this.rover_instruction.size());}
    	}
    	
    	if(display_all_messages){System.out.println(" Line_Number : "+Line_Number);}
    	Token_Scanner(current_line,Line_Number,rover_instruction.lastElement());  //always performs parsing on last element in the Instruction Object List to ensure Validity
    	
    }
    Instructions_Parsed=true; // Flag indicating Parsing for given file is complete
}
 catch (IOException ex){
	 
	 System.out.println("File Not FOund : "+ex);
 }

finally {
    if (Line_Scanner != null) {
    	Line_Scanner.close();
    }
}

}
       /*
	    *  private void  Token_Scanner(String Line,int Line_Number,Rover_Instruction_Objects rover_instruction){
	    * 
	    * Description: Scans for all single character tokens in a line provided by Line_Scanner
	    */	
    
   private void Token_Scanner(String Line,int Line_Number,Rover_Instruction_Objects rover_instruction){
	   Scanner TokenScanner = new Scanner(Line);
	   int token_number=0;
	   String current_token=null;
	   do{
		   try{current_token=TokenScanner.next();}catch(Exception ex){break;}
		   
	   token_number++;
	  // System.out.println("token number >> "+token_number); //DEbugging purposes
	   // Through flags each component function is executed serially
	   if(rover_instruction.Acquired_grid_size && rover_instruction.Acquired_rover_deployed_position_and_direction && !rover_instruction.Acquired_navigate_instructions){get_navigate_instructions( current_token, token_number,rover_instruction,Line_Number);} //else{System.out.println("navigate_instructions skipped" );};
	   	if(rover_instruction.Acquired_grid_size && !rover_instruction.Acquired_rover_deployed_position_and_direction){get_deployed_position_and_direction( current_token, token_number,rover_instruction,Line_Number); }//else{System.out.println("deployed_position_and_direction skipped" );};
		  if(!rover_instruction.Acquired_grid_size){get_grid_size( current_token, token_number,rover_instruction,Line_Number); }// else{System.out.println("Grid Size skipped" );}
		
		  
		  }
	   while(TokenScanner.hasNext());
		   
		  
	   
   }
    
   /*
    * void get_grid_size(String s, int token_number,Rover_Instruction_Objects rover_instruction,int Line_Number)
    * 
    * Description: categorizes tokens found in a line to Grid X and Grid Y token container
    */
   void get_grid_size(String s, int token_number,Rover_Instruction_Objects rover_instruction,int Line_Number){
    	if(this.rover_instruction.size()==1){                     // This condition diverts program flow to automatically assign Grid Sizes to new instruction objects other than first new instruction object
    if(rover_instruction.grid_size[0]==Integer.MIN_VALUE){	try{ // Process token for Grid Size X
    		int token = Integer.parseInt(s);
    		rover_instruction.grid_size[0]=token;
      		if(display_all_messages){System.out.println(token+" => token:"+token_number+" = Grid Size X " );}
    	}
    	catch(Exception ex){									// Process Error token for Grid Size X
    		System.out.println("|Parse Error| = 1st token Should be int for Grid Size X position @ Line:"+Line_Number);
    		System.out.println(ex);System.exit(0);
    	}} else{
    		if(rover_instruction.grid_size[1]==Integer.MIN_VALUE){ try{// Process token for Grid Size Y                               
        		int token = Integer.parseInt(s);              //convert string to integer
        		rover_instruction.grid_size[1]=token;
        		rover_instruction.Acquired_grid_size=true; 
        		if(display_all_messages){System.out.println(token+" => token:"+token_number+" = Grid Size Y " );}
        	}
        	catch(Exception ex){                                  // Process Error token for Grid Size Y
         		ex= new Exception("|Parse Error| = 2nd token Should be int for Grid Size Y position @ Line:"+Line_Number);
         		System.out.println(ex);System.exit(0);
        	}
    	}
    	}	} else{rover_instruction.grid_size[0]=this.rover_instruction.firstElement().grid_size[0];
    			rover_instruction.grid_size[1]=this.rover_instruction.firstElement().grid_size[1];
    			rover_instruction.Acquired_grid_size=true;
    			get_deployed_position_and_direction( s, token_number,rover_instruction,Line_Number);
    				}

     }
    
   /*
    *   public void get_deployed_position_and_direction(String s, int token_number,Rover_Instruction_Objects rover_instruction,int Line_Number)
    * 
    * Description: categorizes tokens found in a line to Position X, Position Y and Direction token containers
    */
   
    public void get_deployed_position_and_direction(String s, int token_number,Rover_Instruction_Objects rover_instruction,int Line_Number){
    	 if(rover_instruction.rover_deployed_position[0]==Integer.MIN_VALUE){	try{
     		int token = Integer.parseInt(s);
     		if(token>rover_instruction.grid_size[0]){System.out.println("|Parse Warning| = 1st for Deployed X position is MORE than Grid Size X @ Line:"+Line_Number);};
     		rover_instruction.rover_deployed_position[0]=token;
      		if(display_all_messages){System.out.println(token+" => token:"+token_number+" = Deployed X position" );}
     	}
     	catch(Exception ex){
     		ex= new Exception("|Parse Error| = 1st token Should be int for Deployed X position @ Line:"+Line_Number);
     		System.out.println(ex);System.exit(0);
     	}} else 
     		if(rover_instruction.rover_deployed_position[1]==Integer.MIN_VALUE){
     		try{
         		int token = Integer.parseInt(s);
         		if(token>rover_instruction.grid_size[0]){System.out.println("|Parse Warning| = 2st for Deployed Y position is MORE than Grid Size Y @ Line:"+Line_Number);};
         		rover_instruction.rover_deployed_position[1]=token;
          		if(display_all_messages){System.out.println(token+" => token:"+token_number+" = Deployed Y position" );}
         	}
         	catch(Exception ex){
         		ex= new Exception("|Parse Error| = 2nd token Should be int for Deployed Y position at @ Line:"+Line_Number);
         		System.out.println(ex);System.exit(0);
         	}
         } else if (rover_instruction.rover_deployed_direction[0]==null){try{
        	 String token =s;
        	 char[] tokenArray = s.toCharArray();
        	 if(tokenArray.length==1 && (tokenArray[0]=='N' || tokenArray[0]=='E' || tokenArray[0]=='S' || tokenArray[0]=='W')){
        	 rover_instruction.rover_deployed_direction[0]=token;
      		rover_instruction.Acquired_rover_deployed_position_and_direction=true;
      		if(display_all_messages){System.out.println(token+" => token:"+token_number+" = Deployed direction" );}
        	 } else{throw new Exception("|Parse Error| = 3rd token Should be single character String as 'N' 'E' 'W' 'S' for Deployed direction @ Line:"+Line_Number);}
      	}
      	catch(Exception ex){
      		System.out.println(ex);System.exit(0);	
      	}} 
     		
    }
    
    /*
     *    public void get_navigate_instructions(String s, int token_number,Rover_Instruction_Objects rover_instruction,int Line_Number)
     * 
     *   Description: categorizes tokens found in a line to Navigate Instruction token containers
     */
    
    public void get_navigate_instructions(String s, int token_number,Rover_Instruction_Objects rover_instruction,int Line_Number){
   	 if(rover_instruction.Navigate_Instructions.isEmpty()){	try{
  		String token = s;
  		char[] tokenArray = s.toCharArray();  // converts string to char array to get all subtokens token from line token, representing instructions.
  		if(display_all_messages){System.out.println(token+" => token:"+token_number+" = Navigate Instructions" );}
  		for(int subToken_number=0;subToken_number<=tokenArray.length-1;subToken_number++){
  			if(tokenArray[subToken_number]=='L'||tokenArray[subToken_number]=='R'||tokenArray[subToken_number]=='M'){
  				rover_instruction.Navigate_Instructions.add(String.valueOf(tokenArray[subToken_number]));
  				if(display_all_messages){System.out.println(tokenArray[subToken_number]+" => sub token:"+subToken_number+" = Navigate Instructions" );}
  		       }else{System.out.println("|Parse Error| = Only Subtokens of 'L' 'R' 'M' Allowed at Line: "+" token: "+" subToken: "+subToken_number);System.exit(0);}
  		}
  		rover_instruction.Acquired_navigate_instructions=true;
  	}
  	catch(Exception ex){
  		System.out.println("|Parse Error| = Only 1st token (with no space) Allowed for Navigate Instructions @ Line:"+Line_Number);
  		System.exit(0);
  	}}
    	
    }
 
}
