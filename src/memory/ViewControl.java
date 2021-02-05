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

    private final BoardGame game;
    private final int rows;
    private final int cols;
    private ArrayList<Card> cards;
    private BufferedImage[] images;
    private final int nPairs;
    private final JLabel textLabel;
    private boolean buttonsDisabled = false;

    public ViewControl (BoardGame game, String title, int rows, int cols) {
        super(title);
        this.game = game;
        this.rows = rows;
        this.cols = cols;
        this.nPairs = (rows * cols) / 2;
        this.setSize(800, 900);
        this.setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(true);
        buttonPanel.setBackground(Color.pink);
        this.add(buttonPanel, BorderLayout.CENTER);

        textLabel = new JLabel("Pick two cards. If they match it's a pair.", JLabel.CENTER);
        textLabel.setFont(new Font("Courier New", Font.PLAIN, 14));
        textLabel.setPreferredSize(new Dimension(0, 100));
        textLabel.setOpaque(true);
        textLabel.setBackground(Color.pink);
        this.add(textLabel, BorderLayout.PAGE_END);

        // Card images
        images = new BufferedImage[this.nPairs];
        for (int i = 0; i < this.nPairs; i++) {
            try {
                images[i] = ImageIO.read(new File("src/images/" + (i + 1)+ ".jpg"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        cards = Board.make_board(rows, cols); // board.getCards();
        for (Card card : cards) {
            card.addActionListener(this);
            buttonPanel.add(card);
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

        if (buttonsDisabled) {
            textLabel.setText("Take it easy cowboy..");
            return;
        }
        clickedButton(((Card) e.getSource()).getI(), ((Card) e.getSource()).getJ());
    }

    public void clickedButton(int i, int j) {
        String status = this.game.move(i, j);
        updateBoard();
        updateText();

        if (status.equals("no pair")) {
            buttonsDisabled = true;
            Timer timer = new Timer(2000, turnCards);
            timer.setRepeats(false);
            timer.start();
        }
    }

    public void updateBoard() {
        for (Card card : cards) {
            String status = this.game.getStatus(card.getI(), card.getJ());
            if (status.equals("x")) {
                card.setIcon(null);
            } else {
                card.setIcon(new ImageIcon(images[Integer.valueOf(status)]));
            }
        }
    }

    private void updateText() {
        String message = this.game.getMessage();
        textLabel.setText(message);
    }

    // Swing Timer: http://stackoverflow.com/questions/1006611/java-swing-timer
    private ActionListener turnCards = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            game.nextMove();
            updateBoard();
            updateText();
            buttonsDisabled = false;
        }
    };
}