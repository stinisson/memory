package memory;

import memory.BoardGame;

public class RunGame {

    public static void main(String[] args) {
        System.out.println("Henlo");

        BoardGame game = new MemoryModel();
        ViewControl viewControl = new ViewControl("Memory", 4, 4);
    }
}
