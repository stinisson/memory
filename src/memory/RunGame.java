package memory;

import memory.BoardGame;

public class RunGame {

    public static void main(String[] args) {
        System.out.println("Henlo");

        int rows = 4;
        int cols = 4;
        BoardGame game = new MemoryModel(rows, cols);
        ViewControl viewControl = new ViewControl("Memory", rows, cols);
    }
}
