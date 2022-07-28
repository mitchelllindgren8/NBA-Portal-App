package NBAapp;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

//import LogonGUI.tempLogin;

import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.ScrollPaneConstants;
import javax.swing.JRadioButton;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import javax.swing.JButton;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;

import logonSys.UserLogin;

import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Image;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.EventQueue;

public class DashBoard extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JLayeredPane layeredPane;
	private JPanel panelLeague;
	private JPanel panelTeam;
	private JPanel panelSign;
	private JPanel panelbtmSign;
	private JPanel panelRelease;
	private JPanel panelbtmRelease;
	private JPanel contentPane;
	private JTextField releasePlayertxt;
	private JTextField signPlayertxt;
	private JLabel jlabels[];
	private JLabel MessageSign;
	private JLabel MessageRelease;
	private JLabel lblTeamName;
	private JLabel lblStartingNum;
	private JLabel outline;
	private JLabel bgbottomSign;
	private JButton btnViewteam;
	private JButton btnViewleague;
	private JButton btnSignplayer;
	private JButton btnReleaseplayer;
	
	private List<String> players = new ArrayList();
	public League2 obj = new League2();
	public boolean Selected = false;
	public boolean alphabetical = true;
	private static String decision;
	private String releasePlayer;
	private String signPlayer;
	private int counter = 0;
	
	//Launch the application
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					DashBoard window = new DashBoard();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//Finds team user input and sets up UI to resemble team colors
	public void setBackground() {
		
		//sets up full List of players
		players.addAll(obj.playerList);
		
		outline = new JLabel();
		outline.setOpaque(true);
		outline.setBounds(21, 262, 451, 372);
		
		switch(UserLogin.teamInput) {
			case "Boston Celtics": 
				outline.setBackground(new Color(0x009900));
				//set image
				break;
				
			case "Brooklyn Nets":
				outline.setBackground(Color.BLACK);
				//set image
				break;
				
			case "Chicago Bulls":
				outline.setBackground(Color.RED);
				//set image
				break;
				
			case "Golden State Warriors":
				outline.setBackground(Color.blue);
				//set image
				break;
				
			case "Los Angeles Lakers":
				outline.setBackground(new Color(0x6600CC));
				//set image
				break;
			
			//FA
			default:
				outline.setBackground(Color.darkGray);
				//set image
				break;
			}
	}
	
	public void switchPanel(JPanel panel) {
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
		layeredPane.revalidate();
	}
	
	//populates the panels with lists of players
	public void CreateLabelsDynamically(int num, Player[] team, String Desc) {
		
		//System.out.println("CreateLabel: Size =" + num);
				
		int incr = 0;		//incrementor
		counter++;			//counter is meant for resetLabels method
		
		switch(Desc){
		
			case "VT":	//ViewTeam
				
				if(!(num < 0)) {
					jlabels = new JLabel[num];
					
					for(int i=0; i<jlabels.length; i++) {
						jlabels[i] = new JLabel(team[i].getPlayer());
						jlabels[i].setBounds(50, 60+incr, 300, 100);
						jlabels[i].setFont(new Font("Helvetica", Font.PLAIN, 20));
						incr = incr + 50;
						panelTeam.add(jlabels[i]);
					}
				}
				panelTeam.validate();
				panelTeam.repaint();
			break;
			
			case "VL":	//ViewLeague
				
				//This creates 3 columns for players to be listed
				int plSize = obj.playerList.size();
				jlabels = new JLabel[plSize];
				
				for(int i=0; i<10; i++) {
					jlabels[i] = new JLabel(players.get(i));
					jlabels[i].setBounds(25, 40+incr, 300, 25);
					jlabels[i].setFont(new Font("Helvetica", Font.PLAIN, 14));
					incr = incr + 33;
					panelLeague.add(jlabels[i]);
				}
				
				incr = 0;	//resets incr
				
				for(int j=10; j<20; j++) {
					jlabels[j] = new JLabel(players.get(j));
					jlabels[j].setBounds(160, 40+incr, 300, 25);
					jlabels[j].setFont(new Font("Helvetica", Font.PLAIN, 14));
					incr = incr + 33;
					panelLeague.add(jlabels[j]);
				}
				
				incr = 0;	//resets incr
				
				for(int j=20; j<30; j++) {
					jlabels[j] = new JLabel(players.get(j));
					jlabels[j].setBounds(315,40+incr, 300, 25);
					jlabels[j].setFont(new Font("Helvetica", Font.PLAIN, 14));
					incr = incr + 33;
					panelLeague.add(jlabels[j]);
				}
				panelLeague.validate();
				panelLeague.repaint();
				
			break;
			
			case "SP":	//Sign Player
				
				jlabels = new JLabel[num];
				
				//checks if Free Agents are over 5 players, creates new column
				if(team == obj.FreeAgents && jlabels.length > 5){
					
					int remainder = jlabels.length - 5;
					int tempF = jlabels.length-remainder;	//first 5
					
					for(int i=0; i<tempF; i++) {
						jlabels[i] = new JLabel(team[i].getPlayer());
						jlabels[i].setBounds(50, 25+incr, 300, 100);
						jlabels[i].setFont(new Font("Helvetica", Font.PLAIN, 20));
						incr = incr + 25;
						panelbtmSign.add(jlabels[i]);
					}
					
					incr = 0;	//resets incr
					
					for(int j=5; j<jlabels.length; j++) {
						jlabels[j] = new JLabel(team[j].getPlayer());
						jlabels[j].setBounds(220, 25+incr, 300, 100);
						jlabels[j].setFont(new Font("Helvetica", Font.PLAIN, 20));
						incr = incr + 25;
						panelbtmSign.add(jlabels[j]);
					}
					
					panelbtmSign.validate();
					panelbtmSign.repaint();
					break;
				}
				
				//checks if player list is empty, (1-5 players)
				if(!(num < 0)) {
					
					for(int i=0; i<jlabels.length; i++) {
						jlabels[i] = new JLabel(team[i].getPlayer());
						jlabels[i].setBounds(50, 25+incr, 300, 100);
						jlabels[i].setFont(new Font("Helvetica", Font.PLAIN, 20));
						incr = incr + 25;
						panelbtmSign.add(jlabels[i]);
					}
				}
				panelbtmSign.validate();
				panelbtmSign.repaint();
				
			break;
			
			case "RP":	//Release Player
				
				jlabels = new JLabel[num];
				//System.out.println("inside RP(jlabels length) " + jlabels.length + " " + num);
				
				//this prints the Free Agent list into 2 columns 
				if(team == obj.FreeAgents && jlabels.length > 5){
					
					int remainder = jlabels.length - 5;
					int tempF = jlabels.length-remainder;	//first 5
					
					for(int i=0; i<tempF; i++) {
						jlabels[i] = new JLabel(team[i].getPlayer());
						jlabels[i].setBounds(50, 25+incr, 300, 100);
						jlabels[i].setFont(new Font("Helvetica", Font.PLAIN, 20));
						incr = incr + 25;
						panelbtmRelease.add(jlabels[i]);
					}
					
					incr = 0;	//resets incr
					
					for(int j=5; j<jlabels.length; j++) {
						jlabels[j] = new JLabel(team[j].getPlayer());
						jlabels[j].setBounds(220, 25+incr, 300, 100);
						jlabels[j].setFont(new Font("Helvetica", Font.PLAIN, 20));
						incr = incr + 25;
						panelbtmRelease.add(jlabels[j]);
					}
					
					panelbtmRelease.validate();
					panelbtmRelease.repaint();
					break;
				}
				
				//checks if player list is empty, (1-5 players)
				if(!(num < 0)) {
					
					for(int i=0; i<jlabels.length; i++) {
						jlabels[i] = new JLabel(team[i].getPlayer());
						jlabels[i].setBounds(50, 25+incr, 300, 100);
						jlabels[i].setFont(new Font("Helvetica", Font.PLAIN, 20));
						incr = incr + 25;
						panelbtmRelease.add(jlabels[i]);
					}
				}

				panelbtmRelease.validate();
				panelbtmRelease.repaint();
			break;
		
			default:
			break;
		}
		
	}
	
	//resets the players names and panels, prevents overlapping
	public void resetSignPanel(int num) {
			
		//System.out.println("inside reset panel: num" + num);
		//System.out.println("Jalbels length " + jlabels.length);
			
		for(int i=0; i<jlabels.length; i++) {
			jlabels[i].setText("");
		}	
	}
	
	//Create the frame
    public DashBoard() throws FileNotFoundException {
    	
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setBounds(500, 100, 500, 700);
    	setResizable(false);
    	contentPane = new JPanel();
    	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    	contentPane.setBackground(Color.WHITE);
        setContentPane(contentPane);
        contentPane.setLayout(null);

        layeredPane = new JLayeredPane();
		layeredPane.setBounds(24, 265, 445, 366);
		contentPane.add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		//calls method to personalize the UI
		setBackground();
		
		panelTeam = new JPanel();
		layeredPane.add(panelTeam, "name_92965632330992");
		panelTeam.setLayout(null);
		panelTeam.setBackground(Color.WHITE);	
		
		panelLeague = new JPanel();
		layeredPane.add(panelLeague, "name_92965632330998");
		panelLeague.setLayout(null);
		panelLeague.setBackground(Color.WHITE);
		
		panelSign = new JPanel();
		layeredPane.add(panelSign, "name_93493465894277");
		panelSign.setLayout(null);
		panelSign.setBackground(Color.DARK_GRAY);
		
		//bottom panel that displays players
		panelbtmSign = new JPanel();
		panelSign.add(panelbtmSign, "name_93497968019407");
		panelbtmSign.setLayout(null);
		panelbtmSign.setBounds(10, 161, 425, 195);
		panelbtmSign.setBackground(Color.WHITE);
		
		panelRelease = new JPanel();
		layeredPane.add(panelRelease, "name_93497968019405");
		panelRelease.setLayout(null);
		panelRelease.setBackground(Color.DARK_GRAY);
		
		//bottom panel that displays players
		panelbtmRelease = new JPanel();
		panelRelease.add(panelbtmRelease, "name_93497968019406");
		panelbtmRelease.setLayout(null);
		panelbtmRelease.setBounds(10, 161, 425, 195);
		panelbtmRelease.setBackground(Color.WHITE);
		
		JLabel lblHeader1 = new JLabel("NBA Portal");
		lblHeader1.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeader1.setFont(new Font("Helvetica", Font.BOLD, 40));
		lblHeader1.setForeground(Color.WHITE);
		lblHeader1.setBounds(50, 30, 400, 50);
		contentPane.add(lblHeader1);
		
		JLabel logo = new JLabel("");		
		ImageIcon img = new ImageIcon(this.getClass().getResource("/NBAlogo.png"));
		Image temp = img.getImage();
		Image modified = temp.getScaledInstance(60, 125, java.awt.Image.SCALE_SMOOTH);
		img = new ImageIcon(modified);
		logo.setIcon(img);
		logo.setBounds(6, 12, 60, 125);
		contentPane.add(logo);
		
		JLabel lblTeamHeader = new JLabel("Welcome " + UserLogin.teamInput + " Owner!");
		lblTeamHeader.setHorizontalAlignment(SwingConstants.LEFT);
		lblTeamHeader.setFont(new Font("Helvetica", Font.PLAIN, 20));
		lblTeamHeader.setBounds(100, 100, 400, 30);
		lblTeamHeader.setForeground(Color.WHITE);
		contentPane.add(lblTeamHeader);
		
		lblTeamName = new JLabel("The " + UserLogin.teamInput);
		lblTeamName.setVisible(false);
		lblTeamName.setHorizontalAlignment(SwingConstants.CENTER);
		lblTeamName.setFont(new Font("Khmer MN", Font.BOLD, 30));
		lblTeamName.setBounds(30, -175, 400, 400);
		panelTeam.add(lblTeamName);
		
		JButton btnSignOut = new JButton("Sign Out");
		btnSignOut.setBounds(24, 646, 180, 29);
		contentPane.add(btnSignOut);
		btnSignOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFrame frame = new JFrame("Sign Out");
				if (JOptionPane.showConfirmDialog(frame, "Are you sure you want to sign-out?", "NBA Portal", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION){
					dispose();
					UserLogin acct = new UserLogin();
					acct.setTitle("Student-Login");
					acct.setVisible(true);
				}
			}
		});
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(289, 646, 180, 29);
		contentPane.add(btnExit);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFrame frame = new JFrame("Exit");
				if (JOptionPane.showConfirmDialog(frame, "Confirm if you want to exit", "NBA Portal", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION){
					System.exit(0);
				}
			}
		});
		
		// **                    ** //
		// ** Code for View Team ** //
		// **                    ** //
		lblStartingNum = new JLabel();
		lblStartingNum.setVisible(false);
		lblStartingNum.setHorizontalAlignment(SwingConstants.LEFT);
		lblStartingNum.setFont(new Font("Apple Chancery", Font.PLAIN, 20));
		lblStartingNum.setBounds(25, -140, 400, 400);
		panelTeam.add(lblStartingNum);
		
		JLabel lblPlayer1 = new JLabel("");
		lblPlayer1.setHorizontalAlignment(SwingConstants.LEFT);
		lblPlayer1.setBounds(50, -100, 400, 400);
		panelTeam.add(lblPlayer1);
		
		JLabel lblPlayer2 = new JLabel("");
		lblPlayer2.setHorizontalAlignment(SwingConstants.LEFT);
		lblPlayer2.setBounds(50, -50, 400, 400);
		panelTeam.add(lblPlayer2);
		
		JLabel lblPlayer3 = new JLabel("");
		lblPlayer3.setHorizontalAlignment(SwingConstants.LEFT);
		lblPlayer3.setBounds(50, 0, 400, 400);
		panelTeam.add(lblPlayer3);
		
		JLabel lblPlayer4 = new JLabel("");
		lblPlayer4.setHorizontalAlignment(SwingConstants.LEFT);
		lblPlayer4.setBounds(50, 50, 400, 400);
		panelTeam.add(lblPlayer4);
		
		JLabel lblPlayer5 = new JLabel("");
		lblPlayer5.setHorizontalAlignment(SwingConstants.LEFT);
		lblPlayer5.setBounds(50, 100, 400, 400);
		panelTeam.add(lblPlayer5);
		
		btnViewteam = new JButton("View Team");
		setVisible(true);
		btnViewteam.setBounds(69, 159, 135, 35);
		contentPane.add(btnViewteam);
		btnViewteam.setFont(new Font("ITF Devanagari", Font.PLAIN, 15));
		btnViewteam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int num;
				Player[] teamN = new Player[5];
				decision = "VT";	//viewTeam
				
				//resets the text fields and error messages
				MessageSign.setVisible(false);
				MessageRelease.setVisible(false);
				signPlayertxt.setText("");
				releasePlayertxt.setText("");
				
				switchPanel(panelTeam);

				switch(UserLogin.teamInput) {
					case "Boston Celtics": 
						num = obj.Celtics.length;
						lblStartingNum.setText("The Celtics Starting " + num);
						teamN = obj.Celtics;
						lblTeamName.setForeground(new Color(0x009900));
						//set image
						break;
						
					case "Brooklyn Nets":
						num = obj.Nets.length;
						lblStartingNum.setText("The Nets Starting " + num);
						teamN = obj.Nets;
						lblTeamName.setForeground(Color.black);
						//set image
						break;
						
					case "Chicago Bulls":
						num = obj.Bulls.length;
						lblStartingNum.setText("The Bulls Starting " + num);
						teamN = obj.Bulls;
						lblTeamName.setForeground(Color.RED);
						//set image
						break;
						
					case "Golden State Warriors":
						num = obj.Warriors.length;
						lblStartingNum.setText("The Warriors Starting " + num);
						teamN = obj.Warriors;
						lblTeamName.setForeground(Color.blue);
						//set image
						break;
						
					case "Los Angeles Lakers":
						num = obj.Lakers.length;
						lblStartingNum.setText("The Lakers Starting " + num);
						teamN = obj.Lakers;
						lblTeamName.setForeground(new Color(0x6600CC));
						//set image
						break;
					
					//FA
					default:
						num = obj.FreeAgents.length;
						lblStartingNum.setText("The FreeAgents Starting " + num);
						teamN = obj.FreeAgents;
						lblTeamName.setForeground(Color.DARK_GRAY);
						//set image
						break;	
				}
				
				lblTeamName.setVisible(true);
				lblStartingNum.setVisible(true);
					
				//once the jlabels have been initiated, this will reset the values so there's no overlap
				if(counter > 0)
					resetSignPanel(num);
				
				CreateLabelsDynamically(num, teamN, decision);
			}
		});
		
		// **                      ** //
		// ** Code for View League ** //
		// **                      ** //
		btnViewleague = new JButton("View League");
		btnViewleague.setBounds(69, 200, 135, 35);
		contentPane.add(btnViewleague);
		btnViewleague.setFont(new Font("ITF Devanagari", Font.PLAIN, 15));
		btnViewleague.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int num = 0;
				Player[] teamN = new Player[5];
				decision = "VL" ;	//viewLeague
				
				//resets the text fields and error messages
				MessageSign.setVisible(false);
				MessageRelease.setVisible(false);
				signPlayertxt.setText("");
				releasePlayertxt.setText("");
				
				switchPanel(panelLeague);
				
				//once the jlabels have been initiated, this will reset the values so there's no overlap
				if(counter > 0)
					resetSignPanel(num);
				
				CreateLabelsDynamically(num, teamN, decision);
			}
		});
		
		JButton btnZA = new JButton("Z-A");	//create button early in order to reference
		JButton btnAZ = new JButton("A-Z");
		btnAZ.setBounds(0, 0, 40, 35);
		btnAZ.setForeground(Color.DARK_GRAY);
		panelLeague.add(btnAZ);
		btnAZ.setForeground(Color.BLUE);
		btnAZ.setFont(new Font("ITF Devanagari", Font.PLAIN, 12));
		btnAZ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int num = 0;
				Player[] teamN = new Player[5];
				alphabetical = true;
				decision = "VL" ;	//viewLeague
				
				btnAZ.setForeground(Color.blue);
				btnZA.setForeground(Color.DARK_GRAY);

				//once the jlabels have been initiated, this will reset the values so there's no overlap
				if(counter > 0)
					resetSignPanel(num);
				
				//organizes list A to Z
				Collections.sort(players);
				
				CreateLabelsDynamically(num, teamN, decision);
			}
		});
		
		//Button ZA Here
		btnZA.setBounds(40, 0, 40, 35);
		btnZA.setForeground(Color.DARK_GRAY);
		panelLeague.add(btnZA);
		btnZA.setFont(new Font("ITF Devanagari", Font.PLAIN, 12));
		btnZA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				List<String> tempPlayers = new ArrayList(30);
				int num = 0;
				Player[] teamN = new Player[5];
				decision = "VL" ;	//viewLeague
				
				btnAZ.setForeground(Color.DARK_GRAY);
				btnZA.setForeground(Color.blue);
				
				//once the jlabels have been initiated, this will reset the values so there's no overlap
				if(counter > 0)
					resetSignPanel(num);
				
				while(alphabetical == true){
					
					//organizes list A to Z
					Collections.sort(players);
					
					//creates a new reversed list, Z-A
					for(int j=players.size(); j>0; j--) {
						tempPlayers.add(players.get(j-1));
					}

					players = tempPlayers;
					alphabetical = false;
				}
				
				CreateLabelsDynamically(num, teamN, decision);
			}
		});
		
		JLabel lbleagueHeader = new JLabel("Browse the League");
		lbleagueHeader.setFont(new Font("Helvetica", Font.CENTER_BASELINE, 20));
		lbleagueHeader.setBounds(130, 7, 275, 30);
		panelLeague.add(lbleagueHeader);
		
		
		// **                      ** //
		// ** Code for Sign Player ** //
		// **                      ** //
		JLabel lblSignPlayer = new JLabel("Free Agency Hub");
		lblSignPlayer.setFont(new Font("Helvetica", Font.CENTER_BASELINE, 25));
		lblSignPlayer.setBounds(30, 20, 275, 30);
		panelSign.add(lblSignPlayer);
		
		JSeparator separatorS = new JSeparator();
		separatorS.setBackground(Color.DARK_GRAY);
		separatorS.setBounds(15, 60, 350, 20);
		panelSign.add(separatorS);
		
		signPlayertxt = new JTextField("");
		signPlayertxt.setForeground(Color.DARK_GRAY);
		signPlayertxt.setFont(new Font("Helvetica", Font.ITALIC, 15));
		signPlayertxt.setBounds(55, 85, 250, 35);
		panelSign.add(signPlayertxt);
		signPlayertxt.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				
				if(signPlayertxt.getText().equals("Enter a player to sign")){
					signPlayertxt.setText("");
				}
			}

			public void focusLost(FocusEvent e) {
				
				if(signPlayertxt.getText().equals("")){
					signPlayertxt.setText("Enter a player to sign");
				}
			}
		});
		
		//top-overview Sign Player Button
		btnSignplayer = new JButton("Sign Player");
		btnSignplayer.setBounds(291, 159, 135, 35);
		contentPane.add(btnSignplayer);
		btnSignplayer.setFont(new Font("ITF Devanagari", Font.PLAIN, 15));
		btnSignplayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				MessageSign.setVisible(false);
				MessageRelease.setVisible(false);
				releasePlayertxt.setText("");
				signPlayertxt.setText("Enter a player to sign");
				
				switchPanel(panelSign);
				decision = "SP";	//Sign Player

			}
		});
		
		JToggleButton tglSwitchTeamS = new JToggleButton("View Free Agency");
		tglSwitchTeamS.setFont(new Font("Helvetica", Font.PLAIN, 15));
		tglSwitchTeamS.setBounds(15, 9, 150, 40);
		panelbtmSign.add(tglSwitchTeamS);
		tglSwitchTeamS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int num;
				Player[] teamN = new Player[5];
				
				tglSwitchTeamS.setText(UserLogin.teamName);
				tglSwitchTeamS.setFont(new Font("Helvetica", Font.PLAIN, 20));
				
				if(tglSwitchTeamS.isSelected()){
					
					tglSwitchTeamS.setText("Free Agency");
					num = obj.FreeAgents.length;
					teamN = obj.FreeAgents;
					tglSwitchTeamS.setForeground(Color.DARK_GRAY);
					//System.out.println("FAnum" + num);
					//System.out.println("FA length" + obj.FreeAgents.length);
					
				} else{
				
					switch(UserLogin.teamInput) {
						case "Boston Celtics": 
							num = obj.Celtics.length;
							teamN = obj.Celtics;
							tglSwitchTeamS.setForeground(new Color(0x009900));
							break;
							
						case "Brooklyn Nets":
							num = obj.Nets.length;
							teamN = obj.Nets;
							tglSwitchTeamS.setForeground(Color.black);
							break;
							
						case "Chicago Bulls":
							num = obj.Bulls.length;
							teamN = obj.Bulls;
							tglSwitchTeamS.setForeground(Color.RED);
							break;
							
						case "Golden State Warriors":
							num = obj.Warriors.length;
							teamN = obj.Warriors;
							tglSwitchTeamS.setForeground(Color.blue);
							break;
							
						case "Los Angeles Lakers":
							num = obj.Lakers.length;
							teamN = obj.Lakers;
							tglSwitchTeamS.setForeground(new Color(0x6600CC));
							break;
							
						
						case "Free Agents":
							num = obj.FreeAgents.length;
							teamN = obj.FreeAgents;
							tglSwitchTeamS.setForeground(Color.BLUE);
							break;
						
						//FA
						default:
							
							System.out.println("tgl-sign default");
							num = obj.FreeAgents.length;
							teamN = obj.FreeAgents;
							tglSwitchTeamS.setForeground(Color.DARK_GRAY);
							break;	
					}
				}
				
				//once the jlabels have been initiated, this will reset the values so there's no overlap
				if(counter > 0)
					resetSignPanel(num);
				
				CreateLabelsDynamically(num, teamN, decision);
			}
		});
		
		MessageSign = new JLabel("");
		MessageSign.setFont(new Font("Times New Roman", Font.BOLD, 15));
		MessageSign.setVisible(false);
		panelSign.add(MessageSign);
		
		JButton btnsignP = new JButton();	
		ImageIcon imgS = new ImageIcon(this.getClass().getResource("/addPlayer.png"));
		Image tempS = imgS.getImage();
		Image modifiedS = tempS.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
		imgS = new ImageIcon(modifiedS);
		btnsignP.setIcon(imgS);
		btnsignP.setBounds(305, 85, 40, 30);
		btnsignP.setOpaque(true);
		btnsignP.setBorderPainted(false);
		btnsignP.setBackground(Color.WHITE);
		panelSign.add(btnsignP);
		btnsignP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MessageSign.setVisible(false);	
				signPlayer = signPlayertxt.getText();
				
				//checks if team is full with 5 players
				if(obj.checkTeams() == true){
					
					//checks if player exists in league
					if(obj.checkPlayer(signPlayer) == true){
						
						try {
							if(obj.signPlayer(signPlayer) == true){
								obj.signPlayer(signPlayer);
								MessageSign.setText("You have successfully signed " + signPlayer);
								MessageSign.setForeground(new Color(0x009900));
								MessageSign.setBounds(75, 125, 400, 16);
								MessageSign.setVisible(true);
							}else{
								MessageSign.setText(signPlayer + " is currently signed to a team");
								MessageSign.setForeground(Color.RED);
								MessageSign.setBounds(75, 125, 400, 16);
								MessageSign.setVisible(true);
							}
							
						} catch (FileNotFoundException e1) {
							e1.printStackTrace();
						}
									
					}else {
						JOptionPane.showMessageDialog(btnsignP, "Player does not exist. Please try again.");
					}
					
				}else {	//indicated the current team has the maximum of 5 players(not including the FA team)
					MessageSign.setText("Drop a player before adding to a full roster");
					MessageSign.setForeground(Color.RED);
					MessageSign.setBounds(75, 125, 400, 16);
					MessageSign.setVisible(true);
				}
			}
		});
		
		JLabel bgtopSign = new JLabel("");
		bgtopSign.setOpaque(true);
		bgtopSign.setBackground(Color.white);
		bgtopSign.setBounds(10, 10, 425, 141);
		panelSign.add(bgtopSign);
		
		bgbottomSign = new JLabel("");
		bgbottomSign.setOpaque(true);
		bgbottomSign.setBackground(Color.white);
		bgbottomSign.setBounds(10, 161, 425, 195);
		panelSign.add(bgbottomSign);
		
		JLabel bgSign = new JLabel("");
		bgSign.setOpaque(true);
		bgSign.setBackground(Color.DARK_GRAY);
		bgSign.setBounds(0, 0, 445, 151);
		panelSign.add(bgSign);
		
		
		// **                         ** //
		// ** Code for Release Player ** //
		// **                         ** //
		JLabel lblReleasePlayer = new JLabel("Manage your team options");
		lblReleasePlayer.setFont(new Font("Helvetica", Font.CENTER_BASELINE, 25));
		lblReleasePlayer.setBounds(30, 20, 350, 30);
		panelRelease.add(lblReleasePlayer);
		
		JSeparator separatorR = new JSeparator();
		separatorR.setBackground(Color.DARK_GRAY);
		separatorR.setBounds(15, 60, 350, 20);
		panelRelease.add(separatorR);
		
		releasePlayertxt = new JTextField("");
		releasePlayertxt.setForeground(Color.DARK_GRAY);
		releasePlayertxt.setFont(new Font("Helvetica", Font.ITALIC, 15));
		releasePlayertxt.setBounds(55, 85, 250, 35);
		panelRelease.add(releasePlayertxt);
		releasePlayertxt.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				
				if(releasePlayertxt.getText().equals("Enter a player to release")){
					releasePlayertxt.setText("");
				}
			}

			public void focusLost(FocusEvent e) {
				
				if(releasePlayertxt.getText().equals("")){
					releasePlayertxt.setText("Enter a player to release");
				}
			}
		});

		//top-overview Release Player Button
		btnReleaseplayer = new JButton("Release Player");
		btnReleaseplayer.setBounds(291, 200, 135, 35);
		contentPane.add(btnReleaseplayer);
		btnReleaseplayer.setFont(new Font("ITF Devanagari", Font.PLAIN, 15));
		btnReleaseplayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MessageSign.setVisible(false);
				MessageRelease.setVisible(false);
				signPlayertxt.setText("");
				
				releasePlayertxt.setText("Enter a player to release");
				
				switchPanel(panelRelease);
				decision = "RP";	//Release Player
			
			}
		});
		
		MessageRelease = new JLabel("");
		MessageRelease.setFont(new Font("Times New Roman", Font.BOLD, 15));
		MessageRelease.setVisible(false);
		panelRelease.add(MessageRelease);
		
		JButton btnreleaseP = new JButton();	
		ImageIcon imgR = new ImageIcon(this.getClass().getResource("/removePlayer.png"));
		Image tempR = imgR.getImage();
		Image modifiedR = tempR.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
		imgR = new ImageIcon(modifiedR);
		btnreleaseP.setIcon(imgR);
		btnreleaseP.setBounds(305, 85, 40, 30);
		btnreleaseP.setOpaque(true);
		btnreleaseP.setBorderPainted(false);
		btnreleaseP.setBackground(Color.WHITE);
		panelRelease.add(btnreleaseP);
		btnreleaseP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MessageRelease.setVisible(false);
				releasePlayer = releasePlayertxt.getText();
				
				//checks if player exists in league
				if(obj.checkPlayer(releasePlayer) == true){
					
					try {
						
						if(obj.releasePlayer(releasePlayer) == true){
							obj.releasePlayer(releasePlayer);
							MessageRelease.setText("You have successfully released " + releasePlayer);
							MessageRelease.setForeground(new Color(0x009900));
							MessageRelease.setBounds(75, 125, 400, 16);
							MessageRelease.setVisible(true);
						}else {
							MessageRelease.setText(releasePlayer + " does not belong to your team");
							MessageRelease.setForeground(Color.RED);
							MessageRelease.setBounds(75, 125, 400, 16);
							MessageRelease.setVisible(true);
						}
						
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}				
				}else {
					JOptionPane.showMessageDialog(btnreleaseP, "Player does not exist. Please try again.");
				}
			}
		});
		
		JLabel bgtopRelease = new JLabel("");
		bgtopRelease.setOpaque(true);
		bgtopRelease.setBackground(Color.white);
		bgtopRelease.setBounds(10, 10, 425, 141);
		panelRelease.add(bgtopRelease);
		
		JToggleButton tglSwitchTeamR = new JToggleButton("View Free Agency");
		tglSwitchTeamR.setFont(new Font("Helvetica", Font.PLAIN, 15));
		tglSwitchTeamR.setBounds(15, 9, 150, 40);
		panelbtmRelease.add(tglSwitchTeamR);
		tglSwitchTeamR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int num = 0;
				Player[] teamN;
				
				tglSwitchTeamR.setText(UserLogin.teamName);
				tglSwitchTeamR.setFont(new Font("Helvetica", Font.PLAIN, 20));
				
				if(tglSwitchTeamR.isSelected()){
					
					tglSwitchTeamR.setText("Free Agency");
					num = obj.FreeAgents.length;
					teamN = obj.FreeAgents;
					tglSwitchTeamR.setForeground(Color.DARK_GRAY);
					//System.out.println("FAnum" + num);
					//System.out.println("FA length" + obj.FreeAgents.length);
					
				} else{
				
					switch(UserLogin.teamInput) {
						case "Boston Celtics": 
							
							num = obj.Celtics.length;
							teamN = obj.Celtics;	
							tglSwitchTeamR.setForeground(new Color(0x009900));
//							System.out.println("num" + num);
//							System.out.println("Celtics length" + obj.Celtics.length);
							break;
							
						case "Brooklyn Nets":
							num = obj.Nets.length;
							teamN = obj.Nets;
							tglSwitchTeamR.setForeground(Color.black);
							break;
							
						case "Chicago Bulls":
							num = obj.Bulls.length;
							teamN = obj.Bulls;
							tglSwitchTeamR.setForeground(Color.RED);
							break;
							
						case "Golden State Warriors":
							num = obj.Warriors.length;
							teamN = obj.Warriors;
							tglSwitchTeamR.setForeground(Color.blue);
							break;
							
						case "Los Angeles Lakers":
							num = obj.Lakers.length;
							teamN = obj.Lakers;
							tglSwitchTeamR.setForeground(new Color(0x6600CC));
							break;
							
						
						case "Free Agents":
							num = obj.FreeAgents.length;
							teamN = obj.FreeAgents;
							tglSwitchTeamR.setForeground(Color.BLUE);
							break;
						
						//FA
						default:
							System.out.println("tgl-release default");
							num = obj.FreeAgents.length;
							teamN = obj.FreeAgents;
							tglSwitchTeamR.setForeground(Color.DARK_GRAY);
							break;	
					}
				}
				
				//once the jlabels have been initiated, this will reset the values so there's no overlap
				if(counter > 0)
					resetSignPanel(num);
				
				CreateLabelsDynamically(num, teamN, decision);
			}
		});
		
		//navy blue on upper panel
		JLabel bgColor = new JLabel("");
		bgColor.setOpaque(true);
		bgColor.setHorizontalAlignment(SwingConstants.CENTER);
		bgColor.setBackground(new Color(0x041C2C));
		bgColor.setBounds(0, 0, 500, 250);
		contentPane.add(bgColor);
		
		//gray background footer
		JLabel footer = new JLabel("");
		footer.setOpaque(true);
		footer.setBackground(Color.DARK_GRAY);
		footer.setBounds(0, 640, 500, 40);
		contentPane.add(footer);
		
		contentPane.add(outline);
		
    }// ends Dashboard()
}
