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
    JTextArea textArea;
    ImageIcon turnedOverCard;
    Image bgImage ;

    int hitCount = 1;
    Card playerCardNum1;
    Card playerCardNum2;
    Card dealerCardNum1;
    Card dealerCardNum2;

    JFrame startingScreenFrame;
    JPanel startingScreenPanel;
    JFrame blackJackScreenFrame;
    JPanel blackJackScreenPanel;


    JButton hitButton  = new JButton("hit");
    JButton standButton = new JButton("stand");
    JLabel playerCard1 = new JLabel();
    JLabel playerCard2 = new JLabel();
    JLabel dealerCard1 = new JLabel();
    JLabel dealerCard2 = new JLabel();

    JLabel playerText = new JLabel("Player Cards");
    JLabel dealerText = new JLabel("Dealer Cards");

    // starting Screen
    JButton startButton  = new JButton("Start");
    JLabel startingCard = new JLabel();
    JLabel startingText = new JLabel("Welcome to Java Blackjack");
    JLabel groupNameText = new JLabel("Designed by: Harrison, Cooper, Asher");

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
        round = new Round(this.deck);
        playerCardNum1 = round.getCard();
        playerCardNum2 = round.getCard();
        dealerCardNum1 =  round.getDealerCard();
        dealerCardNum2 =  round.getDealerCard();
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
        this.blackJackScreenFrame.setLocation(100,100);
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

        playerText.setBounds(60,340, 100,100);
        dealerText.setBounds(60,190, 100,100);
        playerText.setFont(new Font("MV Boli", Font.ITALIC, 13)); // set font of text
        dealerText.setFont(new Font("MV Boli", Font.ITALIC, 13)); // set font of text

        dealerText.setForeground(new Color(227, 217, 217));
        playerText.setForeground(new Color(227, 217, 217));


        newPanel.add(playerCard1);
        newPanel.add(playerCard2);
        newPanel.add(dealerCard1);
        newPanel.add(dealerCard2);

        newPanel.add(playerText);
        newPanel.add(dealerText);

        newPanel.add(hitButton);
        newPanel.add(standButton);

//        ImageIcon icon = new ImageIcon("PNG-cards-1.3/table.jpeg");
//        Image backGroundImage = icon.getImage();
//        bgImage = backGroundImage;
//        paintComponent(backGroundImage);


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
                blackJackScreenPanel.revalidate();
                blackJackScreenPanel.repaint();

            }
        });

        standButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Player Stand");
                dealerTurn();
            }
        });


    }

    public void dealerTurn() {
        int dealerHitCount = 0;
        System.out.println("dealer card 2: " + dealerCardNum2);
        ImageIcon card3 = new ImageIcon(new ImageIcon(dealerCardNum2.getCardName()).getImage().getScaledInstance(100,100,Image.SCALE_SMOOTH));
        dealerCard2.setIcon(card3);
        blackJackScreenPanel.revalidate();
        blackJackScreenPanel.repaint();
        //round.setDealerCardScore();
        //while the dealer card score is less than the player cards score

        while (round.dealerCardScore < round.playerCardScore && (round.playerCardScore < 21)) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

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
            if (round.dealerCardScore > round.BLACKJACK) {
                System.out.println("DEALER BUST");
                break;
            }

            blackJackScreenPanel.revalidate();
            blackJackScreenPanel.repaint();

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }

}
