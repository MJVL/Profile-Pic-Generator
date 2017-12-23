import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class GeneratorGUI extends JFrame {

    private JMenuBar mnuBar = new JMenuBar();
    private JMenu mnuTools = new JMenu("Tools");
    private JMenuItem mnuRandom = new JMenuItem("Random");
    private JMenuItem mnuSave = new JMenuItem("Save");

    private BlockGridPanel myGrid = new BlockGridPanel();

    public GeneratorGUI() {
        mnuTools.add(mnuRandom);
        mnuTools.add(mnuSave);
        mnuBar.add(mnuTools);
        setJMenuBar(mnuBar);
        mnuRandom.addActionListener(new MenuListener());
        mnuSave.addActionListener(new MenuListener());
        add(myGrid);
        setTitle("GitHubPic");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setVisible(true);
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
                myGrid.randColor();
                myGrid.Repaint();
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
        }

    }
}
