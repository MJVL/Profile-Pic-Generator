import javax.swing.*;
import java.awt.*;

public class Block extends JLabel {

    private boolean Active;

    public final static int DEFAULT_SIZE = 100;

    public Block() {
        setOpaque(true);
        Active = false;
        setPreferredSize(new Dimension(DEFAULT_SIZE,DEFAULT_SIZE));
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

    public void Resize(int Base, int n) {
        setPreferredSize(new Dimension(PixelRatio(Base, n),PixelRatio(Base, n)));
    }

    public static int PixelRatio(int Base, int n) {
        return (int) (((double)Base / n) * DEFAULT_SIZE);
    }

}
