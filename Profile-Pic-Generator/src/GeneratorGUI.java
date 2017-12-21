import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GeneratorGUI extends JFrame {

    private JMenuBar mnuBar = new JMenuBar();
    private JMenu mnuTools = new JMenu("Tools");
    private JMenuItem mnuRandom = new JMenuItem("Random");

    private BlockGrid myGrid = new BlockGrid();

    public GeneratorGUI() {
        mnuTools.add(mnuRandom);
        mnuBar.add(mnuTools);
        setJMenuBar(mnuBar);
        mnuRandom.addActionListener(new MenuListener());
        add(myGrid);
        setTitle("GitHubPic");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(500,500);
        //pack();
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
        }

    }
}
