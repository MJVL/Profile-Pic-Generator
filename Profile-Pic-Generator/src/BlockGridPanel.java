import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Random;

public class BlockGridPanel extends JPanel {

    private Block[][] ArrBlocks;
    private Color BlockColor;
    private int GridSize;

    public static final int DEFAULT_SIZE = 5;
    public static final int MIN_SIZE = 5;
    public static final int MAX_SIZE = 50;

    public BlockGridPanel() {
        GridSize = DEFAULT_SIZE;
        ArrBlocks = new Block[GridSize][GridSize];
        setLayout(new GridLayout(GridSize,GridSize));
        for (int i = 0; i < GridSize; i++) {
            for (int j = 0; j < GridSize; j++) {
                ArrBlocks[i][j] = new Block();
                add(ArrBlocks[i][j]);
            }
        }
        randColor();
    }

    public void setBlockColor(Color BlockColor) {
        this.BlockColor = BlockColor;
    }

    public Color getBlockColor() {
        return BlockColor;
    }

    public void Resize(int GridSize) {
        this.GridSize = GridSize;
        removeAll();
        ArrBlocks = new Block[GridSize][GridSize];
        setLayout(new GridLayout(GridSize,GridSize));
        for (int i = 0; i < GridSize; i++) {
            for (int j = 0; j < GridSize; j++) {
                ArrBlocks[i][j] = new Block();
                ArrBlocks[i][j].Resize(MIN_SIZE, GridSize);
                add(ArrBlocks[i][j]);
            }
        }
        randColor();
    }

    public void Paint() {
        Random rSplit = new Random();
        for (int i = 0; i < GridSize; i++) {
            for (int j = 0; j < (double) GridSize / 2 + .5; j++) {
                if (rSplit.nextBoolean()) {
                    ArrBlocks[i][j].setColor(BlockColor);
                    ArrBlocks[i][Reflection(j)].setColor(BlockColor);
                }
            }
        }
    }

    public void Repaint() {
        for (int i = 0; i < GridSize; i++) {
            for (int j = 0; j < (double) GridSize / 2 + .5; j++) {
                if (ArrBlocks[i][j].getActive()) {
                    ArrBlocks[i][j].setColor(BlockColor);
                    ArrBlocks[i][Reflection(j)].setColor(BlockColor);
                }
            }
        }
    }


    public void Clear() {
        for (int i = 0; i < GridSize; i++) {
            for (int j = 0; j < GridSize; j++) {
                ArrBlocks[i][j].Clear();
            }
        }
    }

    public void Save(String Path) {
        BufferedImage Image = new BufferedImage(this.size().width, this.size().height, BufferedImage.TYPE_INT_RGB);
        paint(Image.createGraphics());
        try {
            OutputStream oStream = new FileOutputStream(Path);
            JPEGImageEncoder JIEncoder = JPEGCodec.createJPEGEncoder(oStream);
            JIEncoder.encode(Image);
            oStream.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public void randColor() {
        BlockColor = new Color((int)(Math.random() * 0x1000000));
    }

    private int Reflection (int Index) {
        return -Index + GridSize - 1;
    }







}
