package NBAapp;
import java.util.List;
import java.util.Scanner;
import java.util.Arrays;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.text.SimpleDateFormat;
import java.util.Date;

import logonSys.UserLogin;

public class League2 {
	
	public List<String> playerList = new ArrayList();	//list of list of player
	
	public Player[] PLAYERS = new Player[31];
	protected Player[] Celtics = new Player[5];
	protected Player[] Lakers = new Player[5];
	protected Player[] Nets = new Player[5];
	protected Player[] Bulls = new Player[5];
	protected Player[] Warriors = new Player[5];
	protected Player[] FreeAgents = new Player[5];
	
	private String errorMessageRel = "[Error] The player entered does not belong to your team.\nPlease enter a player to release that belongs to your team.\n";
	private String errorMessageSign = "[Error] The player entered belongs to another team.\nPlease enter a player to sign that is a Free Agent.\n";
	public String[] proteamNames = new String[6];
	//these are the full professional team names
	private String celticsT= "Boston Celtics";
	private String netsT = "Brooklyn Nets";
	private String bullsT = "Chicago Bulls";
	private String warriorsT = "Golden State Warriors";
	private String lakersT = "Los Angeles Lakers";
	private String freeAgentsT = "Free Agents";
	public String nbaTeam = "";
	
	public League2() throws FileNotFoundException {
		
		this.nbaTeam = UserLogin.teamInput;
		createLeague();
	}
	
	//reads DB file of players and adds players to team arrays accordingly
	private void createLeague() throws FileNotFoundException {
		
		//int iterators for team arrays [size]
		int i = 0, itrB=0, itrC=0, itrFA=0, itrL=0, itrN=0, itrW=0;
		File f = new File("players.txt");
		Scanner scanF = new Scanner(f);
		
		while(scanF.hasNext()) {
			String temp = scanF.nextLine();
			Scanner scanR = new Scanner(temp);
			scanR.useDelimiter("#");
			
			String playerName, jerNo, team;
			playerName = scanR.next();
			jerNo = scanR.next();
			team = scanR.next();
			
			if(team.contains("Bulls")){
				Bulls[itrB] = new Player(playerName, team, jerNo);
				itrB++;
				
			} else if(team.contains("Celtics")) {
				Celtics[itrC] = new Player(playerName, team, jerNo);
				itrC++;
				
			} else if(team.contains("FreeAgents")) {
				FreeAgents[itrFA] = new Player(playerName, team, jerNo);
				itrFA++;
				
			} else if(team.contains("Lakers")) {
				Lakers[itrL] = new Player(playerName, team, jerNo);
				itrL++;
				
			} else if(team.contains("Nets")) {
				Nets[itrN] = new Player(playerName, team, jerNo);
				itrN++;
				
			} else {	//"Warriors"
				Warriors[itrW] = new Player(playerName, team, jerNo);
				itrW++;
			}
			
			i++;	//this incremented the player list
			playerList.add(playerName);
		}//ends the while loop 
		
		checkLeague(nbaTeam);
	}
	
	// [OMIT] This was for console outputs, before the GUI was created 
	// because MySQL was imported we no longer need to use this method
	public void checkLeague(String teamName) throws FileNotFoundException {
		
		boolean found = false;
		
		Scanner in = new Scanner(System.in);
		populateLeague(); 

		//Once the while loop ends, found must be true, add an IF statement if wanted
		while(!Arrays.asList(proteamNames).contains(teamName)) {
			//System.out.print("[Error] Please enter the correct team name: ");
			teamName = in.nextLine();
		}
		
		nbaTeam = teamName;
		found = true;
	}
	
	
	public String getTeam() {
		return nbaTeam;
	}

	//adds the professional team names to the League List, "Los Angelos Lakers, etc"
	public void populateLeague() {
		
		proteamNames[0] = celticsT;
		proteamNames[1] = netsT;
		proteamNames[2] = bullsT;
		proteamNames[3] = warriorsT;
		proteamNames[4] = lakersT;
		proteamNames[5] = freeAgentsT;
	}
	
	// [OMIT] This was for console outputs, before the GUI was created
	public String viewTeam() {
		
		Scanner scann = new Scanner(System.in);
		String result = "";
		
		switch(nbaTeam) {
			case "Boston Celtics": 		
				System.out.println("\n** The " + nbaTeam + " starting " + Celtics.length +  "**");
				result = "\n** The " + nbaTeam + " starting " + Celtics.length +  "**";
				for(int i= 0; i<Celtics.length; i++) 
					System.out.println("\t" + Celtics[i].getPlayer());
				System.out.println("***************************************\n");
				break;
				
			case "Brooklyn Nets":
				System.out.println("\n** The " + nbaTeam + " starting " + Nets.length +  "**");
				result = "\n** The " + nbaTeam + " starting " + Celtics.length +  "**";
				for(int i= 0; i<Nets.length; i++) 
					System.out.println("\t" + Nets[i].getPlayer());
				System.out.println("***************************************\n");
				break;
				
			case "Chicago Bulls":
				System.out.println("\n** The " + nbaTeam + " starting " + Bulls.length +  "**");
				result = "\n** The " + nbaTeam + " starting " + Celtics.length +  "**";
				for(int i= 0; i<Bulls.length; i++) 
					System.out.println("\t" + Bulls[i].getPlayer());
				System.out.println("***************************************\n");
				break;
				
			case "Golden State Warriors":
				System.out.println("\n** The " + nbaTeam + " starting " + Warriors.length +  "**");
				result = "\n** The " + nbaTeam + " starting " + Celtics.length +  "**";
				for(int i= 0; i<Warriors.length; i++) 
					System.out.println("\t" + Warriors[i].getPlayer());
				System.out.println("***************************************\n");
				break;
				
			case "Los Angeles Lakers":
				System.out.println("\n** The " + nbaTeam + " starting " + Lakers.length +  "**");
				result = "\n** The " + nbaTeam + " starting " + Celtics.length +  "**";
				for(int i= 0; i<Lakers.length; i++) 
					System.out.println("\t" + Lakers[i].getPlayer());
				System.out.println("***************************************\n");
				break;
				
			//You can never view the FreeAgents team, simply used for printing(KEEP)
			case "Free Agents":
				System.out.println("\n-- The " + nbaTeam + " available --");
				result = "\n** The " + nbaTeam + " starting " + Celtics.length +  "**";
				for(int i= 0; i<FreeAgents.length; i++)
					System.out.println("\t" + FreeAgents[i].getPlayer());
				System.out.println("__________________________________\n");
				break;
			
			default:
				break;	
		}
		return result;
	}
		
	// [OMIT] This was an expanded idea to view players
	public void viewPlayer() {
		System.out.println("A Player to view code(will be added later).....\n");
	}
	
	//checks if player entered exists in league
	public boolean checkPlayer(String player) {
		
		if(playerList.contains(player)){
			System.out.println("Player found");
			return true;
		}else{
			System.out.println("Player not found");
			return false;
		}
		
	}
	
	//checks if team has a full roster
	public boolean checkTeams() {
		
		switch(nbaTeam) {
		 
			case "Boston Celtics":
				if(Celtics.length == 5){
					return false;
				}
				break;
				
			
			case "Brooklyn Nets":
				if(Nets.length == 5){
					return false;
				}
				break;
				
			case "Chicago Bulls":
				if(Bulls.length == 5){
					return false;
				}
				break;
				
			case "Los Angeles Lakers":
				if(Lakers.length == 5){
					return false;
				}
				break;
				
			case "Golden State Warriors":
				if(Warriors.length == 5){
					return false;
				}
				break;
		}
	
		return true;
	}

		
	//add a player
	public boolean signPlayer(String player) throws FileNotFoundException {
		
		String signedPlayer = player;
		
		List<Player> list = Arrays.asList(FreeAgents);
		boolean found = false;
		
		switch(nbaTeam) {
			 
			case "Boston Celtics": 
				
				if(!(Celtics.length < 5)){
					System.out.println("[Error] The " + nbaTeam + " already has a full roster of 5 players.\n");
					break;
				}
	
				//This checks if player exists in the FreeAgency list
				for(Player temp1 : list) {
					if(temp1.getPlayer().equals(signedPlayer)) 
						found = true;		
				}
				
				if(found == true) 
					Celtics = addTheElement(Celtics, signedPlayer);
				else {
					System.out.println(found);
					System.out.println(errorMessageSign);
				}
				
				break;
				
			case "Brooklyn Nets":
				
				if(!(Nets.length < 5)){
					System.out.println("[Error] The " + nbaTeam + " already has a full roster of 5 players.\n");
					break;
				}
				
				for(Player temp1 : list) {
					if(temp1.getPlayer().equals(signedPlayer)) 
						found = true;		
				}
				
				if(found == true) {
					Nets = addTheElement(Nets, signedPlayer);
				} else {
					System.out.println(errorMessageSign);
				}
				break;
				
			case "Chicago Bulls":
				
				if(!(Bulls.length < 5)){
					System.out.println("[Error] The " + nbaTeam + " already has a full roster of 5 players.\n");
					break;
				}
				
				for(Player temp1 : list) {
					if(temp1.getPlayer().equals(signedPlayer)) 
						found = true;		
				}
				
				if(found == true) {
					Bulls = addTheElement(Bulls, signedPlayer);
				} else {
					System.out.println(errorMessageSign);
				}
				break;
				
			case "Los Angeles Lakers":
				
				if(!(Lakers.length < 5)){
					System.out.println("[Error] The " + nbaTeam + " already has a full roster of 5 players.\n");
					break;
				}
				
				for(Player temp1 : list) {
					if(temp1.getPlayer().equals(signedPlayer)) 
						found = true;		
				}
				
				if(found == true) {
					Lakers = addTheElement(Lakers, signedPlayer);
				} else {
					System.out.println(errorMessageSign);
				}
				break;
				
			case "Golden State Warriors":
				
				if(!(Warriors.length < 5)){
					System.out.println("[Error] The " + nbaTeam + " already has a full roster of 5 players.\n");
					break;
				}
				
				for(Player temp1 : list) {
					if(temp1.getPlayer().equals(signedPlayer)) 
						found = true;		
				}
				
				if(found == true) {
					Warriors = addTheElement(Warriors, signedPlayer);
				} else {
					System.out.println(errorMessageSign);
				}
				break;
				
			//This should never run unless nbaTeam is overwritten
			default:
				System.out.println("[Error] No NBA team was chosen.");
				break;
		}//ends switch
			
		return found;
	}
		
		
	public Player[] addTheElement(Player[] team, String name) {
		
		Player[] tempFreeAgents = new Player[FreeAgents.length-1];
		
		if(team == null) 
			return team;
		
		for(int i=0, k=0; i < FreeAgents.length; i++) {
			
			if(FreeAgents[i].getPlayer().equals(name))  {
				
				//any signed player is immediately removed from the Free Agency list
				Player[] tempTeam = new Player[team.length+1];
				
					for(int m=0; i < tempTeam.length; m++) {
						
						//sets last tempTeam roster spot to be the released player
						if( m == tempTeam.length-1){
							tempTeam[tempTeam.length-1] = FreeAgents[i];	
							break;
						}
						tempTeam[m] = team[m];
					}
				
				team = tempTeam;
				continue;	//statement breaks one iteration (in the loop), if a specified condition occurs, and continues with the next iteration in the loop.
			}//ends if
			
			tempFreeAgents[k++] = FreeAgents[i];
		}
			
		FreeAgents = tempFreeAgents;
		return team;
	}
	
	//release a player
	public boolean releasePlayer(String player) throws FileNotFoundException {
		
		String releasedPlayer = player;

		List<Player> list;
		boolean found = false;
		
		switch(nbaTeam) {
		 
			case "Boston Celtics": 
				
				list = Arrays.asList(Celtics);

				//This check needs to iterator through the entire team and check if the player exists,
				for(Player temp1 : list) {
					if(temp1.getPlayer().equals(releasedPlayer)) 
						found = true;		
				}
				
				if(found == true) {
					this.Celtics = removeTheElement(Celtics, releasedPlayer);
				} else {
					System.out.println(errorMessageRel);
				}
				
				break;
				
			case "Brooklyn Nets":
				
				list = Arrays.asList(Nets);
				for(Player temp1 : list) {
					if(temp1.getPlayer().equals(releasedPlayer)) 
						found = true;		
				}
				
				if(found == true) {
					Nets = removeTheElement(Nets, releasedPlayer);
				} else {
					System.out.println(errorMessageRel);
				}
				break;
				
			case "Chicago Bulls":
				
				list = Arrays.asList(Bulls);
				for(Player temp1 : list) {
					if(temp1.getPlayer().equals(releasedPlayer)) 
						found = true;		
				}
				
				if(found == true) {
					Bulls = removeTheElement(Bulls, releasedPlayer);
				} else {
					System.out.println(errorMessageRel);
				}
				break;
				
			case "Los Angeles Lakers":
				
				list = Arrays.asList(Lakers);
				for(Player temp1 : list) {
					if(temp1.getPlayer().equals(releasedPlayer)) 
						found = true;		
				}
				
				if(found == true) {
					Lakers = removeTheElement(Lakers, releasedPlayer);
				} else {
					System.out.println(errorMessageRel);
				}
				break;
				
			case "Golden State Warriors":
				
				list = Arrays.asList(Warriors);
				for(Player temp1 : list) {
					if(temp1.getPlayer().equals(releasedPlayer)) 
						found = true;		
				}
				
				if(found == true) {
					Warriors = removeTheElement(Warriors, releasedPlayer);
				} else {
					System.out.println(errorMessageRel);
				}
				break;
				
			//This should never run unless nbaTeam is overwritten
			default:
				System.out.println("[Error] No NBA team was chosen.");
				break;
		}//ends switch
		
		return found;
	}
	
	public Player[] removeTheElement(Player[] team, String name) {
		
		Player[] tempTeam = new Player[team.length-1];
		
		if(team == null) 
			return team;
		
		for(int i=0, k=0; i < team.length; i++) {
			
			if(team[i].getPlayer().equals(name))  {
				
				//any released player is immediately added to the Free Agency list
				Player[] tempFreeAgents = new Player[FreeAgents.length+1];
				
					for(int m=0; i < tempFreeAgents.length; m++) {
						
						//sets last FAroster spot to be the released player
						if( m == tempFreeAgents.length-1){
							tempFreeAgents[tempFreeAgents.length-1] = team[i];	
							break;
						}
						tempFreeAgents[m] = FreeAgents[m];
					}
				
				FreeAgents = tempFreeAgents;
				continue;	//statement breaks one iteration (in the loop), if a specified condition occurs, and continues with the next iteration in the loop.
			}//ends if
			
			tempTeam[k++] = team[i];
		}
			
		team = tempTeam;
		return team;
	}
}
