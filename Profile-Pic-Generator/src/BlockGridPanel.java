import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Random;

public class BlockGridPanel extends JPanel {

    private Block[][] ArrBlocks = new Block[SIZE][SIZE];

    private Color BlockColor;

    private static final int SIZE = 5;

    public BlockGridPanel() {
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
                    ArrBlocks[i][j].setColor(BlockColor);
                    ArrBlocks[i][Reflection(j)].setColor(BlockColor);
                }
            }
        }
    }

    public void Repaint() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < (double) SIZE / 2 + .5; j++) {
                if (ArrBlocks[i][j].getActive()) {
                    ArrBlocks[i][j].setColor(BlockColor);
                    ArrBlocks[i][Reflection(j)].setColor(BlockColor);
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

    public void Save(String Path) {
        BufferedImage Image = new BufferedImage(this.size().width, this.size().height, BufferedImage.TYPE_INT_RGB);
        paint(Image.createGraphics());
        try {
            OutputStream oStream = new FileOutputStream(Path);
            JPEGImageEncoder JIEnconder = JPEGCodec.createJPEGEncoder(oStream);
            JIEnconder.encode(Image);
            oStream.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public void randColor() {
        BlockColor = new Color((int)(Math.random() * 0x1000000));
    }

    private int Reflection (int Index) {
        return -Index + SIZE - 1;
    }







}
