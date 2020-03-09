package pirate;

import javax.swing.ImageIcon;

public class SpeedPU extends Sprite{
    
    private final String speedPUImg = "images/speedup.png";

    public SpeedPU() {
        ImageIcon ii = new ImageIcon(speedPUImg);
        setImage(ii.getImage());
    }

    public SpeedPU(int x, int y) {

        initSpeedPU(x, y);
    }

    private void initSpeedPU(int x, int y) {

        ImageIcon ii = new ImageIcon(speedPUImg);
        setImage(ii.getImage());

        setX(x);
        setY(y);
    }
    
}
