package edu.gonzaga;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BlackJack {

    ImageIcon background;
    ImageIcon startIcon = new ImageIcon("Graphics/start.png");
    ImageIcon settingsIcon = new ImageIcon("Graphics/settings.png");

    // starting Screen
    JLabel backgroundScreen = new JLabel();
    JLabel introScreen = new JLabel();
    JLabel quitScreen = new JLabel();

    Round round;
    Deck deck;
    Bet bankroll;
    boolean roundOver = false;
    boolean autoDealSetting = false;
    boolean bettingSetting = false;

    int hitCount = 1;
    int playerWinsTotal = 0;
    int dealerWinsTotal = 0;
    boolean firstRound = true;
    String playerName = "Player";
    Card playerCardNum1;
    Card playerCardNum2;
    Card dealerCardNum1;
    Card dealerCardNum2;

    JFrame startingScreenFrame;
    JFrame settingScreenFrame;
    JPanel startingScreenPanel;
    JPanel settingScreenPanel;
    JFrame blackJackScreenFrame;
    JFrame endScreenFrame;
    JPanel blackJackScreenPanel;


    JButton betButton = new JButton("Bet");
    JLabel betLabel = new JLabel("BankRoll: ");
    JTextField betAmountTextField = new JTextField();
    JTextField playerNameTextField = new JTextField();
    JButton hitButton = new JButton("Hit");
    JButton standButton = new JButton("Stand");
    JButton continueButton = new JButton("Continue");
    JButton startButton = new JButton(startIcon);
    JButton settingsButton = new JButton(settingsIcon);
    JButton quitButton = new JButton("Quit");

    JCheckBox bettingToggle = new JCheckBox();
    JCheckBox autoDeal = new JCheckBox();
    JCheckBox gamingModeToggle = new JCheckBox();
    //JCheckBox ;
    JButton returnButton = new JButton("Save and Return to Start");

    JLabel playerCard1 = new JLabel();
    JLabel playerCard2 = new JLabel();
    JLabel dealerCard1 = new JLabel();
    JLabel dealerCard2 = new JLabel();

    JLabel playerText = new JLabel("Player's Cards");
    JLabel dealerText = new JLabel("Dealer's Cards");
    JLabel playerScoreLabel = new JLabel("Player's Score: 0"); // Initializing with default score
    JLabel dealerScoreLabel = new JLabel("Dealer's Score: 0"); // Initializing with default score
    JLabel playerWinsLabel = new JLabel("Player's Wins: 0"); // Initializing with default score
    JLabel dealerWinsLabel = new JLabel("Dealer's Wins: 0"); // Initializing with default score


    String roundHighlights = "";
    JTextArea textArea = new JTextArea();

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            BlackJack app = new BlackJack();
            app.runGUI();
        });
    }

    public BlackJack() {
        deck = new Deck();
        bankroll = new Bet();
        startNextRound();

    }

    public void startNextRound() {
        round = new Round(this.deck);
        playerCardNum1 = round.getCard();
        playerCardNum2 = round.getCard();
        dealerCardNum1 = round.getDealerCard();
        dealerCardNum2 = round.getDealerCard();
        betButton.setEnabled(true);

        System.out.println("value: " + dealerCardNum1.getValue());

        round.dealerStartScore = dealerCardNum1.getValue();
        round.playerCardScore = round.playerHand.calculateScore();
        round.dealerCardScore = round.dealerHand.calculateScore();

        if (!firstRound) {
            // Clearing the panel
            blackJackScreenPanel.removeAll();
            backgroundScreen.removeAll();
            this.blackJackScreenPanel = genBlackJackGUI();

            //checks to see if we need to shuffle
            if(deck.availableCards < 10){
                deck.shuffleDeck();
                System.out.println("Shuffling...");
            }

            blackJackScreenPanel.revalidate();
            blackJackScreenPanel.repaint();

            blackJackScreenFrame.add(blackJackScreenPanel);


            hitCount = 1;
        }
        playerScoreLabel.setText(playerName + "'s Score: " + round.playerCardScore);
        dealerScoreLabel.setText("Dealer's Score: " + round.dealerStartScore);

        roundHighlights = roundHighlights + "\n" + "Starting Round...";
        textArea.setText(roundHighlights);

    }

    void runGUI() {

        System.out.println("Starting GUI");
        startingScreenGUI();

        startingScreenFrame.setVisible(true);
        System.out.println("Finished Running GUI");
    }

    void startingScreenGUI() {
        this.startingScreenFrame = new JFrame("Black Jack");
        this.startingScreenFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.startingScreenFrame.setLocation(100, 100);
        this.startingScreenPanel = genStartingScreenPanel();
        startingScreenFrame.setResizable(false);
        addButtonCallbackHandlers();
        new Background();
        settingScreenGUI();
    }

    void settingScreenGUI() {
        this.settingScreenFrame = new JFrame("Black Jack");
        this.settingScreenFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.settingScreenFrame.setLocation(100, 100);
        this.settingScreenPanel = genSettingScreenPanel();
        settingScreenFrame.setResizable(false);
        new Background();
    }

    private JPanel genSettingScreenPanel() {
        JPanel newPanel = new JPanel();
        newPanel.setLayout(null);

        background = new ImageIcon(new ImageIcon("Graphics/SettingsScreen.png").getImage().getScaledInstance(700, 500, Image.SCALE_SMOOTH));
        backgroundScreen.setIcon(background);


        newPanel.add(bettingToggle);
        newPanel.add(playerNameTextField);
        newPanel.add(autoDeal);
        newPanel.add(gamingModeToggle);
        newPanel.add(returnButton);
        newPanel.add(backgroundScreen);

//        Dimension biggerSize = new Dimension(150, 30); // Width and height in pixels
//        bettingToggle.setPreferredSize(biggerSize);

        backgroundScreen.setBounds(0, 0, 700, 500);
        bettingToggle.setBounds(460, 150, 50, 50);
        autoDeal.setBounds(460, 375, 50, 50);
        playerNameTextField.setBounds(285, 87, 200, 30);
        gamingModeToggle.setBounds(460,250,50,50);
        returnButton.setBounds(250, 450, 200, 40);

        settingScreenFrame.add(newPanel);
        settingScreenFrame.setSize(700, 525);
        settingScreenFrame.setVisible(false);
        settingScreenFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit out of application

        return newPanel;
    }

    void blackJackGUI() {

        this.blackJackScreenFrame = new JFrame("Black Jack");
        this.blackJackScreenFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.blackJackScreenFrame.setLocation(startingScreenFrame.getX(), startingScreenFrame.getY());
        this.blackJackScreenPanel = genBlackJackGUI();

        blackJackScreenFrame.setResizable(false);
        blackJackScreenFrame.setVisible(true);

        round.playRound();

    }

    private JPanel genStartingScreenPanel() {
        JPanel newPanel = new JPanel();
        newPanel.setLayout(null);

        ImageIcon introBackground = new ImageIcon(new ImageIcon("Graphics/intro.png").getImage().getScaledInstance(700, 500, Image.SCALE_SMOOTH));
        introScreen.setIcon(introBackground);

        newPanel.add(settingsButton);
        newPanel.add(startButton);
        newPanel.add(introScreen);


        introScreen.setBounds(0, 0, 700, 500);
        startButton.setBounds(200, 236, 110, 25);
        settingsButton.setBounds(400, 236, 110, 25);

        startingScreenFrame.add(newPanel);
        startingScreenFrame.setSize(700, 525);
        startingScreenFrame.setVisible(true);
        startingScreenFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit out of application

        return newPanel;
    }
    private JPanel genEndScreen() {
        JPanel endPanel = new JPanel();
        endPanel.setLayout(null);

        ImageIcon endScreenBackground = new ImageIcon(new ImageIcon("Graphics/table.png").getImage().getScaledInstance(700, 500, Image.SCALE_SMOOTH));
        quitScreen.setIcon(endScreenBackground);

        quitScreen.add(endPanel);
        quitScreen.setSize(700, 525);
        quitScreen.setVisible(true);
        blackJackScreenFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit out of application

        return endPanel;
    }

    private JPanel genBlackJackGUI() {
        JPanel newPanel = new JPanel();
        newPanel.setLayout(null);

        background = new ImageIcon(new ImageIcon("Graphics/table.png").getImage().getScaledInstance(700, 500, Image.SCALE_SMOOTH));
        backgroundScreen.setIcon(background);


        ImageIcon card1 = new ImageIcon(new ImageIcon(playerCardNum1.getCardName()).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
        ImageIcon card2 = new ImageIcon(new ImageIcon(playerCardNum2.getCardName()).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
        ImageIcon card3 = new ImageIcon(new ImageIcon(dealerCardNum1.getCardName()).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
        ImageIcon turnedOverCard = new ImageIcon(new ImageIcon("PNG-cards-1.3/card back red.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));


        playerCard1.setHorizontalTextPosition(JLabel.CENTER); // set text according to JLabel ( Left, center, or right)
        playerCard1.setVerticalTextPosition(JLabel.BOTTOM);
        playerCard1.setFont(new Font("MV Boli", Font.ITALIC, 17)); // set font of text
        playerCard1.setIconTextGap(10); // set gap of text to image either plus or minus

        playerCard1.setIcon(card1);
        playerCard2.setIcon(card2);

        dealerCard1.setHorizontalTextPosition(JLabel.CENTER); // set text according to JLabel ( Left, center, or right)
        dealerCard1.setVerticalTextPosition(JLabel.BOTTOM);
        dealerCard1.setFont(new Font("MV Boli", Font.ITALIC, 17)); // set font of text
        dealerCard1.setIconTextGap(10); // set gap of text to image either plus or minus

        dealerCard1.setIcon(card3);
        dealerCard2.setIcon(turnedOverCard);

        dealerCard1.setBounds(5, 130, 100, 100);
        dealerCard2.setBounds(120, 130, 100, 100);
        playerCard1.setBounds(5, 280, 100, 100);
        playerCard2.setBounds(120, 280, 100, 100);

        hitButton.setBounds(250, 0, 100, 25);
        standButton.setBounds(350, 0, 100, 25);
        continueButton.setBounds(450, 0, 100, 25);
        betButton.setBounds(100, 0, 76, 25);
        playerWinsLabel.setBounds(5, 0, 300, 25);
        dealerWinsLabel.setBounds(5, 25, 300, 25);
        quitButton.setBounds(0,0,100,25);

        betLabel.setBounds(0, 0, 95, 25);
        betLabel.setForeground(new Color(227, 217, 217));
        this.betLabel.setText("Bankroll: " + bankroll.getBankRollAmount());
        betAmountTextField.setBounds(180, 0, 50, 25);
        this.betAmountTextField.setText("0");

        playerText.setBounds(60, 340, 300, 100);
        dealerText.setBounds(60, 190, 300, 100);
        playerScoreLabel.setBounds(playerText.getX(), playerText.getY() + 20, 300, 100);
        dealerScoreLabel.setBounds(dealerText.getX(), dealerText.getY() + 20, 300, 100);

        playerText.setFont(new Font("MV Boli", Font.ITALIC, 13)); // set font of text
        dealerText.setFont(new Font("MV Boli", Font.ITALIC, 13)); // set font of text
        playerScoreLabel.setFont(new Font("MV Boli", Font.ITALIC, 13)); // set font of text
        dealerScoreLabel.setFont(new Font("MV Boli", Font.ITALIC, 13)); // set font of text
        playerWinsLabel.setFont(new Font("MV Boli", Font.ITALIC, 13)); // set font of text
        dealerWinsLabel.setFont(new Font("MV Boli", Font.ITALIC, 13)); // set font of text

        dealerText.setForeground(new Color(227, 217, 217));
        playerText.setForeground(new Color(227, 217, 217));
        playerScoreLabel.setForeground(new Color(227, 217, 217));
        dealerScoreLabel.setForeground(new Color(227, 217, 217));
        playerWinsLabel.setForeground(new Color(227, 217, 217));
        dealerWinsLabel.setForeground(new Color(227, 217, 217));


        textArea.setEditable(false); // Make it non-editable
        textArea.setLineWrap(true); // Enable line wrapping
        textArea.setWrapStyleWord(true); // Wrap at word boundaries

        // Create a JScrollPane and add the JTextArea to it
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(playerText.getX() - 50, playerText.getY() + 100, 200, 50);

        newPanel.add(playerCard1);
        newPanel.add(playerCard2);
        newPanel.add(dealerCard1);
        newPanel.add(dealerCard2);

        newPanel.add(playerText);
        newPanel.add(dealerText);
        newPanel.add(playerScoreLabel);
        newPanel.add(dealerScoreLabel);
        newPanel.add(quitButton);

        if(bettingSetting){
            newPanel.add(betButton);
            newPanel.add(betLabel);
            newPanel.add(betAmountTextField);
        }else{
            newPanel.add(playerWinsLabel);
            newPanel.add(dealerWinsLabel);
        }


        newPanel.add(hitButton);
        newPanel.add(standButton);
        if(!autoDealSetting){
            newPanel.add(continueButton);
        }

        newPanel.add(scrollPane);
        newPanel.add(backgroundScreen);
        // newPanel.add(backgroundScreen, JLayeredPane.DEFAULT_LAYER);
        textArea.setText(roundHighlights);


        blackJackScreenFrame.add(newPanel);
        blackJackScreenFrame.setSize(700, 525);
        blackJackScreenFrame.setVisible(true);
        blackJackScreenFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit out of application

        return newPanel;
    }


    public void dealerTurn() {
        int dealerHitCount = 1;
        ImageIcon card3 = new ImageIcon(new ImageIcon(dealerCardNum2.getCardName()).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
        System.out.println(dealerCardNum2.getCardName());
        dealerCard2.setIcon(card3);
        dealerScoreLabel.setText("Dealer's Score: " + round.dealerCardScore);

        blackJackScreenPanel.revalidate();
        blackJackScreenPanel.repaint();
        //round.setDealerCardScore();
        //while the dealer card score is less than the player cards score

        System.out.println("dealer card score: " + round.dealerCardScore);
        System.out.println("round card score: " + round.playerCardScore);
        System.out.println("less than 21: " + (round.playerCardScore < 21));

        while (round.dealerCardScore < round.playerCardScore && (round.playerCardScore < round.BLACKJACK)) {

            //while it's less than we need a card so give dealer the card
            int distance = (120 + 110 * dealerHitCount);
            Card dealerCard = round.dealerHand.getDealerCard(deck);
            System.out.println(dealerCard.getCardName());
            ImageIcon newDealerCardImage = new ImageIcon(new ImageIcon(dealerCard.getCardName()).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
            JLabel newPlayerCard = new JLabel();

            newPlayerCard.setIcon(newDealerCardImage);
            newPlayerCard.setBounds(distance, 130, 100, 100);
            backgroundScreen.add(newPlayerCard, JLayeredPane.PALETTE_LAYER);

            dealerCard2.setIcon(card3);
            dealerHitCount++;
            System.out.println("Dealer Hit");

            //calculate the score with that new card
            round.dealerCardScore = round.dealerHand.calculateScore();
            //if the dealer busted then their turn is over
            System.out.println("Dealer's score: " + round.dealerCardScore);
            dealerScoreLabel.setText("Dealer's Score: " + round.dealerCardScore);

            if (round.dealerCardScore > round.BLACKJACK) {
                System.out.println("DEALER BUST");
                roundHighlights = roundHighlights + "\n" + "Dealer Bust -> " + playerName + " Wins";
                textArea.setText(roundHighlights);
                bankroll.betWin();
                betLabel.setText("Bankroll: " + bankroll.getBankRollAmount());
                playerWinsTotal++;
                playerWinsLabel.setText(playerName + "'s Wins: " + playerWinsTotal);
                blackJackScreenPanel.revalidate();
                blackJackScreenPanel.repaint();
                break;
            }
        }

        blackJackScreenPanel.revalidate();
        blackJackScreenPanel.repaint();

        roundOver = true;
        if (round.dealerCardScore < round.BLACKJACK && round.dealerCardScore > round.playerCardScore) {
            System.out.println("DEALER WINS");
            roundHighlights = roundHighlights + "\n" + "Dealer WINS";
            dealerWinsTotal++;
            dealerWinsLabel.setText("Dealer Wins: " + dealerWinsTotal);

            textArea.setText(roundHighlights);
            bankroll.betLoss();
            betLabel.setText("Bankroll: " + bankroll.getBankRollAmount());
            doAutoDeal();

        } else if (round.dealerCardScore == round.playerCardScore) {
            System.out.println("Game Tie");
            roundHighlights = roundHighlights + "\n" + "Game Tie";
            textArea.setText(roundHighlights);
            doAutoDeal();
        } else if (round.dealerCardScore == round.BLACKJACK) {
            System.out.println("Dealer BlackJack");
            roundHighlights = roundHighlights + "\n" + "Dealer BlackJack";
            dealerWinsTotal++;
            dealerWinsLabel.setText("Dealer Wins: " + dealerWinsTotal);
            textArea.setText(roundHighlights);
            bankroll.betLoss();
            betLabel.setText("Bankroll: " + bankroll.getBankRollAmount());
            doAutoDeal();
        }

    }

    private void doAutoDeal(){
        if (autoDealSetting) {
            int delay = 3000; // Delay in milliseconds (3 seconds)

            Timer timer = new Timer(delay, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    roundHighlights = roundHighlights + "\n" + "Auto Dealing...";
                    textArea.setText(roundHighlights);
                    continueButton.doClick();
                }
            });

            timer.setRepeats(false); // Set to false if you want the timer to fire only once
            timer.start();
        }
    }

    private void addButtonCallbackHandlers() {
        betButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(betAmountTextField.getText());
                if (bankroll.getBankRollAmount() <= 0) {
                    System.out.println("You have no more money to bet with");
                    bankroll.setBetValue();
                    roundHighlights = roundHighlights + "\n" + "You have no more money to bet with";
                    textArea.setText(roundHighlights);
                    betButton.setEnabled(true);
                    return;
                }
                if (bankroll.getBankRollAmount() < Integer.parseInt(betAmountTextField.getText())) {
                    System.out.println("You are betting more than you have");
                    bankroll.setBetValue();
                    roundHighlights = roundHighlights + "\n" + "You don't have enough money";
                    textArea.setText(roundHighlights);
                    betButton.setEnabled(true);
                    return;
                }
                bankroll.addBet(Integer.parseInt(betAmountTextField.getText()));
                roundHighlights = roundHighlights + "\n" + "Possible payout: " + bankroll.getPayout();
                textArea.setText(roundHighlights);
                //betLabel.setText("Bankroll: " + (bankroll.getBankRollAmount() - bankroll.betValue));
                System.out.println("Possible payout: " + bankroll.getPayout());
                betButton.setEnabled(false);
            }
        });

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startingScreenFrame.setVisible(false);
                blackJackGUI();
            }
        });

        hitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (roundOver) {
                    return;
                }
                roundHighlights = roundHighlights + "\n" + playerName +" Hit";
                textArea.setText(roundHighlights);

                int distance = (120 + 110 * hitCount);

                Card newCard = round.playerTurn();
                JLabel newPlayerCard = new JLabel();
                ImageIcon cardImage = new ImageIcon(new ImageIcon(newCard.getCardName()).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
                newPlayerCard.setIcon(cardImage);
                newPlayerCard.setBounds(distance, 280, 100, 100);
                backgroundScreen.add(newPlayerCard, JLayeredPane.PALETTE_LAYER);
                if (round.playerCardScore >= round.BLACKJACK) {
                    standButton.doClick();
                }
                playerScoreLabel.setText(playerName + "'s Score:" + round.getPlayerScore());

                blackJackScreenPanel.revalidate();
                blackJackScreenPanel.repaint();

                hitCount++;
            }
        });

        standButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(playerName + " Stand");

                if (roundOver) {
                    return;
                }
                firstRound = false;

                if (round.getPlayerScore() == round.BLACKJACK) {
                    roundHighlights = roundHighlights + "\n" + playerName + " BlackJack";
                    playerWinsTotal++;
                    playerWinsLabel.setText(playerName + " Wins: " + playerWinsTotal);

                    textArea.setText(roundHighlights);
                    bankroll.betWin();
                    betLabel.setText("Bankroll: " + bankroll.getBankRollAmount());
                    doAutoDeal();

                } else if (round.getPlayerScore() > round.BLACKJACK) {
                    roundHighlights = roundHighlights + "\n" + playerName + " Bust -> Dealer Wins";
                    dealerWinsTotal++;
                    dealerWinsLabel.setText("Dealer Wins: " + dealerWinsTotal);

                    textArea.setText(roundHighlights);
                    bankroll.betLoss();
                    betLabel.setText("Bankroll: " + bankroll.getBankRollAmount());
                    doAutoDeal();
                } else {
                    roundHighlights = roundHighlights + "\n" + playerName + " Stand";
                    textArea.setText(roundHighlights);
                }
                dealerTurn();

            }
        });
        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (roundOver) {
                    startNextRound();
                    roundOver = false;
                    firstRound = false;
                } else {
                    roundHighlights = roundHighlights + "\n" + "Click Stand to Continue";
                    textArea.setText(roundHighlights);
                }

            }
        });
        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startingScreenFrame.setVisible(false);
                settingScreenFrame.setVisible(true);
            }
        });
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startingScreenFrame.setVisible(true);
                settingScreenFrame.setVisible(false);
                playerName = playerNameTextField.getText().trim();
                if(playerName.isEmpty()){
                    playerName = "Player";
                }

                System.out.println("Player name: " + playerName);
                playerText.setText(playerName + "'s Cards");
                playerScoreLabel.setText(playerName + "'s Score: " + round.getPlayerScore() );
                playerWinsLabel.setText(playerName + "'s Wins: 0");

                if(autoDeal.isSelected()){
                    autoDealSetting = true;
                }
                if(bettingToggle.isSelected()){
                    bettingSetting = true;
                }
                if(gamingModeToggle.isSelected()){
                    bankroll.setPayoutGamingMode();
                }

            }
        });
        bettingToggle.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(bettingToggle.isSelected()){
                    autoDeal.setEnabled(false);
                }
                else{
                    autoDeal.setEnabled(true);
                }


            }
        });
        bettingToggle.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(autoDeal.isSelected()){
                    autoDeal.setSelected(false);
                }
            }
        });
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                blackJackScreenPanel.removeAll();
                backgroundScreen.removeAll();
                blackJackScreenPanel.revalidate();
                blackJackScreenPanel.repaint();
                blackJackScreenPanel = genEndScreen();
            }
        });

    }
}
