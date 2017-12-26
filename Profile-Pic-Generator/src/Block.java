import javax.swing.*;
import java.awt.*;

public class Block extends JLabel {

    public final static int DEFAULT_SIZE = 75;

    private boolean Active;

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

    public void Resize(int n) {
        Resize(PixelRatio(n),PixelRatio(n));
    }

    public void Resize(int Width, int Height) {
        setPreferredSize(new Dimension(Width, Height));
    }

    public static int PixelRatio(int n) {
        return (int) ((5.0 / n) * DEFAULT_SIZE);
    }

}
