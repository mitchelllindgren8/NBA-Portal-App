package NBAapp;

public class Owner extends Person {
	
	// [OMIT] This is an older feature of the project that displayed console outputs
	public Owner (String firstName, String lastName) {
		super(firstName, lastName);
		
	}
	
	public void print() {
		System.out.println("Owner details: ");
		super.print();
	}

}
