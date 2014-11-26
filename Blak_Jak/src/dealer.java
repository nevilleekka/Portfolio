import java.util.Vector;


public class  dealer {
	

private static int players_id=0;
private static int player_stand=0;

private int dealer_score=0;

private static int deck_id=0;
private int dealer_hit_number=0;

private int current_card_stack_number;


private shoebox deck = new shoebox();

private Vector<cards> card_stack_used =new Vector<cards>(1);
private Vector <card_placeholder> card_ph =new Vector<card_placeholder>(1) ;
private Vector <card_placeholder> dealer_card_ph = new Vector<card_placeholder>(1);

public void deal(players player){
	reset();
	player.reset();
	player.player_reset();
	create_new_deck();
	deck.shuffle();
	this.card_stack_used = deck.card_stack_used;
	dealer_card_ph.add(new card_placeholder());
	dealer_card_ph.add( new card_placeholder(card_stack_used.get(1),false,player));
	dealer_card_ph.add( new card_placeholder(card_stack_used.get(2),true,player));
	this.dealer_hit_number=2;
	
	card_ph.add(new card_placeholder());
	card_ph.add(new card_placeholder(card_stack_used.get(3),true,player));
	card_ph.add(new card_placeholder(card_stack_used.get(4),true,player));
	player.hit_number=2;
	current_card_stack_number =4;
	
	 player.score= player_score(player);
	 
	 deck= new shoebox();
	 System.out.println("ALL PLAYERS= "+players_id);
};

public void hit(players player){ 
	
	if(!player.call_status.equals("stand") ){
	player.call_status="hit";
	player.hit_number++;
	
	this.current_card_stack_number++;
	
    card_ph.add(new card_placeholder(card_stack_used.get(this.current_card_stack_number),true));

    player.score=player_score(player);
  

    if (player.score>21){player.winner=2;dealer_card_ph.get(1).set_show_status(true); player.on_loser_declaration();}
    if (player.score==21){player.winner=1;dealer_card_ph.get(1).set_show_status(true);player.on_winner_declaration();}}
};

public void stand(players player){
	player.call_status="stand";
	this.player_stand++;
	 if(player_stand==players_id){all_stand(); declare_winner(player);}
};

public void all_stand(){
		dealer_card_ph.get(1).set_show_status(true);
		
	dealer_score();	
	
	
	while (dealer_score <17){
		
				this.dealer_hit_number++;
				this.current_card_stack_number++;
			    	
		dealer_card_ph.add( new card_placeholder(card_stack_used.get(this.current_card_stack_number),true));
		dealer_score();
		
	}

	
	
};
public void reset(){
		dealer_score=0;
		
	     deck_id=0;
		 dealer_hit_number=0;
		 
		 player_stand=0;
		 
		 card_stack_used.clear();
	     card_ph.clear();
		 dealer_card_ph.clear();
		 card_stack_used =new Vector<cards>(1);
		 card_ph =new Vector<card_placeholder>(1) ;
		 dealer_card_ph = new Vector<card_placeholder>(1);
		 }	 

public void declare_winner(players player){
	if (player.call_status.equals("stand")){
if (player.score<dealer_score && player.score<21){player.winner=2;player.on_loser_declaration();}
if (player.score>dealer_score && player.score<21){player.winner=1;player.on_winner_declaration();}
}}

public void dealer_score(){
	 int aces_found=0;
for (int i = 1; i <= dealer_hit_number; i++){

	if (!dealer_card_ph.get(i).get_name().equals("A")){
		dealer_score = dealer_score + dealer_card_ph.get(i).get_value();
			}
	else{ aces_found++;}
	}

///--------------------------------------------
if (aces_found>0){
	int [] score_list = new int[(int) Math.pow (2,aces_found)+1];
	int k = 0;
	
	for (int j = 1; j <= (int) Math.pow ( 2, aces_found); j++){
	 String bin = Integer.toBinaryString(j);
	
	 char[] ch = bin.toCharArray(); 
	 int score=0;
	 
	 k++;
	 for (char c : ch){
		
		 if (Character.toString(c).equals("0")){score = score +1;} else {score = score +11;}

	}	
	  score_list[k] = score+dealer_score;
	
	 }
	 int final_score = 0;
	 
	 int temp=0;
	 for (int m = 1; m <=k; m++ ){
		 
			if(score_list[m]==21) { dealer_score = score_list[m];};
			
			if(score_list[m]<21)  {
		   temp=score_list[m];
			if (temp > final_score){final_score  =score_list[m]; dealer_score=final_score ;}
		}
		
	 }

	aces_found=0;
    
}
    };


public int player_score(players player){
	 int aces_found=0;
	 int player_score=0;
for (int i = 1; i <= player.hit_number; i++){

	if (!card_ph.get(i).get_name().equals("A")){
		player_score = player_score + card_ph.get(i).get_value();
			}
	else{ aces_found++;}
	}

///--------------------------------------------
if (aces_found>0){
	int [] score_list = new int[(int) Math.pow (2,aces_found)+1];
	int k = 0;
	
	for (int j = 1; j <= (int) Math.pow ( 2, aces_found); j++){
	 String bin = Integer.toBinaryString(j);
	
	 char[] ch = bin.toCharArray(); 
	 int score=0;
	 
	 k++;
	 for (char c : ch){
		
		 if (Character.toString(c).equals("0")){score = score +1;} else {score = score +11;}
		 
	}	
	  score_list[k] = score+player_score;
	    
	 }
	 int final_score = 0;
	 
	 int temp=0;
	 for (int m = 1; m <=k; m++ ){
		 
			if(score_list[m]==21) { player_score = score_list[m];};
			
			if(score_list[m]<21)  {
		   temp=score_list[m];
			if (temp > final_score){final_score  =score_list[m]; player_score=final_score ;}
		}
		
	 }

	aces_found=0;
   }
return player_score;
};

public void create_new_deck(){

	deck_id=deck_id+1;
	deck.create_deck(deck_id);
}

public card_placeholder get_player_place_holder_by(int i){return card_ph.get(i);}
public card_placeholder get_dealer_place_holder_by(int i){return dealer_card_ph.get(i);}

public int get_dealer_hit_number(){ return dealer_hit_number;}
public void set_players_id(int n){ this.players_id=n;}
public int get_players_id(){ return players_id;}
public int get_dealer_score(){ if(player_stand==players_id){return dealer_score;} else {return 0;} }

}
