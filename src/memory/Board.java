package memory;

import java.awt.*;
import java.util.ArrayList;

public class Board {

    public static ArrayList<Card> make_board(int rows, int cols) {
        final ArrayList<Card> cardList = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Card myCard = new Card(i, j);
                myCard.setPreferredSize(new Dimension(180, 180));
                cardList.add(myCard);
            }
        }
        return cardList;
    }
}
