package memory;

public class RunGame {

    public static void main(String[] args) {

        String level = "hard";  //easy, medium, hard
        int rows;
        int cols;

        if (level.equals("hard")) {
            rows = 4;
            cols = 6;
        }
        else if (level.equals("medium")) {
            rows = 4;
            cols = 5;
        }
        else {
            rows = 4;
            cols = 4;
        }

        BoardGame game = new MemoryModel(rows, cols);
        ViewControl viewControl = new ViewControl(game, "Memory", rows, cols);
    }
}
