package memory;

public interface BoardGame {
    public String move(int i, int j);
    public String getStatus(int i, int j);
    public boolean nextMove();
    public String getMessage();
}
