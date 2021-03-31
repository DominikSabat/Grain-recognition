import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;

public class MainWindow extends JFrame{
    private DataManager dm;
    private JPanel mainPanel;
    private JPanel buttonPanel;

    private JCanvasPanel canvasPanel;

    private JButton meshBut;
    private JButton saveBut;
    private JTextField textField;


    public MainWindow (String title) {
        super(title);
        dm = new DataManager();

        //==============================================================================================
        try {
            BufferedImage bg = ImageIO.read(new File("obrazek.bmp"));
            dm.img = bg;
        } catch (IOException e) {
            e.printStackTrace();
        }

        //===============================================================================================
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        meshBut = new JButton("Mesh");
        saveBut = new JButton("Export Mesh");

        textField = new JTextField("0");
        textField.setBorder(new TitledBorder("ID ziarna, 0- wszystkie ziarna"));

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1));

        buttonPanel.add(textField);
        buttonPanel.add(meshBut);
        buttonPanel.add(saveBut);
        buttonPanel.setPreferredSize(new Dimension(180,0));

        canvasPanel = new JCanvasPanel(dm);

        mainPanel.add(BorderLayout.CENTER, canvasPanel);
        mainPanel.add(BorderLayout.EAST, buttonPanel);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);

        this.setSize(new Dimension(1030, 860));
        this.setLocationRelativeTo(null);

        meshBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Utility util = new Utility(dm);
                dm.choice = 1;
                dm.choice2=Integer.parseInt(textField.getText()) - 1;

                util.divide(dm.rootNode);
                canvasPanel.repaint();
            }
        });

        saveBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Utility util = new Utility(dm);
                dm.choice = 2;
                dm.choice2=Integer.parseInt(textField.getText()) - 1;

                util.divide(dm.rootNode);
                canvasPanel.repaint();
            }
        });
    }

    public static void main(String[] args){
        MainWindow mw = new MainWindow("Meshowanie bitmapy");
        mw.setVisible(true);
        mw.canvasPanel.repaint();

        Utility util = new Utility(mw.dm);
        util.constructQT();
    }
}
