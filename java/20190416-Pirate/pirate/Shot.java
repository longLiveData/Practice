package pirate;

import javax.swing.ImageIcon;

public class Shot extends Sprite{
    
    private final String shotImg = "images/speedup.png";

    public Shot() {
    }

    public Shot(int x, int y) {

        initShot(x, y);
    }

    private void initShot(int x, int y) {

        ImageIcon ii = new ImageIcon(shotImg);
        setImage(ii.getImage());

        setX(x);
        setY(y);
    }
    
}
