package edu.gonzaga;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartingScreenGUI extends JFrame{
    JFrame frame = new JFrame("Title Screen");

    JPanel panel = new JPanel();
    //panel with title screen
    //panel with game screen / winning screen
    //content pane
    // layout manager border layout
    // splash screen has game in it
    // 1 widget per panel can add panel to widget and add game
    // panel for start screen in spalsh then replace with actual game
    // text area in south // append to end scrolll bar with dealer gets points or etc
    //dealer ui player ui
    //player design player interface, set of cards
    //hit and stay  above it , bet (text field) , wallet (money have) , all in panel
    // panel inside panel
    //dealer has its own panel
    //box layout of dealer ui above player ui
    //panel with boxLayout verticle

    //panel of cards, flowLayout of labels (6 cards is good enough to handle)
    // grid x,y coordinates
    // player.setPanel reutrns panel
    // button.disable()
    // bet then enables hit and stay
    // bet gets disatbled draw cards
    // player oakys gane by itself can handle game
    // state machine, handles state, game engine check if player loses
    // game engine after player plays tell dealer to play
    // dealer passed player points and hit till stop point
    // dealer done did delaer bust or player busted or whos higher
    // Deck
        // create
    // shuffle
    // draw front of deck and increments
    // 6 decks shuffle deck
    // arraylist. collections. shuffle()
    // if draw runs out shuffle and start over draw
    // player gets reference to card they have
    // frame.add borderlayout center
    // add player ui and dealer ui
    // game state class
        // states are splash screen, main play, score screen,
    // main play gets started after start , setup at first, then player, deck, and dealer, dealer.add(player . add(deck)
    // player . add(deck) not constructor
    // main play then builds game panel
    // play deal result repeart until done or out of money
    //

    JButton startButton  = new JButton("Start");

    JLabel startingCard = new JLabel();


    JLabel startingText = new JLabel("Welcome to Java Blackjack");
    JLabel groupNameText = new JLabel("Designed by: Harrison, Cooper, Asher");

    StartingScreenGUI(){

        setStartingTextStyle();
        panel.setLayout(null);

        ImageIcon turnedOverCard = new ImageIcon(new ImageIcon("PNG-cards-1.3/card back red.png").getImage().getScaledInstance(150,150,Image.SCALE_DEFAULT));
        startingCard.setIcon(turnedOverCard);

        panel.add(startButton);
        panel.add(startingCard);
        panel.add(startingText);
        panel.add(groupNameText);
        panel.add(startingCard);

        startButton.setBounds(300,400,100,25);
        startingText.setBounds(250,0 , 500,50);
        groupNameText.setBounds(200,28, 500,100);
        startingCard.setBounds(300,150, 150,150);




        frame.add(panel);
        frame.setSize(700,550);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit out of application
        panel.setBackground(new Color(35,54,5));


        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new BlackJackGUI();
            }
        });
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


}
