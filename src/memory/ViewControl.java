package memory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ViewControl extends JFrame implements ActionListener {

    public ViewControl (String title, int rows, int cols) {
        super(title);

        setSize(800, 900);
        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        this.add(buttonPanel, BorderLayout.CENTER);
        buttonPanel.setOpaque(true);
        buttonPanel.setBackground(Color.pink);


        CardBoard cardBoard = new CardBoard(rows, cols);
        ArrayList<Card> cards = cardBoard.getCards();
        for (Card card : cards) {
            card.addActionListener(this);
            buttonPanel.add(card);
        }

        JLabel textLabel = new JLabel("Pick two cards. If they match it's a pair and you may continue.", JLabel.CENTER);
        textLabel.setFont(new Font("Courier New", Font.PLAIN, 14));
        textLabel.setPreferredSize(new Dimension(0, 100));
        this.add(textLabel, BorderLayout.PAGE_END);
        textLabel.setOpaque(true);
        textLabel.setBackground(Color.pink);

        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("\nYou clicked: ");
        System.out.println("Row " + ((Card) e.getSource()).getI());
        System.out.println("Col " + ((Card) e.getSource()).getJ());
    }
}