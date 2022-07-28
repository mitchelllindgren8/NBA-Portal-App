package NBAapp;

public class Player extends Person {
	
	private String name;
	private String jerseyNum;
	private String height;
	private String weight;
	private String position;
	
	
	public Player(String fullNamee, String teamm, String jerNo) {
		
		//the super calls the parent class's methods/constructors and then sets the variables to global variables in Person.
		super(fullNamee, teamm);
		this.jerseyNum = jerNo;
		
	}
	
	public String getName() {
		return name;
	}
	
	public String getHeight(){
		return height;
	}
	
	public String getWeight(){
		return weight;
	}
	
	public String getPosition(){
		return position;
	}
	
	public void print() {
		
		System.out.print("Player details: \n\t"+" #" +jerseyNum + " ");
		//[OMIT] This is an older feature that printed onto the local console
		//super.print();
	}

}
