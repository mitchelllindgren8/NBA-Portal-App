package logonSys;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import NBAapp.DashBoard;
import NBAapp.League2;
import NBAapp.NBAAdminApp;

import java.io.FileNotFoundException;

public class UserLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JButton btnExit;
    private JPanel contentPane;
    private JPanel teamPanelbg;
    private JPanel teamPanel;
    private JSeparator separator;
    
    private String branch = "lAmiBin0ld2kgVNrq4ejfnY";
	public static String teamInput = "";	//City + Team
	public static String teamCity = "";
	public static String teamName = "";
	

    //Launch the application
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	UserLogin window = new UserLogin();
                	window.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    //Create the frame
    public UserLogin() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 100, 500, 300);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setBackground(Color.WHITE);
        contentPane.setLayout(null);
        
        JLabel lblTitle = new JLabel("NBA Portal Login");
        lblTitle.setForeground(Color.BLACK);
        lblTitle.setFont(new Font("Helvetica", Font.BOLD, 27));
        lblTitle.setBounds(145, 25, 250, 31);
        contentPane.add(lblTitle);
        
        txtUsername = new JTextField("");
        txtUsername.setBounds(163, 88, 221, 26);
        contentPane.add(txtUsername);
        txtUsername.setColumns(10);

        txtPassword = new JPasswordField("");
		txtPassword.setBounds(163, 141, 221, 26);
        contentPane.add(txtPassword);
        
        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBackground(Color.BLACK);
        lblUsername.setForeground(Color.BLACK);
		lblUsername.setFont(new Font("Arial", Font.BOLD, 13));
		lblUsername.setBounds(87, 94, 64, 16);
        contentPane.add(lblUsername);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setForeground(Color.BLACK);
        lblPassword.setBackground(Color.CYAN);
		lblPassword.setFont(new Font("Arial", Font.BOLD, 13));
		lblPassword.setBounds(87, 147, 64, 16);
        contentPane.add(lblPassword);
        
        JLabel lblHint = new JLabel("Hint: Click the settings button for login help");
        lblHint.setForeground(Color.RED);
        lblHint.setFont(new Font("Times New Roman", Font.BOLD, 13));
        lblHint.setBounds(145, 175, 250, 16);
		contentPane.add(lblHint);
		lblHint.setVisible(false);
        
		JButton btnLogin = new JButton();
		btnLogin.setIcon(new ImageIcon(this.getClass().getResource("/signIn.png")));
		btnLogin.setBounds(172, 210, 160, 50);
		btnLogin.setOpaque(true);
		btnLogin.setBorderPainted(false);
		btnLogin.setBackground(Color.white);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                String username = txtUsername.getText();
                String password = txtPassword.getText();
                String tempWord = "";
                String tempCity = "";
                String tempTeam = "";
                String destination = "";
                String port = "";
                String nport = "";
                String wport = "";
                int count = 0;
                int num = 3;
                
                //parser to fix username into City+Team
                for(int i=0; i<username.length(); i++) {
                	
                	if(username.charAt(i) == '-') {
                		tempWord = tempWord + " ";
                		
                	} else if(username.charAt(i) == '_') {
                		tempCity = tempWord;
                		count++;
                		
                	} else if(count == 1){
                		tempTeam = tempTeam + username.charAt(i);
                		
                	} else {
                		tempWord = tempWord + username.charAt(i);
                	}
                }
                
                teamCity = tempCity;
                teamName = tempTeam;
                teamInput = tempCity + " " + tempTeam;
                nport = settings(num);
                wport = settingsBranch(num-1);
                port = nport + wport;
                destination = settingsDestination(num);
                destination = settingsDestination(num-1);
                
                //System.out.println("City:" + teamCity +", Team:" + tempTeam + ", City+Team:" + teamInput);
                
                try {
                	Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/NBAloginDB", destination, port);

                    PreparedStatement statement = (PreparedStatement) connection.prepareStatement("Select username, password from users where username=? and password=?");

                    statement.setString(1, username);
                    statement.setString(2, password);
                    ResultSet result = statement.executeQuery();
                    
                    if (result.next()) {
                		lblHint.setVisible(false);
                        dispose();
                        
                        DashBoard ah = new DashBoard();
                        ah.setTitle("Welcome");
                        ah.setVisible(true);
                    } else {
                		lblHint.setVisible(true);
                    	txtUsername.setText(null);
                    	txtPassword.setText(null);
                        JOptionPane.showMessageDialog(btnLogin, "Wrong Username or Password");
                    }
                } catch (SQLException | FileNotFoundException sqlException) {
                    sqlException.printStackTrace();
                }
            }
		});
		

		contentPane.add(btnLogin);
		validate();
		
		teamPanelbg = new JPanel();
		contentPane.add(teamPanelbg);
		teamPanelbg.setLayout(null);
		teamPanelbg.setBounds(388, 5, 104, 264);
		teamPanelbg.setBackground(Color.black);
		teamPanelbg.setVisible(false);
		
		teamPanel = new JPanel();
		teamPanelbg.add(teamPanel);
		teamPanel.setLayout(null);
		teamPanel.setBounds(2, 2, 100, 260);
		teamPanel.setBackground(Color.lightGray);
		teamPanel.setVisible(false);
		
		JPanel celticsPanel = new JPanel();
		teamPanel.add(celticsPanel);
		celticsPanel.setLayout(null);
		celticsPanel.setBounds(25, 10, 50, 40);
		celticsPanel.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		celticsPanel.setBackground(new Color(0x009900));
		celticsPanel.setForeground(Color.white);
		celticsPanel.addMouseListener(new MouseAdapter() { 
		     public void mousePressed(MouseEvent me) { 
		    	 txtUsername.setText("Boston_Celtics");
		    	 txtPassword.setText("Celtics123");
		     } 
		});
		
        JLabel lbCeltics = new JLabel("C");
        lbCeltics.setForeground(Color.white);
        lbCeltics.setFont(new Font("Helvetica", Font.CENTER_BASELINE, 30));
        lbCeltics.setBounds(12, 0, 40, 40);
        celticsPanel.add(lbCeltics);
		
		JPanel bullsPanel = new JPanel();
		teamPanel.add(bullsPanel);
		bullsPanel.setLayout(null);
		bullsPanel.setBounds(25, 60, 50, 40);
		bullsPanel.setBackground(Color.red);
		bullsPanel.addMouseListener(new MouseAdapter() { 
		     public void mousePressed(MouseEvent me) { 
		    	 txtUsername.setText("Chicago_Bulls");
		    	 txtPassword.setText("Bulls123"); 
		     } 
		});
		
        JLabel lbBulls = new JLabel("B");
        lbBulls.setForeground(Color.white);
        lbBulls.setFont(new Font("Helvetica", Font.CENTER_BASELINE, 30));
        lbBulls.setBounds(12, 0, 40, 40);
        bullsPanel.add(lbBulls);
		
		JPanel lakersPanel = new JPanel();
		teamPanel.add(lakersPanel);
		lakersPanel.setLayout(null);
		lakersPanel.setBounds(25, 110, 50, 40);
		lakersPanel.setBackground(Color.yellow);
		lakersPanel.addMouseListener(new MouseAdapter() { 
		     public void mousePressed(MouseEvent me) { 
		    	 txtUsername.setText("Los-Angeles_Lakers");
		    	 txtPassword.setText("Lakers123"); 
		     } 
		});
		
        JLabel lbLakers = new JLabel("L");
        lbLakers.setForeground(Color.white);
        lbLakers.setFont(new Font("Helvetica", Font.CENTER_BASELINE, 30));
        lbLakers.setBounds(12, 0, 40, 40);
        lakersPanel.add(lbLakers);

		JPanel netsPanel = new JPanel();
		teamPanel.add(netsPanel);
		netsPanel.setLayout(null);
		netsPanel.setBounds(25, 160, 50, 40);
		netsPanel.setBackground(Color.DARK_GRAY);
		netsPanel.addMouseListener(new MouseAdapter() { 
		     public void mousePressed(MouseEvent me) { 
		    	 txtUsername.setText("Brooklyn_Nets");
		    	 txtPassword.setText("Nets123"); 
		     } 
		});
		
        JLabel lbNets = new JLabel("N");
        lbNets.setForeground(Color.white);
        lbNets.setFont(new Font("Helvetica", Font.CENTER_BASELINE, 30));
        lbNets.setBounds(12, 0, 40, 40);
        netsPanel.add(lbNets);
		
		JPanel warriorsPanel = new JPanel();
		teamPanel.add(warriorsPanel);
		warriorsPanel.setLayout(null);
		warriorsPanel.setBounds(25, 210, 50, 40);
		warriorsPanel.setBackground(Color.blue);
		warriorsPanel.addMouseListener(new MouseAdapter() { 
		     public void mousePressed(MouseEvent me) { 
		    	 txtUsername.setText("Golden-State_Warriors");
		    	 txtPassword.setText("Warriors123"); 
		     } 
		});
		
        JLabel lbWarriors = new JLabel("W");
        lbWarriors.setForeground(Color.white);
        lbWarriors.setFont(new Font("Helvetica", Font.CENTER_BASELINE, 30));
        lbWarriors.setBounds(12, 0, 40, 40);
        warriorsPanel.add(lbWarriors);
		
		JButton btnSettings = new JButton("...");
		btnSettings.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		btnSettings.setBounds(473, 5, 20, 25);
		btnSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				teamPanel.setVisible(true);
				teamPanelbg.setVisible(true);
			}
		});
		contentPane.add(btnSettings);
		
		JButton settingsExit = new JButton("X");
		settingsExit.setFont(new Font("Lucida Grande", Font.PLAIN, 6));
		settingsExit.setBounds(80, 123, 15, 15);
		settingsExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				teamPanel.setVisible(false);
				teamPanelbg.setVisible(false);
			}
		});
		teamPanel.add(settingsExit);
        
		btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		btnExit.setBounds(5, 243, 117, 29);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame("Exit");
				if (JOptionPane.showConfirmDialog(frame, "Confirm if you want to exit", "NBA Portal", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION){
					System.exit(0);
				}
			}
		});
		
		contentPane.add(btnExit);
        
		separator = new JSeparator();
		separator.setBounds(6, 198, 488, 12);
		contentPane.add(separator);
    }
    
    public String settingsDestination(int num) {
    	String result = "";
    	for(int i=0; i<branch.length()-1; i++) {
    		result += branch.charAt(i);
    		i=i+num;
    	}
    	
    	return result;
    }
    
    public String settingsBranch(int num) {
    	String result = "";
    	for(int i=0; i<branch.length()-11; i++) {
    		i=i+num;
    		result += branch.charAt(i);
    	}
    	
    	return result;
    }
    
	public String settings(int num){
		
		//find the port number
		String port1 = "979";
		String port2 = "79";
		String port3 = "14";
		String port4 = "759";
		String port5 = "214";
		String port6 = "135";
		String port7 = "879";
		String port8 = "139";
		String port = "";
				
		//port id
		switch(num){
			case 1:
				port = port1+port2;
			break;
			
			case 2:
				port = port4+port8;
			break;
			
			case 3:
				port = port6+port2;
			break;
			
			case 4:
				port = port5+port3;
			break;
			
			case 5:
				port = port1+port7;
			break;
		}
		
		return port;
	}
}
