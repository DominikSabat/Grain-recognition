import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;

public class JCanvasPanel extends JPanel {

    DataManager dm;

    public JCanvasPanel(DataManager dm) {
        this.dm = dm;
    }

    void drawSubdivision(Node root, Graphics2D g2) {
        if (root.n1 != null) {
            drawSubdivision(root.n1, g2);
        }
        if (root.n2 != null) {
            drawSubdivision(root.n2, g2);
        }
        if (root.n3 != null) {
            drawSubdivision(root.n3, g2);
        }
        if (root.n4 != null) {
            drawSubdivision(root.n4, g2);
        }

        if(root.n1==null)
        if (root.rect.kolor!=null)
            if (root.rect.kolor.equals(dm.seeds.get(dm.choice2).color)) {
                g2.setColor(Color.white);
                g2.drawRect(root.rect.origin.x, root.rect.origin.y, root.rect.width, root.rect.height);
                //System.out.println(dm.seeds.get(dm.choice2).id); // test czy ID ziarna dziala poprawnie
                //g2.drawLine(root.rect.origin.x, root.rect.origin.y, root.rect.origin.x+root.rect.width, root.rect.origin.y+root.rect.height);
                //g2.drawLine(root.rect.origin.x, root.rect.origin.y+root.rect.width, root.rect.origin.x+root.rect.height, root.rect.origin.y);

            }
    }

    void drawInitial(Node root, Graphics2D g2) {
        if (root.n1 != null) {
            drawInitial(root.n1, g2);
        }
        if (root.n2 != null) {
            drawInitial(root.n2, g2);
        }
        if (root.n3 != null) {
            drawInitial(root.n3, g2);
        }
        if (root.n4 != null) {
            drawInitial(root.n4, g2);
        }

        if(root.n1==null)
            if (root.rect.kolor!=null) {
                g2.setColor(Color.white);
                g2.drawRect(root.rect.origin.x, root.rect.origin.y, root.rect.width, root.rect.height);
            }
    }

    public void exportImage() {

        String imageName = "export.bmp";

        BufferedImage image = new  BufferedImage(dm.width, dm.height,BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();

        if (dm.choice2==-1) {renderAll(graphics, dm.rootNode); }

        else {renderSeed(graphics,dm.rootNode); }

        graphics.dispose();

        try {
            System.out.println("Wyeksportowano obraz o nazwie: '"+imageName+ "' do katalogu z projektem");
            FileOutputStream out = new FileOutputStream(imageName);
            ImageIO.write(image, "bmp", out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void renderSeed(Graphics2D g2, Node root) {
        g2.drawImage(dm.img, 0, 0, this);
        if (root.n1 != null) {
            drawSubdivision(root.n1, g2);
        }
        if (root.n2 != null) {
            drawSubdivision(root.n2, g2);
        }
        if (root.n3 != null) {
            drawSubdivision(root.n3, g2);
        }
        if (root.n4 != null) {
            drawSubdivision(root.n4, g2);
        }

        if(root.n1==null)
            if (root.rect.kolor!=null)
                if (root.rect.kolor.equals(dm.seeds.get(dm.choice2).color)) {
                    g2.setColor(Color.white);
                    g2.drawRect(root.rect.origin.x, root.rect.origin.y, root.rect.width, root.rect.height);
                }
    }

    private void renderAll(Graphics2D g2, Node root){
        g2.drawImage(dm.img, 0, 0, this);
        if (root.n1 != null) {
            drawInitial(root.n1, g2);
        }
        if (root.n2 != null) {
            drawInitial(root.n2, g2);
        }
        if (root.n3 != null) {
            drawInitial(root.n3, g2);
        }
        if (root.n4 != null) {
            drawInitial(root.n4, g2);
        }

        if(root.n1==null)
            if (root.rect.kolor!=null) {
                g2.setColor(Color.white);
                g2.drawRect(root.rect.origin.x, root.rect.origin.y, root.rect.width, root.rect.height);
            }
    }


    @Override
        public void paint(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.drawImage(dm.img, 0, 0, this);
            if(dm.choice2==(-1)){
                drawInitial(dm.rootNode,g2);
            }
            else {
                drawSubdivision(dm.rootNode, g2);
            }
            if (dm.choice == 2)
                exportImage();

        }

    @Override
    public void repaint() {
        super.repaint();
    }
}