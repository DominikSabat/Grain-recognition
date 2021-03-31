import java.awt.*;

public class Utility {
    DataManager dm;
    int ID=1;

    public Utility(DataManager dm){
        this.dm = dm;
    }

    void seedIdentify(Rectangle rect){
            for(int hi = rect.origin.y; hi < (rect.origin.y+rect.height); hi++){
                for(int wi = rect.origin.x; wi < (rect.origin.x+rect.width); wi++){

                boolean exists = false;
                Color color = new Color(dm.img.getRGB(wi, hi));

                if(dm.seeds !=null)
                {
                    for (Seed s : dm.seeds) {
                        if (s.color.equals(color)) {
                            exists = true;
                        }
                    }
                }

                if(!exists)
                {
                    Seed seed = new Seed(color,ID);
                    dm.seeds.add(seed);  //ID koloru to numer indeksu (na liście) ziarna do którego należy,
                    ID++;               // dlatego też ID jako zmienna typu int nie jest potrzebne do dzialania programu, ani nigdzie jej nie wykorszystuję
                }
            }
        }
    }

    Boolean checkFill(Rectangle rect){

        Boolean mixed = false;

        for(int wi = rect.origin.x; wi < (rect.origin.x+rect.width)-1; wi++){
            for(int hi = rect.origin.y; hi < (rect.origin.y+rect.height)-1; hi++){

                Color color = new Color(dm.img.getRGB(wi, hi));
                Color color1 = new Color(dm.img.getRGB(wi+1, hi+1));

                if(!color.equals(color1))
                {
                    mixed = true;
                    rect.kolor=null;
                }

                else
                {
                    rect.kolor=color;
                }
            }
        }

        return mixed;
    }

    void divide(Node root){
        if(checkFill(root.rect)){

            int width = root.rect.width / 2;
            int height = root.rect.height / 2;

            if(width > dm.tolerance && height > dm.tolerance){

                Node n1 = new Node(new Rectangle(new Point(root.rect.origin.x, root.rect.origin.y), width, height), null, null, null, null);
                Node n2 = new Node(new Rectangle(new Point(root.rect.origin.x + width, root.rect.origin.y), width, height), null, null, null, null);
                Node n3 = new Node(new Rectangle(new Point(root.rect.origin.x, root.rect.origin.y + height), width, height), null, null, null, null);
                Node n4 = new Node(new Rectangle(new Point(root.rect.origin.x + width, root.rect.origin.y + height), width, height), null, null, null, null);

                root.n1 = n1;
                root.n2 = n2;
                root.n3 = n3;
                root.n4 = n4;

                divide(root.n1);
                divide(root.n2);
                divide(root.n3);
                divide(root.n4);

            }
        }
    }

    void constructQT(){

        int width = 820;
        int height = 815;

        Rectangle initialRectangle = new Rectangle(new Point(0,0), width, height);

        dm.rootNode = new Node(initialRectangle, null, null, null, null);
        seedIdentify(initialRectangle);
    }
}