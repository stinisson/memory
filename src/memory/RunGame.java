package memory;

public class RunGame {

    public static void main(String[] args) {
        int rows = 4;
        int cols = 4;
        BoardGame game = new MemoryModel(rows, cols);
        ViewControl viewControl = new ViewControl(game, "Memory", rows, cols);
    }
}
