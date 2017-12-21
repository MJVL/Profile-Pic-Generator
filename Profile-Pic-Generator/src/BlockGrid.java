import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class BlockGrid extends JPanel {

    private Block[][] ArrBlocks = new Block[SIZE][SIZE];

    private Color BlockColor;

    private static final int SIZE = 5;

    public BlockGrid() {
        setLayout(new GridLayout(SIZE,SIZE));
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                ArrBlocks[i][j] = new Block();
                add(ArrBlocks[i][j]);
            }
        }
        randColor();
    }

    public void Paint() {
        Random rSplit = new Random();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < (double) SIZE / 2 + .5; j++) {
                if (rSplit.nextBoolean()) {
                    ArrBlocks[i][j].setBackground(BlockColor);
                    ArrBlocks[i][Reflection(j)].setBackground(BlockColor);
                }
            }
        }
    }

    public void Clear() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                ArrBlocks[i][j].Clear();
            }
        }
    }

    public void randColor() {
        BlockColor = new Color((int)(Math.random() * 0x1000000));
    }

    private int Reflection (int Index) {
        return -Index + SIZE - 1;
    }







}
