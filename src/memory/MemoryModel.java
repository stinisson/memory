package memory;


import java.util.ArrayList;

public class MemoryModel implements BoardGame {

    private final int rows;
    private final int cols;
    private int [][] facit = new int [4][4];;

    public MemoryModel(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        populateBoard();
    }

    private void populateBoard() {
        int nPairs = (rows * cols) / 2;
        ArrayList<Integer> cards = new ArrayList<Integer>();
        for (int i = 0; i < nPairs; i++) {
            cards.add(i);
            cards.add(i);
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int randomIndex = (int) (Math.random() * cards.size());
                facit[i][j] = cards.get(randomIndex);
                cards.remove(randomIndex);
            }
        }
    }

    public boolean isMyTurn() {
        return false;
    }
}
