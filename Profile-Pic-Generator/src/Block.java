import javax.swing.*;
import java.awt.*;

public class Block extends JLabel {

    private boolean Active;

    public Block() {
        setOpaque(true);
        Active = false;
        //setPreferredSize(new Dimension(75,75));
    }

    public void setActive(boolean Active) {
        this.Active = Active;
    }

    public boolean getActive() {
        return Active;
    }

    public void toggleActive() {
        Active = !Active;
    }

    public void setColor(Color BlockColor) {
        setBackground(BlockColor);
        setActive(true);
    }

    public void Clear() {
        setBackground(null);
        Active = false;
    }

}
