
public class players extends player_manager implements player_format{
	

	
	players(String name, int balance,dealer deal_er) {
		super(name, balance);
		this.deal_er=deal_er;
		calculate_id(deal_er);
		
	}

	@Override
	public void on_winner_declaration() {  // Below code occurs when the game ends. Any code can be placed here by the programmer
		System.out.println("-------------------------->"+this.name+" HAS JUST WON HIS BET");
		System.out.println();
	}

	@Override
	public void on_loser_declaration() {// Below code occurs when the game ends. Any code can be placed here by the programmer
		System.out.println("-------------------------->"+this.name+" HAS JUST LOST HIS BET");
		System.out.println();
	}

	@Override
	public void player_reset() {
		// TODO Auto-generated method stub
		
	}

	
			

	
}
	