import java.util.Random;
import java.util.Vector;


public class shoebox {

public int deck_id;
public int card_id=0;
public int stack_limit= 100;

public Vector<cards> card_stack = new Vector<cards>(1);
public Vector<cards> card_stack_used = new Vector<cards>(1);

private Random generator = new Random();


public void shuffle(){
	card_stack_used = new Vector<cards>(1);
	for (int i = 1; i <= stack_limit; i++){
	int rand = generator.nextInt(card_stack.size());
	this.card_stack_used.add(card_stack.get(rand));
	}
	card_stack = new Vector<cards>(1);
};

public void create_deck(int deck_id){
this.deck_id=deck_id;
create_cards();
}

private void create_cards(){
String name="";
int value=0;
for (int suite = 1; suite <= 4; suite++){
for (int i = 1; i <= 13; i++){
	card_id=card_id+1;
	value=i;
	name=""+i; 
	if(i==1){name="A";value= 0;};
	if(i==11){name="j";value=10;};
	if(i==12){name="q";value=10;};
	if(i==13){name="k";value=10;};
		card_stack.add(new cards(""+name,""+suite,value,this.deck_id,card_id));
};};}

private void reset_shoebox(){
	deck_id=0;
    card_id=0;
	stack_limit = 0;
	 card_stack.clear();
	
	card_stack_used.clear();
	
}



}
