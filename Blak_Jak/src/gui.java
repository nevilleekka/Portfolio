
class gui {
	
	// In this test GUI class, deal, hit and stand methods are tested . Ignore when the 
	//winner is announced and ended which occurs when the player has already hit 21 or higher.
	// At every call Stand, the game ends with final player score.

 
	public static void main(String[] args) {

		dealer zebra = new dealer(); 
		players nex= new players("nex", 1000, zebra);

		zebra.deal(nex);
		update_dealer_pH(zebra);// update dealer placeholder
		update_player_pH(zebra,nex); // update player placeholder
		System.out.println("nex score= "+nex.score+"\n"); // get score
		
		zebra.hit(nex);
		System.out.println("call_status= "+nex.call_status);
		update_dealer_pH(zebra);
		update_player_pH(zebra,nex);
		System.out.println("nex score= "+nex.score+"\n");
		
		zebra.stand(nex);
		System.out.println("call_status= "+nex.call_status);
		update_dealer_pH(zebra);
		update_player_pH(zebra,nex);
		System.out.println("nex score= "+nex.score+"\n");
		
	}
		
	
	public static void update_dealer_pH(dealer deal_er){
		System.out.print("Dealer => ");
		 for (int i = 1; i <= deal_er.get_dealer_hit_number(); i++){
		  System.out.print(" "+deal_er.get_dealer_place_holder_by(i).get_name() + "  ");}
		  System.out.println("");}
 
	public static void update_player_pH(dealer deal_er, players player){
		System.out.print("Player => ");
		 for (int i = 1; i <= player.get_player_hit_number(); i++){
		  System.out.print(" "+deal_er.get_player_place_holder_by(i).get_name() + "  ");}
		  System.out.println("");}
	
	

}
