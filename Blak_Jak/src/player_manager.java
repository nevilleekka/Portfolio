
public abstract class player_manager {
	public String name;
	protected int id;
	public int score;
	public int hit_number=0;
	private int balance;
	public String call_status="";
	public int winner;
	public static dealer deal_er;
	
	player_manager(String name, int balance){
		this.name=name;
		this.balance= balance;
	}
	
  public void calculate_id(dealer deal_er){
	 
	 int temp = 0;
	 temp = deal_er.get_players_id();
	 this.id= temp +1;
	
	 deal_er.set_players_id(this.id) ;
  }
	
  public void add_balance(){
	  int temp=0;
	  temp = this.balance;
	  this.balance =  this.balance+ temp;
  }
  
  public void subtract_balance(){
	  int temp=0;
	  temp = this.balance;
	  this.balance =  this.balance+ temp;
  }
  
  public int get_player_hit_number(){ return hit_number;}
  
  public void reset(){
		
		score=0;
		hit_number=0;
		balance=0;
		call_status="";
		winner=0;
		
	  }
  
}
