package memory;

import java.awt.*;
import java.util.ArrayList;

public class CardBoard {

    private final ArrayList<Card> cardList;

    public CardBoard(int rows, int cols) {

        this.cardList = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Card myCard = new Card(i, j);
                myCard.setPreferredSize(new Dimension(180, 180));
                this.cardList.add(myCard);
            }
        }
    }
    public ArrayList<Card> getCards() {
        return this.cardList;
    }
}
