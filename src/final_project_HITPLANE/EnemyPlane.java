package final_project_HITPLANE;

import java.awt.image.BufferedImage;

/**
 * Create EnemyPlane class that inherit FlyerObject and implement EnemyScore
 */
public class EnemyPlane extends FlyerObject implements EnemyScore {
    private final int speed;
    private int index;

    /**
     * constructor for EnemyPlane will set up the speed, width and height for object
     */
    public EnemyPlane() {
        super(150, 150);
        speed = 2;
        index=1;
    }

    /**
     * let the EnemyPlane move downside with its speed
     */
    @Override
    public void move() {
        y += speed;
    }



    /**
     * This method will return the image of EnemyPlane object
     * @return the EnemyPlane image if it is alive / the bom image if it is dead / null if it is remove
     */

    @Override
    public BufferedImage getImage() {
        if (isLive()) {
            return Images.enemyPlane[0];
        } else if (isDead()) {
            BufferedImage img = Images.enemyPlane[index++];
            if (index == Images.enemyPlane.length) {
                state = FlyerState.Remove;
            }
            return img;
        }
        return null;
    }

    /**
     * add the player's score with 1
     *
     * @return the score will be added
     */
    public int getScore() {
        return 1;
    }
}