package final_project_HITPLANE;

import java.awt.image.BufferedImage;


/**
 * Create BigEnemyPlane class that inherit FlyerObject and implement EnemyScore
 */
public class BigEnemyPlane extends FlyerObject implements EnemyScore {

    private final int speed ;
    private int index ;

    /**
     * constructor for EnemyPlane will set up the speed, width and height for object
     */
    public BigEnemyPlane() {
        super(250, 250);
        speed =2;
        index=1;
    }

    /**
     * let the BigEnemyPlane move downside with its speed
     */
    public void move() {
        y += speed;
    }

    /**
     * This method will return the image of BigEnemyPlane object
     * @return the BigEnemyPlane image if it is alive / the bom image if it is dead / null if it is remove
     */

    @Override
    public BufferedImage getImage() {

        if (isLive()) {
            return Images.bigEnemyPlane[0];
        } else if (isDead()) {
            BufferedImage img = Images.bigEnemyPlane[this.index++];
            if (index == Images.bigEnemyPlane.length) {
                state = FlyerState.Remove;
            }
            return img;
        }
        return null;
    }

    /**
     * add the player's score with 3
     *
     * @return the score will be added
     */
    public int getScore() {
        return 3;
    }
}