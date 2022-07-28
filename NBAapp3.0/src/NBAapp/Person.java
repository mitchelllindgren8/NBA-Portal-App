package NBAapp;

public class Person {
	
	public String fullNameP;
	public String teamName;

	public Person(String fullNameee, String teammm) {
		fullNameP = fullNameee;
		teamName = teammm;
		
	}
	
	public String getPlayer(){
		return fullNameP;
	}
	
	public String getTeam(){
		return teamName;
	}
	
	
	public void print() {
		System.out.print(fullNameP + " " + teamName + "\n");
	}
}
