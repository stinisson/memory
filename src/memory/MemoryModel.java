package memory;


import java.util.ArrayList;

public class MemoryModel implements BoardGame {
    private final int rows;
    private final int cols;
    private int [][] solution;
    private String [][] status;
    private int [] firstCardIdx = new int [2];
    private int [] secondCardIdx = new int [2];
    private boolean wasAPair = true;
    private int nAttempts = 1;
    private boolean firstClick = true;

    private enum NextMove {
        FirstCard,
        SecondCard
    }
    NextMove nextMove = NextMove.FirstCard;

    public MemoryModel(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        solution = new int [rows][cols];
        status = new String [rows][cols];
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
                int randomIdx = (int) (Math.random() * cards.size());
                solution[i][j] = cards.get(randomIdx);
                cards.remove(randomIdx);
                setStatus(i, j, "x");
            }
        }
    }

    public String move(int i, int j) {
        // "invalid" - if invalid move, "pair" - if it's a pair, "no pair" otherwise
        if (!status[i][j].equals("x")) {
            return "invalid";
        }
        setStatus(i, j, Integer.toString(solution[i][j]));          // Turn card facing up

        if (nextMove == NextMove.FirstCard) {
            // First dram
            firstCardIdx[0] = i;
            firstCardIdx[1] = j;
            nextMove = NextMove.SecondCard;
            return "valid";
        } else {
            firstClick = false;
            // Second draw
            secondCardIdx[0] = i;
            secondCardIdx[1] = j;
            nextMove = NextMove.FirstCard;
            if (solution[firstCardIdx[0]][firstCardIdx[1]] == solution[secondCardIdx[0]][secondCardIdx[1]]) { // A pair
                wasAPair = true;
                return "pair";
            } else {
                wasAPair = false;
            }
        }
        return "no pair";
    }

    // Next move - turn back cards
    public boolean nextMove() {
        if (nextMove == NextMove.FirstCard && !wasAPair){	// If there is no match - turn the cards again
            nAttempts++;
            status[firstCardIdx[0]][firstCardIdx[1]] = "x";
            status[secondCardIdx[0]][secondCardIdx[1]] = "x";
            return true;
        }
        return false;
    }

    public String getStatus(int i, int j) {
        return status[i][j];
    }

    public void setStatus(int i, int j, String text) {
        status[i][j] = text;
    }

    public String getMessage() {
        if (firstClick)
            return "I \u221A4U :)";
        String message = "Attempt number: " + nAttempts;
        if (gameEnded()) {
            message = "Well done! It took " + nAttempts;
            if (nAttempts == 1)
                 message += " try to finish the board.";
            else
                message +=  " tries to finish the board.";
        }
        return message;
    }

    public boolean gameEnded() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (getStatus(i, j).equals("x")) {
                    return false;
                }
            }
        }
        return true;
    }
}
