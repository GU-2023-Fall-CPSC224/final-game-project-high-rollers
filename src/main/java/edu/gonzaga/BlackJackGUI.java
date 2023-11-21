package edu.gonzaga;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;


public class BlackJackGUI extends JFrame {

    JFrame frame = new JFrame("Black Jack");
    JPanel panel = new JPanel();
    JButton hitButton  = new JButton("hit");
    JButton standButton = new JButton("stand");
    JLabel playerCard1 = new JLabel();
    JLabel playerCard2 = new JLabel();
    JLabel dealerCard1 = new JLabel();
    JLabel dealerCard2 = new JLabel();

    JLabel playerText = new JLabel("Player Cards");
    JLabel dealerText = new JLabel("Dealer Cards");

    int hitCount = 1;

    String[] cardFileNames = {
            "2_of_hearts.png", "3_of_hearts.png", "4_of_hearts.png", "5_of_hearts.png", "6_of_hearts.png", "7_of_hearts.png", "8_of_hearts.png", "9_of_hearts.png", "10_of_hearts.png", "jack_of_hearts.png", "queen_of_hearts.png",
            "king_of_hearts.png", "ace_of_hearts.png", "2_of_diamonds.png", "3_of_diamonds.png", "4_of_diamonds.png", "5_of_diamonds.png", "6_of_diamonds.png", "7_of_diamonds.png", "8_of_diamonds.png", "9_of_diamonds.png", "10_of_diamonds.png", "jack_of_diamonds.png", "queen_of_diamonds.png", "king_of_diamonds.png", "ace_of_diamonds.png", "2_of_clubs.png", "3_of_clubs.png", "4_of_clubs.png", "5_of_clubs.png",
            "6_of_clubs.png", "7_of_clubs.png", "8_of_clubs.png", "9_of_clubs.png", "10_of_clubs.png", "jack_of_clubs.png", "queen_of_clubs.png", "king_of_clubs.png", "ace_of_clubs.png", "2_of_spades.png",
            "3_of_spades.png", "4_of_spades.png", "5_of_spades.png", "6_of_spades.png", "7_of_spades.png", "8_of_spades.png", "9_of_spades.png", "10_of_spades.png", "jack_of_spades.png", "queen_of_spades.png", "king_of_spades.png", "ace_of_spades.png"
    };

    BlackJackGUI(){
        panel.setLayout(null);
        panel.setBackground(new Color(35,54,5));

        ImageIcon card1 = new ImageIcon(new ImageIcon("PNG-cards-1.3/" + getRandomCard()).getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT));
        ImageIcon card2 = new ImageIcon(new ImageIcon("PNG-cards-1.3/" + getRandomCard()).getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT));
        ImageIcon card3 = new ImageIcon(new ImageIcon("PNG-cards-1.3/" + getRandomCard()).getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT));
        //ImageIcon card4 = new ImageIcon(new ImageIcon("PNG-cards-1.3/9_of_clubs.png").getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT));
        ImageIcon turnedOverCard = new ImageIcon(new ImageIcon("PNG-cards-1.3/card back red.png").getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT));


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


        panel.add(playerCard1);
        panel.add(playerCard2);
        panel.add(dealerCard1);
        panel.add(dealerCard2);

        panel.add(playerText);
        panel.add(dealerText);

        panel.add(hitButton);
        panel.add(standButton);


        standButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("STANDING");
            }
        });

        frame.add(panel);
        frame.setSize(700,550);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit out of application

        hitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int distance = (120 + 110 * hitCount);


                ImageIcon card = new ImageIcon(new ImageIcon("PNG-cards-1.3/" + getRandomCard()).getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT));
                JLabel newPlayerCard  = new JLabel();
                newPlayerCard.setIcon(card);
                newPlayerCard.setBounds(distance, 280, 100, 100);
                panel.add(newPlayerCard);

                ImageIcon card2 = new ImageIcon(new ImageIcon("PNG-cards-1.3/" + getRandomCard()).getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT));
                JLabel newDealerCard  = new JLabel();
                newDealerCard.setIcon(card2);
                distance = (120 + 110 * (hitCount));
                newDealerCard.setBounds(distance, 130, 100, 100);
                panel.add(newDealerCard);
                panel.revalidate();
                panel.repaint();
                hitCount++;
            }
        });
    }

    public String getRandomCard(){
        Random rand = new Random();
        int randomIndex = rand.nextInt(cardFileNames.length);
        return cardFileNames[randomIndex];
    }
}