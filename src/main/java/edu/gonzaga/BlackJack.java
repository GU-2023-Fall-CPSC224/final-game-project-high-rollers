package edu.gonzaga;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

public class BlackJack{

    Player player;
    Dealer dealer;
    Round round;
    Deck deck;
    boolean roundOver = false;
    ImageIcon turnedOverCard;

    int hitCount = 1;
    boolean firstRound = true;
    Card playerCardNum1;
    Card playerCardNum2;
    Card dealerCardNum1;
    Card dealerCardNum2;

    JFrame startingScreenFrame;
    JPanel startingScreenPanel;
    JFrame blackJackScreenFrame;
    JPanel blackJackScreenPanel;


    JButton hitButton  = new JButton("Hit");
    JButton standButton = new JButton("Stand");
    JButton continueButton = new JButton("Continue");
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

    // starting Screen
    JButton startButton  = new JButton("Start");
    JLabel startingCard = new JLabel();
    JLabel startingText = new JLabel("Welcome to Java Blackjack");
    JLabel groupNameText = new JLabel("Designed by: Harrison, Cooper, Asher");



    ImageIcon background;


    ImageIcon icon = new ImageIcon("PNG-cards-1.3/start.png");

    // starting Screen
    JLabel backgroundScreen = new JLabel();


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
            this.blackJackScreenPanel = genBlackJackGUI();
            blackJackScreenFrame.add(blackJackScreenPanel);



            blackJackScreenPanel.revalidate();
            blackJackScreenPanel.repaint();

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
        setStartingTextStyle();
        newPanel.setLayout(null);

        turnedOverCard = new ImageIcon(new ImageIcon("PNG-cards-1.3/card back red.png").getImage().getScaledInstance(150,150,Image.SCALE_SMOOTH));
        startingCard.setIcon(turnedOverCard);

        newPanel.add(startButton);
        newPanel.add(startingCard);
        newPanel.add(startingText);
        newPanel.add(groupNameText);
        newPanel.add(startingCard);

        startButton.setBounds(300,400,100,25);
        startingText.setBounds(250,0 , 500,50);
        groupNameText.setBounds(200,28, 500,100);
        startingCard.setBounds(300,150, 150,150);

        startingScreenFrame.add(newPanel);
        startingScreenFrame.setSize(700,550);
        startingScreenFrame.setVisible(true);
        startingScreenFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit out of application
        newPanel.setBackground(new Color(35,54,5));

        return newPanel;
    }

    private JPanel genBlackJackGUI(){
        JPanel newPanel = new JPanel();

        newPanel.setLayout(null);
        newPanel.setBackground(new Color(35,54,5));

        ImageIcon card1 = new ImageIcon(new ImageIcon("" +  playerCardNum1.getCardName()).getImage().getScaledInstance(100,100,Image.SCALE_SMOOTH));
        ImageIcon card2 = new ImageIcon(new ImageIcon("" + playerCardNum2.getCardName()).getImage().getScaledInstance(100,100,Image.SCALE_SMOOTH));
        ImageIcon card3 = new ImageIcon(new ImageIcon("" + dealerCardNum1.getCardName()).getImage().getScaledInstance(100,100,Image.SCALE_SMOOTH));
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

        playerText.setBounds(60,340, 100,100);
        dealerText.setBounds(60,190, 100,100);
        playerScoreLabel.setBounds(playerText.getX(), playerText.getY() + 20, 100,100);
        dealerScoreLabel.setBounds(dealerText.getX(), dealerText.getY() + 20, 100,100);

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


        newPanel.add(hitButton);
        newPanel.add(standButton);
        newPanel.add(continueButton);
        newPanel.add(scrollPane);
        textArea.setText(roundHighlights);


        blackJackScreenFrame.add(newPanel);
        blackJackScreenFrame.setSize(700,550);
        blackJackScreenFrame.setVisible(true);
        blackJackScreenFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit out of application

        return newPanel;
    }

    public void setStartingTextStyle(){
        startingText.setHorizontalTextPosition(JLabel.CENTER); // set text according to JLabel ( Left, center, or right)
        startingText.setVerticalTextPosition(JLabel.BOTTOM);
        startingText.setFont(new Font("MV Boli", Font.ITALIC, 17)); // set font of text
        startingText.setIconTextGap(10); // set gap of text to image either plus or minus

        groupNameText.setHorizontalTextPosition(JLabel.CENTER); // set text according to JLabel ( Left, center, or right)
        groupNameText.setVerticalTextPosition(JLabel.BOTTOM);
        groupNameText.setFont(new Font("MV Boli", Font.ITALIC, 17)); // set font of text
        groupNameText.setIconTextGap(10); // set gap of text to image either plus or minus

        groupNameText.setForeground(new Color(227, 217, 217));
        startingText.setForeground(new Color(227, 217, 217));
    }

    public void dealerTurn() {
        int dealerHitCount = 0;
        ImageIcon card3 = new ImageIcon(new ImageIcon(dealerCardNum2.getCardName()).getImage().getScaledInstance(100,100,Image.SCALE_SMOOTH));
        dealerCard2.setIcon(card3);
        blackJackScreenPanel.revalidate();
        blackJackScreenPanel.repaint();
        //round.setDealerCardScore();
        //while the dealer card score is less than the player cards score

        while (round.dealerCardScore < round.playerCardScore && (round.playerCardScore < 21)) {

            dealerHitCount++;
            //while it's less than we need a card so give dealer the card
            int distance = (120 + 110 * dealerHitCount);
            Card dealerCard = round.dealerHand.getDealerCard(deck);
            ImageIcon newDealerCardImage = new ImageIcon(new ImageIcon(dealerCard.getCardName()).getImage().getScaledInstance(100,100,Image.SCALE_SMOOTH));
            JLabel newPlayerCard  = new JLabel();
            newPlayerCard.setIcon(newDealerCardImage);
            newPlayerCard.setBounds(distance, 130, 100, 100);
            blackJackScreenPanel.add(newPlayerCard);

            dealerCard2.setIcon(card3);
            hitCount++;
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
        }
    }

    private void addButtonCallbackHandlers() {
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
                blackJackScreenPanel.add(newPlayerCard);
                if(round.playerCardScore >= 21){
                    standButton.doClick();
                }
                playerScoreLabel.setText("Player Score: " + round.getPlayerScore());

                blackJackScreenPanel.revalidate();
                blackJackScreenPanel.repaint();

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
                }
                else if(round.getPlayerScore() > 21){
                    roundHighlights = roundHighlights + "\n" + "Player Bust -> Dealer Wins";
                    textArea.setText(roundHighlights);
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
                }

            }
        });
    }
}
