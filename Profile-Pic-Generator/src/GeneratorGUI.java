import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class GeneratorGUI extends JFrame {

    private JMenuBar mnuBar = new JMenuBar();

    private JMenu mnuTools = new JMenu("Tools");
    private JMenuItem mnuRandom = new JMenuItem("Random");
    private JMenuItem mnuSave = new JMenuItem("Save");

    private JMenu mnuSettings = new JMenu("Settings");
    private JMenuItem mnuGridSize = new JMenuItem("Grid Size");
    private JMenuItem mnuColor = new JMenuItem("Color");

    private BlockGridPanel myGrid = new BlockGridPanel();

    public GeneratorGUI() {
        mnuTools.add(mnuRandom);
        mnuTools.add(mnuSave);
        mnuBar.add(mnuTools);
        mnuSettings.add(mnuGridSize);
        mnuSettings.add(mnuColor);
        mnuBar.add(mnuSettings);
        setJMenuBar(mnuBar);
        mnuRandom.addActionListener(new MenuListener());
        mnuSave.addActionListener(new MenuListener());
        mnuGridSize.addActionListener(new MenuListener());
        mnuColor.addActionListener(new MenuListener());
        add(myGrid);
        setTitle("Profile Pic Generator");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setIconImages(new ArrayList<>(Arrays.asList(new ImageIcon("src/Assets/icon-16.png").getImage(),new ImageIcon("src/Assets/icon-32.png").getImage(),new ImageIcon("src/Assets/icon-64.png").getImage())));
        setResizable(false);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        myGrid.Paint();
    }

    private class MenuListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == mnuRandom) {
                myGrid.Clear();
                myGrid.randColor();
                myGrid.Paint();
            }
            else if (e.getSource() == mnuSave) {
                JFrame parentFrame = new JFrame();
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("PNG Portable Network Graphics Format", ".png"));
                fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("JPEG File Interchange Format", ".jpg", ".jpeg"));
                fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("GIF Graphics Interchange Format", ".gif"));
                fileChooser.setAcceptAllFileFilterUsed(false);
                fileChooser.setDialogTitle("Save As");
                int userSelection = fileChooser.showSaveDialog(parentFrame);
                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    String Path = fileChooser.getSelectedFile().getAbsolutePath() + "." + fileChooser.getFileFilter().getDescription().substring(0,4).trim().toLowerCase();
                    if (Path.contains(".") && !Path.contains("jpg") && !Path.contains(".jpeg") && !Path.contains(".png") && !Path.contains(".gif")) {
                        JOptionPane.showMessageDialog(null,"Error. Please enter a proper file extension (.jpg, jpeg, png, gif).","Extension Error",JOptionPane.WARNING_MESSAGE);
                    }
                    else {
                        myGrid.Save(Path);
                    }
                }
            }
            else if (e.getSource() == mnuGridSize) {
                try {
                    int newSize = Integer.parseInt(JOptionPane.showInputDialog(null,"Enter grid size (5-50).","Resize",JOptionPane.QUESTION_MESSAGE));
                    if (newSize >= 5 && newSize <= 50) {
                        myGrid.Resize(newSize);
                        myGrid.Paint();
                        pack();
                    }
                    else {
                        JOptionPane.showMessageDialog(null,"Error. Please enter an integer within the range of 5-50.","Resize Error",JOptionPane.WARNING_MESSAGE);
                    }
                } catch (Exception je) {
                    JOptionPane.showMessageDialog(null,"Error. Please enter an integer within the range of 5-50.","Resize Error",JOptionPane.WARNING_MESSAGE);
                }
            }
            else if (e.getSource() == mnuColor) {
                Color newColor = JColorChooser.showDialog(null, "Choose a color.", myGrid.getBlockColor());
                if (newColor != null) {
                    myGrid.setBlockColor(newColor);
                    myGrid.Repaint();
                }
                else {
                    JOptionPane.showMessageDialog(null,"Error. Please choose a proper color.","Color Error",JOptionPane.WARNING_MESSAGE);
                }
            }
        }

    }
}
