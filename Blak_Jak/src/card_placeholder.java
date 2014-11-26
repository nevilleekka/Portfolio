
public class card_placeholder {

	public int ph_id;
	
	
	private String name;
	private String suite;
	private int value;
	
	private boolean show_status;
	
	card_placeholder(cards card,boolean show_status,players player){
		this.name=card.name;
		this.suite = card.suite;
		this.value= card.value;
		this.show_status= show_status;
		
	}
	
     card_placeholder(){}; 
	card_placeholder(cards card,boolean show_status){
		this.name=card.name;
		this.suite = card.suite;
		this.value= card.value;
		this.show_status= show_status;
	
		
		
	}

	public boolean get_show_status(){return show_status ;};
	public void set_show_status(boolean n){show_status=n;};
	public String get_name(){if (show_status==true){return this.name;} else{return "X";}}
	public String get_suite(){if (show_status==false){return "X";} else{return this.suite;}}
	public int get_value(){if (show_status==false){return 0;} else{return this.value;}};
	
}
