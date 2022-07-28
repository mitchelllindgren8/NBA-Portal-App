package NBAapp;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NBAAdminApp {
	
	private static String teamInput = "";
	public static String Owner = "";
	
	//public String[][] LEAGUE = new String[][] {Bulls, Celtics, Lakers, Nets, Warriors, FreeAgents};
	public static String[][] LEAGUE = new String[6][];
	
	
	public static void main(String[] args) throws FileNotFoundException {
	
		// [OMIT] This is an older feature of the project that displayed console outputs
		System.out.println("VERSION 4.0\n");
		System.out.print("*** Welcome NBA Owner ***\n(Please choose your respective basketball team)"
				+ "\n\tBoston Celtics\n\tBrooklyn Nets\n\tChicago Bulls\n\tGolden State Warriors\n\tLos Angeles Lakers\n>>>");
		
		// [OMIT] This is an older feature of the project that displayed console outputs
		Scanner scan = new Scanner(System.in);
		teamInput = scan.nextLine();
		Team tm = new Team(teamInput);
		
	}
}
