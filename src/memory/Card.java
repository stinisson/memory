package memory;

import javax.swing.*;
import java.awt.*;

public class Card extends JButton {

    private final int i;
    private final int j;

    public Card(int i, int j) {
        this.i = i;
        this.j = j;
        this.setBackground(Color.white);
    }

    public Integer getI() {
        return this.i;
    }

    public Integer getJ() {
        return this.j;
    }
}
