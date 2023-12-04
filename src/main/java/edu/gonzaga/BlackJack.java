package edu.gonzaga;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

public class BlackJack{

    ImageIcon background;
    ImageIcon startIcon = new ImageIcon("Graphics/start.png");
    ImageIcon settingsIcon = new ImageIcon("Graphics/settings.png");
    ImageIcon settingBackground = new ImageIcon(new ImageIcon("PNG-cards-1.3/BJSettingScreen.jpg").getImage().getScaledInstance(700,500,Image.SCALE_SMOOTH));

    ImageIcon settingBackground2 =new ImageIcon("PNG-cards-1.3/BJSettingScreen.jpg");
    JLabel backgroundLabel = new JLabel(settingBackground);

    // starting Screen
    JLabel backgroundScreen = new JLabel();
    JLabel settingBackgroundScreen = new JLabel();


    Player player;
    Dealer dealer;
    Round round;
    Deck deck;
    Bet bankroll;
    boolean roundOver = false;
    ImageIcon turnedOverCard;

    int hitCount = 1;
    boolean firstRound = true;
    Card playerCardNum1;
    Card playerCardNum2;
    Card dealerCardNum1;
    Card dealerCardNum2;

    JFrame startingScreenFrame;
    JFrame settingScreenFrame;
    JPanel startingScreenPanel;
    JPanel settingScreenPanel;
    JFrame blackJackScreenFrame;
    JPanel blackJackScreenPanel;


    JButton betButton = new JButton("Bet");
    JLabel betLabel = new JLabel("BankRoll: ");
    JTextField betAmountTextField = new JTextField();
    JButton hitButton  = new JButton("Hit");
    JButton standButton = new JButton("Stand");
    JButton continueButton = new JButton("Continue");
    JButton startButton  = new JButton(startIcon);
    JButton settingsButton = new JButton(settingsIcon);

    JCheckBox bettingToggle = new JCheckBox("Betting enable");
    JCheckBox autoDeal = new JCheckBox("Auto Deal");
    //JCheckBox ;
    JButton returnButton = new JButton("Save and Return to Start");

    JLabel playerCard1 = new JLabel();
    JLabel playerCard2 = new JLabel();
    JLabel dealerCard1 = new JLabel();
    JLabel dealerCard2 = new JLabel();

    JLabel playerText = new JLabel("Player Cards");
    JLabel dealerText = new JLabel("Dealer Cards");
    JLabel playerScoreLabel = new JLabel("Player Score: "); // Initializing with default score
    JLabel dealerScoreLabel = new JLabel("Dealer Score: "); // Initializing with default score

    String roundHighlights = "";
    JTextArea textArea = new JTextArea();

    public static void main(String [] args){
        BlackJack app = new BlackJack();
        app.runGUI();

        /*
        for(int i = 0; i < 10; i++){
            Round round = new Round(new Deck());
            winStatus = round.playRound();
            if(winStatus == 2){
                playerWins++;
                System.out.println("PLAYER WIN");
            }
            else if (winStatus == 1){
                System.out.println("TIE");
            }
            else{
                dealerWins++;
                System.out.println("DEALER WIN");
            }
        }
        System.out.println("PLAYER WINS: " + playerWins);
        System.out.println("DEALER WINS: " + dealerWins);
         */

    }

    public BlackJack(){
        deck = new Deck();
        bankroll = new Bet();
        startNextRound();

    }

    public void startNextRound(){
        round = new Round(this.deck);
        playerCardNum1 = round.getCard();
        playerCardNum2 = round.getCard();
        dealerCardNum1 =  round.getDealerCard();
        dealerCardNum2 =  round.getDealerCard();

        System.out.println("value: " + dealerCardNum1.getValue());

        round.dealerStartScore = dealerCardNum1.getValue();
        round.playerCardScore = round.playerHand.calculateScore();
        round.dealerCardScore = round.dealerHand.calculateScore();

        if (!firstRound) {
            // Clearing the panel
            blackJackScreenPanel.removeAll();
            backgroundScreen.removeAll();
            this.blackJackScreenPanel = genBlackJackGUI();

            blackJackScreenPanel.revalidate();
            blackJackScreenPanel.repaint();

            blackJackScreenFrame.add(blackJackScreenPanel);


            hitCount = 1;
        }
        playerScoreLabel.setText("Player Score: " + round.playerCardScore);
        dealerScoreLabel.setText("Dealer Score: " + round.dealerStartScore);

        roundHighlights = roundHighlights + "\n" + "Starting Round...";
        textArea.setText(roundHighlights);

    }

    void runGUI(){

        System.out.println("Starting GUI");
        startingScreenGUI();

        startingScreenFrame.setVisible(true);
        System.out.println("Finished Running GUI");
    }

    void startingScreenGUI(){

        this.startingScreenFrame = new JFrame("Black Jack");
        this.startingScreenFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.startingScreenFrame.setLocation(100,100);
        this.startingScreenPanel = genStartingScreenPanel();
        startingScreenFrame.setResizable(false);
        addButtonCallbackHandlers();
        new Background();
        settingScreenGUI();
    }

    void settingScreenGUI(){
        this.settingScreenFrame = new JFrame("Black Jack");
        this.settingScreenFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.settingScreenFrame.setLocation(100,100);
        this.settingScreenPanel = genSettingScreenPanel();
        settingScreenFrame.setResizable(false);
        new Background();
    }

    private JPanel genSettingScreenPanel(){
        JPanel newPanel = new JPanel();
        newPanel.setLayout(null);

        background = new ImageIcon(new ImageIcon("PNG-cards-1.3/table.png").getImage().getScaledInstance(700,500,Image.SCALE_SMOOTH));
        backgroundScreen.setIcon(background);

        newPanel.add(bettingToggle);
        newPanel.add(autoDeal);
        newPanel.add(returnButton);
        newPanel.add(backgroundScreen);


        backgroundScreen.setBounds(0,0, 700,500);
        bettingToggle.setBounds(50,50, 100,100);
        autoDeal.setBounds(100,100, 100,100);
        returnButton.setBounds(25,100, 100,100);

        settingScreenFrame.add(newPanel);
        settingScreenFrame.setSize(700,525);
        settingScreenFrame.setVisible(false);
        settingScreenFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit out of application

        return newPanel;
    }

    void blackJackGUI(){

        this.blackJackScreenFrame = new JFrame("Black Jack");
        this.blackJackScreenFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.blackJackScreenFrame.setLocation(startingScreenFrame.getX(),startingScreenFrame.getY());
        this.blackJackScreenPanel = genBlackJackGUI();

        blackJackScreenFrame.setResizable(false);
        blackJackScreenFrame.setVisible(true);

        round.playRound();

    }

    private JPanel genStartingScreenPanel(){

        JPanel newPanel = new JPanel();
        newPanel.setLayout(null);

        background = new ImageIcon(new ImageIcon("Graphics/intro.png").getImage().getScaledInstance(700,500,Image.SCALE_SMOOTH));
        backgroundScreen.setIcon(background);


        newPanel.add(backgroundScreen);
        newPanel.add(settingsButton, JLayeredPane.POPUP_LAYER);
        newPanel.add(startButton);




        startButton.setBounds(200,236,100,25);
        settingsButton.setBounds(400, 236, 100, 25);
        backgroundScreen.setBounds(0,0, 700,500);

        startingScreenFrame.add(newPanel);
        startingScreenFrame.setSize(700,525);
        startingScreenFrame.setVisible(true);
        startingScreenFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit out of application
       // newPanel.setBackground(new Color(35,54,5));

        return newPanel;

    }

    private JPanel genBlackJackGUI(){
        JPanel newPanel = new JPanel();
        newPanel.setLayout(null);

        background = new ImageIcon(new ImageIcon("PNG-cards-1.3/table.png").getImage().getScaledInstance(700,500,Image.SCALE_SMOOTH));
        backgroundScreen.setIcon(background);


        ImageIcon card1 = new ImageIcon(new ImageIcon(playerCardNum1.getCardName()).getImage().getScaledInstance(100,100,Image.SCALE_SMOOTH));
        ImageIcon card2 = new ImageIcon(new ImageIcon( playerCardNum2.getCardName()).getImage().getScaledInstance(100,100,Image.SCALE_SMOOTH));
        ImageIcon card3 = new ImageIcon(new ImageIcon(dealerCardNum1.getCardName()).getImage().getScaledInstance(100,100,Image.SCALE_SMOOTH));
        ImageIcon turnedOverCard = new ImageIcon(new ImageIcon("PNG-cards-1.3/card back red.png").getImage().getScaledInstance(100,100,Image.SCALE_SMOOTH));


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

        hitButton.setBounds(250,0,100,25);
        standButton.setBounds(350,0,100,25);
        continueButton.setBounds(450,0,100,25);
        betButton.setBounds(100,0,76,25);
        betLabel.setBounds(0,0,95,25);
        betLabel.setForeground(new Color(227, 217, 217));
        this.betLabel.setText("Bankroll: " + bankroll.getBankRollAmount());
        betAmountTextField.setBounds(180,0,50,25);
        this.betAmountTextField.setText("0");

        playerText.setBounds(60,340, 100,100);
        dealerText.setBounds(60,190, 100,100);
        playerScoreLabel.setBounds(playerText.getX(), playerText.getY() + 20, 100,100);
        dealerScoreLabel.setBounds(dealerText.getX(), dealerText.getY() + 20, 150,100);

        playerText.setFont(new Font("MV Boli", Font.ITALIC, 13)); // set font of text
        dealerText.setFont(new Font("MV Boli", Font.ITALIC, 13)); // set font of text
        playerScoreLabel.setFont(new Font("MV Boli", Font.ITALIC, 13)); // set font of text
        dealerScoreLabel.setFont(new Font("MV Boli", Font.ITALIC, 13)); // set font of text


        dealerText.setForeground(new Color(227, 217, 217));
        playerText.setForeground(new Color(227, 217, 217));
        playerScoreLabel.setForeground(new Color(227, 217, 217));
        dealerScoreLabel.setForeground(new Color(227, 217, 217));


        textArea.setEditable(false); // Make it non-editable
        textArea.setLineWrap(true); // Enable line wrapping
        textArea.setWrapStyleWord(true); // Wrap at word boundaries

        // Create a JScrollPane and add the JTextArea to it
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(playerText.getX() - 50, playerText.getY() + 100, 200,50);


        newPanel.add(playerCard1);
        newPanel.add(playerCard2);
        newPanel.add(dealerCard1);
        newPanel.add(dealerCard2);

        newPanel.add(playerText);
        newPanel.add(dealerText);
        newPanel.add(playerScoreLabel);
        newPanel.add(dealerScoreLabel);

        newPanel.add(betButton);
        newPanel.add(betLabel);
        newPanel.add(betAmountTextField);
        newPanel.add(hitButton);
        newPanel.add(standButton);
        newPanel.add(continueButton);
        newPanel.add(scrollPane);
        newPanel.add(backgroundScreen);
       // newPanel.add(backgroundScreen, JLayeredPane.DEFAULT_LAYER);
        textArea.setText(roundHighlights);


        blackJackScreenFrame.add(newPanel);
        blackJackScreenFrame.setSize(700,525);
        blackJackScreenFrame.setVisible(true);
        blackJackScreenFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit out of application

        return newPanel;
    }



    public void dealerTurn() {
        int dealerHitCount = 1;
        ImageIcon card3 = new ImageIcon(new ImageIcon(dealerCardNum2.getCardName()).getImage().getScaledInstance(100,100,Image.SCALE_SMOOTH));
        System.out.println(dealerCardNum2.getCardName());
        dealerCard2.setIcon(card3);
        dealerScoreLabel.setText("Dealer Score: " + round.dealerCardScore);

        blackJackScreenPanel.revalidate();
        blackJackScreenPanel.repaint();
        //round.setDealerCardScore();
        //while the dealer card score is less than the player cards score

        System.out.println("dealer card score: " + round.dealerCardScore);
        System.out.println("round card score: " + round.playerCardScore);
        System.out.println("less than 21: " + (round.playerCardScore < 21));

        while (round.dealerCardScore < round.playerCardScore && (round.playerCardScore < 21)) {

            //while it's less than we need a card so give dealer the card
            int distance = (120 + 110 * dealerHitCount);
            Card dealerCard = round.dealerHand.getDealerCard(deck);
            System.out.println(dealerCard.getCardName());
            ImageIcon newDealerCardImage = new ImageIcon(new ImageIcon(dealerCard.getCardName()).getImage().getScaledInstance(100,100,Image.SCALE_SMOOTH));
            JLabel newPlayerCard  = new JLabel();
            newPlayerCard.setIcon(newDealerCardImage);
            newPlayerCard.setBounds(distance, 130, 100, 100);
            backgroundScreen.add(newPlayerCard, JLayeredPane.PALETTE_LAYER);

            dealerCard2.setIcon(card3);
            dealerHitCount++;
            System.out.println("Dealer Hit");

            //calculate the score with that new card
            round.dealerCardScore = round.dealerHand.calculateScore();
            //if the dealer busted then their turn is over
            System.out.println("Dealer score: " + round.dealerCardScore);
            dealerScoreLabel.setText("Dealer Score: " + round.dealerCardScore);

            if (round.dealerCardScore > round.BLACKJACK) {
                System.out.println("DEALER BUST");
                roundHighlights = roundHighlights + "\n" + "Dealer Bust -> Player Wins";
                textArea.setText(roundHighlights);
                bankroll.betWin();
                betLabel.setText("Bankroll: " + bankroll.getBankRollAmount());
                blackJackScreenPanel.revalidate();
                blackJackScreenPanel.repaint();
                break;
            }

            blackJackScreenPanel.revalidate();
            blackJackScreenPanel.repaint();

        }
        roundOver = true;
        if (round.dealerCardScore < round.BLACKJACK && round.dealerCardScore > round.playerCardScore) {
            System.out.println("DEALER WINS");
            roundHighlights = roundHighlights + "\n" + "Dealer WINS";
            textArea.setText(roundHighlights);
            bankroll.betLoss();
            betLabel.setText("Bankroll: " + bankroll.getBankRollAmount());
        }
        else if (round.dealerCardScore  == round.playerCardScore) {
            System.out.println("Game Tie");
            roundHighlights = roundHighlights + "\n" + "Game Tie";
            textArea.setText(roundHighlights);
        }
        else if (round.dealerCardScore  == round.BLACKJACK) {
            System.out.println("Dealer BlackJack");
            roundHighlights = roundHighlights + "\n" + "Dealer BlackJack";
            textArea.setText(roundHighlights);
            bankroll.betLoss();
            betLabel.setText("Bankroll: " + bankroll.getBankRollAmount());
        }
    }

    private void addButtonCallbackHandlers() {
        betButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(betAmountTextField.getText());
                if(bankroll.getBankRollAmount() <= 0) {
                    System.out.println("You have no more money to bet with");
                    bankroll.setBetValue();
                    roundHighlights = roundHighlights + "\n" + "You have no more money to bet with";
                    textArea.setText(roundHighlights);
                    return;
                }
                if(bankroll.getBankRollAmount() <= Integer.parseInt(betAmountTextField.getText())) {
                    System.out.println("You are betting more than you have");
                    //bankroll.setBetValue();
                    roundHighlights = roundHighlights + "\n" + "You don't have enough money";
                    textArea.setText(roundHighlights);
                    return;
                }
                bankroll.addBet(Integer.parseInt(betAmountTextField.getText()));
                roundHighlights = roundHighlights + "\n" + "Possible payout: " + bankroll.getPayout();
                textArea.setText(roundHighlights);
                //betLabel.setText("Bankroll: " + (bankroll.getBankRollAmount() - bankroll.betValue));
                System.out.println("Possible payout: " + bankroll.getPayout());
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
                if(roundOver){
                    return;
                }
                roundHighlights = roundHighlights + "\n" + "Player Hit";
                textArea.setText(roundHighlights);

                int distance = (120 + 110 * hitCount);

                Card newCard = round.playerTurn();
                //String player = round.deck.cards.
                JLabel newPlayerCard  = new JLabel();
                ImageIcon cardImage = new ImageIcon(new ImageIcon(newCard.getCardName()).getImage().getScaledInstance(100,100,Image.SCALE_SMOOTH));
                newPlayerCard.setIcon(cardImage);
                newPlayerCard.setBounds(distance, 280, 100, 100);
                backgroundScreen.add(newPlayerCard, JLayeredPane.PALETTE_LAYER);
                if(round.playerCardScore >= 21){
                    standButton.doClick();
                }
                playerScoreLabel.setText("Player Score: " + round.getPlayerScore());

                blackJackScreenPanel.revalidate();
                blackJackScreenPanel.repaint();

                hitCount++;
            }
        });

        standButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Player Stand");

                if (roundOver){
                    return;
                }
                firstRound = false;

                if(round.getPlayerScore() == 21){
                    roundHighlights = roundHighlights + "\n" + "Player BlackJack";
                    textArea.setText(roundHighlights);
                    bankroll.betWin();
                    betLabel.setText("Bankroll: " + bankroll.getBankRollAmount());
                }
                else if(round.getPlayerScore() > 21){
                    roundHighlights = roundHighlights + "\n" + "Player Bust -> Dealer Wins";
                    textArea.setText(roundHighlights);
                    bankroll.betLoss();
                    betLabel.setText("Bankroll: " + bankroll.getBankRollAmount());
                }else{
                    roundHighlights = roundHighlights + "\n" + "Player Stand";
                    textArea.setText(roundHighlights);
                }

                dealerTurn();
            }
        });
        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(roundOver){
                    startNextRound();
                    roundOver = false;
                    firstRound = false;
                }else{
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

            }
        });
    }
}
