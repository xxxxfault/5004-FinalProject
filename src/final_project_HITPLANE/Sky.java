package final_project_HITPLANE;

import java.awt.image.BufferedImage;

/**
 * This Sky class is also a FlyerObject in this game, stitch two same background pics together
 */
public class Sky extends FlyerObject {

    private int y1;
    private int speed;

    /**
     * Constructor for the sky
     */
    public Sky() {

        super(GamePanel.WIDTH, GamePanel.HEIGHT, 0, 0);
        speed = 1;
        // set up the second image just up to the first one
        y1 = -GamePanel.HEIGHT;
    }

    /**
     * Override the move method, let the two identical background images move continuously one by one
     */
    @Override
    public void move() {
        y += speed;
        y1 += speed;
        if (y >= GamePanel.HEIGHT) {
            y = -GamePanel.HEIGHT;
        }
        if (y1 >= GamePanel.HEIGHT) {
            y1 = -GamePanel.HEIGHT;
        }
    }

    /**
     * Get background image from Images class
     */
    @Override
    public BufferedImage getImage() {
        return Images.sky;

    }

    /**
     * get the value of y1
     */
    public int getY1() {
        return y1;
    }
}
