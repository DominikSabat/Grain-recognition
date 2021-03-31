import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class DataManager {

    BufferedImage img;
    ArrayList<Seed> seeds;

    Node rootNode;

    int choice = 0;
    int choice2 = 0;

    int tolerance = 1;
    int width = 820;
    int height = 815;

    public DataManager()
    {
        seeds = new ArrayList<>();
    }

}