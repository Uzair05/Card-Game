import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


/**
 * @author Uzair Bin Colangoy Asim; 3035603071
 * @version 12.0
 * @since 08/12/2019
 */

public class Main {
	private String path = "./Images/";
	
	private JFrame frame = new JFrame();

	private int currentCash = 100;
	private int cardChanges = 0; //reset on new game

	private JButton startButton = new JButton("Start");
	private JButton resultButton = new JButton("Results");

	private JLabel dealerCard1 = new JLabel();
	private JLabel dealerCard2 = new JLabel();
	private JLabel dealerCard3 = new JLabel();
	private JLabel playerCard1 = new JLabel();
	private JLabel playerCard2 = new JLabel();
	private JLabel playerCard3 = new JLabel();

	private	JButton replaceCard1 = new JButton("Replace Card 1");
	private JButton replaceCard2 = new JButton("Replace Card 2");
	private JButton replaceCard3 = new JButton("Replace Card 3");



	private String[] cards = {"card_11.gif","card_110.gif","card_111.gif","card_112.gif","card_113.gif","card_12.gif","card_13.gif","card_14.gif","card_15.gif","card_16.gif","card_17.gif","card_18.gif","card_19.gif","card_21.gif","card_210.gif","card_211.gif","card_212.gif","card_213.gif","card_22.gif","card_23.gif","card_24.gif","card_25.gif","card_26.gif","card_27.gif","card_28.gif","card_29.gif","card_31.gif","card_310.gif","card_311.gif","card_312.gif","card_313.gif","card_32.gif","card_33.gif","card_34.gif","card_35.gif","card_36.gif","card_37.gif","card_38.gif","card_39.gif","card_41.gif","card_410.gif","card_411.gif","card_412.gif","card_413.gif","card_42.gif","card_43.gif","card_44.gif","card_45.gif","card_46.gif","card_47.gif","card_48.gif","card_49.gif"};
	private String[] specialCards = {"card_111.gif","card_112.gif", "card_113.gif", "card_211.gif", "card_212.gif", "card_213.gif", "card_311.gif", "card_312.gif", "card_313.gif", "card_411.gif", "card_412.gif", "card_413.gif"}; //To fill
	private int[] chosenCards = {0,1,2,3,4,5};


	private JLabel gamble_info = new JLabel(this.setNoticeLabel("0"));
	private JLabel important_messages = new JLabel();

	private JTextField txt_inputbet = new JTextField(10);
	private ImageIcon cardBack = new ImageIcon(path + "card_back.gif");


	/**
	 *
	 * @param args
	 * Initializes and starts the program
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Main gui = new Main();
		gui.go();
	}

	/**
	 * Main public void concerning this program
	 * It creates and establishes the game and all its elements
	 * Main platform for the GUI
	 *
	 */
	public void go() {

		this.shuffle();
		JPanel mainPanel = new JPanel(); //First Panel; Contains All the Lower Level panels

		mainPanel.setBackground(Color.yellow);
		mainPanel.setLayout(new GridLayout(5,1));

		JPanel dealerPanel = new JPanel();
		JPanel playerPanel = new JPanel();
		JPanel cardBttnPanel = new JPanel();
		JPanel toolsBttnPanel = new JPanel();
		JPanel noticesPanel = new JPanel();

		toolsBttnPanel.setBackground(Color.orange);
		noticesPanel.setBackground(Color.orange);
		cardBttnPanel.setBackground(Color.pink);
		dealerPanel.setBackground(Color.pink);
		playerPanel.setBackground(Color.pink);
		dealerPanel.setLayout(new GridLayout(1,3));
		playerPanel.setLayout(new GridLayout(1,3));
		cardBttnPanel.setLayout(new BoxLayout(cardBttnPanel, BoxLayout.X_AXIS));
		toolsBttnPanel.setLayout(new BoxLayout(toolsBttnPanel, BoxLayout.X_AXIS));





		this.dealerCard1.setIcon(this.cardBack);
		this.dealerCard2.setIcon(this.cardBack);
		this.dealerCard3.setIcon(this.cardBack);

		this.playerCard1.setIcon(this.cardBack);
		this.playerCard2.setIcon(this.cardBack);
		this.playerCard3.setIcon(this.cardBack);

		dealerPanel.add(this.dealerCard1);
		dealerPanel.add(this.dealerCard2);
		dealerPanel.add(this.dealerCard3);

		playerPanel.add(this.playerCard1);
		playerPanel.add(this.playerCard2);
		playerPanel.add(this.playerCard3);

		mainPanel.add(dealerPanel);
		mainPanel.add(playerPanel);


		this.replaceCard1.addActionListener(new replaceCard1());
		this.replaceCard2.addActionListener(new replaceCard2());
		this.replaceCard3.addActionListener(new replaceCard3());
		cardBttnPanel.setLayout(new GridLayout(1,3));
		cardBttnPanel.add(this.replaceCard1);
		cardBttnPanel.add(this.replaceCard2);
		cardBttnPanel.add(this.replaceCard3);

		JLabel bet_Info = new JLabel("Bet $: ");
		bet_Info.setOpaque(true);
		bet_Info.setBackground(Color.white);

		this.replaceCard1.setEnabled(false);
		this.replaceCard2.setEnabled(false);
		this.replaceCard3.setEnabled(false);

		this.startButton.addActionListener(new startTask());
		this.resultButton.addActionListener(new resultTask());
		this.resultButton.setEnabled(false);
		toolsBttnPanel.add(bet_Info);


		toolsBttnPanel.add(this.txt_inputbet);
		toolsBttnPanel.add(this.startButton);
		toolsBttnPanel.add(this.resultButton);


		noticesPanel.add(this.gamble_info, BorderLayout.CENTER);
		noticesPanel.add(this.important_messages, BorderLayout.CENTER);
		mainPanel.add(noticesPanel);

		JMenuBar menuBar = new JMenuBar();
		JMenu menu1 = new JMenu("Control");
		JMenu menu2 = new JMenu("Help");
		JMenuItem menuItem1 = new JMenuItem("Exit");
		JMenuItem menuItem2 = new JMenuItem("Rules");
		menu2.add(menuItem2);
		menuItem2.addActionListener(new askingHelp());
		menuItem1.addActionListener(new exitCase());
		menu1.add(menuItem1);
		menuBar.add(menu1);
		menuBar.add(menu2);

		mainPanel.add(dealerPanel);
		mainPanel.add(playerPanel);
		mainPanel.add(cardBttnPanel);
		mainPanel.add(toolsBttnPanel);
		mainPanel.add(noticesPanel);

		this.frame.setJMenuBar(menuBar);
		this.frame.add(mainPanel);
		this.frame.setVisible(true);
		this.frame.setSize(450, 600);
		this.frame.setTitle("A Simple Card Game!");
	}


	private String setNoticeLabel(String bet){
		String s = "Please place your bet. Amount of money you have is $" + this.currentCash +".";
		if (this.verifyBet(bet)==true) {
			if (this.currentCash > 0) {
				s = "Your Current Bet is $" + bet + ". Amount of Money you have is $" + this.currentCash + ".";
			}else {
				s = "";
				this.errorMessage("You have no more money. Please Start a new game!");
				important_messages.setText("You have no more money. Please Start a new game!");
			}
		}
		return s;
	}
	/**
	 *
	 * @author Uzair Bin Colangoy Asim; 3035603071
	 * Inner class which implements ActionListener
	 * Used as an ActionListener for the JButton variable 'startButton'.
	 * Completes the actions of when the game should show start.
	 *
	 */
	class startTask implements ActionListener{
		/**
		 * @param event
		 * Actions to complete when button is pressed
		 * Extention of 'actionPerformed' method
		 */
		public void actionPerformed(ActionEvent event) {
			String bet = "0";
			bet = txt_inputbet.getText();
			if (bet.length() > 0) {
				if (verifyBet(bet) == true) {
					important_messages.setText("");
					resultButton.setEnabled(true);
					startButton.setEnabled(false);
					replaceCard1.setEnabled(true);
					replaceCard2.setEnabled(true);
					replaceCard3.setEnabled(true);
					gamble_info.setText(setNoticeLabel(bet));
				}

			}
		}
	}
	/**
	 *
	 * @author Uzair Bin Colangoy Asim; 3035603071
	 * Inner class which implements ActionListener
	 * Used as an ActionListener for the JButton variable 'resultButton'.
	 * Completes the actions of when the game should show results and amend scores.
	 *
	 */
	class resultTask implements ActionListener{
		/**
		 * @param event
		 * Actions to complete when button is pressed
		 * Extention of 'actionPerformed' method
		 */
		public void actionPerformed(ActionEvent event) {
			String bet = txt_inputbet.getText();
			if (bet.length() > 0) {

				dealerCard1.setIcon(new ImageIcon(path+cards[chosenCards[0]]));
				dealerCard2.setIcon(new ImageIcon(path+cards[chosenCards[1]]));
				dealerCard3.setIcon(new ImageIcon(path+cards[chosenCards[2]]));

				playerCard1.setIcon(new ImageIcon(path+cards[chosenCards[3]]));
				playerCard2.setIcon(new ImageIcon(path+cards[chosenCards[4]]));
				playerCard3.setIcon(new ImageIcon(path+cards[chosenCards[5]]));

				if (specialWon() == 1) {
					popupMessage("Congratulations, You win this round!");
					currentCash += Integer.parseInt(bet);
				}else if(specialWon() == 0) {
					popupMessage("Sorry, the dealer wins this round");
					currentCash -= Integer.parseInt(bet);
				}else {
					if (handWon() == 1) {
						popupMessage("Congratulations, You win this round!");
						currentCash += Integer.parseInt(bet);
					}else {
						popupMessage("Sorry, the dealer wins this round");
						currentCash -= Integer.parseInt(bet);
					}
				}

				txt_inputbet.setText("");
				txt_inputbet.requestFocus();
			}
			if (currentCash > 0) {
				startButton.setEnabled(true);
			}
			resultButton.setEnabled(false);
			cardChanges = 0;
			playerCard1.setIcon(new ImageIcon(path+"card_back.gif"));
			playerCard2.setIcon(new ImageIcon(path+"card_back.gif"));
			playerCard3.setIcon(new ImageIcon(path+"card_back.gif"));
			dealerCard1.setIcon(new ImageIcon(path+"card_back.gif"));
			dealerCard2.setIcon(new ImageIcon(path+"card_back.gif"));
			dealerCard3.setIcon(new ImageIcon(path+"card_back.gif"));
			shuffle();
			replaceCard1.setEnabled(false);
			replaceCard2.setEnabled(false);
			replaceCard3.setEnabled(false);
			gamble_info.setText(setNoticeLabel("0"));
			important_messages.setText("");
		}

	}

	private int handWon() {
		int i=-1;
		int dealerWon = valueOfHand(0);
		int playerWon = valueOfHand(3);

		if (dealerWon > playerWon) {
			i = 0;
		}else if (dealerWon < playerWon) {
			i = 1;
		}
		return i;
	}
	private int valueOfHand(int a) {
		int val=0;
		for(int i=a;i<(a+3);i++) {
			val+= cardValues(cards[chosenCards[i]]);
		}
		return val;
	}
	private int cardValues(String a) {
		String[] cardListed = {"card_11.gif", "card_12.gif", "card_13.gif", "card_14.gif", "card_15.gif", "card_16.gif", "card_17.gif", "card_18.gif", "card_19.gif", "card_110.gif", "card_21.gif", "card_22.gif", "card_23.gif", "card_24.gif", "card_25.gif", "card_26.gif", "card_27.gif", "card_28.gif", "card_29.gif", "card_210.gif","card_31.gif","card_32.gif","card_33.gif","card_34.gif","card_35.gif","card_36.gif","card_37.gif","card_38.gif","card_39.gif","card_310.gif","card_41.gif","card_42.gif","card_43.gif","card_44.gif","card_45.gif","card_46.gif","card_47.gif","card_48.gif","card_49.gif","card_410.gif"};
			int val = 0;

		if (isSpecial(a) == false) {
			for(int i=0;i<cardListed.length;i++){
				if (a == cardListed[i]) {
					val = (i%10);
					break;
				}
			}
		}

		return val;
	}

 	private int specialWon() {
		int i=-1;
		int dealerspecial = numberOFSpecial(0);
		int playerspecial = numberOFSpecial(3);

		if (dealerspecial > playerspecial) {
			i=0;
		}else if (dealerspecial < playerspecial) {
			i=1;
		}else {
			i=-1;
		}
		return i;
	}
	private int numberOFSpecial(int a) {
		int num = 0;
		for(int i=a;i<(a+3);i++) {
			if (isSpecial(cards[chosenCards[i]])==true) {
				num++;
			}
		}
		return num;
	}
	private boolean isSpecial(String s) {
		boolean special = false;

		for(int i=0;i<specialCards.length;i++) {
			if (specialCards[i] == s) {
				special = true;
				break;
			}
		}
		return special;
	}

	private void errorMessage(String messg) {
		important_messages.setText(messg);
		JOptionPane.showMessageDialog(
				this.frame,
				messg,
			    "Error",
			    JOptionPane.ERROR_MESSAGE);
	}
	/**
	 *
	 * @author Uzair Bin Colangoy Asim; 3035603071
	 * Inner class which implements ActionListener
	 * Used as an ActionListener for the JButton variable 'replaceCard1'.
	 * Gets the next card in the stack and replaces the first player card by that card.
	 *
	 */
	class replaceCard1 implements ActionListener{
		/**
		 * @param event
		 * Actions to complete when button is pressed
		 * Extention of 'actionPerformed' method
		 */
		public void actionPerformed(ActionEvent event) {
			if (cardChanges<2) {
				chosenCards[3] = cardChanges + 6;
			}
			cardChanges++;
			if (cardChanges == 2) {
				replaceCard1.setEnabled(false);
				replaceCard2.setEnabled(false);
				replaceCard3.setEnabled(false);
			}
			replaceCard1.setEnabled(false);
		}
	}
	/**
	 *
	 * @author Uzair Bin Colangoy Asim; 3035603071
	 * Inner class which implements ActionListener
	 * Used as an ActionListener for the JButton variable 'replaceCard2'.
	 * Gets the next card in the stack and replaces the second player card by that card.
	 *
	 */
	class replaceCard2 implements ActionListener{
		/**
		 * @param event
		 * Actions to complete when button is pressed
		 * Extention of 'actionPerformed' method
		 */
		public void actionPerformed(ActionEvent event) {
			if (cardChanges<2) {
				chosenCards[4] = cardChanges + 6;
			}
			cardChanges++;
			if (cardChanges == 2) {
				replaceCard1.setEnabled(false);
				replaceCard2.setEnabled(false);
				replaceCard3.setEnabled(false);
			}
			replaceCard2.setEnabled(false);
		}
	}
	/**
	 *
	 * @author Uzair Bin Colangoy Asim; 3035603071
	 * Inner class which implements ActionListener
	 * Used as an ActionListener for the JButton variable 'replaceCard3'.
	 * Gets the next card in the stack and replaces the third player card by that card.
	 *
	 */
	class replaceCard3 implements ActionListener{
		/**
		 * @param event
		 * Actions to complete when button is pressed
		 * Extention of 'actionPerformed' method
		 */
		public void actionPerformed(ActionEvent event) {
			if (cardChanges<2) {
				chosenCards[5] = cardChanges + 6;
			}
			cardChanges++;
			if (cardChanges == 2) {
				replaceCard1.setEnabled(false);
				replaceCard2.setEnabled(false);
				replaceCard3.setEnabled(false);
			}
			replaceCard3.setEnabled(false);
		}
	}
	private void popupMessage(String messg) {
		JOptionPane.showMessageDialog(
				this.frame,
				messg,
			    "Message",
			    JOptionPane.PLAIN_MESSAGE);
	}

	private boolean isNumeric(String strNum) {

		boolean isValid = true;

		for(int i=0;i<strNum.length();i++) {
			char a = strNum.charAt(i);
			if (((a>='0') && (a<='9')) == false) {
				isValid=false;
				break;
			}
		}
	    return isValid;
	}
	private boolean verifyBet(String bet) {
		boolean isValid = true;

		if (this.isNumeric(bet) == true) {
			double d = Double.parseDouble(bet);
			int money = this.currentCash;

			if ((d - (int)d) == 0) {
				if ((int)d >= 0) {
					if ((money - (int)d)>=0) {
						isValid = true;
					}else {
						isValid = false;
						this.errorMessage("Betting Value is insufficient");
					}
				}else {
					isValid = false;
					this.errorMessage("Betting Value is not a positive integer");
				}
			}else {
				isValid = false;
				this.errorMessage("Betting Value is not a positive Integer");
			}
		}else {
			isValid = false;
			this.errorMessage("Betting Value is not a positive integer");
		}
		return isValid;
	}
	/**
	 *
	 * @author Uzair Bin Colangoy Asim; 3035603071
	 * Inner class which implements ActionListener
	 * Used as an ActionListener for the JMenuItem variable 'rules'.
	 * Displays a dialog box explaining the rules of the game.
	 *
	 */
	class askingHelp implements ActionListener{
		private String helpMessage = "Rules to determine who has better cards:\nJ, Q, K are regarded as special cards.\nRule 1: The one with more special cards wins.\nRule 2: If both have the same number of special cards, add the face value of the other card(s) and take the remainder after dividing the sum be 10. The one with a bigger remainder wins.\nRule 3: The dealer wins if both rule 1 and 2 cannot distinguish the winner.";
		/**
		 * @param event
		 * Actions to complete when button is pressed
		 * Exention of 'actionPerformed' method
		 */
		public void actionPerformed(ActionEvent event) {
			JOptionPane.showMessageDialog(frame,
					helpMessage,
				    "Help Box",
				    JOptionPane.PLAIN_MESSAGE);
		}
	}

	/**
	 *
	 * @author Uzair Bin Colangoy Asim; 3035603071
	 * Inner class which implements ActionListener
	 * Used as an ActionListener for the JMenuItem variable 'exit'.
	 * Completes action by exiting the application
	 *
	 */
	class exitCase implements ActionListener{
		/**
		 * @param event
		 * Actions to complete when button is pressed
		 * Exention of 'actionPerformed' method
		 */
		public void actionPerformed(ActionEvent event) {
			System.exit(0);
		}
	}
	private void shuffle() {
		for(int m=0;m<cards.length;m++) {
			for(int n=0;n<(cards.length-1);n++) {
				int r = (int)(Math.random() * 101);
				if(r%2 == 0) {
					String temp = cards[n];
					cards[n]=cards[n+1];
					cards[n+1]=temp;
				}
			}
		}
	}
}
