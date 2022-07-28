package NBAapp;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

import NBAapp.DashBoard;

public class Team {
	
	private static String nbaTeam;
	private int iterator = 0;
	public League2 league = new League2();
	
	//[OMIT] This is an older file that displays on the local console
	public Team(String team) throws FileNotFoundException {
		
		boolean found = false;
		//boolean found = league.checkLeague(team);
		setTeam();

	}
	
	//[OMIT] This is an older file that displays on the local console
	public String setTeam() {
		
		nbaTeam = league.nbaTeam;
		return nbaTeam;
	}
	
	
	//main print menu
	//[OMIT] This is an older file that displays on the local console
	public void printMenu() throws FileNotFoundException {
		
		Scanner scan = new Scanner(System.in);
		System.out.print("\nWelcome " + nbaTeam + " Owner!\n");
		
		/*
		while(iterator == 0) {
			
			DashBoard obj = new DashBoard();
			
			System.out.print("[Please select a team option to perform]\n1. View Team\n2. View League\n3. Sign Player\n4. Release Player\n5. Exit \n>>>");
			
			System.out.println(obj.ownerInput);
			if(obj.ownerInput == 1) {	//1. View Team
				league.viewTeam();
				
			} else if(obj.ownerInput == 2) {	//2. View League
				//resets the league rosters
				league.print();
				
			} else if(obj.ownerInput == 3) {	//3. Sign Player
				//need a method to reset the playerlist/teams after a player has been added and removed
				//league.signPlayer();
				
			} else if(obj.ownerInput == 4) {	//4. Release Player
				//need a method to reset the playerlist/teams after a player has been added and removed
				//league.releasePlayer();
			
			} else if(obj.ownerInput == 5) {	//5. Exit 
				
				System.out.println("\nThank you for using the NBA Administration App. The app will now exit.");
				iterator++;	//this needed to be shared with the while loop and original while on line 51*
				
			} else {
				System.out.print("Error: Please enter only 1, 2, or 3: ");
				obj.ownerInput = scan.nextInt();
			}
		}//ends while
		*/
		
		
		//This code is meant for console outputs, checking if the logic works
		/*
		while(iterator == 0) {
			
			System.out.print("[Please select a team option to perform]\n1. View Team\n2. View League\n3. Sign Player\n4. Release Player\n5. Exit \n>>>");
			ownerInput = scan.nextInt();
			
			if(ownerInput == 1) {	//1. View Team
				league.viewTeam();
				
			} else if(ownerInput == 2) {	//2. View League
				//resets the league rosters
				league.print();
				
			} else if(ownerInput == 3) {	//3. Sign Player
				//need a method to reset the playerlist/teams after a player has been added and removed
				league.signPlayer();
				
			} else if(ownerInput == 4) {	//4. Release Player
				//need a method to reset the playerlist/teams after a player has been added and removed
				league.releasePlayer();
			
			} else if(ownerInput == 5) {	//5. Exit 
				
				System.out.println("\nThank you for using the NBA Administration App. The app will now exit.");
				iterator++;	//this needed to be shared with the while loop and original while on line 51*
				
			} else {
				System.out.print("Error: Please enter only 1, 2, or 3: ");
				ownerInput = scan.nextInt();
			}
		}//ends while
		*/
		
	}
	

}//ends main team class
