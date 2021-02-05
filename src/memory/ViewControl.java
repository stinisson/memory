package memory;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ViewControl extends JFrame implements ActionListener {

    ArrayList<Card> cards;
    BufferedImage images[];
    int nPairs;
    private JLabel textLabel;

    public ViewControl (String title, int rows, int cols) {
        super(title);

        setSize(800, 900);
        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        this.add(buttonPanel, BorderLayout.CENTER);
        buttonPanel.setOpaque(true);
        buttonPanel.setBackground(Color.pink);



        JLabel textLabel = new JLabel("Pick two cards. If they match it's a pair and you may continue.",
                JLabel.CENTER);
        textLabel.setFont(new Font("Courier New", Font.PLAIN, 14));
        textLabel.setPreferredSize(new Dimension(0, 100));
        this.add(textLabel, BorderLayout.PAGE_END);
        textLabel.setOpaque(true);
        textLabel.setBackground(Color.pink);

        // Card images
        nPairs = (rows * cols)/2;
        images = new BufferedImage[nPairs];
        for (int i = 0; i < nPairs; i++) {
            try {
                images[i] = ImageIO.read(new File("src/images/" + (i+1)+ ".jpg"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Board board = new Board(rows, cols);
        cards = board.getCards();
        for (Card card : cards) {
            card.addActionListener(this);
            buttonPanel.add(card);
            //card.setIcon(new ImageIcon(images[4]));
        }

        // Display board
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("\nYou clicked: ");
        System.out.println("Row " + ((Card) e.getSource()).getI());
        System.out.println("Col " + ((Card) e.getSource()).getJ());

        clickedButton(((Card) e.getSource()).getI(), ((Card) e.getSource()).getJ());
        Timer timer = new Timer(2000, turnCards);
        timer.setRepeats(false);
        timer.start();
    }

    public void clickedButton(int i, int j) {
        updateBoard(true);
    }

    public void updateBoard(Boolean show_cards) {

        if (show_cards) {
            for (Card card : cards) {
                card.setIcon(new ImageIcon(images[cards.indexOf(card)/2]));
            }
        } else {
            for (Card card : cards) {
                card.setIcon(null);
            }
        }
    }

    private void updateText() {
        textLabel.setText("Test");
    }

    // Swing Timer: http://stackoverflow.com/questions/1006611/java-swing-timer
    private ActionListener turnCards = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            updateBoard(false);
        }
    };

}