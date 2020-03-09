package pirate;

import javax.swing.ImageIcon;

public class BloodPU extends Sprite{
    
    private final String bloodPUImg = "images/bloodup.png";

    public BloodPU() {
        
        ImageIcon ii = new ImageIcon(bloodPUImg);
        setImage(ii.getImage());
    }

    public BloodPU(int x, int y) {

        initBloodPU(x, y);
    }

    private void initBloodPU(int x, int y) {

        ImageIcon ii = new ImageIcon(bloodPUImg);
        setImage(ii.getImage());

        setX(x);
        setY(y);
    }
}
